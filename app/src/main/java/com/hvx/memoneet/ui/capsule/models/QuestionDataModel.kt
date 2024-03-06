package com.hvx.memoneet.ui.capsule.models

data class QuestionDataModel (
    val questionId: Int,
    val question: String,
    val options: List<String>,
    val correct: Int
)
