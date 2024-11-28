package com.cmex.fragmentsandadaptivescreen.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.cmex.fragmentsandadaptivescreen.R
import com.cmex.fragmentsandadaptivescreen.databinding.ActivityMainBinding
import com.cmex.fragmentsandadaptivescreen.presentation.fragments.FragmentN1

class MainActivity : AppCompatActivity() {
    private lateinit var actionBar:ActionBar
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

      utilSetColorStatusBar(this,getColor(R.color.white_light_font))
        initActionBar()
    }
  private fun initActionBar(){
      setSupportActionBar(binding.toolbar)
     supportActionBar?.let {
         actionBar=it
     }
      actionBar.setDisplayShowTitleEnabled(false)
      Glide.with(this)
          .load(R.drawable.lera_anim)
          .error(R.drawable.no)
          .into(binding.ivToolBar)
  }
}