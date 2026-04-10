package p3.prog.models;

import p3.prog.ENum.GalaxyArm;

public class Star {
    private final String name;
    private final GalaxyArm location;
    private final int distanceFromSun;
    private final boolean canEmitRays;

    public Star(String name, GalaxyArm location, int distanceFromSun, boolean canEmitRays) {
        this.name = name;
        this.location = location;
        this.distanceFromSun = distanceFromSun;
        this.canEmitRays = canEmitRays;
    }

    public String getName() {
        return name;
    }

    public GalaxyArm getLocation() {
        return location;
    }

    public int getDistanceFromSun() {
        return distanceFromSun;
    }

    public boolean canEmitRays() {
        return canEmitRays;
    }
}
