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

class CaminoNorteActivity: AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camino_norte)

        val mapButton: ImageButton = findViewById(R.id.icon_map_camino_norte)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        val homeButton: ImageButton = findViewById(R.id.icon_home_page_button_camino_norte)
        homeButton.setOnClickListener{
            val intent = Intent(this, PantallaInicioActivity::class.java)
            startActivity(intent)
        }
        val searchButton: ImageButton= findViewById(R.id.icon_route_camino_norte)
        searchButton.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout_camino_norte)
        val navView: NavigationView = findViewById(R.id.nav_view_camino_norte)
        val navViewPerfil: NavigationView = findViewById(R.id.nav_view_perfil_camino_norte)

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
        val iconMenuButton: ImageButton = findViewById(R.id.icon_menu_camino_norte)
        iconMenuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        val etapas:TextView=findViewById(R.id.etapas_camino_norte)
        etapas.setOnClickListener {
            val alertDialog: android.app.AlertDialog = android.app.AlertDialog.Builder(this)
                .setTitle("Etapas")
                .setMessage("Etapa 1: Irún – San Sebastián (27,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Esta etapa se puede dividir en dos partes: el camino por el Monte Jaizkibel hasta Pasajes de San Juan; y el camino por el Monte Ulía, que termina en San Sebastián.\n" +
                        "\n" +
                        "Una ciudad, final de nuestra etapa, que bien merece la pena un paseo para contemplar sus edificios, sus playas —especialmente la de La Concha—, el antiguo puerto pesquero, y disfrutar del ambiente de su casco antiguo.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 2: San Sebastián – Zarauz (20,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6 h. :: Dificultad: Media\n" +
                        "\n" +
                        "\n" +
                        "El Paseo de la Concha marca el inicio de esta etapa, y durante el camino se podrá disfrutar de maravillosas vistas del Cantábrico.\n" +
                        "\n" +
                        "Zarauz es un bonito y famoso pueblo surfero, en el que podemos aprovechar para visitar Santa María la Real con su templo gótico, la Torre y sus yacimientos arqueológicos.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 3: Zarautz – Deba (22 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "\n" +
                        "Una etapa con vistas hacia la costa, que cuenta con ascensos considerables aunque cortos.\n" +
                        "\n" +
                        "Se puede ir por el camino del paseo marítimo hasta Guetaria, o por el camino del monte pasando por la ermita de Santa Bárbara.\n" +
                        "\n" +
                        "Esta etapa está mayormente asfaltada, y tiene disponibilidad de varios establecimientos de restauración.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 4: Deba – Markina (24,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,5 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Esta es la etapa más solitaria de todo el Camino de Santiago del Norte. Apenas dispone de servicios a disposición de los caminantes.\n" +
                        "\n" +
                        "Es un duro y muy bello recorrido por la montaña y por valles rurales vascos, con caseríos, praderas…\n" +
                        "\n" +
                        "\n" +
                        "Etapa 5: Markina – Gernika (25,4 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Etapa montañosa con varios senderos, pistas de tierra, carreteras… Alejada del mar con frecuentes desniveles y algunos tramos que se embarran fácilmente con la lluvia. Hay una buena señalización.\n" +
                        "\n" +
                        "Guernica es un lugar conocido por el ataque aéreo de los alemanes en 1937, que Pablo Picasso plasmó en uno de su cuadros.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 6: Gernika – Lezama (19,8 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "\n" +
                        "Esta etapa del Camino de Santiago del Norte está alejada de la costa ya que se sigue en un terreno montañoso, con frecuentes subidas y bajadas.\n" +
                        "\n" +
                        "En Lezama podemos aprovechar para visitar la iglesia románica de Santa María.\n" +
                        "\n" +
                        "Hay que tener en cuenta que los albergues de Lezama sólo abren en temporada alta.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 7: Lezama – Bilbao (11 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 3,25 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Esta etapa del camino es corta y puede alargarse hasta Portugalete.\n" +
                        "\n" +
                        "Un día más se sigue lejos de mar, y tras pasar el Monte Avril, se llega a Bilbao, la ciudad más poblada del Camino del Norte.\n" +
                        "\n" +
                        "A la entrada de Bilbao se llega la basílica de Nuestra Señora de Begoña y seguidamente se llega a Las Siete Calles en el Casco Viejo, donde se encuentra la catedral de la ciudad.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 8: Bilbao – Portugalete (19,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 4,75 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Hay dos caminos para realizar esta etapa, que son el tradicional o el moderno.\n" +
                        "\n" +
                        "El camino tradicional tiene subidas y bajadas y es un recorrido más largo; y el camino moderno sale de Bilbao y recorre el barrio de Castrexana pasando por Cruces, Baracaldo y Sestao hasta llegar a Portugalete, famosa por su puente colgante.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 9: Portugalete – Castro Urdiales (25,4 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Esta etapa del camino nos lleva hacia La Arena, una de las playas más turísticas del norte y a su vez, nos encontramos con el mar Cantábrico.\n" +
                        "\n" +
                        "Hay un buen tramo de vía verde y nos introducimos en la la Comunidad de Cantabria.\n" +
                        "\n" +
                        "Para llegar a Castro Urdiales hay dos rutas: el camino oficial (7,2 km más largo) y el camino por la carretera, en el que los caminantes tienen que tener precaución.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 10: Castro Urdiales – Laredo (25,7 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "En esta etapa del camino hay bonitos paisajes costeros con acantilados, playas y calas, bosques, valles… Una etapa para disfrutar de los paisajes.\n" +
                        "\n" +
                        "Nos encontraremos con desniveles moderados y, como la etapa de ayer, hay dos rutas posibles: puedes hacer el camino oficial por Rioseco o la ruta alternativa, en la que se ahorra 3,5 km.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 11: Laredo – Güemes (28,7 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7,25 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Es una etapa bien señalizada.\n" +
                        "\n" +
                        "En invierno sólo se puede ir por Colindres, ya que no funciona el servicio de barca para ir desde Laredo hasta Santoña.\n" +
                        "\n" +
                        "Además, durante el camino se pasa por dos largas playas, que son la de Berria y la de Noja.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 12: Güemes – Santander (15,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 3,75 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "\n" +
                        "Esta es una etapa del camino bastante corta y sin desniveles.\n" +
                        "\n" +
                        "Es recomendable tomar la ruta costera por los maravillosos paisajes con acantilados.\n" +
                        "\n" +
                        "Y nuevamente tenemos un final de etapa en una bonita ciudad costera, Santander, famosa y conocida, que bien merece un reposado paseo, así como visitar su catedral.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 13: Santander – Santillana del Mar (36,3 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 9,25 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Etapa del camino larga y casi en su totalidad asfaltada.\n" +
                        "\n" +
                        "Hay pistas rurales con desniveles moderados y además, esta etapa finaliza en Santillana del Mar, que es una de las villas con más monumentos de la costa cantábrica.\n" +
                        "\n" +
                        "Tiene una vez más el camino oficial o la alternativa que pasa por la ría de San Martín de la Arena.\n" +
                        "\n" +
                        "Según la ruta que se escoja esta etapa tendrá 37 km o 42,5 km.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 14: Santillana del Mar – Comillas (22,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Se trata de una etapa tranquila, alejada del mar y con mucha carretera, y un kilometraje moderado.\n" +
                        "\n" +
                        "Comillas, punto final de esta etapa, es una localidad costera con varios referentes modernistas, como el capricho de Gaudí, la fachada del cementerio o la Universidad Pontificia, por lo que bien merece la pena aprovechar para conocerlos.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 15: Comillas – Colombres (28 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7 h. :: Dificultad: Media\n" +
                        "\n" +
                        "\n" +
                        "Esta es una etapa del camino muy rural con varios desniveles ya que poco a poco nos acercamos a la montaña asturiana.\n" +
                        "\n" +
                        "Hay pocos servicios durante el trayecto, por lo que hay que ir convenientemente aprovisionado.\n" +
                        "\n" +
                        "En Colombres nos encontraremos con unas maravillosas vistas.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 16: Colombres – Llanes (22,9 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Es una etapa del camino cómoda con bonitos paisajes en la que recomendamos la senda costera.\n" +
                        "\n" +
                        "Llanes es una villa medieval con un bonito casco antiguo, una bonita costa, una bonita sierra, el Faro…\n" +
                        "\n" +
                        "\n" +
                        "Etapa 17: Llanes – Ribadesella (29,7 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Esta etapa del camino es larga y con desniveles moderados.\n" +
                        "\n" +
                        "Hay unas playas preciosas y zonas rurales con poca presión turística.\n" +
                        "\n" +
                        "Ribadesella es una villa que fue ballenera, y hoy en día es marinera, en donde se encuentra la desembocadura del río Sella, y tiene un bonito casco antiguo, edificaciones modernistas y grandes murales que narran la historia del pueblo.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 18: Ribadesella – Sebrayo (31,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "\n" +
                        "Aparte de esta ruta hay dos más alternativas que pasan por Colunga o por Villaviciosa.\n" +
                        "\n" +
                        "Sin embargo, la etapa desde Ribadesella hasta Sebrayo es larga y montañosa, una parte costera y otra parte más rural.\n" +
                        "\n" +
                        "Sebrayo es una pequeña aldea con unos 40 habitantes que no tiene servicios, algo a tener en cuenta y tomar la precaución de aprovisionarnos antes de llegar allí..\n" +
                        "\n" +
                        "\n" +
                        "Etapa 19: Sebrayo – Gijón (36 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 8 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Hasta el momento, esta es la etapa más dura por su longitud y por los grandes ascensos que tiene.\n" +
                        "\n" +
                        "Generalmente tiene una buena señalización y tiene varios desvíos como el desvío hacia el Camino Primitivo.\n" +
                        "\n" +
                        "Gijón es una ciudad de Asturias (capital: Oviedo) en la que destaca su universidad, la Universidad Laboral; el mirador de la torre, su costa y sus montañas, el jardín botánico Atlántico o el Acuario, que tiene una arquitectura muy llamativa.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 20: Gijón – Avilés (26,7 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6,5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Es una etapa sencilla y relativamente corta que enlaza dos ciudades muy potentes en la industria siderúrgica asturiana.\n" +
                        "\n" +
                        "En Avilés podemos disfrutar de su casco antiguo, muy cuidado últimamente, del que dicen que es el más bonito de Asturias, y el barrio de Sabugo.\n" +
                        "\n" +
                        "Sin olvidar, por supuesto, el Centro Niemeyer.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 21: Avilés – Muros de Nalón (23,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,75 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Etapa corta con mucho asfalto y alternancia de desniveles moderados.\n" +
                        "\n" +
                        "La etapa transcurre por el interior peninsular hasta llegar a la localidad costero de Salinas.\n" +
                        "\n" +
                        "Lo más destacado de esta jornada es el Castillo de San Martín, aunque no se puede visitar.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 22: Muros de Nalón – Soto de Luiña (15,3 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 3,75 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Etapa corta con subidas y bajadas frecuentes, que transcurre generalmente por el interior peninsular.\n" +
                        "\n" +
                        "Hay una buena señalización y numerosos servicios en localidades como El Peñedo.\n" +
                        "\n" +
                        "Esta etapa finaliza en Soto de Luiña, población pequeña que tiene una iglesia, la iglesia de Santa María y una Casa Rectoral.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 23: Soto de Luiña – Cadavedo (18,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Etapa bien señalizada para el peregrino que transcurre por montaña con mucho más desnivel y sin servicios para los peregrinos.\n" +
                        "\n" +
                        "En Cadavedo podemos destacar una torre de la Baja Edad Media, que es un claro ejemplo de una arquitectura defensiva.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 24: Cadavedo – Luarca (15,3 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 3,75 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Etapa cómoda y corta, y al llegar a Luarca podremos disfrutar de paisajes muy bonitos.\n" +
                        "\n" +
                        "Además, en Luarca se encuentra el Museo del Calamar, también hay un planetario, y son famosos los Jardines del Chano.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 25: Luarca – La Caridad (29,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Etapa del camino un poco larga con subidas y bajadas no muy pronunciadas.\n" +
                        "\n" +
                        "No nos encontramos con la costa en esta etapa, y hay disponibles servicios variados para la comodidad de los caminantes en la mayoría de las localidades.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 26: La Caridad – Ribadeo (21,3 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,5 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Es una etapa del camino corta y llana, con escasos servicios para los caminantes.\n" +
                        "\n" +
                        "Al llegar a Ribadeo se puede disfrutar de su casco histórico que tiene varios edificios arquitectónicos como el Castillo de San Damián, la Torre dos Morenos…\n" +
                        "\n" +
                        "También destaca el Convento de Santa Clara.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 27: Ribadeo – Lourenzá (28,4 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Desde esta etapa, el camino ya va tomando dirección hacia el suroeste, es decir, hacia Santiago de Compostela.\n" +
                        "\n" +
                        "A partir de esta etapa dejamos la zona de costa y el Camino se centra más en la zona montañosa del norte gallego, donde predominan las zonas rurales y ganaderas.\n" +
                        "\n" +
                        "Las subidas y bajadas son moderadas para el peregrino.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 28: Lourenzá – Gontán (23 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Se trata de una jornada intermedia que no es muy larga.\n" +
                        "\n" +
                        "Tiene subidas y bajadas no muy pronunciadas, está bien señalizada y cuenta con diversos servicios para los peregrinos a lo largo del recorrido.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 29: Gontán – Vilalba (23 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,75 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Es una etapa del camino cómoda, con paisajes bonitos y con pocas subidas y bajadas.\n" +
                        "\n" +
                        "Hay alternancia de camino de tierra y asfalto durante el recorrido.\n" +
                        "\n" +
                        "De Vilalba se puede destacar las dotes culinarias, sobre todo para la preparación de la carne.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 30: Vilalba – Baamonde (18,6 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 4,5 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Etapa del camino corta parecida a la anterior, con bonitos paisajes y subidas y bajadas no muy pronunciadas.\n" +
                        "\n" +
                        "Hay distintos servicios durante el camino para los caminantes.\n" +
                        "\n" +
                        "Baamonde pertenece a Begonte, y en esta localidad se ofrecen todos los servicios necesarios.\n" +
                        "\n" +
                        "Además también se puede visitar la iglesia de Santiago y la casa del famoso escultor Víctor Corral.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 31: Baamonde – Sobrado dos Monxes (39,8 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 10 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Etapa muy larga que se puede dividir en dos jornadas, ya que durante el camino hay distintos albergues para descansar. También cuenta con una variante que acorta el recorrido en unos 5 km.\n" +
                        "\n" +
                        "Hay subidas y bajadas, aunque no muy importantes, y existe una alternancia de pista de asfalto y camino de tierra.\n" +
                        "\n" +
                        "Es un camino solitario, con pocos servicios aunque con numerosas parroquias muy hospitalarias.\n" +
                        "\n" +
                        "Sobrado destaca por el Monasterio de Santa María, que sigue habitado por monjes benedictinos.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 32: Sobrado dos Monxes – Arzúa (19 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 10 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Esta etapa es la última del Camino del Norte, ya que las dos etapas que nos quedan para llegar a Santiago forman parte del Camino Francés.\n" +
                        "\n" +
                        "Es una etapa corta y con poca dificultad, que alterna las carreteras y los caminos de tierra.\n" +
                        "\n" +
                        "En Arzúa enlazamos el Camino de Santiago del Norte con el Camino Francés, en el que se notará gran diferencia porque el Camino Francés es mucho más concurrido (al igual que el Camino Primitivo).\n" +
                        "\n" +
                        "\n" +
                        "Etapa Arzúa – Monte do Gozo – Camino Francés (34,1 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 8 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Entre Arzúa y la catedral de Santiago median casi 40 kilómetros y aunque lo más lógico y prudente sería dividir el tramo en dos jornadas haciendo parada y fonda en Santa Irene u O Pedrouzo, elegimos continuar hasta el Monte del Gozo.\n" +
                        "\n" +
                        "Arzúa cede el testigo a O Pino en un trayecto cómodo, con pendientes más fáciles y por pistas siempre cercanas a la carretera N-547.\n" +
                        "\n" +
                        "Sobre pistas de hojarasca, entre las últimas manchas de eucaliptos y robles, en una larga caminata llegamos al Monte do Gozo.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa Monte do Gozo – Santiago de Compostela – Camino Francés (4,1 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 1 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Más que una etapa, un simple paseo, después de haber llegado a las puerta de Santiago, con tiempo suficiente para llegar a la Misa del Peregrino y tener un día completo para disfrutar de todo lo que Santiago de Compostela ofrece, tanto a los turistas como al peregrino. En este sentido, hay que recordar que la mayoría de albergues en Santiago permiten que el peregrino pernocte en el mismo más de una noche.")
                .setNegativeButton(
                    "Cierra"
                ) { dialog, _ -> dialog.dismiss() }
                .create()
            alertDialog.show()
        }

        val verEnMapaButton: Button=findViewById(R.id.button_mostrar_mapa_camino_norte)
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
        val addButton: ImageButton= findViewById(R.id.icon_add_camino_norte)
        addButton.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

            // Set the onClickListener for the icon_profile button (opens the profile drawer)
        val iconProfileButton: ImageButton = findViewById(R.id.icon_profile_camino_norte)
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
