package com.example.fitness_app.model

import android.content.Context
import androidx.room.*

@Database(entities = [Calc::class], version = 1)
@TypeConverters(dateConverter::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun calcDao() : CalcDao
    companion object{
        private var INSTANCE: AppDataBase? = null


        fun getDatabase(context: Context) : AppDataBase{
            return if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "Fitness tracker"
                    ).build()
                }
                INSTANCE as AppDataBase
            } else {
                INSTANCE as AppDataBase
            }
        }
    }

}