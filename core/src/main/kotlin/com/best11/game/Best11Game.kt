package com.best11.game

import com.badlogic.gdx.Game
import com.best11.game.screens.HomeMenuScreen

class Best11Game : Game() {
    override fun create() {
        setScreen(HomeMenuScreen(this))
    }
}
