package com.example.mirutaideal

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class RutasCreadasActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    private var rutasList: List<Ruta>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutas_creadas)

        val mapButton: ImageButton = findViewById(R.id.icon_map_rutas_creadas)
        mapButton.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

        val homeButton: ImageButton = findViewById(R.id.icon_home_page_button_rutas_creadas)
        homeButton.setOnClickListener {
            startActivity(Intent(this, PantallaInicioActivity::class.java))
        }

        val searchButton: ImageButton = findViewById(R.id.icon_route_rutas_creadas)
        searchButton.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        val addRouteText: TextView = findViewById(R.id.link_create_route)
        addRouteText.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        val recyclerView: RecyclerView = findViewById(R.id.recycler_routes)
        val emptyCard: CardView = findViewById(R.id.card_empty_routes)




        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout_rutas_creadas)
        val navView: NavigationView = findViewById(R.id.nav_view_rutas_creadas)
        val navViewPerfil: NavigationView = findViewById(R.id.nav_view_perfil_rutas_creadas)

        // Setup ActionBarDrawerToggle (hamburger icon)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Navigation drawer menu item clicks
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ayuda -> startActivity(Intent(this, AyudasActivity::class.java))
                R.id.rutas -> startActivity(Intent(this, SearchActivity::class.java))
                R.id.donaciones -> startActivity(Intent(this, DonacionesActivity::class.java))
                R.id.hospedaje -> startActivity(Intent(this, HospedajeActivity::class.java))
                R.id.restaurantes -> startActivity(Intent(this, Restaurante_Activity::class.java))
                R.id.configuracion -> startActivity(Intent(this, ConfiguracionActivity::class.java))
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Open main drawer on hamburger menu click
        val iconMenuButton: ImageButton = findViewById(R.id.icon_menu_rutas_creadas)
        iconMenuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Open profile drawer on profile icon click
        val iconProfileButton: ImageButton = findViewById(R.id.icon_profile_rutas_creadas)
        iconProfileButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        val addButton: ImageButton = findViewById(R.id.icon_add_rutas_creadas)
        addButton.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        navViewPerfil.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ruta_creada -> {
                    // Handle ruta_creada item click if needed
                }
                R.id.idioma -> showLanguagePopupMenu()
                R.id.configuracion -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.END)
                }
            }
            true
        }

        // Retrofit API call to get rutas
        val api = RetrofitInstance.getInstance().create(ApiInterface::class.java)
        val call = api.getRutas()


        call.enqueue(object : retrofit2.Callback<List<Ruta>> {
            override fun onResponse(
                call: retrofit2.Call<List<Ruta>>,
                response: retrofit2.Response<List<Ruta>>
            ) {
                rutasList = response.body()
                if (rutasList != null && rutasList!!.isNotEmpty()) {
                    emptyCard.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    recyclerView.adapter = RutaAdapter(rutasList!!)
                } else {
                    // Show empty card if no rutas
                    emptyCard.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }

            }

            override fun onFailure(call: retrofit2.Call<List<Ruta>>, t: Throwable) {
                Toast.makeText(
                    this@RutasCreadasActivity,
                    "Error de red: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    // Popup menu for language selection
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
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }
}
