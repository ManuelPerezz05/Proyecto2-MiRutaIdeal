package com.example.mirutaideal

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.util.*

class DonacionesActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donaciones)

        setupNavigation()
    }

    private fun setupNavigation() {
        val mapButton: ImageButton = findViewById(R.id.icon_map_donaciones)
        val homeButton: ImageButton = findViewById(R.id.icon_home_page_button_donaciones)
        val searchButton: ImageButton = findViewById(R.id.icon_route_donaciones)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout_donaciones)
        val navView: NavigationView = findViewById(R.id.nav_view_donaciones)
        val navViewPerfil: NavigationView = findViewById(R.id.nav_view_perfil_donaciones)
        val iconMenuButton: ImageButton = findViewById(R.id.icon_menu_donaciones)
        val addButton: ImageButton = findViewById(R.id.icon_add_donaciones)
        val iconProfileButton: ImageButton = findViewById(R.id.icon_profile_donaciones)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mapButton.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }
        homeButton.setOnClickListener {
            startActivity(Intent(this, PantallaInicioActivity::class.java))
        }
        searchButton.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        addButton.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        iconMenuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        iconProfileButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ayuda -> startActivity(Intent(this, AyudasActivity::class.java))
                R.id.rutas -> startActivity(Intent(this, SearchActivity::class.java))
                R.id.donaciones -> drawerLayout.closeDrawer(GravityCompat.START)
                R.id.hospedaje -> startActivity(Intent(this, HospedajeActivity::class.java))
                R.id.restaurantes -> startActivity(Intent(this, Restaurante_Activity::class.java))
                R.id.configuracion -> startActivity(Intent(this, ConfiguracionActivity::class.java))
            }
            true
        }

        navViewPerfil.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ruta_creada -> {
                    val intent = Intent(this, RutasCreadasActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.END)
                }

                R.id.idioma -> showLanguagePopupMenu()
                R.id.configuracion -> startActivity(Intent(this, PerfilActivity::class.java))
            }
            true
        }
    }

    private fun showLanguagePopupMenu() {
        val popupMenu = PopupMenu(this, findViewById(R.id.idioma))
        popupMenu.menuInflater.inflate(R.menu.languages_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
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
        popupMenu.show()
    }

    private fun setLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return toggle.onOptionsItemSelected(item)
    }
}
