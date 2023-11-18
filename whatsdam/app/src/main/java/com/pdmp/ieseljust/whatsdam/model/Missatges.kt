package com.pdmp.ieseljust.whatsdam.model

import androidx.lifecycle.MutableLiveData
import org.json.JSONObject

object Missatges {

    //Inicialització llista buida. Ahora como LiveData

        //var missatges: ArrayList<Missatge> = ArrayList()


    private val _missatges = MutableLiveData<ArrayList<Missatge>>().apply{
        value= ArrayList<Missatge>()
    }

    //Variable de acceso público

    val missatges : MutableLiveData<ArrayList<Missatge>> = _missatges


    //Implementació métodes MVVM ---------------------------

    fun getMessageAt(position:Int): Missatge {
       return missatges.value!!.get(position)
    }


    fun add (usuari: String, text:String) : Boolean {

        val missatge = Missatge(usuari, text)

        missatges.value?.add(missatge)

        //missatges.add(missatge)

        missatges.postValue(missatges.value)


        return true

    }


    //pte actualizar en multihilo
    fun sendMessage (missatge : Missatge){

        val JSONmissatge = JSONObject()

        JSONmissatge.put("usuari", missatge.usuari)
        JSONmissatge.put("text", missatge.text)


    }


}