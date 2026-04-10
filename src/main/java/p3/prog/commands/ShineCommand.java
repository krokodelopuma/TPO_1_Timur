package p3.prog.commands;

import p3.prog.ENum.VisualEffect;
import p3.prog.models.DeltaCutter;

public class ShineCommand implements Command {


    private final DeltaCutter cutter;

    public ShineCommand(DeltaCutter cutter) {
        this.cutter = cutter;
    }

    @Override
    public void apply() {
        cutter.setEffect(VisualEffect.SHINING);
    }


    @Override
    public String getSentencePart() {
        return "в " + cutter.getEffect().getDescription();
    }


}
