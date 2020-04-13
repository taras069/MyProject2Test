package com.example.comp_admin.a35viewmodeltest1

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

class MyViewModel : ViewModel() {

    var randomNumber: String? = null

    fun createNumber() {
        var random = Random()
        var number = random.nextInt(10 - 1) + 1
        randomNumber= "Number $number"
    }

    fun getNumber(): String? {
        if(randomNumber == null){
            createNumber()
        }
        return randomNumber
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("demo", "view model cleared")
    }
}