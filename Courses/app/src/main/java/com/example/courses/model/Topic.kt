package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes
    val nameId: Int,
    val associatedCoursesCount: Int,
    @DrawableRes
    val thumbnailId: Int,
)
