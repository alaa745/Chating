package com.example.chatapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class State(
    val name: String
): Parcelable