package com.best11.game.match
data class ShotResult(val speed:Float, val lift:Float, val curve:Float)
object ShotModel {
    fun calculate(power:Float, finesse:Boolean, swipeCurve:Float):ShotResult {
        val p=power.coerceIn(0f,1f)
        return ShotResult(8f+22f*p, if(p<0.45f) 0.25f else 1.2f*p, if(finesse) swipeCurve.coerceIn(-1f,1f)*2.4f else 0f)
    }
}
