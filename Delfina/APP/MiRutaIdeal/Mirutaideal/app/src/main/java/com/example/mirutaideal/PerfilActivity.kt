package com.example.mirutaideal


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import org.w3c.dom.Text
import java.util.Locale

class PerfilActivity : AppCompatActivity(){
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val returnButton: ImageButton = findViewById(R.id.return_button)
        returnButton.setOnClickListener {
            finish()
        }
        val rutas_creadas: TextView = findViewById(R.id.text_rutas_creadas)
        rutas_creadas.setOnClickListener {
            val intent = Intent(this, RutasCreadasActivity::class.java)
            startActivity(intent)
        }

        val idioma_text: TextView = findViewById(R.id.text_idioma)
        idioma_text.setOnClickListener {
            showLanguagePopupMenu()
        }
        val perfil_medico: TextView= findViewById(R.id.text_perfil_medico)
        perfil_medico.setOnClickListener {
            val intent = Intent(this, PerfilMedicoActivity::class.java)
            startActivity(intent)
        }
    }
    // Method to show the PopupMenu for language selection
    private fun showLanguagePopupMenu() {
        // Create a PopupMenu, attaching it to the view that was clicked
        val popupMenu = PopupMenu(this, findViewById(R.id.text_idioma))

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
    }
}