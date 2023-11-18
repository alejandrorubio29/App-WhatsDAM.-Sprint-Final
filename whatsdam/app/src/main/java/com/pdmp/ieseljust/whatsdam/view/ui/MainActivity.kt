package com.pdmp.ieseljust.whatsdam.view.ui

import android.content.Intent
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.pdmp.ieseljust.whatsdam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //callback para boton conectar
        binding.buttonConnect.setOnClickListener{

            //enlazo con los edittext

            val nickNameText = binding.nickNameText.text.toString()
            val serverAddressText = binding.serverAddressText.text.toString()

            //Si nickname no vacío y IP válida

            if((nickNameText.isNotEmpty())&&(Patterns.IP_ADDRESS.matcher(serverAddressText).matches())){ //Nivel de API insuficiente para trabajar con isNumericAdress

                //Intent: inicia actividad MessagesWindows

                val intent = Intent(baseContext, MessagesWindow::class.java) //creamos el intent para la nueva clase indicando su nombre, en este caso la ventana de conexion exitosa

                //pasamos las variables a la nueva clase
                intent.putExtra("nickNameText", nickNameText)
                intent.putExtra("serverAddressText", serverAddressText)

                //iniciamos la actividad
                startActivity(intent)

            }

        }


    }




}