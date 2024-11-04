package com.assessment.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val age: String = "",
    val thumbnail: String = "",
    val address: String = "",
    val gender: String = "",
    val image: String = ""
) : Parcelable