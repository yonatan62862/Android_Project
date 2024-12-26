package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step 1 - Add Student Button
        // Step 2 - Navigate to AddStudentActivity
        // Step 3 - Create AddStudentLayout
        // Step 4 - Save Student

        Log.d("ACTIVITY", "onCreate $savedInstanceState")

        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)

        finish()

    }

    override fun onStart() {
        super.onStart()
        Log.d("ACTIVITY", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ACTIVITY", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ACTIVITY", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ACTIVITY", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ACTIVITY", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ACTIVITY", "onDestroy")
    }


}