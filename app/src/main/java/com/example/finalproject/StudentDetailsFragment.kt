package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.finalproject.model.Student


class StudentDetailsFragment : Fragment() {

    private var student: Student? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        student = arguments?.let {
            StudentDetailsFragmentArgs.fromBundle(it).student
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_details, container, false)

        val nameTextView: TextView = view.findViewById(R.id.student_details_name)
        val idTextView: TextView = view.findViewById(R.id.student_details_id)
        val phoneTextView: TextView = view.findViewById(R.id.student_details_phone)
        val addressTextView: TextView = view.findViewById(R.id.student_details_address)
        val imageView: ImageView = view.findViewById(R.id.student_details_image)
        val checkBox: CheckBox = view.findViewById(R.id.student_details_check_box)
        val editButton: Button = view.findViewById(R.id.student_details_edit_button)

        nameTextView.text = student?.name
        idTextView.text = student?.id
        phoneTextView.text = student?.phone
        addressTextView.text = student?.address
        checkBox.isChecked = student?.isChecked ?: false


//        editButton.setOnClickListener {
//            val action = StudentDetailsFragmentDirections
//                .actionStudentDetailsFragmentToEditStudentFragment(student)
//            Navigation.findNavController(view).navigate(action)
//        }
        return view
    }
}