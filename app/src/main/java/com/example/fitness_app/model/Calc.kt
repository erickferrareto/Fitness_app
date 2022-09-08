package com.example.fitness_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Calc(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "tipo") var tipo: String,
    @ColumnInfo(name = "res") var res: Float,
    @ColumnInfo(name = "created_date") var createDate: Date = Date(),
)
