package com.best11.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.best11.game.Best11Game

class HomeMenuScreen(private val game: Best11Game) : ScreenAdapter() {
    private val batch = SpriteBatch()
    private val font = BitmapFont()
    private val title = BitmapFont().apply { data.setScale(2.2f) }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.035f, 0.09f, 0.075f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        batch.begin()
        title.draw(batch, "BEST 11", 48f, Gdx.graphics.height - 70f)
        font.draw(batch, "BUILD YOUR CLUB  •  OWN THE PITCH", 52f, Gdx.graphics.height - 112f)
        font.draw(batch, "TAP / CLICK TO KICK OFF A QUICK MATCH", 52f, 100f)
        font.draw(batch, "OFFLINE FIRST  |  FICTIONAL PLAYERS  |  VERSION 0.1", 52f, 64f)
        batch.end()
        if (Gdx.input.justTouched()) game.screen = MatchScreen(game)
    }

    override fun dispose() { batch.dispose(); font.dispose(); title.dispose() }
}
