package com.example.project.data

import java.io.Serializable

data class Habit (
    val name: String,
    val description: String,
    val priority: String,
    val type: String,
    val repeats: String,
    val period: String
) : Serializable