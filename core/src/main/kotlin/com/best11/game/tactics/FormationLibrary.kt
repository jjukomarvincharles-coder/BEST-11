package com.best11.game.tactics
object FormationLibrary {
    val presets=listOf(
        Formation("4-3-3","4-3-3 Attack",listOf("GK","RB","CB","CB","LB","CDM","CM","CM","RW","ST","LW")),
        Formation("4-2-3-1","4-2-3-1 Wide",listOf("GK","RB","CB","CB","LB","CDM","CDM","RW","CAM","LW","ST")),
        Formation("4-1-2-1-2","4-1-2-1-2 Diamond",listOf("GK","RB","CB","CB","LB","CDM","CM","CM","CAM","ST","ST")),
        Formation("3-5-2","3-5-2",listOf("GK","CB","CB","CB","RWB","CM","CDM","CM","LWB","ST","ST"))
    )
}
