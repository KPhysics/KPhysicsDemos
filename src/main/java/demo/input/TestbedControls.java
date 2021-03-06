package demo.input;

import de.chaffic.explosions.ParticleExplosion;
import de.chaffic.explosions.ProximityExplosion;
import de.chaffic.math.Vec2;
import demo.tests.*;
import demo.window.Camera;
import demo.window.TestBedWindow;

import java.awt.event.MouseEvent;

public abstract class TestbedControls {
    protected final TestBedWindow TESTBED;
    protected final Camera CAMERA;
    protected static String currentDemo;

    public TestbedControls(TestBedWindow testBedWindow) {
        this.TESTBED = testBedWindow;
        this.CAMERA = testBedWindow.getCamera();
        currentDemo = "Chains";
    }

    public void loadDemo(String demo) {
        currentDemo = demo;
        TESTBED.clearTestbedObjects();
        resetUniqueEventHandlers();
        switch (currentDemo) {
            case "Chains" -> {
                Chains.load(TESTBED);
                TESTBED.setCurrentDemo(0);
            }
            case "Line of sight" -> {
                LineOfSight.load(TESTBED);
                TESTBED.setCurrentDemo(1);
            }
            case "Particle explosion" -> {
                ParticleExplosionTest.load(TESTBED);
                TESTBED.setCurrentDemo(2);
            }
            case "Proximity explosion" -> {
                ProximityExplosionTest.load(TESTBED);
                TESTBED.setCurrentDemo(3);
            }
            case "Raycast explosion" -> {
                RaycastExplosionTest.load(TESTBED);
                TESTBED.setCurrentDemo(4);
            }
            case "Raycast" -> {
                Raycast.load(TESTBED);
                TESTBED.setCurrentDemo(5);
            }
            case "Trebuchet" -> {
                Trebuchet.load(TESTBED);
                TESTBED.followPayload = true;
                TESTBED.setCurrentDemo(6);
            }
            case "Slice objects" -> {
                SliceObjects.load(TESTBED);
                TestBedWindow.HERTZ = 120;
                TESTBED.setCurrentDemo(7);
            }
            case "Bouncing ball" -> {
                BouncingBall.load(TESTBED);
                TESTBED.setCurrentDemo(8);
            }
            case "Mixed shapes" -> {
                MixedShapes.load(TESTBED);
                TESTBED.setCurrentDemo(9);
            }
            case "Newtons cradle" -> {
                NewtonsCradle.load(TESTBED);
                TESTBED.setCurrentDemo(10);
            }
            case "Wrecking ball" -> {
                WreckingBall.load(TESTBED);
                TESTBED.setCurrentDemo(11);
            }
            case "Friction" -> {
                Friction.load(TESTBED);
                TESTBED.setCurrentDemo(12);
            }
            case "Drag" -> {
                Drag.load(TESTBED);
                TESTBED.setCurrentDemo(13);
            }
            case "Restitution" -> {
                Restitution.load(TESTBED);
                TESTBED.setCurrentDemo(14);
            }
            case "Stacked objects" -> {
                StackedObjects.load(TESTBED);
                TESTBED.setCurrentDemo(15);
            }
            case "Bouncy Ball" -> {
                BouncyBall.load(TESTBED);
                TESTBED.setCurrentDemo(16);
            }
        }
    }

    private void resetUniqueEventHandlers() {
        TESTBED.setCamera(new Vec2(0, 0), 1);
        TESTBED.followPayload = false;
        ProximityExplosionTest.active = false;
        ParticleExplosionTest.active = false;
        RaycastExplosionTest.active = false;
        SliceObjects.active = false;
        LineOfSight.active = false;
        Trebuchet.active = false;
        TestBedWindow.HERTZ = 60;
    }

    protected void setProximityEpicentre(MouseEvent e) {
        Vec2 v = CAMERA.convertToWorld(new Vec2(e.getX(), e.getY()));
        ProximityExplosion p = (ProximityExplosion) TESTBED.getRayExplosions().get(0);
        p.setEpicentre(v);
    }

    protected void generateParticleExplosion(MouseEvent e) {
        ParticleExplosion p = new ParticleExplosion(CAMERA.convertToWorld(new Vec2(e.getX(), e.getY())), 100, 10);
        p.createParticles(0.5, 100, 5, TESTBED.getWorld());
        p.applyBlastImpulse(100);
        TESTBED.add(p, 2);
    }
}