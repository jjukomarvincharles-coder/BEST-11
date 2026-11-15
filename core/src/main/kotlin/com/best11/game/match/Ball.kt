package com.best11.game.match
data class Ball(var x:Float=0f, var y:Float=0.2f, var z:Float=0f, var vx:Float=0f, var vy:Float=0f, var vz:Float=0f, var spin:Float=0f) {
    fun tick(dt:Float) { x+=vx*dt; y=(y+vy*dt).coerceAtLeast(0.2f); z+=vz*dt; vy-=9.81f*dt; vx*=0.995f; vz*=0.995f; if(y<=0.2f) vy=0f }
}
