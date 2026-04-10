package p3.prog.commands;

import p3.prog.ENum.CutterState;
import p3.prog.ENum.PersonState;
import p3.prog.models.*;

public class TravelCommand implements Command {

    private final Person person;
    private final DeltaCutter cutter;
    private final Sea sea;

    public TravelCommand(Person person, DeltaCutter cutter, Sea sea) {
        this.person = person;
        this.cutter = cutter;
        this.sea = sea;
    }

    @Override
    public void apply() {
        cutter.setSpeed(120);
        //cutter.setState(CutterState.FLYING);
        person.setState(PersonState.RIDING);
    }


    @Override
    public String getSentencePart() {
        return person.getName() + ", " +
                person.getPosition().name().toLowerCase() + ", " +
                person.getState().getDescription() +
                " по " + sea.getName() +
                " в своем " + cutter.getType().getDescription() +
                " " + cutter.getName();
    }


}

