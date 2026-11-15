package com.best11.game.match
enum class TackleType { STANDING, SLIDE }
object TackleModel { fun foulRisk(type:TackleType, timing:Float):Float = (if(type==TackleType.SLIDE) 0.28f else 0.08f)*(1f-timing.coerceIn(0f,1f)*0.5f) }
