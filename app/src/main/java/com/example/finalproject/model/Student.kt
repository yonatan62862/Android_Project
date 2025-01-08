package com.example.finalproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student (
    @PrimaryKey val id: String,
    val name: String,
    val avatarUrl: String,
    var isChecked: Boolean
)