package com.example.mirutaideal

import android.graphics.Color
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import com.tomtom.sdk.map.display.common.WidthByZoom

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.font.FontVariation.width
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.tomtom.sdk.location.GeoBoundingBox
import com.tomtom.sdk.location.GeoPoint
import com.tomtom.sdk.map.display.MapOptions
import com.tomtom.sdk.map.display.TomTomMap
import com.tomtom.sdk.map.display.camera.CameraOptions
import com.tomtom.sdk.map.display.style.StyleMode
import com.tomtom.sdk.map.display.ui.MapFragment
import com.tomtom.sdk.map.display.polyline.Polyline
import com.tomtom.sdk.map.display.polyline.PolylineOptions
import org.json.JSONArray
import java.util.Collections.addAll
import java.util.Locale

class MapActivity : AppCompatActivity() {

    private lateinit var tomtomMap: TomTomMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val homeButton: ImageButton = findViewById(R.id.icon_home_page_button)
        homeButton.setOnClickListener{
            val intent = Intent(this, PantallaInicioActivity::class.java)
            startActivity(intent)
        }
        val addButton: ImageButton = findViewById(R.id.icon_add_mapa)
        addButton.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        val ocultarLeyenda: TextView = findViewById(R.id.leyenda)
        ocultarLeyenda.setOnClickListener {
            val mapLegend:LinearLayout = findViewById(R.id.map_legend)
            mapLegend.visibility= View.GONE
        }

        val mostrarLeyenda: TextView = findViewById(R.id.leyenda_mostrar)
        mostrarLeyenda.setOnClickListener {
            val mapLegend:LinearLayout = findViewById(R.id.map_legend)
            mapLegend.visibility= View.VISIBLE
        }

        val apiKey = BuildConfig.TOMTOM_API_KEY
        val mapOptions = MapOptions(apiKey)

        val mapFragment = MapFragment.newInstance(mapOptions)
        supportFragmentManager.beginTransaction()
            .replace(R.id.map_container, mapFragment)
            .commit()

        mapFragment.getMapAsync { tomtomMap: TomTomMap ->
            this.tomtomMap = tomtomMap
            tomtomMap.setStyleMode(StyleMode.MAIN)
            tomtomMap.showHillShading()

            val santiago = GeoPoint(42.8782, -8.5448)
            val cameraOptions = CameraOptions(
                position = santiago,
                zoom = 10.0,
                tilt = 45.0,
                rotation = 90.0
            )
            tomtomMap.animateCamera(cameraOptions)

            // Load and draw route from JSON file
            drawRouteFromAsset("camino_frances.json", Color.BLUE)
            drawRouteFromAsset("camino_norte.json", Color.YELLOW)
            drawRouteFromAsset("camino_primitivo.json", Color.WHITE)
            setupNavigationMenus()
        }
        val drawerLayout: DrawerLayout=findViewById(R.id.drawerLayout)
        setupUIListeners(drawerLayout)
    }
    private fun drawRouteFromAsset(fileName: String, color: Int) {
        val inputStream = assets.open(fileName)
        val json = inputStream.bufferedReader().use { it.readText() }

        val jsonArray = JSONArray(json)
        val points = mutableListOf<GeoPoint>()

        for (i in 0 until jsonArray.length()) {
            val point = jsonArray.getJSONObject(i)
            val lat = point.getDouble("lat")
            val lon = point.getDouble("lon")
            points.add(GeoPoint(lat, lon))
        }

        if (points.isEmpty()) return

        val polylineOptions = PolylineOptions(
            coordinates = points,
            lineColor = color,
            lineWidths = listOf(WidthByZoom(6.0))
        )

        tomtomMap.addPolyline(polylineOptions)
    }




    // Function to read the route data from the JSON file in assets

    private fun setupNavigationMenus() {
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navViewPerfil: NavigationView = findViewById(R.id.nav_view_perfil)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)

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
                R.id.restaurantes -> {
                    val intent = Intent(this, Restaurante_Activity::class.java)
                    startActivity(intent)
                }
                R.id.configuracion -> {
                    val intent = Intent(this, ConfiguracionActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
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
                R.id.configuracion -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    drawerLayout.closeDrawer(GravityCompat.END)
                }
            }
            true
        }
    }

    private fun setupUIListeners(drawerLayout: DrawerLayout) {
        findViewById<ImageButton>(R.id.icon_menu_mapa).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        findViewById<ImageButton>(R.id.icon_profile_mapa).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        findViewById<ImageButton>(R.id.icon_home_page_button).setOnClickListener {
            val intent = Intent(this, PantallaInicioActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.icon_route_mapa).setOnClickListener {
            val intent= Intent(this, SearchActivity::class.java)
            startActivity(intent)
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
}

private fun PolylineOptions.color(blue: Int) {

}
