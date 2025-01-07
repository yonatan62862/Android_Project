package com.example.finalproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BlueFragment : Fragment() {

    private var textView: TextView? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(TITLE)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_blue, container, false)

        textView = view.findViewById(R.id.blue_fragment_text_view)
        textView?.text = title

        return view
    }

    companion object {

        const val TITLE = "TITLE"

        fun newInstance(title: String): BlueFragment {
            return BlueFragment().apply {
                arguments = Bundle().apply {
                    putString(TITLE, title)
                }
            }
        }
    }
}