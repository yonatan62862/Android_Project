package com.example.finalproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student (
    @PrimaryKey val name: String,
    val id: String,
    val avatarUrl: String,
    var isChecked: Boolean
)