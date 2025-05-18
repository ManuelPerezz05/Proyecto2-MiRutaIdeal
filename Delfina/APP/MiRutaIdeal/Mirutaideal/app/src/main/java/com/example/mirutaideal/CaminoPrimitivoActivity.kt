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
import com.google.android.material.navigation.NavigationView
import java.util.Locale

class CaminoPrimitivoActivity: AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camino_primitivo)

        val mapButton: ImageButton = findViewById(R.id.icon_map_camino_primitivo)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        val homeButton: ImageButton = findViewById(R.id.icon_home_page_button_camino_primitivo)
        homeButton.setOnClickListener{
            val intent = Intent(this, PantallaInicioActivity::class.java)
            startActivity(intent)
        }
        val searchButton: ImageButton= findViewById(R.id.icon_route_camino_primitivo)
        searchButton.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout_camino_primitivo)
        val navView: NavigationView = findViewById(R.id.nav_view_camino_primitivo)
        val navViewPerfil: NavigationView = findViewById(R.id.nav_view_perfil_camino_primitivo)

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
        val iconMenuButton: ImageButton = findViewById(R.id.icon_menu_camino_primitivo)
        iconMenuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        val etapas:TextView=findViewById(R.id.etapas)
        etapas.setOnClickListener {
            val alertDialog: android.app.AlertDialog = android.app.AlertDialog.Builder(this)
                .setTitle("Etapas")
                .setMessage("ETAPA 1: Oviedo – San Juan de Villapañada (30,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7,5 h :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Esta primera etapa del Camino Primitivo transcurre, tras atravesar Oviedo, por paisaje rural por cómodas pistas y carreteritas secundarias que atraviesan suaves valles de pequeño desnivel, aunque con mucho suelo pavimentado. Eso sí, el final es fuerte.\n" +
                        "\n" +
                        "ETAPA 2: San Juan de Villapañada – Salas (20,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h :: Dificultad: Media\n" +
                        "\n" +
                        "Iniciamos la etapa con el pronunciado, aunque tranquilo, ascenso al Alto del Fresno para seguir en descenso en busca del valle del Narcea, entrando en Cornellana.\n" +
                        "\n" +
                        "Luego un cómodo y suave camino nos lleva por bosques y paisajes rurales de cultivo en suave ascenso hacia Salas, donde conviene descansar antes de afrontar la primera de las fuertes etapas que dan fama de duro a este Camino.\n" +
                        "\n" +
                        "ETAPA 3: Salas – Tineo (20,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h :: Dificultad: Media\n" +
                        "\n" +
                        "Comenzamos el día con el fuerte ascenso al alto de La Espina, más de 11 km de continua subida; eso si, por sendas y caminos forestales repletos de la tranquilidad y soledad que es lo que el peregrino necesita.\n" +
                        "\n" +
                        "Una vez pasada La Pereda el trazado nos lleva, siempre retirados del asfalto, por cómodos y bucólicos caminos carreteros hasta la localidad de Tineo y su estupendo albergue de peregrinos.\n" +
                        "\n" +
                        "Etapa 4: Tineo – Pola de Allande (28,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7 h :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Nuevamente conviene desayunar bien antes de salir, porque solo encontraremos pequeñas aldeas en el trayecto y porque esta es una etapa de montaña.\n" +
                        "\n" +
                        "Así, seguiremos combinando pistas y caminos ascendiendo al alto de Piedratecha y descendiendo lo subido hasta la mítica Obona.\n" +
                        "\n" +
                        "Luego seguiremos un buen tramo pisando el asfalto de la carretera hacia Borres, para continuar por caminos que atraviesan la montaña hacia La Mortera, Porciles y, finalmente Pola de Allande.\n" +
                        "\n" +
                        "ETAPA 5: Pola de Allande – La Mesa (21,9 km.)\n" +
                        "Tiempo estimado: 6 h :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "La de hoy es la segunda de las etapas fuertes de este Camino.\n" +
                        "\n" +
                        "Comenzamos con el duro ascenso al alto de El Palo, ocho km. de continua subida por caminos de tierra y mucha piedra (las bicicletas es mejor por carretera).\n" +
                        "\n" +
                        "Después de la subida viene la bajada, muy fuerte y con mucha piedra, hasta la aldea de Berducedo. Luego seguiremos por tranquilos y bucólicos caminos hacia Lago y La Mesa.\n" +
                        "\n" +
                        "ETAPA 6: La Mesa – Grandas de Salime (16,8 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 4,5 h :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Corta y bonita etapa la de hoy, aunque el perfil es rompedor.\n" +
                        "\n" +
                        "Desde La Mesa ascendemos por asfalto hasta el alto de Buspol. Ahora comienza un fuerte descenso de casi 700 metros por camino con mucha piedra suelta hacia el embalse de Salime, pero con un hermoso paisaje.\n" +
                        "\n" +
                        "Luego todo ascenso hasta Grandas de Salime, casi todo el tramo por carretera.\n" +
                        "\n" +
                        "ETAPA 7: Grandas de Salime – A Fonsagrada (25,2 km.)\n" +
                        "Tiempo estimado: 6,75 h :: Dificultad: Media\n" +
                        "\n" +
                        "Nos enfrentamos hoy al último gran ascenso de este Camino.\n" +
                        "\n" +
                        "Siete kilómetros suaves y otros cuatro de fuerte pendiente nos llevarán al alto del Acebo y también a tierras gallegas.\n" +
                        "\n" +
                        "Así pues dejaremos Asturias y descenderemos por terrenos rocosos hacia Fonfría y Paradanova.\n" +
                        "\n" +
                        "Un último repecho nos pone en A Fonsagrada y, un poco más allá en el albergue de A Fosagrada, una verdadera maravilla de acogida y bienvenida a Galicia.\n" +
                        "\n" +
                        "ETAPA 8: A Fonsagrada – O Cádavo Baleira (24,3 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6 h :: Dificultad: Media\n" +
                        "\n" +
                        "En la primera etapa netamente gallega se suaviza el perfil para atravesar suaves colinas con ambiente rural.\n" +
                        "\n" +
                        "Es impresionante ver las ruinas del antiguo hospital de peregrinos de Montouto, ubicado en un pedregoso alto desde el que descenderemos hasta A Degolada para ascender nuevamente hasta Fontaneira y dejarnos caer hacia la capital de la comarca, O Cádavo.\n" +
                        "\n" +
                        "\n" +
                        "ETAPA 9: O Cádavo Baleira – Lugo (29,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7,25 h :: Dificultad: Media\n" +
                        "\n" +
                        "Hoy también se nos terminan las colinas, un breve ascenso al inicio y su correspondiente descenso nos dejan en Castroverde.\n" +
                        "\n" +
                        "A partir de aquí encontraremos las típicas corredoiras gallegas que, atravesando robledales, hayedos y zonas rurales de cultivo y ganadería, nos acercan a la única ciudad que encontraremos desde Oviedo a Santiago, la romana Lucus Augusta.\n" +
                        "\n" +
                        "Etapa 10: Lugo – San Romao da Retorta (19,7 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h :: Dificultad: Media\n" +
                        "\n" +
                        "Una etapa prácticamente llana, totalmente aburrida y anodina, que transcurre casi en su totalidad por asfalto.\n" +
                        "\n" +
                        "El único aliciente que tiene es saber que bajo el asfalto que hoy pisamos se encuentran las piedras romanas de la Vía XIX que unía Lucus Augusta con Iria Flavia, y que antes que por nosotros fueron pisadas por sandalias romanas y por peregrinos medievales que se dirigían a la tumba del Apóstol.\n" +
                        "\n" +
                        "ETAPA 11: San Romao da Retorta – Melide (27,7 km)\n" +
                        "\n" +
                        "Tiempo estimado: 7 h :: Dificultad: Media\n" +
                        "\n" +
                        "La jornada de hoy comienza con variante, desde la iglesia de San Román da Retorta podemos seguir hacia Seixalbos (más asfalto) o por el trazado de la antigua calzada romana (ahora inexistente y todo por camino) hacia Burgo Negral.\n" +
                        "\n" +
                        "Ambas opciones coinciden nuevamente en Mosteiro. Seguiremos combinando caminos y carreteritas por pequeñas aldeas hasta el alto de Hospital das Seixas para descender hacia Melide y unirnos al Camino Francés.\n" +
                        "\n")
                .setNegativeButton(
                    "Cierra"
                ) { dialog, _ -> dialog.dismiss() }
                .create()
            alertDialog.show()
        }

        val verEnMapaButton: Button=findViewById(R.id.button_mostrar_mapa)
        verEnMapaButton.setOnClickListener{
            val intent = Intent(this, MapActivity::class.java)
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
        val addButton: ImageButton= findViewById(R.id.icon_add_camino_primitivo)
        addButton.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

            // Set the onClickListener for the icon_profile button (opens the profile drawer)
        val iconProfileButton: ImageButton = findViewById(R.id.icon_profile_camino_primitivo)
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
