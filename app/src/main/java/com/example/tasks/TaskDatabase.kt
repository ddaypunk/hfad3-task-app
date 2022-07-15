package com.example.tasks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    // entities: can include any class marked with @Entity
    // version: update ANY TIME the schema is changed, or the app will crash
    // exportSchema: export to folder so we can record its version history

    // specify DAO interfaces, which contain marked methods
    abstract val taskDao: TaskDao

    //create and return an instance of the database (singleton pattern)
    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            synchronized(this) {
                var instance = INSTANCE

                // if it doesn't already exist, build one
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "tasks_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}