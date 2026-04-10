package p3.prog.ENum;

public enum CutterType {
    ION_REACTIVE("ионно-реактивном"),
    PLASMA("плазменном");

    private final String description;

    CutterType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

