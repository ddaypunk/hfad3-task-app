package com.example.tasks

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel(private val dao: TaskDao): ViewModel() {
    // dao: the VM needs a reference to the DAO to interact with the DB

    // we need to get this from the UI
    var newTaskName: String = ""
    private val tasks = dao.getAll() // This is LiveData<List<Task>>

    // This observes the tasks live data, and returns a live data wrapped string.
    val tasksString = Transformations.map(tasks) {
            tasks -> formatTasks(tasks)
    }

    // create a new task object, update it's name, and insert into the DB
    fun addTask() {
        val newTask = Task()
        newTask.taskName = newTaskName
        viewModelScope.launch {
            dao.insert(newTask)
        }
    }

    /**
     * Takes list of tasks and breaks into singular tasks
     */
    private fun formatTasks(tasks: List<Task>): String {
        return tasks.fold("") {
            str, item -> str + '\n' + formatTask(item)
        }
    }

    /**
     * Formats singular tasks into a string with returns and such
     */
    private fun formatTask(task: Task): String {
        var str = "ID: ${task.taskId}"
        str += '\n' + "Name: ${task.taskName}"
        str += '\n' + "Complete: ${task.taskDone}" + '\n'
        return str
    }
}