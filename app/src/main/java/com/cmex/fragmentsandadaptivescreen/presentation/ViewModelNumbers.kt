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
    private val listSumNumbers= mutableListOf<Int>()

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
   private val _max=MutableLiveData<Int>()
    val max:LiveData<Int>
        get() = _max
    private val _min=MutableLiveData<Int>()
    val min:LiveData<Int>
        get() = _min
    fun startGeneration(level: Level){
        _isFinishTimer.value=false
        setting(level)
        timerRun()
        generation(settings)

    }

     private fun setResult():Result{
         val minSumFromList=listSumNumbers.min()
             val maxSumFromList=listSumNumbers.max()
         return if(minSumFromList<=settings.minSum && maxSumFromList>=settings.maxSum){
             Result(true,listSumNumbers)
         }
         else Result(false,listSumNumbers)
     }

    private fun generation(settings: Settings) {

        _numbersModel.value=listenerNumbersUseCase(settings)
        _numbersModel.value?.let {
            numbers=it
        }
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
                    generation(settings)
                    listSumNumbers.add(numbers.listNumbers.sum())
                    _max.value=listSumNumbers.max()
                    _min.value=listSumNumbers.min()
                }
                _timerModel.value=  msToString(msec)

            }
            override fun onFinish() {
                _resultModel.value=setResult()
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