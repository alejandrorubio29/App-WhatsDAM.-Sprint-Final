package com.pdmp.ieseljust.whatsdam.viewmodel

import android.icu.text.SimpleDateFormat
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdmp.ieseljust.whatsdam.R
import com.pdmp.ieseljust.whatsdam.model.Missatge
import java.util.Date


//Implementació viewHolder

class MissatgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    //Instancia als components de les vistes (subvistes)

    private val msgtext = itemView.findViewById(R.id.msg_text) as TextView
    private val msgtimestamp = itemView.findViewById(R.id.msg_me_timestamp) as TextView


    //Enllaç text/hora amb component vista

    fun bind(missatge: Missatge){

        msgtext.text = missatge.text

       //Métode indicat per a obtindre l'hora

        // 1. Creem un objecte per al format de l'hora
                val dateFormat = SimpleDateFormat("HH:mm")

        // 2. Obtenim l'hora amb la funció `Date()`
                val horaActual = Date()

        // 3. Obtenim l'hora en el format que hem creat:
                val horaFormatada=dateFormat.format(horaActual)

      //bind de l'hora

        msgtimestamp.text = horaFormatada
    }




}