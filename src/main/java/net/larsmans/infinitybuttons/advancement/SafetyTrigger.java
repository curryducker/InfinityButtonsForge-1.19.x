package net.larsmans.infinitybuttons.advancement;

import com.google.gson.JsonObject;
import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.item.custom.SafeEmergencyButtonItem;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;

public class SafetyTrigger extends SimpleCriterionTrigger<SafetyTrigger.TriggerInstance> {
    private static final ResourceLocation ID = new ResourceLocation(InfinityButtons.MOD_ID, "safety_button_head");

    @Override
    protected TriggerInstance createInstance(JsonObject pJson, EntityPredicate.Composite pPlayer, DeserializationContext pContext) {
        return new TriggerInstance(pPlayer);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, (instance) -> instance.matches(pPlayer));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        public TriggerInstance(EntityPredicate.Composite pPlayer) {
            super(SafetyTrigger.ID, pPlayer);
        }

        public boolean matches(ServerPlayer player) {
            return player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof SafeEmergencyButtonItem;
        }
    }
}
