package com.cmex.fragmentsandadaptivescreen.presentation

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cmex.fragmentsandadaptivescreen.R

@BindingAdapter("setTextToMinResult")
fun setMinToTextView(textView: TextView,list: List<Int>){
    val min=list.min()
    textView.text= textView.context.getString(R.string.minSum_result,min)
}

@BindingAdapter("setTextToMaxResult")
fun setMaxToTextView(textView: TextView,list:List<Int>){
    val max=list.max()
    textView.text=textView.context.getString(R.string.maxSum_result,max)

}
@BindingAdapter("bindSetTextFromInt")
fun setTextFromInt(textView: TextView,number:Int) {
    textView.text = number.toString()
}
@BindingAdapter("bindSetImageResult")
fun onSetImageResult(imageView: ImageView,flag:Boolean){
    if(flag){
     Glide.with(imageView.context).load(R.drawable.correct_answer).error(R.drawable.no).into(imageView)
    }else{
        Glide.with(imageView.context).load(R.drawable.loss_gif).error(R.drawable.no).into(imageView)
    }
}