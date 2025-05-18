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



class MainActivity : AppCompatActivity() {

    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        // Initialize Retrofit interface once here
        apiInterface = RetrofitInstance.getInstance().create(ApiInterface::class.java)

        val emailEditText = findViewById<EditText>(R.id.EmailAddressEditText)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val inicioButton = findViewById<Button>(R.id.button_inicio)
        val textPrivacidad: TextView = findViewById(R.id.politica_privacidad_text_inicio)
        val textoRegistro: TextView = findViewById(R.id.text_registro)

        textPrivacidad.setOnClickListener {
            startActivity(Intent(this, PrivacyPolicyActivity::class.java))
        }

        textoRegistro.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        inicioButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(email, password)
            apiInterface.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if(response.isSuccessful) {
                        val loginResponse = response.body()
                        // TODO: Save token in SharedPreferences or similar
                        Toast.makeText(this@MainActivity, "Login exitoso", Toast.LENGTH_SHORT).show()

                        // Navigate to next screen
                        val intent = Intent(this@MainActivity, PantallaInicioActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
