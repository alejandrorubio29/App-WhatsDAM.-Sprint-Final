package com.pdmp.ieseljust.whatsdam.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pdmp.ieseljust.whatsdam.model.Missatge
import com.pdmp.ieseljust.whatsdam.repository.MissatgeRepository

class MissatgesViewModel (application: Application) : AndroidViewModel(application) {

    /* Propietats del ViewModel */

    //Guardar l'adaptador
    private val _adaptador = MutableLiveData<AdaptadorMissatges>().apply{
        value= AdaptadorMissatges()}

    val adaptador:MutableLiveData<AdaptadorMissatges> =_adaptador

    //Ultim missatge

    private val _ultimMissatge = MutableLiveData<Int>()
    val ultimMissatge : MutableLiveData<Int> = _ultimMissatge

    public fun add(missatge: Missatge){  //tinc dubtes del objecte missatge

        //Preparem para ser observat
        if (MissatgeRepository.getInstance().add(missatge)){
            adaptador.value?.notifyItemInserted(MissatgeRepository.getInstance().getNumMissatges()-1)
        }
    }

}