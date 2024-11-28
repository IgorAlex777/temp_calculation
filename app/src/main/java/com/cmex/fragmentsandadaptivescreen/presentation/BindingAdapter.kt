package com.cmex.fragmentsandadaptivescreen.presentation

import android.widget.ImageView
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
@BindingAdapter("bindSetImage")
fun setImageInToolbar(imageView: ImageView){
    Glide.with(imageView.context)
        .load(R.drawable.lera_anim)
        .error(R.drawable.no)
        .into(imageView)
}