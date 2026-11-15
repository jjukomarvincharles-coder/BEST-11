package com.best11.game.club
enum class Facility { STANDS, MEDICAL, TRAINING, COMMERCIAL }
object FacilityUpgrades { fun cost(level:Int)=level.coerceAtLeast(1)*250000L }
