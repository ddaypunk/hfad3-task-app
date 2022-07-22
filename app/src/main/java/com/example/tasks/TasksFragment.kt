package com.example.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        // get a reference to the current application context
        // use it to get the TaskDatabase instance (or create if it doesn't exist) and it's taskDao property
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao

        // create a factory with the dao
        // use it with the ViewModelProvider to get the TasksViewModel
        // bind it with data binding to the ui
        val factory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, factory).get(TasksViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}