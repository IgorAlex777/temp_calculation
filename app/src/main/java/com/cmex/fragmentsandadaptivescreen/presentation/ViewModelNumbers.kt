package com.cmex.fragmentsandadaptivescreen.presentation


import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmex.fragmentsandadaptivescreen.data.GenerationNumbersUseCase
import com.cmex.fragmentsandadaptivescreen.data.RepositoryImpl
import com.cmex.fragmentsandadaptivescreen.data.SettingsUseCase
import com.cmex.fragmentsandadaptivescreen.domain.Level
import com.cmex.fragmentsandadaptivescreen.domain.Numbers
import com.cmex.fragmentsandadaptivescreen.domain.Result
import com.cmex.fragmentsandadaptivescreen.domain.Settings


class ViewModelNumbers:ViewModel() {

   private val listenerImpl= RepositoryImpl
   private val listenerSettingsUseCase= SettingsUseCase(listenerImpl)
   private lateinit var settings: Settings
   private  val listenerNumbersUseCase= GenerationNumbersUseCase(listenerImpl)
    private lateinit var timerDown:CountDownTimer
   private lateinit var numbers: Numbers

  private val _numbersModel=MutableLiveData<Numbers>()
    val numbersModel:LiveData<Numbers>
        get() = _numbersModel
    private val _settingsModel=MutableLiveData<Settings>()
    val settingsModel:LiveData<Settings>
        get() = _settingsModel

    private val _timerModel=MutableLiveData<String>()
     val timerModel:LiveData<String>
         get() = _timerModel
    private val _isFinishTimer=MutableLiveData<Boolean>()
     val isFinishTimer:LiveData<Boolean>
         get() = _isFinishTimer

    private val _resultModel=MutableLiveData<Result>()
    val resultModel:LiveData<Result>
        get() = _resultModel

    fun startGeneration(level: Level){
        _isFinishTimer.value=false
        setting(level)
        timerRun()
        generation(settings)

    }
     private fun setResult():Result{
      numbersModel.value?.let {
          numbers=it
      }
         return if(numbers.maxSum!=0 || numbers.minSum!=0) Result(true,numbers.minSum,numbers.maxSum)
         else Result(false,numbers.minSum,numbers.maxSum)
     }

    private fun generation(settings: Settings) {

        _numbersModel.value=listenerNumbersUseCase(settings)
    }
    private fun setting(level: Level){
        _settingsModel.value=listenerSettingsUseCase(level)
      _settingsModel.value?.let {
          settings=it
      }
    }


    private fun timerRun(){
        val ms=settings.timer* INTERVAL
        timerDown=object :CountDownTimer(ms, INTERVAL){
            override fun onTick(msec: Long) {
                if( msec.toInt()%2==0){
                    myLog("${(msec.toInt()/1000)%2}")
                   generation(settings)
                }
                _timerModel.value=  msToString(msec)
                _resultModel.value=setResult()
            }

            override fun onFinish() {
                _isFinishTimer.value=true
            }

        }.start()
    }
    private fun msToString(ms:Long):String{
        val secondsAll=ms/ INTERVAL
        val minutes=secondsAll/ SECONDS_IN_MINUTE
        val seconds=secondsAll-(minutes* SECONDS_IN_MINUTE)
        return String.format("%02d:%02d",minutes,seconds)
    }

    override fun onCleared() {
        super.onCleared()
        timerDown.cancel()
    }
    companion object{
        private const val INTERVAL=1000L
        private const val SECONDS_IN_MINUTE=60
    }
}