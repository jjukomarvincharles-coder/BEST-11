package com.best11.game.tactics
enum class Mentality { BALANCED, ATTACKING, ULTRA_DEFENSIVE }
enum class DefensiveLine { DEEP, NORMAL, HIGH }
data class TacticalSliders(var defensiveLine:DefensiveLine=DefensiveLine.NORMAL,var pressIntensity:Float=0.45f,var attackSpeed:Float=0.5f)
