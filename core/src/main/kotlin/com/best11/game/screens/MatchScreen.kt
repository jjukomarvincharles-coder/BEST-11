package com.best11.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g3d.*
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils
import com.best11.game.GameConfig
import kotlin.math.max

/**
 * First playable vertical slice: procedural 3D pitch, goals, two teams,
 * controllable blue player, ball, clock and basic keyboard/touch input.
 * Replace primitive meshes with authored models/animations as production art arrives.
 */
class MatchScreen(private val game: com.best11.game.Best11Game) : ScreenAdapter() {
    private val camera = PerspectiveCamera(58f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()).apply {
        position.set(0f, 25f, 22f); lookAt(0f, 0f, 0f); near = 0.1f; far = 150f; update()
    }
    private val batch3d = ModelBatch()
    private val batch2d = SpriteBatch()
    private val font = BitmapFont()
    private val environment = Environment().apply {
        set(ColorAttribute(ColorAttribute.AmbientLight, 0.78f, 0.78f, 0.78f, 1f))
        add(DirectionalLight().set(0.9f, 0.9f, 0.9f, -1f, -0.8f, -0.4f))
    }
    private val models = mutableListOf<Model>()
    private val instances = mutableListOf<ModelInstance>()
    private var playerX = 0f
    private var playerZ = 7f
    private var ballX = 0f
    private var ballZ = 5.5f
    private var stamina = 100f
    private var clock = 0f
    private var scoreHome = 0
    private var scoreAway = 0

    init {
        val builder = ModelBuilder()
        fun box(w: Float, h: Float, d: Float, color: Color, x: Float, y: Float, z: Float) {
            val model = builder.createBox(w, h, d,
                Material(ColorAttribute.createDiffuse(color)),
                (com.badlogic.gdx.graphics.VertexAttributes.Usage.Position or com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal).toLong())
            models += model
            instances += ModelInstance(model, x, y, z)
        }
        fun sphere(size: Float, color: Color, x: Float, y: Float, z: Float) {
            val model = builder.createSphere(size, size, size, 16, 16,
                Material(ColorAttribute.createDiffuse(color)),
                (com.badlogic.gdx.graphics.VertexAttributes.Usage.Position or com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal).toLong())
            models += model
            instances += ModelInstance(model, x, y, z)
        }
        box(30f, 0.15f, 48f, Color(0.08f, 0.42f, 0.22f, 1f), 0f, -0.15f, 0f)
        // Simplified pitch markings are represented by thin light strips.
        box(0.08f, 0.02f, 44f, Color.WHITE, 0f, -0.06f, 0f)
        box(28f, 0.02f, 0.08f, Color.WHITE, 0f, -0.06f, 0f)
        box(28f, 0.02f, 0.08f, Color.WHITE, 0f, -0.06f, -22f)
        box(28f, 0.02f, 0.08f, Color.WHITE, 0f, -0.06f, 22f)
        box(3f, 2f, 0.3f, Color.WHITE, 0f, 0.8f, -23f)
        box(3f, 2f, 0.3f, Color.WHITE, 0f, 0.8f, 23f)
        sphere(0.8f, Color.WHITE, 0f, 0.45f, 5.5f)
        // Player capsules are low-poly spheres + torso blocks.
        for (i in 0..4) {
            val z = -12f + i * 5f
            sphere(1.1f, Color(0.85f, 0.2f, 0.18f, 1f), -5f + (i % 3) * 5f, 0.8f, z)
            sphere(1.1f, Color(0.15f, 0.45f, 0.95f, 1f), -6f + (i % 3) * 6f, 0.8f, 13f - i * 5f)
        }
        sphere(1.15f, Color(0.15f, 0.55f, 1f, 1f), playerX, 0.85f, playerZ)
    }

    override fun render(delta: Float) {
        val dt = delta.coerceAtMost(0.05f)
        clock = max(0f, clock + dt)
        val sprint = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)
        val speed = GameConfig.PLAYER_SPEED * if (sprint && stamina > 0f) GameConfig.SPRINT_MULTIPLIER else 1f
        var dx = 0f; var dz = 0f
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) dx -= 1f
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) dx += 1f
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) dz -= 1f
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) dz += 1f
        val length = kotlin.math.sqrt(dx*dx + dz*dz).coerceAtLeast(1f)
        playerX = (playerX + dx / length * speed * dt).coerceIn(-13f, 13f)
        playerZ = (playerZ + dz / length * speed * dt).coerceIn(-21f, 21f)
        stamina = (stamina + if (sprint && (dx != 0f || dz != 0f)) -GameConfig.SPRINT_DRAIN_PER_SECOND * dt else 3f * dt).coerceIn(0f, 100f)

        val controlled = instances.last()
        controlled.transform.setToTranslation(playerX, 0.85f, playerZ)
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            ballX = playerX * 0.65f
            ballZ = playerZ - 5f
            instances[7].transform.setToTranslation(ballX, 0.45f, ballZ)
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) game.screen = HomeMenuScreen(game)

        camera.position.set(playerX * 0.35f, 25f, playerZ + 22f)
        camera.lookAt(playerX * 0.25f, 0f, playerZ - 3f)
        camera.update()

        ScreenUtils.clear(0.02f, 0.12f, 0.08f, 1f, true)
        batch3d.begin(camera)
        instances.forEach { batch3d.render(it, environment) }
        batch3d.end()

        batch2d.begin()
        font.draw(batch2d, "BEST 11     $scoreHome - $scoreAway     ${String.format("%02d:%02d", (clock/60).toInt(), (clock%60).toInt())}", 20f, Gdx.graphics.height - 24f)
        font.draw(batch2d, "STAMINA ${stamina.toInt()}%   MOVE: WASD / ARROWS   SPRINT: SHIFT   PASS: SPACE   ESC: MENU", 20f, 24f)
        batch2d.end()
    }

    override fun dispose() {
        batch3d.dispose(); batch2d.dispose(); font.dispose(); models.forEach { it.dispose() }
    }
}
