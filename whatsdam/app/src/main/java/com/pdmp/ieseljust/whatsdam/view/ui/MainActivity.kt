package com.pdmp.ieseljust.whatsdam.view.ui

import android.content.Intent
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ieseljust.pmdm.whatsdam.viewmodel.LoginViewModel

import com.pdmp.ieseljust.whatsdam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: LoginViewModel  //Añadido


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //instancia viewmodel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        //callback para boton conectar
        binding.buttonConnect.setOnClickListener{

            //enlazo con los edittext

            val nickNameText = binding.nickNameText.text.toString()
            val serverAddressText = binding.serverAddressText.text.toString()

            // Establecer un observador para la propiedad loginStatus del ViewModel
            /*viewModel.loginStatus.observe(this, { status ->
                when (status) {
                    viewModel.loginStatus == CONNECTING -> {
                        // Aquí puedes manejar el estado de "conectando"
                    }
                    loginStatus.CONNECTED -> {
                        // Aquí puedes manejar el estado de "conectado"
                    }
                    LoginStatus.ERROR -> {
                        // Aquí puedes manejar el estado de "error"
                    }
                }
*/

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