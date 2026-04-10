package p3.test;

import p3.prog.*;
import p3.prog.ENum.CutterState;
import p3.prog.ENum.CutterType;
import p3.prog.ENum.GalaxyArm;
import p3.prog.ENum.PersonState;
import p3.prog.ENum.Position;
import p3.prog.models.*;
import p3.prog.commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DomainModelTest {

    @Test
    void testStarModel() {
        Star star = new Star("Солнце", GalaxyArm.SPIRAL_ARM, 0, true);

        assertEquals("Солнце", star.getName());
        assertEquals(GalaxyArm.SPIRAL_ARM, star.getLocation());
        assertEquals(0, star.getDistanceFromSun());
        assertTrue(star.canEmitRays());
    }

    @Test
    void testPersonModel() {
        Sea sea = new Sea("дамогранские моря");
        Person p = new Person("Зафод Библброкс", Position.PRESIDENT, sea);

        assertEquals("Зафод Библброкс", p.getName());
        assertEquals(Position.PRESIDENT, p.getPosition());
        assertEquals(sea, p.getLocation());
        assertEquals("стоит", p.getState().getDescription());
    }

    @Test
    void testSeaModel() {
        Sea sea = new Sea("дамогранские моря");
        assertEquals("дамогранские моря", sea.getName());
    }

    @Test
    void testDeltaCutterModel() {
        Sea sea = new Sea("моря");
        Person owner = new Person("Зафод", Position.PRESIDENT, sea);
        DeltaCutter cutter = new DeltaCutter("дельта-катер", CutterType.ION_REACTIVE, owner);

        assertEquals("дельта-катер", cutter.getName());
        assertEquals(CutterType.ION_REACTIVE, cutter.getType());
        assertEquals(owner, cutter.getOwner());
        assertEquals("не движется", cutter.getState().getDescription());
    }

    @Test
    void testTravelCommand() {
        Sea sea = new Sea("дамогранским морям");
        Person zaphod = new Person("Зафод", Position.PRESIDENT, sea);
        DeltaCutter cutter = new DeltaCutter("дельта-катер", CutterType.ION_REACTIVE, zaphod);

        TravelCommand cmd = new TravelCommand(zaphod, cutter, sea);
        cmd.apply();
        String part = cmd.getSentencePart();

        assertEquals(PersonState.RIDING, zaphod.getState());
        assertEquals(CutterState.MOVING_FAST, cutter.getState());

        assertTrue(part.contains("Зафод"));
        assertTrue(part.contains("мч"));
        assertTrue(part.contains("дельта-катер"));
    }

    @Test
    void testShineCommand() {
        DeltaCutter cutter = new DeltaCutter("дельта-катер", CutterType.ION_REACTIVE, null);

        ShineCommand cmd = new ShineCommand(cutter);
        cmd.apply();

        assertEquals("сверкает", cutter.getEffect().getDescription());
        assertEquals("в сверкает", cmd.getSentencePart());
    }

    @Test
    void testReflectCommand() {
        DeltaCutter cutter = new DeltaCutter("дельта-катер", CutterType.ION_REACTIVE, null);

        ReflectCommand cmd = new ReflectCommand(cutter);
        cmd.apply();

        assertEquals("переливается", cutter.getEffect().getDescription());
        assertEquals("и переливается", cmd.getSentencePart());
    }

    @Test
    void testStarRaysCommand() {
        Star star = new Star("дамогранское солнце", GalaxyArm.OPPOSITE_ARM, 500000, true);

        StarRaysCommand cmd = new StarRaysCommand(star);
        cmd.apply();

        assertEquals("в лучах дамогранское солнце", cmd.getSentencePart());
    }

    @Test
    void testScenarioExecution() {

        Sea sea = new Sea("дамогранские моря");
        Person zaphod = new Person("Зафод Библброкс", Position.PRESIDENT, sea);
        DeltaCutter cutter = new DeltaCutter("дельта-катер", CutterType.ION_REACTIVE, zaphod);
        Star damogranSun = new Star("дамогранское солнце", GalaxyArm.OPPOSITE_ARM, 500000, true);

        Scenario scenario = new Scenario();
        scenario.addCommand(new TravelCommand(zaphod, cutter, sea));
        scenario.addCommand(new ShineCommand(cutter));
        scenario.addCommand(new ReflectCommand(cutter));
        scenario.addCommand(new StarRaysCommand(damogranSun));

        String sentence = scenario.buildSentence();

        assertEquals("переливается", cutter.getEffect().getDescription());
        assertEquals(PersonState.RIDING, zaphod.getState());

        assertTrue(sentence.contains("Зафод Библброкс"));
        assertTrue(sentence.contains("мч"));
        assertTrue(sentence.contains("дельта-катер"));
        assertTrue(sentence.contains("сверка"));
        assertTrue(sentence.contains("перел"));
        assertTrue(sentence.contains("дамогранское солнце"));

        assertTrue(damogranSun.getDistanceFromSun() > 100);
    }

    @Test
    void testCutterTypeDescription() {
        assertEquals("ионно-реактивном", CutterType.ION_REACTIVE.getDescription());
        assertEquals("плазменном", CutterType.PLASMA.getDescription());
    }

    @Test
    void testGalaxyArmDescription() {
        assertEquals("спиральная ветвь Галактики", GalaxyArm.SPIRAL_ARM.getDescription());
        assertEquals("противоположная спиральная ветвь Галактики", GalaxyArm.OPPOSITE_ARM.getDescription());
    }

    @Test
    void testSpeedAffectsState() {
        DeltaCutter cutter = new DeltaCutter("катер", CutterType.ION_REACTIVE, null);

        cutter.setSpeed(0);
        assertEquals(CutterState.STOPPED, cutter.getState());

        cutter.setSpeed(20);
        assertEquals(CutterState.MOVING_SLOW, cutter.getState());

        cutter.setSpeed(100);
        assertEquals(CutterState.MOVING_FAST, cutter.getState());
    }
}
