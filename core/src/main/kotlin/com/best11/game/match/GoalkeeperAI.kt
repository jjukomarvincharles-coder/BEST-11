package com.best11.game.match
import kotlin.math.sqrt
object GoalkeeperAI {
    fun rushDistance(ballX:Float, ballZ:Float, keeperX:Float, keeperZ:Float):Float {
        val dx=ballX-keeperX; val dz=ballZ-keeperZ
        return sqrt(dx*dx+dz*dz).coerceAtMost(8f)
    }
}
