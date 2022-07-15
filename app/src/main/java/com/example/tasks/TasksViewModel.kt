package com.example.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao): ViewModel() {
    // dao: the VM needs a reference to the DAO to interact with the DB

    // we need to get this from the UI
    var newTaskName: String = ""

    // create a new task object, update it's name, and insert into the DB
    fun addTask() {
        val newTask = Task()
        newTask.taskName = newTaskName
        viewModelScope.launch {
            dao.insert(newTask)
        }
    }
}