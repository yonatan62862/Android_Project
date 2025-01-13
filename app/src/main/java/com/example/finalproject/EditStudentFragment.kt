package com.example.finalproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalproject.model.Student
import com.example.finalproject.model.dao.AppLocalDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditStudentFragment : Fragment() {

    private var student: Student? = null
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        student = arguments?.let {
            EditStudentFragmentArgs.fromBundle(it).student
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        val nameEditText: EditText = view.findViewById(R.id.edit_student_name)
        val idEditText: EditText = view.findViewById(R.id.edit_student_id)
        val phoneEditText: EditText = view.findViewById(R.id.edit_student_phone)
        val addressEditText: EditText = view.findViewById(R.id.edit_student_address)
        val saveButton: Button = view.findViewById(R.id.edit_student_save_button)
        val cancelButton: Button = view.findViewById(R.id.edit_student_cancel_button)
        val deleteButton: Button = view.findViewById(R.id.edit_student_delete_button)
        val imageView: ImageView = view.findViewById(R.id.edit_student_image)
        val changeImageButton: Button = view.findViewById(R.id.edit_student_change_image_button)

        student?.let {
            nameEditText.setText(it.name)
            idEditText.setText(it.id)
            phoneEditText.setText(it.phone)
            addressEditText.setText(it.address)

            if (it.avatarUrl.isNotEmpty()) {
                imageView.setImageURI(Uri.parse(it.avatarUrl))
            }
        }

        changeImageButton.setOnClickListener {
            selectImageFromGallery()
        }

        saveButton.setOnClickListener {
            student?.let {
                val updatedStudent = Student(
                    id = student?.id ?: "",
                    name = nameEditText.text.toString(),
                    phone = phoneEditText.text.toString(),
                    address = addressEditText.text.toString(),
                    avatarUrl = imageUri?.toString() ?: student?.avatarUrl ?: "",
                    isChecked = student?.isChecked ?: false
                )
                updateStudentInDatabase(updatedStudent)
            }
        }

        cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        deleteButton.setOnClickListener {
            student?.let {
                deleteStudentFromDatabase(it)
            }
        }

        return view
    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            imageUri = result.data?.data
            view?.findViewById<ImageView>(R.id.edit_student_image)?.setImageURI(imageUri)
        } else {
            Toast.makeText(requireContext(), "No image selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateStudentInDatabase(student: Student) {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = AppLocalDb.database.studentDao()
            dao.updateStudent(student)

            withContext(Dispatchers.Main) {
                findNavController().popBackStack()
            }
        }
    }

    private fun deleteStudentFromDatabase(student: Student) {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = AppLocalDb.database.studentDao()
            dao.deleteStudent(student)

            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "Student deleted", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }
}