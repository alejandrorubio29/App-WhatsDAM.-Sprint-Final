package com.pdmp.ieseljust.whatsdam.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdmp.ieseljust.whatsdam.R
import com.pdmp.ieseljust.whatsdam.model.Missatges

class AdaptadorMissatges: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //Creació nou viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context) //Creem l'inflater a partir del ViewGroup
        val vista=inflater.inflate(R.layout.my_msg_viewholder, parent,false);

        return MissatgeViewHolder(vista) //retornem un objecte missatgewiewholder amb la vista "inflada"


    }

    //Cuenta numero de mensajes
    override fun getItemCount(): Int {
        return Missatges.missatges.size
    }


    //Enllaç de valores layout amb vistes. Ací en realitat es fa la crida a MissatgesViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MissatgeViewHolder).bind(Missatges.missatges[position]);
    }


}