package com.example.mirutaideal

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class SearchActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val mapButton: ImageButton = findViewById(R.id.icon_map_search)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        val homeButton: ImageButton= findViewById(R.id.icon_home_page_button)
        homeButton.setOnClickListener{
            val intent =Intent(this, PantallaInicioActivity::class.java)
            startActivity(intent)
        }
        val addButton: ImageButton= findViewById(R.id.icon_add_search)
        addButton.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout_search)
        val navView: NavigationView = findViewById(R.id.nav_view_search)
        val navViewPerfil: NavigationView = findViewById(R.id.nav_view_perfil_search)

        // Set up the ActionBarDrawerToggle (This links the hamburger icon with the drawer)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set up the NavigationView listener (to handle item selection)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ayuda -> {
                    val intent = Intent(this, AyudasActivity::class.java)
                    startActivity(intent)
                }

                R.id.rutas -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.donaciones -> {
                    val intent = Intent(this, DonacionesActivity::class.java)
                    startActivity(intent)
                }

                R.id.hospedaje -> {
                    val intent = Intent(this, HospedajeActivity::class.java)
                    startActivity(intent)
                }
                R.id.restaurantes ->{
                    val intent = Intent(this, Restaurante_Activity::class.java)
                    startActivity(intent)
                }

                R.id.configuracion ->  {
                    val intent = Intent(this, ConfiguracionActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true
        }

        // Set the onClickListener for the icon_menu button (opens the main drawer)
        val iconMenuButton: ImageButton = findViewById(R.id.icon_menu_search)
        iconMenuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        val caminoPrimitivoButton: Button = findViewById(R.id.button_camino_primitivo)
        caminoPrimitivoButton.setOnClickListener{
            val intent = Intent(this, CaminoPrimitivoActivity::class.java)
            startActivity(intent)
        }
        val caminoFrancesButton: Button = findViewById(R.id.button_camino_frances_adventure)
        caminoFrancesButton.setOnClickListener{
            val intent = Intent(this, CaminoFrancesActivity::class.java)
            startActivity(intent)
        }
        val caminoNorteButton: Button = findViewById(R.id.button_camino_del_norte)
        caminoNorteButton.setOnClickListener{
            val intent = Intent(this, CaminoNorteActivity::class.java)
            startActivity(intent)
        }


        // Set up the NavigationView listener for profile navigation items
        navViewPerfil.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ruta_creada -> {
                    val intent = Intent(this, RutasCreadasActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.END)
                }



                R.id.idioma -> {
                    showLanguagePopupMenu()
                }

                R.id.configuracion -> {
                    // Open PerfilActivity when "Configuración" is clicked
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.END)  // Close the profile drawer after selecting the item
                }
            }
            true
        }

        // Set the onClickListener for the icon_profile button (opens the profile drawer)
        val iconProfileButton: ImageButton = findViewById(R.id.icon_profile_search)
        iconProfileButton.setOnClickListener {
            // Open the profile drawer instead of the main one
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    // Method to show the PopupMenu for language selection
    private fun showLanguagePopupMenu() {
        // Create a PopupMenu, attaching it to the view that was clicked
        val popupMenu = PopupMenu(this, findViewById(R.id.idioma))

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