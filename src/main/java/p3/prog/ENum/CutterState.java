package p3.prog.ENum;

public enum CutterState {
    STOPPED("не движется"),
    MOVING_SLOW("движется медленно"),
    MOVING_FAST("мчится"),
    FLYING("летит над поверхностью");

    private final String description;

    CutterState(String description) { this.description = description; }

    public String getDescription() { return description; }
}
