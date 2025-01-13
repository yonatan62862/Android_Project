package com.example.finalproject.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val phone: String,
    val address: String,
    val avatarUrl: String,
    var isChecked: Boolean
) : Parcelable
