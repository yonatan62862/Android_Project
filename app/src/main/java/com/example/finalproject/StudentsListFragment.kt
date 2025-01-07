package com.example.finalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapter.StudentsRecyclerAdapter
import com.example.finalproject.model.Model
import com.example.finalproject.model.Student

class StudentsListFragment : Fragment() {

    private var students: MutableList<Student>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO: Set DB
        // TODO: Refactor Model to support local db
        // TODO: Refactor Fragments to work with live data
        // TODO: Add progress indicator for better UX
        // TODO: Migrate to ViewBinding

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_students_list, container, false)

        students = Model.shared.students
        val recyclerView: RecyclerView = view.findViewById(R.id.students_list_activity_recycler_view)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        val adapter = StudentsRecyclerAdapter(students)

        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
            }

            override fun onItemClick(student: Student?) {
                Log.d("TAG", "On student clicked name: ${student?.name}")
//                Navigation.findNavController(view).navigate(R.id.action_studentsListFragment_to_blueFragment)
                student?.let {
                    val action = StudentsListFragmentDirections.actionStudentsListFragmentToBlueFragment(it.name)
                    Navigation.findNavController(view).navigate(action)
                }
            }
        }

        recyclerView.adapter = adapter

        val imageButton: ImageButton = view.findViewById(R.id.list_fragment_add_button)
        val action = StudentsListFragmentDirections.actionGlobalAddStudentFragment()
        imageButton.setOnClickListener(Navigation.createNavigateOnClickListener(action))
        return view
    }
}