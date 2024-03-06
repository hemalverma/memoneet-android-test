package com.hvx.memoneet.ui.capsule.viewmodel

import com.hvx.memoneet.ui.capsule.models.QuestionDataModel

class AppViewModel {
    companion object {
        const val totalSeconds = 300;

        val totalSteps = mapOf(
            0 to mapOf(
                "heading" to "Video",
                "title" to "Notes",
                "desc" to "Record the notes of the video",
            ),
            1 to mapOf(
                "heading" to "Note",
                "title" to "Notes",
                "desc" to "Add your notes here",
            ),
            2 to mapOf(
                "heading" to "Quiz",
                "title" to "Quiz",
                "desc" to "Answer the questions to test your knowledge",
            ),
            3 to mapOf(
                "heading" to "Result",
                "title" to "Result",
                "desc" to "Evaluate the Capsule for Reward",
            )
        )

        val Questions = arrayOf(
            QuestionDataModel(
                1,
                "What is the capital of France?",
                listOf("Berlin", "Paris", "London", "Madrid"),
                2
            ),
            QuestionDataModel(
                2,
                "Which planet is known as the 'Red Planet'?",
                listOf("Venus", "Mars", "Jupiter", "Saturn"),
                2
            ),
            QuestionDataModel(
                3,
                "Who wrote 'Romeo and Juliet'?",
                listOf("Charles Dickens", "William Shakespeare", "Jane Austen", "Emily Brontë"),
                2
            ),
            QuestionDataModel(
                4,
                "What is the chemical symbol for gold?",
                listOf("Gd", "Au", "Ag", "Fe"),
                2
            ),
            )

    }

}