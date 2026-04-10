package p3.prog.ENum;

public enum VisualEffect {
    NONE(""),
    SHINING("сверкает"),
    REFLECTING("переливается");

    private final String description;
    VisualEffect(String description) { this.description = description; }
    public String getDescription() { return description; }
}
