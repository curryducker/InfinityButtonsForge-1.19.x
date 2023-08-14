package net.larsmans.infinitybuttons.advancement;

import com.google.gson.JsonObject;
import net.larsmans.infinitybuttons.InfinityButtons;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class WaxOffTrigger extends SimpleCriterionTrigger<WaxOffTrigger.TriggerInstance> {
    private static final ResourceLocation ID = new ResourceLocation(InfinityButtons.MOD_ID, "wax_off");

    @Override
    protected TriggerInstance createInstance(JsonObject pJson, EntityPredicate.Composite pPlayer, DeserializationContext pContext) {
        return new TriggerInstance(pPlayer);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, TriggerInstance::matches);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        public TriggerInstance(EntityPredicate.Composite pPlayer) {
            super(WaxOffTrigger.ID, pPlayer);
        }

        public boolean matches() {
            return true;
        }
    }
}
