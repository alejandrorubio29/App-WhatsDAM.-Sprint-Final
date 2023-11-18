package com.pdmp.ieseljust.whatsdam.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pdmp.ieseljust.whatsdam.model.Missatge
import com.pdmp.ieseljust.whatsdam.repository.MissatgeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MissatgesViewModel (application: Application) : AndroidViewModel(application) {

    /* Propietats del ViewModel */

    //Guardar l'adaptador
    private val _adaptador = MutableLiveData<AdaptadorMissatges>().apply{
        value= AdaptadorMissatges()}

    val adaptador:MutableLiveData<AdaptadorMissatges> =_adaptador

    //Ultim missatge

    private val _ultimMissatge = MutableLiveData<Int>()
    val ultimMissatge : MutableLiveData<Int> = _ultimMissatge

    //Referencia al repositorio

    val repository = MissatgeRepository.getInstance()

    //Lista de mensajes como livedata
 /*
    val llistaMissatges: LiveData<ArrayList<Missatge>> by lazy{
        repository.getMissatge()
    }
*/
    public fun add(missatge: Missatge){
        viewModelScope.launch(Dispatchers.Main) {
            val resultat = withContext(Dispatchers.IO) {
                repository.sendMessage(missatge)
            }
            if (resultat) {
            adaptador.value?.notifyItemInserted(repository.getNumMissatges() )
        }
        }

        //Nota: para importar viewModelScope https://developer.android.com/topic/libraries/architecture/coroutines?hl=es-419


        /*
        //Preparem para ser observat
        if (MissatgeRepository.getInstance().add(missatge)){
            adaptador.value?.notifyItemInserted(MissatgeRepository.getInstance().getNumMissatges()-1)
        }*/
    }

    //Métodos auxiliares

    fun getUserName(): String {
        return repository.username
    }

    fun getServer(): String {
        return repository.server
    }


}