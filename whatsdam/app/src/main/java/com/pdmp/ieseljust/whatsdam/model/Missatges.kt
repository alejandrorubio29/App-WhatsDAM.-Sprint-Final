package com.pdmp.ieseljust.whatsdam.model

object Missatges {

    //Inicialització llista buida

    var missatges: ArrayList<Missatge> = ArrayList()

    //Implementació métode add (rep params. de missatge)

    fun add (usuari: String, text:String) : Boolean {

        val missatge = Missatge(usuari, text)

        missatges.add(missatge)


        return true

    }


}