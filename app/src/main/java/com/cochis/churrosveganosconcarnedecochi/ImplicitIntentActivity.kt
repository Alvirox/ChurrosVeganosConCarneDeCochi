package com.cochis.churrosveganosconcarnedecochi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class ImplicitIntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent)

        agregarAccionAlBotonWeb()
        agregarAccionAlBotonEmail()
        agregarAccionAlBotonPhone()
        agregarAccionAlBotonCamera()

        //agregar soporte para que muestre el icono en la barra de acción
        supportActionBar!!.setIcon(R.mipmap.ic_launcher)
    }

    private fun agregarAccionAlBotonCamera() {
        //obtener el enlace al botonCamera
        val botonCamera = findViewById<ImageButton>(R.id.imageButtonCamera)
        //asignar la accion al boton
        botonCamera.setOnClickListener{
            //definir el intent para llamar a la camara
            val intentCamera= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //ejecutar el intent
            startActivity(intentCamera)
        }
    }

    private fun agregarAccionAlBotonPhone() {
        //obtener el enlace al botonPhone
        val botonPhone = findViewById<ImageButton>(R.id.imageButtonPhone)
        //asignar la accion al botonPhone para que intente llamar al telefono
        botonPhone.setOnClickListener {
            //obtener el número telefonico de la caja de texto
            val telefono = findViewById<EditText>(R.id.editTextPhone).text
            //validar que el número telefonico haya sido ingresado
            //de lo contrario enviar un mensaje
            if (telefono.isEmpty()) {
                Toast.makeText(
                    this,
                    "Favor de Ingresar un Número telefonico",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intentPhone = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$telefono"))
                startActivity(intentPhone)
            }
        }
    }

    private fun agregarAccionAlBotonEmail() {
        //obtener el enlace al botonEmail
        val botonCorreo = findViewById<Button>(R.id.buttonEmail)
        //asignar la accion al botonCorreo para que envie un mensaje por correo
        botonCorreo.setOnClickListener {
            //definir al remitente
            val email = "edgar.alvarez.valenzuela.2019@cetis108.edu.mx"
            //inicializar el intent con el remitente
            val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(email))
            //agregar el asunto del correo
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, "HOLA")
            //agregar el mensaje del correo
            intentEmail.putExtra(Intent.EXTRA_TEXT, "Responde por favor")
            //agregar el destinatario
            intentEmail.putExtra(Intent.EXTRA_EMAIL, "mordecaimordecai121314@gmail.com")
            //definir el tipo de contenido
            intentEmail.type = "text/plaint"
            //Ejecutar el intent
            startActivity(Intent.createChooser(intentEmail, "Enciar con..."))
        }
    }


    private fun agregarAccionAlBotonWeb() {
        //obtener el enlace al boton para enviar uri a web
        val botonWeb = findViewById<ImageButton>(R.id.imageButtonWeb)
        //asignar la accion al botonWeb para que llame al navegador
        botonWeb.setOnClickListener {
            // obtener el valor de la caja de texto de web
            val textoweb = findViewById<EditText>(R.id.editTextWeb).text
            //validar que no este vacia la caja de texto
            if (textoweb.isEmpty())
            //enviar mensaje de error, para que ingrese algo
                Toast.makeText(this, "Escribe algo", Toast.LENGTH_SHORT).show()
            else {
                //definir el intent inplicito
                val intenWeb = Intent()
                //definir la accion para el intent
                intenWeb.action = Intent.ACTION_VIEW
                //convertir texto web a uri y pasarla al data de intent
                intenWeb.data = Uri.parse("https://$textoweb")
                //llamar al intent
                startActivity(intenWeb)
            }
        }

    }
}