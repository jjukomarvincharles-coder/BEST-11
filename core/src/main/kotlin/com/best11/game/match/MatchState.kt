package com.best11.game.match
data class MatchState(var homeGoals:Int=0, var awayGoals:Int=0, var elapsedSeconds:Float=0f, var addedTime:Int=0, var possessionHome:Boolean=true, var shotsHome:Int=0, var shotsAway:Int=0)
