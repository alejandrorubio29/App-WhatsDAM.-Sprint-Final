package com.pdmp.ieseljust.whatsdam.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdmp.ieseljust.whatsdam.viewmodel.AdaptadorMissatges
import com.pdmp.ieseljust.whatsdam.R
import com.pdmp.ieseljust.whatsdam.databinding.ActivityMessagesWindowBinding
import com.pdmp.ieseljust.whatsdam.model.Missatge
import com.pdmp.ieseljust.whatsdam.model.Missatges
import com.pdmp.ieseljust.whatsdam.viewmodel.MissatgesViewModel

class MessagesWindow : AppCompatActivity() {

    //volvemos a usar View Binding
    private lateinit var binding: ActivityMessagesWindowBinding

    // Afegim actyalització Viewmodel: creem la instància a aquesta nova classe que fa d'intermediaria  entre la vista i el model

    private lateinit var viewModel: MissatgesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recuperamos los valores del intent
        val nickNameText = intent.getStringExtra("nickNameText")
        val serverAddressText = intent.getStringExtra("serverAddressText")

        //enlazamos con el textview que hemos definido
        val connectionInfoTextView : TextView = findViewById(R.id.connectionInfoTextView)

        //Configuramos mensaje
        connectionInfoTextView.text = "Conectado a $serverAddressText como $nickNameText"

    //Construcció RecyclerView

        //Afegit MVVM: instanciem el ViewModel mitjanjant el ViewModelProvider

        viewModel =
            ViewModelProvider(this)[MissatgesViewModel::class.java]

        //Establim el LayoutManager per al RecycleView

        binding.MissatgesRecyclerView.layoutManager= LinearLayoutManager(this)

        //Indiquem grandària fixa
        binding.MissatgesRecyclerView.setHasFixedSize(true)

        // Modificat MVVM : Ja no cal //Afegim adaptador

                //  binding.MissatgesRecyclerView.adapter = AdaptadorMissatges()

        //CALLBACK

      //Afegit MVVM:

        // Creeem un observador i el subscrivim al LiveData adaptador
        // definit al ViewModel. Així, quan es produisquen canvis al ViewModel,
        // es reflexaran en l'adaptador del RecyclerView

        viewModel.adaptador.observe(this) {
            binding.MissatgesRecyclerView.adapter = it
        }

        // Associem un escoltador d'esdeveniments al clic sobre
        // el boto d'accio flotant per afegir un element nou a la llista
        binding.sendMessage.setOnClickListener{

            val missatge : Missatge = binding.MessageText.text as Missatge

            viewModel.add(missatge)

            //Ja no cal avisar al adapatador

            //Smooth scroll a l'ultim element

            val index = Missatges.missatges.size - 1  //el index será el ultim element (ojo es una llista, comença per 0)

            binding.MissatgesRecyclerView.smoothScrollToPosition(index)

            //Netejem missatge
            binding.MessageText.text.clear()
        }

        //CALLBACK

        /*
        binding.sendMessage.setOnClickListener{

            //Obtencio del missatge del edittext. Ojo que el edittext retorna editable, no String
            val missatge : String = binding.MessageText.text.toString()

            //Obtenció del usuari
                //TODO

            //Afegim a la llista. Nickname text será no null perque ve del no null de la MainActivity
            Missatges.add(nickNameText!!,missatge) //A adaptar amb el altre usuari

            //Avisem al adaptador

            binding.MissatgesRecyclerView.adapter?.notifyItemInserted(Missatges.missatges.size - 1)

            //Smooth scroll a l'ultim element

            val index = Missatges.missatges.size - 1  //el index será el ultim element (ojo es una llista, comença per 0)

            binding.MissatgesRecyclerView.smoothScrollToPosition(index)

            //Netejem missatge
            binding.MessageText.text.clear()



        }*/


    }
}