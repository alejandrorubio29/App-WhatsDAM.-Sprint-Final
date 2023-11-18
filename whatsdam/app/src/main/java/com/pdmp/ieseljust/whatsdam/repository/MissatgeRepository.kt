package com.pdmp.ieseljust.whatsdam.repository

import com.pdmp.ieseljust.whatsdam.model.Missatge
import com.pdmp.ieseljust.whatsdam.model.Missatges

class MissatgeRepository {

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

    fun getMissatge() = Missatges.missatges
    fun getNumMissatges() = Missatges.missatges.size
    fun add(c : Missatge) : Boolean = Missatges.missatges.add(c)


}