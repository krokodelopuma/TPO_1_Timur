package p3.prog.commands;

import p3.prog.ENum.VisualEffect;
import p3.prog.models.DeltaCutter;

public class ReflectCommand implements Command {

    private final DeltaCutter cutter;

    public ReflectCommand(DeltaCutter cutter) {
        this.cutter = cutter;
    }

    @Override
    public void apply() {
        cutter.setEffect(VisualEffect.REFLECTING);
    }

    @Override
    public String getSentencePart() {
        return "и " + cutter.getEffect().getDescription();
    }


}
