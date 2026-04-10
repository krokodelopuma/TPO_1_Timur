package p3.prog;

import p3.prog.ENum.CutterType;
import p3.prog.ENum.GalaxyArm;
import p3.prog.ENum.Position;
import p3.prog.models.*;
import p3.prog.commands.*;
/*
public class App {
    public static void main(String[] args) {

        Star sun = new Star("Солнце", GalaxyArm.SPIRAL_ARM, 0, true);
        Star damogranSun = new Star(
                "дамогранское солнце",
                GalaxyArm.OPPOSITE_ARM,
                500000,
                true
        );

        Sea damogranSea = new Sea("дамогранские моря");

        Person zaphod = new Person(
                "Зафод Библброкс",
                Position.PRESIDENT,
                damogranSea
        );

        DeltaCutter cutter = new DeltaCutter(
                "дельта-катер",
                CutterType.ION_REACTIVE,
                zaphod
        );

        Scenario scenario = new Scenario();
        scenario.addCommand(new TravelCommand(zaphod, cutter, damogranSea));
        scenario.addCommand(new ShineCommand(cutter));
        scenario.addCommand(new ReflectCommand(cutter));
        scenario.addCommand(new StarRaysCommand(damogranSun));

        String actionSentence = scenario.buildSentence();

        String fullSentence =
                "Очень далеко, " +
                        damogranSun.getLocation().getDescription() + ", " +
                        "в " + damogranSun.getDistanceFromSun() + " световых лет от звезды по имени Солнце, " +
                        actionSentence;

        System.out.println(fullSentence);
    }
}*/
