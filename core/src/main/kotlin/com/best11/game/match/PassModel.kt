package com.best11.game.match
enum class PassType { TO_FEET, THROUGH, DRIVEN, LOFTED }
object PassModel { fun speed(power:Float)=6f+14f*power.coerceIn(0f,1f) }
