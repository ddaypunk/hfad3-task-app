package com.example.tasks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    // Query can be used to write pure SQL statements and assign them to a function call
    @Query("SELECT * FROM task_table WHERE taskId = :key")
    fun get(key: Int): LiveData<Task>

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task) // This method could also take a list of Tasks if desired and room will handle them

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task: Task) // updates Task with matching taskId

    @Delete
    suspend fun delete(task: Task) // deletes Task with matching taskId
}