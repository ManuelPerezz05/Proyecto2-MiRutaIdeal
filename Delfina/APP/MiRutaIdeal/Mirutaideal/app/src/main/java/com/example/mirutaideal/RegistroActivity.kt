package com.example.mirutaideal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroActivity : AppCompatActivity() {

    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        apiInterface = RetrofitInstance.getInstance().create(ApiInterface::class.java)

        val emailEditText = findViewById<EditText>(R.id.EmailAddressEditText)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val registroButton = findViewById<Button>(R.id.button_registro)
        val textPrivacidad: TextView = findViewById(R.id.politica_privacidad_text)

        textPrivacidad.setOnClickListener {
            startActivity(Intent(this, PrivacyPolicyActivity::class.java))
        }

        registroButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val registerRequest = RegisterRequest(email, password,)

            apiInterface.registerUser(registerRequest).enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegistroActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()

                        // Optionally go back to login screen
                        val intent = Intent(this@RegistroActivity, PantallaInicioActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@RegistroActivity, "Error en el registro", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(this@RegistroActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
