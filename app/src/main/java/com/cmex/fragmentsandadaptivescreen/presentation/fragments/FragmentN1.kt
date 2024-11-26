package com.cmex.fragmentsandadaptivescreen.presentation.fragments



import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.bumptech.glide.Glide
import com.cmex.fragmentsandadaptivescreen.R
import com.cmex.fragmentsandadaptivescreen.databinding.FragmentN1Binding
import com.cmex.fragmentsandadaptivescreen.domain.Level
import com.cmex.fragmentsandadaptivescreen.domain.Numbers
import com.cmex.fragmentsandadaptivescreen.domain.Result
import com.cmex.fragmentsandadaptivescreen.domain.Settings
import com.cmex.fragmentsandadaptivescreen.presentation.ViewModelNumbers


class FragmentN1 : Fragment() {
    private val args by navArgs<FragmentN1Args>()
     private val model by lazy{ ViewModelProvider(this)[ViewModelNumbers::class.java]}
      private lateinit var binding:FragmentN1Binding
    private lateinit var settings: Settings
    private lateinit var numbers: Numbers
    private lateinit var level: Level
    private lateinit var result: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         level=args.level
        model.startGeneration(level)

    }

    private val listNumbersView by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tv1)
            add(binding.tv2)
            add(binding.tv3)
            add(binding.tv4)
            add(binding.tv5)
            add(binding.tv6)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding=FragmentN1Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
    }
    private fun initObserver(){
        model.settingsModel.observe(viewLifecycleOwner){
            settings=it

        }
        model.numbersModel.observe(viewLifecycleOwner){
            numbers=it
            onSetScreen()
        }
        model.timerModel.observe(viewLifecycleOwner){
            binding.tvTimer.text=it
        }
        model.isFinishTimer.observe(viewLifecycleOwner){
            if(it){
                getFragment(result)
            }
        }
        model.resultModel.observe(viewLifecycleOwner){
            result=it
        }

    }
    private fun getFragment(result: Result){
      findNavController().navigate(FragmentN1Directions.actionFragmentN1ToFragmentResult(result))

    }

    private fun closeFragment(){
        requireActivity().supportFragmentManager.popBackStack()
    }
    private fun onSetScreen()= with(binding){
        for(i in 0 until numbers.listNumbers.size){
            listNumbersView[i].text=numbers.listNumbers[i].toString()
        }
       tvMaxSum.text=numbers.maxSum.toString()
       tvMinSum.text=numbers.minSum.toString()
        Glide.with(this@FragmentN1)
            .load(R.drawable.lera_anim)
            .error(R.drawable.no)
            .into(imageView)
    }


}