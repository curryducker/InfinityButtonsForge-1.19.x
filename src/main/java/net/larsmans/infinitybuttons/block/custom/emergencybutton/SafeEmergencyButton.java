package net.larsmans.infinitybuttons.block.custom.emergencybutton;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.advancement.InfinityButtonsTriggers;
import net.larsmans.infinitybuttons.config.AlarmEnum;
import net.larsmans.infinitybuttons.config.InfinityButtonsConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public class SafeEmergencyButton extends FaceAttachedHorizontalDirectionalBlock {
    InfinityButtonsConfig config = AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig();
    
    public static final EnumProperty<SEBStateEnum> STATE = EnumProperty.create("state", SEBStateEnum.class);

    private static final VoxelShape STONE_DOWN = Block.box(3, 0, 3, 13, 1, 13);
    private static final VoxelShape STONE_UP = Block.box(3, 15, 3, 13, 16, 13);
    private static final VoxelShape STONE_NORTH = Block.box(3, 3, 15, 13, 13, 16);
    private static final VoxelShape STONE_EAST = Block.box(0, 3, 3, 1, 13, 13);
    private static final VoxelShape STONE_SOUTH = Block.box(3, 3, 0, 13, 13, 1);
    private static final VoxelShape STONE_WEST = Block.box(15, 3, 3, 16, 13, 13);

    private static final VoxelShape FLOOR_CLOSED_SHAPE = Shapes.or(
            Block.box(4, 1, 4, 12, 8, 12), STONE_DOWN);
    private static final VoxelShape FLOOR_OPEN_SHAPE = Shapes.or(
            Block.box(5, 1, 5, 11, 5, 11), STONE_DOWN);
    private static final VoxelShape FLOOR_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 1, 5, 11, 3, 11), STONE_DOWN);
    private static final VoxelShape CEILING_CLOSED_SHAPE = Shapes.or(
            Block.box(4, 8, 4, 12, 15, 12), STONE_UP);
    private static final VoxelShape CEILING_OPEN_SHAPE = Shapes.or(
            Block.box(5, 11, 5, 11, 15, 11), STONE_UP);
    private static final VoxelShape CEILING_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 13, 5, 11, 15, 11), STONE_UP);
    private static final VoxelShape NORTH_CLOSED_SHAPE = Shapes.or(
            Block.box(4, 4, 8, 12, 12, 15), STONE_NORTH);
    private static final VoxelShape NORTH_OPEN_SHAPE = Shapes.or(
            Block.box(5, 5, 11, 11, 11, 15), STONE_NORTH);
    private static final VoxelShape NORTH_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 5, 13, 11, 11, 15), STONE_NORTH);
    private static final VoxelShape EAST_CLOSED_SHAPE = Shapes.or(
            Block.box(1, 4, 4, 8, 12, 12), STONE_EAST);
    private static final VoxelShape EAST_OPEN_SHAPE = Shapes.or(
            Block.box(1, 5, 5, 5, 11, 11), STONE_EAST);
    private static final VoxelShape EAST_PRESSED_SHAPE = Shapes.or(
            Block.box(1, 5, 5, 3, 11, 11), STONE_EAST);
    private static final VoxelShape SOUTH_CLOSED_SHAPE = Shapes.or(
            Block.box(4, 4, 1, 12, 12, 8), STONE_SOUTH);
    private static final VoxelShape SOUTH_OPEN_SHAPE = Shapes.or(
            Block.box(5, 5, 1, 11, 11, 5), STONE_SOUTH);
    private static final VoxelShape SOUTH_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 5, 1, 11, 11, 3), STONE_SOUTH);
    private static final VoxelShape WEST_CLOSED_SHAPE = Shapes.or(
            Block.box(8, 4, 4, 15, 12, 12), STONE_WEST);
    private static final VoxelShape WEST_OPEN_SHAPE = Shapes.or(
            Block.box(11, 5, 5, 15, 11, 11), STONE_WEST);
    private static final VoxelShape WEST_PRESSED_SHAPE = Shapes.or(
            Block.box(13, 5, 5, 15, 11, 11), STONE_WEST);

    public SafeEmergencyButton(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(STATE, SEBStateEnum.CLOSED).setValue(FACE, AttachFace.FLOOR));
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(STATE, FACING, FACE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        switch (state.getValue(FACE)) {
            case FLOOR -> {
                switch (state.getValue(STATE)) {
                    case CLOSED -> {
                        return FLOOR_CLOSED_SHAPE;
                    }
                    case OPEN -> {
                        return FLOOR_OPEN_SHAPE;
                    }
                    case PRESSED -> {
                        return FLOOR_PRESSED_SHAPE;
                    }
                }
            }
            case CEILING -> {
                switch (state.getValue(STATE)) {
                    case CLOSED -> {
                        return CEILING_CLOSED_SHAPE;
                    }
                    case OPEN -> {
                        return CEILING_OPEN_SHAPE;
                    }
                    case PRESSED -> {
                        return CEILING_PRESSED_SHAPE;
                    }
                }
            }
            case WALL -> {
                switch (direction) {
                    case NORTH -> {
                        switch (state.getValue(STATE)) {
                            case CLOSED -> {
                                return NORTH_CLOSED_SHAPE;
                            }
                            case OPEN -> {
                                return NORTH_OPEN_SHAPE;
                            }
                            case PRESSED -> {
                                return NORTH_PRESSED_SHAPE;
                            }
                        }
                    }
                    case EAST -> {

                        switch (state.getValue(STATE)) {
                            case CLOSED -> {
                                return EAST_CLOSED_SHAPE;
                            }
                            case OPEN -> {
                                return EAST_OPEN_SHAPE;
                            }
                            case PRESSED -> {
                                return EAST_PRESSED_SHAPE;
                            }
                        }
                    }
                    case SOUTH -> {
                        switch (state.getValue(STATE)) {
                            case CLOSED -> {
                                return SOUTH_CLOSED_SHAPE;
                            }
                            case OPEN -> {
                                return SOUTH_OPEN_SHAPE;
                            }
                            case PRESSED -> {
                                return SOUTH_PRESSED_SHAPE;
                            }
                        }
                    }
                    case WEST -> {
                        switch (state.getValue(STATE)) {
                            case CLOSED -> {
                                return WEST_CLOSED_SHAPE;
                            }
                            case OPEN -> {
                                return WEST_OPEN_SHAPE;
                            }
                            case PRESSED -> {
                                return WEST_PRESSED_SHAPE;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        switch (state.getValue(STATE)) {
            case PRESSED -> {
                return InteractionResult.CONSUME;
            }
            case OPEN -> {
                if (player.isShiftKeyDown()) {
                    this.closeCase(state, worldIn, pos);
                    this.playToggleSound(player, worldIn, pos, false);
                } else {
                    this.powerBlock(state, worldIn, pos);
                    this.playClickSound(player, worldIn, pos, true);
                    if (config.alarmSoundType != AlarmEnum.OFF) {
                        EmergencyButton.emergencySound(worldIn, pos, player);
                    }
                    if (player instanceof ServerPlayer) {
                        InfinityButtonsTriggers.EMERGENCY_TRIGGER.trigger((ServerPlayer) player);
                    }
                    if (!worldIn.isClientSide && config.alarmVillagerPanic) {
                        List<LivingEntity> villagers = new ArrayList<>();
                        if (config.alarmSoundType == AlarmEnum.GLOBAL) {
                            villagers = new ArrayList<>();
                            List<LivingEntity> villagersDup = worldIn.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(512), entity -> entity.getType() == EntityType.VILLAGER);
                            for (Player player1 : worldIn.players())
                                villagersDup.addAll(worldIn.getEntitiesOfClass(LivingEntity.class, new AABB(player1.getOnPos()).inflate(512), entity -> entity.getType() == EntityType.VILLAGER));
                            for (LivingEntity villager : villagersDup)
                                if (!villagers.contains(villager))
                                    villagers.add(villager);

                        }
                        if (config.alarmSoundType == AlarmEnum.RANGE) {
                            villagers = worldIn.getEntitiesOfClass(LivingEntity.class, new AABB(pos).inflate(config.alarmSoundRange), entity -> entity.getType() == EntityType.VILLAGER);
                        }
                        for (LivingEntity villager : villagers) {
                            if (villager instanceof Villager villagerEntity) {
                                villagerEntity.getBrain().setMemory(MemoryModuleType.HEARD_BELL_TIME, worldIn.getGameTime());
                            }
                        }
                    }
                    worldIn.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
                }
            }
            case CLOSED -> {
                if (player.isShiftKeyDown()) {
                    this.openCase(state, worldIn, pos);
                    this.playToggleSound(player, worldIn, pos, true);
                } else {
                    player.displayClientMessage(InfinityButtonsUtil.SAFE_EMERGENCY_BUTTON_ACTIONBAR_TEXT, true);
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    public void openCase(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(STATE, SEBStateEnum.OPEN), 3);
        this.updateNeighbors(state, world, pos);
    }

    public void closeCase(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(STATE, SEBStateEnum.CLOSED), 3);
        this.updateNeighbors(state, world, pos);
    }

    public void powerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(STATE, SEBStateEnum.PRESSED), 3);
        this.updateNeighbors(state, world, pos);
        world.scheduleTick(pos, this, 10);
    }

    protected void playClickSound(@javax.annotation.Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean pressed) {
        worldIn.playSound(pressed ? playerIn : null, pos, SoundEvents.BONE_BLOCK_BREAK, SoundSource.BLOCKS, 1, pressed ? 0.6f : 0.5f);
    }

    protected void playToggleSound(@javax.annotation.Nullable Player playerIn, LevelAccessor world, BlockPos pos, boolean pressed) {
        world.playSound(pressed ? playerIn : null, pos, pressed ? SoundEvents.IRON_TRAPDOOR_OPEN : SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.BLOCKS, 1f, 1);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean moved) {
        if (moved || state.is(newState.getBlock())) {
            return;
        }
        if (state.getValue(STATE) == SEBStateEnum.PRESSED) {
            this.updateNeighbors(state, worldIn, pos);
        }
        super.onRemove(state, worldIn, pos, newState, moved);
    }


    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(STATE) == SEBStateEnum.PRESSED ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return (blockState.getValue(STATE) == SEBStateEnum.PRESSED && EmergencyButton.getConnectedDirection(blockState) == side) ? 15 : 0;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if(state.getValue(STATE) == SEBStateEnum.PRESSED) {
            worldIn.setBlock(pos, state.setValue(STATE, SEBStateEnum.OPEN), 3);
            this.updateNeighbors(state, worldIn, pos);
            this.playClickSound(null, worldIn, pos, false);
            worldIn.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }

    private void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
        worldIn.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        InfinityButtonsUtil.tooltip(pTooltip, "safe_emergency_button");
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
