package com.best11.game.modes.career
data class CareerMode(val division:Int=8,val season:Int=1,val points:Int=0) { fun promoted()=copy(division=(division-1).coerceAtLeast(1)) }
