package com.pdmp.ieseljust.whatsdam.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdmp.ieseljust.whatsdam.R
import com.pdmp.ieseljust.whatsdam.model.Missatges
import com.pdmp.ieseljust.whatsdam.repository.MissatgeRepository

class AdaptadorMissatges: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Atributos identificadores de mensajes

    private val MISSATGE_D_USUARI = 1
    private val MISSATGE_D_ALTRE = 2

    // Referencia al repositorio
    val repository = MissatgeRepository.getInstance()

    override fun getItemViewType(position: Int): Int {
        // Aquesta funcio permet definir el tipus de vista segons algun criteri

        // Obtenim el missatge en la posició que se'ns indica
        var message=repository.getMissatge(position)

        // Comprovem si l'usuari que ha enviat el missatge
        // és l'usuari actual (per això fem ús del nom d'usuari
        // guardat al repositori)
        return if (message.usuari== repository.username ) {
            MISSATGE_D_USUARI
        } else {
            MISSATGE_D_ALTRE
        }
    }

    //Creació nou viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context) //Creem l'inflater a partir del ViewGroup
        val vista=inflater.inflate(R.layout.my_msg_viewholder, parent,false);

        //return MissatgeViewHolder(vista) //retornem un objecte missatgewiewholder amb la vista "inflada"
        return when (viewType) {
            MISSATGE_D_USUARI -> {
                val vista = inflater.inflate(R.layout.my_msg_viewholder, parent, false)
                MissatgeViewHolder(vista)
            }
            MISSATGE_D_ALTRE -> {
                val vista = inflater.inflate(R.layout.other_msg_viewholder, parent, false)
                MissatgeAltreViewHolder(vista)
            }
            else -> throw IllegalArgumentException("Tipo de vista desconocido")
        }
    }

    //Cuenta numero de mensajes
    override fun getItemCount(): Int {
        return Missatges.missatges.value?.size ?: 0

    }


    //Enllaç de valores layout amb vistes. Ací en realitat es fa la crida a MissatgesViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MissatgeViewHolder -> holder.bind(repository.getMissatge(position))
            is MissatgeAltreViewHolder -> holder.bind(repository.getMissatge(position))
            else -> throw IllegalArgumentException("Tipo de ViewHolder desconocido")
        }
    }
    // (holder as MissatgeViewHolder).bind(Missatges.getMessageAt(position)); //modificado con el nuevo metodo
}


