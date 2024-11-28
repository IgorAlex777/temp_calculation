package com.cmex.fragmentsandadaptivescreen.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cmex.fragmentsandadaptivescreen.R

@BindingAdapter("bindMinSum")
fun bindMinSum(textView: TextView,listSum: List<Int>){
     val minSum=listSum.min()
     textView.text=String.format(textView.context.getString(R.string.minSum_result,minSum))
}
@BindingAdapter("bindMaxSum")
fun bindMax(textView: TextView,listSum:List<Int>){
     val maxSum=listSum.max()
     textView.text=String.format(textView.context.getString(R.string.maxSum_result,maxSum))
}
@BindingAdapter("bindImageResult")
fun bindImage(imageView: ImageView,flag:Boolean){
     if(flag){
          Glide.with(imageView.context)
               .load(R.drawable.correct_answer)
               .error(R.drawable.no)
               .into(imageView)
     }else{
          Glide.with(imageView.context)
               .load(R.drawable.loss_gif)
               .error(R.drawable.no)
               .into(imageView)
     }
}
@BindingAdapter("bindIntToText")
fun bindMinN1(textView: TextView,min:Int){
textView.text=min.toString()
}

