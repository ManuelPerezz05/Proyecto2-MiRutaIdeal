package com.example.mirutaideal

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class ConfiguracionActivity : AppCompatActivity(){
    lateinit var toggle: ActionBarDrawerToggle
    @SuppressLint("UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)

        val returnButton: ImageButton = findViewById(R.id.return_button_configuracion)
        returnButton.setOnClickListener {
            finish()
        }
        val idioma_text: TextView = findViewById(R.id.idioma_configuracion)
        idioma_text.setOnClickListener {
            showLanguagePopupMenu()
        }
        val privacyButton : TextView= findViewById(R.id.privacidad)
        privacyButton.setOnClickListener {
            val intent = Intent(this, PrivacyPolicyActivity::class.java)
            startActivity(intent)
        }
        val infoPerfil: TextView = findViewById(R.id.info_perfil)
        infoPerfil.setOnClickListener {
            val intent= Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
        val textSoporte: TextView= findViewById(R.id.soporte_tecnico)
        textSoporte.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:soporte@example.com") // Replace with your support email
                putExtra(Intent.EXTRA_SUBJECT, "Consulta de soporte")
                putExtra(Intent.EXTRA_TEXT, "Hola, necesito ayuda con...")
            }
            startActivity(intent)
        }
    }
    // Method to show the PopupMenu for language selection
    private fun showLanguagePopupMenu() {
        // Create a PopupMenu, attaching it to the view that was clicked
        val popupMenu = PopupMenu(this, findViewById(R.id.idioma_configuracion))

        // Inflate the menu with language options
        popupMenu.menuInflater.inflate(R.menu.languages_menu, popupMenu.menu)

        // Set a listener for item selection
        popupMenu.setOnMenuItemClickListener { menuItem ->
            // Handle the language selection
            when (menuItem.itemId) {
                R.id.lang_english -> {
                    setLanguage("en")
                    true
                }

                R.id.lang_spanish -> {
                    setLanguage("es")
                    true
                }

                else -> false
            }
        }

        // Show the PopupMenu
        popupMenu.show()
    }

    // Method to change the language
    private fun setLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)

        // Optionally, restart the activity to apply changes
        recreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Toggle the drawer when the hamburger icon is clicked in the toolbar
        return toggle.onOptionsItemSelected(item)
    }}