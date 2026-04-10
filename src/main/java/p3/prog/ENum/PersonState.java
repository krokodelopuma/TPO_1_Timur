package p3.prog.ENum;

public enum PersonState {
    STANDING("стоит"),
    MOVING("идет"),
    RUNNING("бежит"),
    RIDING("мчится на катере");

    private final String description;

    PersonState(String description) { this.description = description; }

    public String getDescription() { return description; }
}

