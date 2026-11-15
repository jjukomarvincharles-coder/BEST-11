package com.best11.game.data
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.JsonReader
class SquadLoader {
    fun load(path:String)=JsonReader().parse(Gdx.files.internal(path))
}
