package p3.prog.ENum;


public enum GalaxyArm {
    SPIRAL_ARM("спиральная ветвь Галактики"),
    OPPOSITE_ARM("противоположная спиральная ветвь Галактики");

    private final String description;

    GalaxyArm(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
