package com.cmex.fragmentsandadaptivescreen.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmex.fragmentsandadaptivescreen.R

class FragmentN2 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_n2, container, false)
    }

    companion object {


        fun newInstance() =
            FragmentN2().apply {
                arguments = Bundle().apply {

                }
            }
    }
}