package p3.prog.models;

import p3.prog.ENum.CutterState;
import p3.prog.ENum.CutterType;
import p3.prog.ENum.VisualEffect;

public class DeltaCutter {
    private final String name;
    private final CutterType type;
    private final Person owner;

    private CutterState state = CutterState.STOPPED;
    private VisualEffect effect = VisualEffect.NONE;
    private int speed;

    public DeltaCutter(String name, CutterType type, Person owner) {
        this.name = name;
        this.type = type;
        this.owner = owner;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if (speed == 0) state = CutterState.STOPPED;
        else if (speed < 50) state = CutterState.MOVING_SLOW;
        else state = CutterState.MOVING_FAST;
    }

    public void setEffect(VisualEffect effect) {
        this.effect = effect;
    }

    public VisualEffect getEffect() {
        return effect;
    }

    public String getName() { return name; }
    public CutterType getType() { return type; }
    public Person getOwner() { return owner; }

    public CutterState getState() { return state; }
}
