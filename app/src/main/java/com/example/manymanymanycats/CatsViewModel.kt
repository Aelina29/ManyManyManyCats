package com.example.manymanymanycats

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.manymanymanycats.api.ApiFactory
import com.example.manymanymanycats.api.CatInfo
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class CatsViewModel(application: Application) : AndroidViewModel(application) {
    val catsMLD = MutableLiveData<List<CatInfo>>()
    val list = arrayListOf<CatInfo>()
init {
    Log.i("aelinka","init")
    getCats(0,application.baseContext)
}

    fun getCats(page: Int,context:Context) {
        Log.i("aelinka",catsMLD.value.toString())
        viewModelScope.launch {
            try{
               val catsResponse =  ApiFactory.getApiService().getCat(10,page)
                list.addAll(catsResponse.body()!!)
                catsMLD.value=catsResponse.body()
            }
            catch(error:UnknownHostException){
                Toast.makeText(context,"Нет интернета",Toast.LENGTH_SHORT).show()
            }


//            if (catsResponse.isSuccessful && catsResponse.body()!=null){
//
//            } else {
//                Toast.makeText(context,"Нет интернета",Toast.LENGTH_SHORT).show()
//            }

        }
    }
}