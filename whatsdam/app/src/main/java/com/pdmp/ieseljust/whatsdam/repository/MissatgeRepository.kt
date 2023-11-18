package com.pdmp.ieseljust.whatsdam.repository

import com.pdmp.ieseljust.whatsdam.model.Missatge
import com.pdmp.ieseljust.whatsdam.model.Missatges
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject


class MissatgeRepository {

    var username:String=""
    var server:String=""

    //Classe amb patró singleton:

    companion object {
        // Referencia a la propia instancia de la classe
        private var INSTANCE: MissatgeRepository? = null

        // Metode per obtenir la referencia a aquesta instancia
        fun getInstance(): MissatgeRepository {
            if (INSTANCE == null) {
                //INSTANCE = ContacteRepository(context)
                INSTANCE = MissatgeRepository()
            }
            return INSTANCE!!
        }
    }

    // Mètodes del repositori com a API

    fun getMissatge (position:Int) = Missatges.getMessageAt(position)

    fun getNumMissatges () = Missatges.missatges


    //fun add(c : Missatge) : Boolean = Missatges.add(c)

    // FUNCIONES

        //funcion de suspension que envia un mensaje al servidor
        suspend fun sendMessage(msg: Missatge): Boolean {
            withContext(Dispatchers.IO) {
                try{
                    Missatges.sendMessage(msg)
                    true
                } catch (e : Exception){
                    false
                }

            }
            return true // some default value

        }

        //Funcion de suspension de login, emplea la de Missatges

    val msg = JSONObject()
        suspend fun Login(username: String, server: String): JSONObject {
            withContext(Dispatchers.IO) {
                // Actualizar las propiedades en la clase
                this@MissatgeRepository.username = username
                this@MissatgeRepository.server = server



                msg.put("command", "register");

                msg.put("user", username)

                msg
            }

            return msg // some default value

        }

    //ref funciones suspension https://medium.com/kotlin-en-android/coroutines-con-kotlin-suspend-functions-9f9994ddd713

}