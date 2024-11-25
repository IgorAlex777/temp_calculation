package com.cmex.fragmentsandadaptivescreen.presentation.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import com.cmex.fragmentsandadaptivescreen.R
import com.cmex.fragmentsandadaptivescreen.databinding.FragmentResultBinding
import com.cmex.fragmentsandadaptivescreen.domain.Result
import com.cmex.fragmentsandadaptivescreen.presentation.myLog


class FragmentResult : Fragment() {
    private lateinit var binding:FragmentResultBinding
    private lateinit var result: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         checkBuild()
    }
     private fun checkBuild(){
         if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
             arguments?.getParcelable(RESULT_KEY,Result::class.java)?.let {
                  result=it
              }
         }else{
             @Suppress("DEPRECATION")
             arguments?.getParcelable<Result>(RESULT_KEY)?.let {
                 result=it
             }
         }
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
            myLog("press Exit")
            requireActivity().supportFragmentManager.popBackStack(FragmentLevel.LEVEL_N1,1)
        }
    }
     private fun setScreen()= with(binding){
         if(result.flagResult){
             Glide.with(this@FragmentResult)
                 .load(R.drawable.correct_answer)
                 .error(R.drawable.no)
                 .into(ivResult)
         } else{
             Glide.with(this@FragmentResult)
                 .load(R.drawable.loss_gif)
                 .error(R.drawable.no)
                 .into(ivResult)
         }
         tvSumMin.text=getString(R.string.minSum_result,result.sumNumbersMin)
         tvSumMax.text=getString(R.string.maxSum_result,result.sumNumbersMax)
     }

    private fun closeFragmentOnBackPress(){
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.popBackStack(FragmentLevel.LEVEL_N1,1)
            }

        })
    }
    companion object {
      private const val RESULT_KEY="result"
        fun newInstance(result: Result) =
            FragmentResult().apply {
                arguments = Bundle().apply {
                  putParcelable(RESULT_KEY,result)
                }
            }
    }
}