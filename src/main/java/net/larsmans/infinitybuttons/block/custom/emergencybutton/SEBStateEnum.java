package net.larsmans.infinitybuttons.block.custom.emergencybutton;

import net.minecraft.util.StringRepresentable;

public enum SEBStateEnum implements StringRepresentable {
    OPEN("open"),
    CLOSED("closed"),
    PRESSED("pressed");

    private final String name;

    SEBStateEnum(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
