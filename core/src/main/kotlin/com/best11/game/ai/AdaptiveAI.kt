package com.best11.game.ai
data class DefensiveAdjustment(val closeLongShotSpace:Boolean, val dropDefensiveLine:Boolean, val protectWideChannels:Boolean)
object AdaptiveAI {
    fun adjust(h:HabitSnapshot)=DefensiveAdjustment(h.longShots>=3,h.throughBalls>=3,h.wingAttacks>=4)
}
