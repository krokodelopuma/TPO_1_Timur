package p3.prog.models;

import p3.prog.ENum.PersonState;
import p3.prog.ENum.Position;

public class Person {
    private final String name;
    private final Position position;
    private final Sea location;

    private PersonState state = PersonState.STANDING;

    public Person(String name, Position position, Sea location) {
        this.name = name;
        this.position = position;
        this.location = location;
    }

    public String getName() { return name; }
    public Position getPosition() { return position; }
    public Sea getLocation() { return location; }

    public PersonState getState() { return state; }
    public void setState(PersonState state) { this.state = state; }
}
