package com.example.mirutaideal

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class HospedajeActivity : AppCompatActivity() {
    lateinit var dateRangeText: TextView
    lateinit var calendar: ImageButton

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospedaje)

        // Map button navigation
        val mapButton: ImageButton = findViewById(R.id.icon_map_hospedaje)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        // Home button navigation
        val homeButton: ImageButton = findViewById(R.id.icon_home_page_button_hospedaje)
        homeButton.setOnClickListener {
            val intent = Intent(this, PantallaInicioActivity::class.java)
            startActivity(intent)
        }
        val searchButton: ImageButton= findViewById(R.id.icon_route_hospedaje)
        searchButton.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        val addButton: ImageButton= findViewById(R.id.icon_add_hospedaje)
        addButton.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        // Initialize date range text and calendar button
        dateRangeText = findViewById(R.id.date)
        calendar = findViewById(R.id.dropdown_date)

        val materialDatePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setSelection(androidx.core.util.Pair(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds()))
            .build()

        // Set the calendar button click listener
        calendar.setOnClickListener {
            // Show date picker when the calendar button is clicked
            materialDatePicker.show(supportFragmentManager, "tag_picker")
        }

        // Handle positive button click of the date picker
        materialDatePicker.addOnPositiveButtonClickListener { selection ->
            // selection is a Pair<Long, Long>
            val startDate = selection.first
            val endDate = selection.second
            dateRangeText.text = materialDatePicker.headerText
        }



        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout_hospedaje)
        val navView: NavigationView = findViewById(R.id.nav_view_hospedaje)
        val navViewPerfil: NavigationView = findViewById(R.id.nav_view_perfil_hospedaje)

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
                        val intent = Intent(this, SearchActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.donaciones -> {
                        val intent = Intent(this, DonacionesActivity::class.java)
                        startActivity(intent)
                    }

                    R.id.hospedaje -> {
                        drawerLayout.closeDrawer(GravityCompat.START)
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
            val buttonInfo1: Button = findViewById(R.id.button_mostrar_info_hotel1)
            buttonInfo1.setOnClickListener{
                val intent = Intent(this, DetallesHospedajeActivity::class.java)
                startActivity(intent)
            }
        val buttonInfo2: Button = findViewById(R.id.button_mostrar_info_hotel2)
        buttonInfo2.setOnClickListener{
            val intent = Intent(this, DetallesHospedajeActivity::class.java)
            startActivity(intent)
        }
        val buttonInfo3: Button = findViewById(R.id.button_mostrar_info_hotel3)
        buttonInfo1.setOnClickListener{
            val intent = Intent(this, DetallesHospedajeActivity::class.java)
            startActivity(intent)
        }
            // Set the onClickListener for the icon_menu button (opens the main drawer)
            val iconMenuButton: ImageButton = findViewById(R.id.icon_menu_hospedaje)
            iconMenuButton.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
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
        val iconProfileButton: ImageButton = findViewById(R.id.icon_profile_hospedaje)
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
