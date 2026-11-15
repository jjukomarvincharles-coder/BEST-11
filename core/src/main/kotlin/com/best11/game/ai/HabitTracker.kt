package com.best11.game.ai
data class HabitSnapshot(val longShots:Int, val throughBalls:Int, val wingAttacks:Int)
class HabitTracker {
    private var longShots=0; private var throughBalls=0; private var wingAttacks=0
    fun recordLongShot(){longShots++}; fun recordThroughBall(){throughBalls++}; fun recordWingAttack(){wingAttacks++}
    fun snapshot()=HabitSnapshot(longShots,throughBalls,wingAttacks)
}
