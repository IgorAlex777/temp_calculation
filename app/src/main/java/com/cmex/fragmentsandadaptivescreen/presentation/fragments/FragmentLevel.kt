package com.cmex.fragmentsandadaptivescreen.presentation.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.cmex.fragmentsandadaptivescreen.R
import com.cmex.fragmentsandadaptivescreen.databinding.FragmentLevelBinding
import com.cmex.fragmentsandadaptivescreen.domain.Level


class FragmentLevel : Fragment() {
    private lateinit var binding:FragmentLevelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }



    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding=FragmentLevelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickLevel()
    }
    private fun getFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .addToBackStack(LEVEL_N1)
            .commit()
    }
    private fun onClickLevel()= with(binding){
        val listener=listenerClickLevel()
        tvLevel1.setOnClickListener(listener)
        tvLevel2.setOnClickListener(listener)
        tvLevel3.setOnClickListener(listener)
    }
    private fun listenerClickLevel():OnClickListener= with(binding){
        return OnClickListener {
            when(it){
                tvLevel1->{getFragment(FragmentN1.newInstance(Level.NUMBERS10))}
                tvLevel2->{getFragment(FragmentN1.newInstance(Level.NUMBERS100))}
                tvLevel3->{getFragment(FragmentN1.newInstance(Level.NUMBERS1000))}
            }
        }
    }
    companion object {
        const val LEVEL_N1="level_1"
        fun newInstance() =
            FragmentLevel().apply {
                arguments = Bundle().apply {

                }
            }
    }
}