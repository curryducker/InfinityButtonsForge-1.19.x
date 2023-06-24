package net.larsmans.infinitybuttons.block.custom.button;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class CopperButton extends WaxedCopperButton implements WeatheringButton {

    protected final WeatherState weatherState;

    public CopperButton(Properties properties, boolean large, WeatherState weatherState) {
        super(properties, large);
        this.weatherState = weatherState;
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        this.onRandomTick(pState, pLevel, pPos, pRandom);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(handIn);
        if (state.getValue(PRESSED)) {
            return InteractionResult.CONSUME;
        } else if (itemStack.getItem() instanceof HoneycombItem) {
            return wax(state, worldIn, pos, player, itemStack);
        } else if (itemStack.getItem() instanceof AxeItem && getAge() != WeatherState.UNAFFECTED) {
            return scrape(state, worldIn, pos, player, itemStack);
        } else {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return WeatheringButton.getNext(pState.getBlock()).isPresent() && !pState.getValue(PRESSED);
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }
}
