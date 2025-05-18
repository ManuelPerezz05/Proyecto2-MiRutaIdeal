package com.example.mirutaideal

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Geocoder
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.tomtom.quantity.Distance
import com.tomtom.sdk.location.DefaultLocationProviderFactory
import com.tomtom.sdk.location.GeoPoint
import com.tomtom.sdk.location.LocationProvider
import com.tomtom.sdk.location.LocationProviderConfig
import com.tomtom.sdk.location.OnLocationUpdateListener
import com.tomtom.sdk.map.display.MapOptions
import com.tomtom.sdk.map.display.TomTomMap
import com.tomtom.sdk.map.display.camera.CameraOptions
import com.tomtom.sdk.map.display.location.LocationMarkerOptions
import com.tomtom.sdk.map.display.route.RouteClickListener
import com.tomtom.sdk.map.display.style.StyleMode
import com.tomtom.sdk.map.display.ui.MapFragment
import com.tomtom.sdk.routing.RoutePlanner
import com.tomtom.sdk.routing.RoutePlanningCallback
import com.tomtom.sdk.routing.RoutePlanningResponse
import com.tomtom.sdk.routing.RoutingFailure
import com.tomtom.sdk.routing.online.OnlineRoutePlanner
import com.tomtom.sdk.routing.options.Itinerary
import com.tomtom.sdk.routing.options.RoutePlanningOptions
import com.tomtom.sdk.routing.options.calculation.CostModel
import com.tomtom.sdk.routing.options.calculation.RouteType
import com.tomtom.sdk.routing.options.guidance.GuidanceOptions
import com.tomtom.sdk.vehicle.Vehicle
import kotlinx.coroutines.Dispatchers
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds

class AddActivity : AppCompatActivity() {

    private var currentRouteDistanceKm: Double? = null
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navViewPerfil: NavigationView
    private lateinit var tomtomMap: TomTomMap
    private lateinit var routePlanner: RoutePlanner
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var locationProvider: LocationProvider
    private lateinit var mapFragment: MapFragment
    private var route: com.tomtom.sdk.map.display.route.Route? = null
    private lateinit var onLocationUpdateListener: OnLocationUpdateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_route)

        drawerLayout = findViewById(R.id.drawerLayout_ruta)
        navView = findViewById(R.id.nav_view_ruta)
        navViewPerfil = findViewById(R.id.nav_view_perfil_ruta)

        val apiKey = BuildConfig.TOMTOM_API_KEY
        val routeAPIKey = BuildConfig.TOMTOM_ROUTE_API_KEY

        val locationProviderConfig = LocationProviderConfig(
            minTimeInterval = 250L.milliseconds,
            minDistance = Distance.meters(20.0),
        )
        val defaultLocationProvider: LocationProvider = DefaultLocationProviderFactory.create(
            context = applicationContext,
            dispatcher = Dispatchers.Default,
            config = locationProviderConfig,
        )
        locationProvider = defaultLocationProvider

        routePlanner = OnlineRoutePlanner.create(applicationContext, routeAPIKey)
        val mapOptions = MapOptions(mapKey = apiKey)

        mapFragment = MapFragment.newInstance(mapOptions)
        supportFragmentManager.beginTransaction()
            .replace(R.id.add_map_container_ruta, mapFragment)
            .commit()

        mapFragment.getMapAsync { map ->
            tomtomMap = map
            tomtomMap.setStyleMode(StyleMode.MAIN)
            tomtomMap.showHillShading()
            tomtomMap.setLocationProvider(locationProvider)

            val locationMarkerOptions =
                LocationMarkerOptions(type = LocationMarkerOptions.Type.Pointer)
            tomtomMap.enableLocationMarker(locationMarkerOptions)

            // Set default location to Barcelona
            val barcelona = GeoPoint(41.3874, 2.1686)
            tomtomMap.moveCamera(CameraOptions(barcelona, zoom = 12.0))

            showUserLocation()
            setUpMapListeners()
        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        setupNavigationMenus()
        setupUIListeners()

        val mapButton: ImageButton = findViewById(R.id.icon_map_ruta)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        val homeButton: ImageButton = findViewById(R.id.icon_home_page_button_ruta)
        homeButton.setOnClickListener {
            val intent = Intent(this, PantallaInicioActivity::class.java)
            startActivity(intent)
        }
        val searchButton: ImageButton = findViewById(R.id.icon_route_ruta)
        searchButton.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

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
            true
        }


        val iconMenuButton: ImageButton = findViewById(R.id.icon_menu_ruta)
        iconMenuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }



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
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.END)  // Close the profile drawer after selecting the item
                }
            }
            true
        }
        val addButton: ImageButton = findViewById(R.id.button_add_route)
        addButton.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialogue_add_route, null)
            val editName = dialogView.findViewById<EditText>(R.id.editRouteName)
            val editDificultad = dialogView.findViewById<EditText>(R.id.editRouteDificultad)
            val editTipo = dialogView.findViewById<EditText>(R.id.editRouteTipo)
            val dialog = AlertDialog.Builder(this)
                .setTitle("Añade nueva ruta")
                .setView(dialogView)
                .setPositiveButton("Guardar") { _, _ ->

                    val name = editName.text.toString()
                    val dificultad = editDificultad.text.toString()
                    val tipo = editTipo.text.toString()


                    val newRuta = Ruta(
                        id = 0,
                        nombre = name,
                        descripcion = null,
                        distancia_km = currentRouteDistanceKm ?: 0.0,
                        altitud_maxima = null,
                        altitud_minima = null,
                        nivel_dificultad = dificultad,
                        tipo = tipo
                    )

                    val api = RetrofitInstance.getInstance().create(ApiInterface::class.java)
                    val call = api.addRuta(newRuta)
                    call.enqueue(object : retrofit2.Callback<Ruta> {
                        override fun onResponse(
                            call: retrofit2.Call<Ruta>,
                            response: retrofit2.Response<Ruta>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    this@AddActivity,
                                    "Ruta creada exitosamente!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // You can also finish() activity or refresh UI
                            } else {
                                Toast.makeText(
                                    this@AddActivity,
                                    "Error al crear ruta: ${response.code()}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<Ruta>, t: Throwable) {
                            Toast.makeText(
                                this@AddActivity,
                                "Error de red: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()

        }



        // Set the onClickListener for the icon_profile button (opens the profile drawer)
        val iconProfileButton: ImageButton = findViewById(R.id.icon_profile_ruta)
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

        recreate()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return toggle.onOptionsItemSelected(item)
    }




    private fun showUserLocation() {
        onLocationUpdateListener = OnLocationUpdateListener { location ->
            val locationMarker = LocationMarkerOptions(type = LocationMarkerOptions.Type.Pointer)
            tomtomMap.enableLocationMarker(locationMarker)

            // Optionally comment this out to keep the camera fixed on Barcelona initially
            tomtomMap.moveCamera(CameraOptions(location.position, zoom = 8.0))
        }
        locationProvider.addOnLocationUpdateListener(onLocationUpdateListener)
    }

    private fun setUpMapListeners() {
        tomtomMap.addRouteClickListener(RouteClickListener {
            route?.let {
                startNavigation(it)
            }
        })
    }

    private fun setupUIListeners() {
        findViewById<ImageButton>(R.id.icon_menu_ruta).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        findViewById<ImageButton>(R.id.icon_profile_ruta).setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

    }

    private fun geocodeAddressesAndCalculateRoute(startAddress: String, endAddress: String) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val startLocation = geocoder.getFromLocationName(startAddress, 1)?.firstOrNull()
        val endLocation = geocoder.getFromLocationName(endAddress, 1)?.firstOrNull()

        if (startLocation != null && endLocation != null) {
            val origin = GeoPoint(startLocation.latitude, startLocation.longitude)
            val destination = GeoPoint(endLocation.latitude, endLocation.longitude)

            calculateRoute(origin, destination)
        } else {
            Toast.makeText(this, "Unable to geocode one or both addresses", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateRoute(origin: GeoPoint, destination: GeoPoint) {
        val itinerary = Itinerary(origin = origin, destination = destination)

        val options = RoutePlanningOptions(
            itinerary = itinerary,
            costModel = CostModel(RouteType.Fast),
            vehicle = Vehicle.Car(),
            guidanceOptions = GuidanceOptions()
        )

        routePlanner.planRoute(options, object : RoutePlanningCallback {
            override fun onSuccess(result: RoutePlanningResponse) {

                result.routes.firstOrNull()?.let { plannedRoute ->
                    val mapRouteOptions = com.tomtom.sdk.map.display.route.RouteOptions(plannedRoute.geometry)
                    tomtomMap.clear()
                    route = tomtomMap.addRoute(mapRouteOptions)
                    tomtomMap.moveCamera(CameraOptions(origin, zoom = 10.0))
                    val distanceInKm = plannedRoute.summary.length.inMeters().div(1000.0)


                    // Save the distance in kilometers here (distance is usually in meters)
                    currentRouteDistanceKm = distanceInKm


                    Toast.makeText(this@AddActivity, "Distance: ${currentRouteDistanceKm} km", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(failure: RoutingFailure) {
                Toast.makeText(this@AddActivity, failure.message, Toast.LENGTH_SHORT).show()
            }

            override fun onRoutePlanned(route: com.tomtom.sdk.routing.route.Route) = Unit
        })
    }


    private fun startNavigation(route: com.tomtom.sdk.map.display.route.Route) {
        Toast.makeText(this, "Starting navigation...", Toast.LENGTH_SHORT).show()
        // TODO: Implement full navigation logic
    }

    private fun setupNavigationMenus() {
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        navViewPerfil.setNavigationItemSelectedListener {
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }
    }

    private fun ensureLocationPermissions(onGranted: () -> Unit) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            onGranted()
        } else {
            requestLocationPermission(onGranted)
        }
    }

    private fun requestLocationPermission(onGranted: () -> Unit) {
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                onGranted()
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    override fun onDestroy() {
        if (::tomtomMap.isInitialized) tomtomMap.setLocationProvider(null)
        if (::locationProvider.isInitialized) locationProvider.close()
        super.onDestroy()
    }
}