package com.example.dailydiet

enum class Screen(
    val route: String
) {
    HOME("home"),
    STATISTIC("statistic"),
    NEW_SNACK("new_snack"),
    EDIT_SNACK("edit_snack/{snackId}"),
    FEEDBACK_POSITIVE("feedback_positive"),
    FEEDBACK_NEGATIVE("feedback_negative"),
    SNACK_VIEW("snack_view/{snackId}")
}