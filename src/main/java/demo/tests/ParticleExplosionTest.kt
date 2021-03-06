package demo.tests

import de.chaffic.dynamics.World
import de.chaffic.math.Vec2
import demo.window.TestBedWindow

object ParticleExplosionTest {
    @JvmField
    val text = arrayOf("Particle Explosions:", "Left click: casts an explosion")
    @JvmField
    var active = false
    @JvmStatic
    fun load(testBedWindow: TestBedWindow) {
        testBedWindow.world = World(Vec2(.0, -9.81))
        testBedWindow.setCamera(Vec2(.0, 300.0), 2.0)
        active = true
        testBedWindow.buildExplosionDemo()
    }
}