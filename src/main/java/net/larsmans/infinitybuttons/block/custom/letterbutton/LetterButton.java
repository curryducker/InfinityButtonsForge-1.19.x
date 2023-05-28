package net.larsmans.infinitybuttons.block.custom.letterbutton;

import net.larsmans.infinitybuttons.block.custom.button.AbstractSmallButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.gui.LetterButtonGui;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public class LetterButton extends AbstractSmallButton {

    public static final EnumProperty<LetterButtonEnum> CHARACTER = EnumProperty.create("character", LetterButtonEnum.class);

    public LetterButton(Properties properties) {
        super(true, true, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false).setValue(FACE, AttachFace.FLOOR).setValue(CHARACTER, LetterButtonEnum.NONE));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (player.isShiftKeyDown()) {
            if (worldIn.isClientSide) {
                Minecraft.getInstance().setScreen(new LetterButtonGui(this, state, worldIn, pos));
            }
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        } else {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override
    public int getPressDuration() {
        return 30;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean pressed) {
        return SoundEvents.STONE_BUTTON_CLICK_ON;
    }

    public int getEnumId(BlockState state) {
        return state.getValue(CHARACTER).getId();
    }

    public void setState(BlockState state, Level worldIn, BlockPos pos, LetterButtonEnum buttonEnum) {
        worldIn.setBlock(pos, state.setValue(CHARACTER, buttonEnum), 3);
        this.updateNeighbors(state, worldIn, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CHARACTER);
    }
}
