package net.larsmans.infinitybuttons.advancement;

import net.minecraft.advancements.CriteriaTriggers;

public class InfinityButtonsTriggers {

    public static SafetyTrigger SAFETY_TRIGGER = new SafetyTrigger();
    public static EmergencyTrigger EMERGENCY_TRIGGER = new EmergencyTrigger();
    public static WaxOffTrigger WAX_OFF_TRIGGER = new WaxOffTrigger();

    public static void register() {
        CriteriaTriggers.register(SAFETY_TRIGGER);
        CriteriaTriggers.register(EMERGENCY_TRIGGER);
        CriteriaTriggers.register(WAX_OFF_TRIGGER);
    }
}
