package com.upchiapas.kt_template.impactcalculator.presentation

enum class SocialNetwork(val displayName: String, val dopamineMultiplier: Float) {
    TIKTOK("TikTok", 1.5f),
    INSTAGRAM("Instagram", 1.2f),
    TWITTER("Twitter/X", 1.3f),
    FACEBOOK("Facebook", 1.0f)
}

enum class ContentType(val displayName: String, val multiplier: Float) {
    EDUCATIONAL("Educativo", 0.5f),
    ENTERTAINMENT("Entretenimiento", 1.0f),
    DOOMSCROLLING("Doomscrolling (Negativo)", 2.0f)
}

data class ImpactUiState(
    val selectedNetwork: SocialNetwork = SocialNetwork.TIKTOK,
    val dailyHours: Float = 2f,
    val selectedContent: ContentType = ContentType.ENTERTAINMENT,
    val harmfulDopamineScore: Int = 0,
    val healthyDopamineScore: Int = 0,
    val impactLevelText: String = "",
    val suggestedActivity: String = "",
    val isDangerLevel: Boolean = false
)