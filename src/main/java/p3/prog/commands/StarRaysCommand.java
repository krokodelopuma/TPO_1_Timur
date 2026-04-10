package p3.prog.commands;

import p3.prog.models.Star;

public class StarRaysCommand implements Command {

    private final Star star;

    public StarRaysCommand(Star star) {
        this.star = star;
    }

    @Override
    public void apply() {
        // состояние звезды
    }

    @Override
    public String getSentencePart() {
        return "в лучах " + star.getName();
    }

}
