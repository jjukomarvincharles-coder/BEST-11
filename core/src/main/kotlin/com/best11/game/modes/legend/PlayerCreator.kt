package com.best11.game.modes.legend
enum class Archetype { PHYSICAL, TECHNICAL }
data class CreatedPlayer(val name:String,val nationality:String,val shirtNumber:Int,val archetype:Archetype)
