package com.example.comp_admin.a36viewmodelwithlivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MyViewModel2 : ViewModel(){

    var randomNumber: MutableLiveData<String>? = null

    fun createNumber() {
        var random = Random()
        var number = random.nextInt(10 - 1) + 1
        randomNumber?.value= "Number $number"
    }

    fun getNumber(): MutableLiveData<String>? {
        if(randomNumber == null){
            randomNumber = MutableLiveData()
            createNumber()
        }
        return randomNumber
    }
}