package com.best11.game.match
object StaminaSystem {
    fun tick(current:Float, sprinting:Boolean, dt:Float):Float = (current + if(sprinting) -8f*dt else 2.5f*dt).coerceIn(0f,100f)
    fun paceMultiplier(stamina:Float):Float = when { stamina>60f -> 1f; stamina>30f -> 0.88f; else -> 0.72f }
}
