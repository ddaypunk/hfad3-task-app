package com.example.tasks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task) // This method could also take a list of Tasks if desired and room will handle them

    @Update
    suspend fun update(task: Task) // updates Task with matching taskId

    @Delete
    suspend fun delete(task: Task) // deletes Task with matching taskId

    // Query can be used to write pure SQL statements and assign them to a function call
    @Query("SELECT * FROM task_table WHERE taskId = :taskId")
    fun get(taskId: Long): LiveData<Task>

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll(): LiveData<List<Task>>
}