package com.cmex.fragmentsandadaptivescreen.presentation.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.cmex.fragmentsandadaptivescreen.R
import com.cmex.fragmentsandadaptivescreen.databinding.FragmentResultBinding
import com.cmex.fragmentsandadaptivescreen.domain.Result
import com.cmex.fragmentsandadaptivescreen.presentation.myLog


class FragmentResult : Fragment() {
    private val args by  navArgs<FragmentResultArgs>()
    private lateinit var binding:FragmentResultBinding
    private lateinit var result: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        result=args.result
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding=FragmentResultBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreen()
        closeFragmentOnBackPress()
        onClickExit()
    }
    private fun onClickExit(){
        binding.ivExit.setOnClickListener {
            findNavController().popBackStack()
        }
    }
     private fun setScreen(){
         binding.resultData=result
     }
    private fun closeFragmentOnBackPress(){
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               findNavController().popBackStack()
            }
        })
    }
}