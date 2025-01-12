package com.example.finalproject.model

import java.io.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Student (
    @PrimaryKey val id: String,
    val name: String,
    val phone: String,
    val address: String,
    val avatarUrl: String,
    var isChecked: Boolean
): Serializable