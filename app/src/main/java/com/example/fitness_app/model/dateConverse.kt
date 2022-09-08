package com.example.fitness_app.model

import androidx.room.TypeConverter
import java.util.*

object dateConverter{
    @TypeConverter
    fun toDate(dateLong : Long): Date?{
        return if (dateLong != null) Date(dateLong) else null
    }

    @TypeConverter
    fun fromDate(date: Date?): Long?{
        return date?.time
    }
}