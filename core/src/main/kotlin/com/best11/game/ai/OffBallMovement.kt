package com.best11.game.ai
enum class TacticalRole { INVERTED_FULLBACK, MEZZALA, FALSE_NINE, CLASSIC_WINGER }
data class Offset(val x:Float,val z:Float)
object OffBallMovement {
    fun target(role:TacticalRole, side:Float):Offset=when(role) {
        TacticalRole.INVERTED_FULLBACK -> Offset(-side*2f, -1f)
        TacticalRole.MEZZALA -> Offset(side*4f, -3f)
        TacticalRole.FALSE_NINE -> Offset(0f, -4f)
        TacticalRole.CLASSIC_WINGER -> Offset(side*7f, 0f)
    }
}
