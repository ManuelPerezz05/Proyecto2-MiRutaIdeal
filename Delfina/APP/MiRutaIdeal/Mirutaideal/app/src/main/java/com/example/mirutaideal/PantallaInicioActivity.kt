package com.example.mirutaideal

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.widget.PopupMenu
import retrofit2.Call
import java.util.*

class PantallaInicioActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_inicio)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navViewPerfil: NavigationView = findViewById(R.id.nav_view_perfil)

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
        val iconMenuButton: ImageButton = findViewById(R.id.icon_menu)
        iconMenuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        val mapButton: ImageButton = findViewById(R.id.icon_map)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        val addButton: ImageButton= findViewById(R.id.icon_add)
        addButton.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        val estoyEnCaminoButton: Switch = findViewById(R.id.estoy_en_camino_button)
        estoyEnCaminoButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        val searchButton: ImageButton= findViewById(R.id.icon_route)
        searchButton.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        val comienzaRuta: Button=findViewById(R.id.button_comenzar_ruta)
        comienzaRuta.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        val retrofit = RetrofitInstance.getInstance()
        val api = retrofit.create(ApiInterface::class.java)

        val call = api.getRutas()
        call.enqueue(object : retrofit2.Callback<List<Ruta>> {
            override fun onResponse(
                call: Call<List<Ruta>>,
                response: retrofit2.Response<List<Ruta>>
            ) {
                if (response.isSuccessful) {
                    val listaRutas = response.body()
                    // TODO: mostrar la lista en un RecyclerView o Logcat
                    listaRutas?.forEach {
                        Log.d("Ruta", it.toString())
                    }
                } else {
                    Log.e("RutaApi", "Respuesta no exitosa: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Ruta>>, t: Throwable) {
                Log.e("RutaApi", "Error al conectar: ${t.localizedMessage}")
            }
        })

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
        val caminoPrimitivoButton: Button = findViewById(R.id.button_camino_primitivo_pantalla_inicio)
        caminoPrimitivoButton.setOnClickListener{
            val intent = Intent(this, CaminoPrimitivoActivity::class.java)
            startActivity(intent)
        }
        val caminoFrancesButton: Button = findViewById(R.id.button_camino_frances_pantalla_inicio)
        caminoFrancesButton.setOnClickListener{
            val intent = Intent(this, CaminoFrancesActivity::class.java)
            startActivity(intent)
        }
        val caminoNorteButton: Button = findViewById(R.id.button_camino_del_norte_pantalla_inicio)
        caminoNorteButton.setOnClickListener{
            val intent = Intent(this, CaminoNorteActivity::class.java)
            startActivity(intent)
        }

        val iconProfileButton: ImageButton = findViewById(R.id.icon_profile)
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