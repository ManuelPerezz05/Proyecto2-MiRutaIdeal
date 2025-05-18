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

class CaminoFrancesActivity: AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camino_frances)

        val mapButton: ImageButton = findViewById(R.id.icon_map_camino_frances)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
        val homeButton: ImageButton = findViewById(R.id.icon_home_page_button_camino_frances)
        homeButton.setOnClickListener{
            val intent = Intent(this, PantallaInicioActivity::class.java)
            startActivity(intent)
        }
        val searchButton: ImageButton= findViewById(R.id.icon_route_camino_frances)
        searchButton.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout_camino_frances)
        val navView: NavigationView = findViewById(R.id.nav_view_camino_frances)
        val navViewPerfil: NavigationView = findViewById(R.id.nav_view_perfil_camino_frances)

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
        val iconMenuButton: ImageButton = findViewById(R.id.icon_menu_camino_frances)
        iconMenuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        val etapas:TextView=findViewById(R.id.etapas_camino_frances)
        etapas.setOnClickListener {
            val alertDialog: android.app.AlertDialog = android.app.AlertDialog.Builder(this)
                .setTitle("Etapas")
                .setMessage("Etapa 1: Saint-Jean-Pied-de-Port – Roncesvalles (24,7 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7 h. :: Dificultad: Alta\n" +
                        "\n" +
                        "Saint-Jean-Pied-de-Port se ha convertido en el punto de partida preferido por muchos peregrinos para iniciar su caminar por el Camino de Santiago Francés.\n" +
                        "\n" +
                        "También conocida como «Ruta de Napoleón», Es una etapa muy larga y dura, que reta al peregrino con un desnivel de más de 1.250 metros, pero le recompensa con unos paisajes atlánticos excepcionales y el atractivo de traspasar a pie la frontera entre Francia y España.\n" +
                        "\n" +
                        "\n" +
                        "En invierno es obligatorio y más que aconsejable, seguir la variante por Valcarlos, para evitar las inclemencias meteorológicas y, especialmente, la niebla y la nieve.\n" +
                        "\n" +
                        "Etapa 2: Roncesvalles – Zubiri (21,5 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Desde Roncesvalles hasta Espinal —con Burguete en medio—, el perfil es llano y alterna tramos de bosques con extensas llanuras y praderas.\n" +
                        "\n" +
                        "Llegamos al valle de Erro, que hace gala de bonitos hayedos y robledales y de una orografía cambiante.\n" +
                        "\n" +
                        "Se sube Mezkiritz y se desciende hasta Bizkarreta y Lintzoain para afrontar la dura subida al puerto de Erro, para finalmente, en un vertiginoso descenso entre bojes y pinos, llegar a Zubiri, en valle de Esteribar.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 3: Zubiri – Pamplona (20,4 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 4,5 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Como en el juego de la Oca, de puente a puente y tiro porque me lleva la corriente.\n" +
                        "\n" +
                        "En esta etapa atravesamos el puente de la Rabia en Zubiri, de los Bandidos en Larrasoaña, el de Iturgaiz en Irotz y de la Magdalena en Pamplona.\n" +
                        "\n" +
                        "Todos ellos sobre el río Arga, que riega el valle de Esteribar y nos conduce hasta Pamplona, la primera ciudad del Camino.\n" +
                        "\n" +
                        "Etapa 4: Pamplona – Puente la Reina (24 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Una vez atravesada Pamplona, el Camino se interna en la Cendea de Cizur entre cultivos de cereal.\n" +
                        "\n" +
                        "Alcanza las alturas del más que famoso Alto del Perdón, una barrera natural entre la Cuenca de Pamplona y Valdizarbe.\n" +
                        "\n" +
                        "En el descenso, tras Uterga y Muruzábal llegamos a Obanos, donde ya se unen los peregrinos venidos desde el Somport por el Camino Aragonés, a escasa distancia de Puente la Reina, población indisoluble del peregrinaje a Santiago.\n" +
                        "\n" +
                        "Etapa 5: Puente la Reina – Estella (22,6 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Un amanecer rojizo sobre la calle empedrada, el puente medieval que sobrevive a los envites del río, paisajes de cereal y viñedos.\n" +
                        "\n" +
                        "Una senda cargada de peregrinos que avanza hacia de un pueblo medieval a otro, una calzada romana, un río de agua salada y un santo benefactor.\n" +
                        "\n" +
                        "Todas éstas son postales que regala el Camino a quien recorre esta etapa, en la que el espíritu del Camino es total y se siente en cada lugar atravesado, en cada paso dado.\n" +
                        "\n" +
                        "Etapa 6: Estella – Los Arcos (21,8 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Saliendo de Estella, ante los muros del Monasterio de Irache una fuente de vino nos ofrece ayuda antes de adentrarnos en el sinuoso tramo que conduce a Villamayor de Monjardín.\n" +
                        "\n" +
                        "A partir de aquí, y hasta Los Arcos, los viñedos y olivares son los únicos aliados del peregrino.\n" +
                        "\n" +
                        "Mudos testigos y compañía en un largo tramo, que evitan caer en los soliloquios producidos por las pistas de concentración y la ausencia de poblaciones intermedias.\n" +
                        "\n" +
                        "Etapa 7: Los Arcos – Logroño (28 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,25 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Una larga etapa que nos conduce hasta Logroño.\n" +
                        "\n" +
                        "Pero antes el Camino embauca al peregrino a desplazarse entre barrancos hasta Viana, ciudad monumental en la que merece la pena parar y hacer un breve recorrido.\n" +
                        "\n" +
                        "Se despide Navarra, recorrida de norte a sur y de este a oeste durante 142 kilómetros, y se nos presenta La Rioja.\n" +
                        "\n" +
                        "Un final largo y monótono, que hará que disfrutemos más de todo lo que nos ofrece la capital de la Rioja.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 8: Logroño – Nájera (30,4 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6,5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Una larga y monótona salida entre viviendas y zonas industriales nos lleva hasta el parque de San Miguel y el entorno natural del embalse de la Grajera, cerrando así la estancia en Logroño.\n" +
                        "\n" +
                        "Tras la capital de La Rioja nos aparece el suave paisaje riojano, plagado de viñas y árboles frutales.\n" +
                        "\n" +
                        "Navarrete, pueblo de gran tradición alfarera, y más adelante Ventosa y el alto de San Antón, dan paso al valle del río Najerilla, donde se asienta Nájera, lugar codiciado durante la reconquista, dominada por los cerros del Castillo y Malpica.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 9: Nájera – Santo Domingo de la Calzada (21 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Una etapa que tiene como meta la legendaria ciudad de Santo Domingo de la Calzada, población de La Rioja Alta donde cuenta la leyenda que se obró el milagro de la gallina que cantó después de asada.\n" +
                        "\n" +
                        "El Camino atraviesa un par de poblaciones: la acogedora Azofra y Cirueña, afeada por una moderna urbanización, y un campo de golf que desentona en el paisaje circundante de campos de cereal que van sustituyendo a las plantaciones de vid y nos anuncian las proximidades de Castilla y sus inmensos campos.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 10: Santo Domingo de la Calzada – Belorado (22,7 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Dejamos atrás Santo Domingo de la Calzada y, pasado ya Grañón, ya a la vista de Redecilla del Camino, un espigado cartel informativo nos da la bienvenida a la Comunidad de Castilla y León y a la provincia de Burgos.\n" +
                        "\n" +
                        "De ahora en adelante el Camino Francés discurrirá durante un par de semanas por los infinitos paisajes de la meseta castellana.\n" +
                        "\n" +
                        "El objetivo más próximo es Belorado, situado a la sombra de un picacho donde aún permanecen antiguas cuevas de ermitaños y la ruinas de un mítico castillo.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 11: Belorado – San Juan de Ortega (24 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Una etapa que consta de dos partes bien diferenciadas.\n" +
                        "\n" +
                        "La primera sirve de calentamiento y atraviesa las poblaciones de Tosantos, Villambistia, Espinosa del Camino y Villafranca Montes de Oca, lugar donde da comienzo un largo y solitario segundo tramo por los antaño temidos Montes de Oca.\n" +
                        "\n" +
                        "Tras una dura subida que hay que tomarse con cierta calma, el Camino discurre por pistas forestales cercadas de robles, pinos y brezos que nos conducen así a nuestro final de etapa, San Juan de Ortega, que se nos aparece como un oasis en el desierto.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 12: San Juan de Ortega – Burgos (26,6 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Desde un alto calizo y pelado, pasado Atapuerca, ya se distingue la primera capital castellana: Burgos.\n" +
                        "\n" +
                        "Lanzado hacia Burgos, el Camino deja atrás el valle del río Pico y se detiene en una bifurcación que lleva hasta Villafría o Castañares.\n" +
                        "\n" +
                        "Accediendo a esta última se puede esquivar uno de los los pesados accesos a la ciudad tomando el paseo fluvial del río Arlanzón hasta el puente de San Pablo, situado cerca de la catedral.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 13: Burgos – Hornillos del Camino (19,8 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Una jornada dura, sin arbolado donde guarecerse de los rigores de la meteorología —ya sea del temido sol estival, del penetrante frío de invierno o del incómodo viento— que se queda grabada en la memoria por el paso bajo los arcos de San Antón.\n" +
                        "\n" +
                        "\n" +
                        "Aunque nada especial destaque sobremanera en esta etapa en el ámbito cultural, caben destacar los pueblos de Rabé y Hornillos, formados por recias casas de piedra, y casi desérticos si no fuera por los peregrinos, han sabido conservar su carácter tradicional.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 14: Hornillos del Camino – Castrojeriz (20,6 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Otra etapa sin arbolado donde guarecerse de los rigores del clima, tanto en invierno como en verano.\n" +
                        "\n" +
                        "Quedan grabadas en la memoria el paso bajo los arcos del convento de San Antón y la visita a Castrojeriz, final de esta etapa.\n" +
                        "\n" +
                        "La relevancia cultura de la etapa se centra hoy en el patrimonio monumental de Castrojeriz, pero tampoco debemos olvidarnos del convento de San Antón, situado poco antes de esta localidad.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 15: Castrojeriz – Frómista (25,4 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Una etapa que queda marcada en la memoria por la dura subida al Teso de Mostelares tras salir de Castrojeriz.\n" +
                        "\n" +
                        "Desde este punto se abre al peregrino la impresionante vista de la brutal Tierra de Campos, comarca orgullosa de su cereal, de sus palomares y de su valioso arte.\n" +
                        "\n" +
                        "Alcanzado Puente Fitero, la provincia de Burgos se funde a orillas del Pisuerga para recibir a Palencia.\n" +
                        "\n" +
                        "Etapa 16: Frómista – Carrión de los Condes (19,6 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 4,5 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Tras Frómista, población eclipsada por la belleza de la iglesia románica de San Martín, llegan la llanura y la monotonía de un andadero, sólo rota por el paso fugaz de coches y ‘bicigrinos’\n" +
                        "\n" +
                        "Pasamos por los núcleos de Tierra de Campos, como Población, Revenga y Villarmentero.\n" +
                        "\n" +
                        "En el tramo final, antes de llegar a Carrión, Villalcázar de Sirga llama la atención con Santa María la Blanca, iglesia – fortaleza atribuida a los Templarios.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 17: Carrión de los Condes – Ledigos (23,4 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5,5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Entre Carrión y Calzadilla de la Cueza nos encontramos con 17 kilómetros en los que no hay ninguna población alguna.\n" +
                        "\n" +
                        "Gran parte del trayecto por la llamada Vía Aquitana, totalmente desprotegida.\n" +
                        "\n" +
                        "De Calzadilla a Ledigos, un socorrido andadero evita el asfalto de la carretera N-120, sin apenas tráfico ahora desde la apertura de la autovía del Camino de Santiago, la A-231.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 18: Ledigos – Sahagún (16,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 4 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Moratinos y San Nicolás del Real Camino son los últimos pueblos del trazado francés a su paso por la provincia de Palencia y ceden el testigo a León, la provincia con más kilómetros del Camino de Santiago: nada menos que 214,4.\n" +
                        "\n" +
                        "Tras la mítica Terradillos de los Templarios, nombre que evoca a los caballeros medievales cristianos y las cruzadas, alcanzamos el final de etapa en Sahagún, un lugar de paso y descanso, pleno de arte mudéjar.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 19: Sahagún – El Burgo Ranero (17,8 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 4,5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Tras Sahagún aparece una bifurcación al pie de la N-120 que da la opción de continuar hacia Bercianos del Real Camino y El Burgo Ranero o hacia Calzada del Coto y Calzadilla de los Hermanillos.\n" +
                        "\n" +
                        "La primera alternativa discurre por un camino construido ex profeso para los peregrinos y sombreado por una fila de árboles sin fin.\n" +
                        "\n" +
                        "La segunda, solitaria y por la Vía Trajana, avanza por áreas de monte bajo, matorral y pequeños bosques.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 20: El Burgo Ranero – Mansilla de las Mulas (18,8 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 4,5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "La pista arbolada continúa durante casi diecinueve planos kilómetros por la provincia de León, hacia Reliegos y Mansilla de las Mulas, donde dice adiós.\n" +
                        "\n" +
                        "Una etapa corta para abordar al día siguiente la gran ciudad de León con tiempo suficiente para visitarla y disfrutar de todo lo que ofrece al peregrino y al turista.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 21: Mansilla de las Mulas – León (19,7 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 4,5 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Desde el río Esla, a la salida de Mansilla de las Mulas, hasta el Porma, avanzamos por caminos paralelos a la carretera N-601 hasta Puente Villarente.\n" +
                        "\n" +
                        "A partir de esta localidad el Camino se distancia ligeramente de la nacional para llegar a Arcahueja.\n" +
                        "\n" +
                        "Finalmente, el barrio de Puente Castro, a orillas del Torío, supone la entrada definitiva en León.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 22: León – Villadangos del Páramo (21,0 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "El Camino nos lleva por amplias avenidas hasta el Hostal de San Marcos, a orillas del río Bernesga.\n" +
                        "\n" +
                        "De ahí, tras cinco kilómetros por zonas urbanas, incluida un polígono industrial, llegamos a la Virgen del Camino.\n" +
                        "\n" +
                        "Nos adentramos por el por el páramo leonés, siempre a la vera de la N-120, y alcanzamos Valverde de la Virgen, San Miguel del Camino para concluir en Villadangos del Páramo.\n" +
                        "\n" +
                        "Variante Sur\n" +
                        "Etapa 22.a: León – Villar de Mazarife (21,1 Km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "A la salida de La Virgen del Camino hemos de elegir entre el camino histórico, por Villadangos del Páramo, o la Variante Sur, por Villar de Mazarife.\n" +
                        "\n" +
                        "Esta última es una buena opción, pues nos aleja de la carretera nacional y de áreas residenciales e industriales.\n" +
                        "\n" +
                        "Esta Variante Sur por Villar de Mazarife, tiene un primer tramo urbano, y luego avanza por zonas de cultivo y descampados, alejados de las áreas urbanas y de los grandes ejes viarios, y sin apenas desniveles.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 22.b: Villar de Mazarife – Astorga (31,4 Km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7,5 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Hasta Hospital de Órbigo la etapa sigue la misma pauta que la de ayer, un terreno llano, carente de desniveles, entre cultivos y escasos árboles.\n" +
                        "\n" +
                        "Pasado Hospital de Órbigo, tras interminables días de llanura, caminamos por fin por un terreno ligeramente montañoso, aunque nada comparable a lo que nos espera en las jornadas posteriores.\n" +
                        "\n" +
                        "En la bonita ciudad de Astorga, la Asturica Augusta romana, podremos admirar la catedral y el Palacio Episcopal, éste último obra de famosísimo arquitecto Antoni Gaudí.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 23: Villadangos del Páramo – Astorga (28,9 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "El Camino sigue el mismo ritmo que en la etapa anterior, paralelo a la N-120, y nos lleva hasta San Martín del Camino y Puente Órbigo para salvar el afluente del Esla por el mítico puente del Passo Honroso.\n" +
                        "\n" +
                        "En Hospital de Órbigo se puede escoger entre continuar por la monótona senda o disfrutar de un paisaje agrícola, surcado por multitud de acequias de acequias de riego, que visita Villares de Órbigo y Santibáñez de Valdeiglesias.\n" +
                        "\n" +
                        "Las dos opciones se funden en el crucero de Santo Toribio, excelente mirador sobre San Justo de la Vega, la ciudad de Astorga y el monte Teleno.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 24: Astorga – Rabanal del Camino (21,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 5 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "El Camino Francés se interna por la comarca de La Maragatería, divisando ya al fondo los montes de León.\n" +
                        "\n" +
                        "Una comarca habitada en su día por arrieros, y que aún conserva sus casas recias en sillarejo y con portalón, y el famoso cocido maragato.\n" +
                        "\n" +
                        "La etapa nos lleva a Rabanal del Camino, fin de la novena etapa en el Codex Calixtinus, y Foncebadón, en las laderas del Irago, que se presenta como otro posible final de etapa.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 25: Rabanal del Camino – Ponferrada (35,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 8,5 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "El Camino Francés llega a su techo en la Cruz de Ferro, clavada a 1.500 metros de altitud y sobre la que los peregrinos han ido construyendo un enorme montículo de piedras.\n" +
                        "\n" +
                        "Cerca, entre tañidos de campana y señales de humo, se sitúa el curioso refugio templario de Manjarín, en pie desde 1993 gracias a Tomás Martínez.\n" +
                        "\n" +
                        "Abandonamos La Maragatería y nos introducimos en El Bierzo, que se presenta en un crudo descenso entre pastos y piornos.\n" +
                        "\n" +
                        "El Acebo, Riego de Ambrós y, finalmente, Molinaseca, a orillas del río Meruelo, son los lugares que de este tramo de la etapa, para finalmente, por llano, alcanzar Ponferrada.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 26: Ponferrada – Villafranca del Bierzo (24,9 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Dejamos atrás Ponferrada y nos introducimos en la hoya del Bierzo, una llanura resguardada por montañas que impiden el paso del clima atlántico.\n" +
                        "\n" +
                        "En esta zona, campos con vides retorcidas de uva mencía y pueblos con todos los servicios reciben a diario a los peregrinos.\n" +
                        "\n" +
                        "Poco más que un largo paseo cómodo por Compostilla, Columbrianos, Fuentes Nuevas, Camponaraya y Cacabelos, que se vuelve un tanto pesado tras cruzar el río Cúa en dirección a Pieros y Villafranca del Bierzo.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 27: Villafranca del Bierzo – O Cebreiro (26,3 km.)\n" +
                        "Tiempo estimado: 7,5 h. :: Dificultad: Alta\n" +
                        "\n" +
                        "Vuelve la montaña al Camino de Santiago.\n" +
                        "\n" +
                        "La etapa del día es la etapa reina, seguramente la más recordada al terminar el Camino.\n" +
                        "\n" +
                        "En ella abandonamos León, y Castilla, para adentrarnos en Galicia, a través de O Cebreiro, una antigua aldea de pallozas hoy restaurada.\n" +
                        "\n" +
                        "Subimos a través del valle del encajonado río Valcarce, partido por la N-VI y la A-6 y con muchas poblaciones.\n" +
                        "\n" +
                        "El ascenso es tardío, y no es patente hasta el kilómetro 21 de la etapa, sobrepasado el barrio de Hospital.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 28: O Cebreiro – Samos (30,8 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7,5 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Tras la dura jornada del día anterior, un recorrido activo pero que también sirve para recuperara fuerzas antes de los sinuosos trazados posteriores.\n" +
                        "\n" +
                        "El Camino alcanza su máxima cota en Galicia en el monte Area y aún despliega latigazos en el repecho del alto do Poio.\n" +
                        "\n" +
                        "En O Biduedo, pasamos del municipio de Pedrafita do Cebreiro al de Triacastela, cuyo núcleo principal descansa en el fondo del valle bajo la sombra del monte Oribio.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 29: Samos – Sarria – Portomarín (37,2 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 9 h. :: Dificultad: Alta\n" +
                        "\n" +
                        "Para muchos la primera etapa, ya que Sarria se encuentra muy cerca de los últimos 100 kilómetros de camino, la distancia mínima que hay que recorrer a pie para ganar la Compostela.\n" +
                        "\n" +
                        "Una etapa no defrauda en absoluto ni al primerizo ni al peregrino curtido.\n" +
                        "\n" +
                        "Atraviesa incontables aldeas de los municipios de Sarria, Paradela y Portomarín, se disfruta de buenos ejemplos del arte románico, se camina por pistas vecinales asfaltadas, sendas y corredores rurales, puentes medievales y pasarelas rústicas.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 30: Portomarín – Palas de Rei (25,7 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 6 h. :: Dificultad: Media/Baja\n" +
                        "\n" +
                        "Etapa está partida por la sierra de Ligonde, que a su vez divide las cuencas de los ríos Miño y Ulloa y los municipios de Portomarín y Monterroso.\n" +
                        "\n" +
                        "Aunque el comienzo es prometedor y regala un apacible ascenso por el monte San Antonio, el resto discurre junto a modestas carreteras modestas, provinciales e incluso nacionales.\n" +
                        "\n" +
                        "Pero la jornada es amenizada por buenos ejemplos de iglesias románicas, antiguos hospitales de peregrinos y el valioso crucero de Lameiros.\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Etapa 31: Palas de Rei – Arzúa (28,9 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 7 h. :: Dificultad: Media\n" +
                        "\n" +
                        "Una etapa larga con un perfil quebrado, de los conocidos como rompepiernas, que la convierte en un verdadero reto.\n" +
                        "\n" +
                        "El Camino traspasa los límites de la provincia de Lugo para entrar en la de La Coruña por la aldea de O Coto.\n" +
                        "\n" +
                        "Pasa por Leboreiro y la pulpera Melide, donde enlaza con el Camino Primitivo, y continúa por un sinfín de toboganes y escenarios mágicos, como el del río Catasol, hasta Ribadixo da Baixo y Arzúa, donde a su vez conecta con el Camino del Norte.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 32: Arzúa – Monte do Gozo (34,1 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 8 h. :: Dificultad: Media/Alta\n" +
                        "\n" +
                        "Entre Arzúa y la catedral de Santiago median casi 40 kilómetros y aunque lo más lógico y prudente sería dividir el tramo en dos jornadas haciendo parada y fonda en Santa Irene u O Pedrouzo, elegimos continuar hasta el Monte del Gozo, para así, el día siguiente, ya en Santiago, tener tiempo suficiente para disfrutar de la ciudad.\n" +
                        "\n" +
                        "Arzúa cede el testigo a O Pino en un trayecto cómodo, con pendientes más fáciles y por pistas siempre cercanas a la N-547. Sobre pistas de hojarasca, entre las últimas manchas de eucaliptos y robles, en una larga caminata llegamos al Monte do Gozo.\n" +
                        "\n" +
                        "\n" +
                        "Etapa 33: Monte do Gozo – Santiago de Compostela (4,1 km.)\n" +
                        "\n" +
                        "Tiempo estimado: 1 h. :: Dificultad: Baja\n" +
                        "\n" +
                        "Más que una etapa, un simple paseo, después de partir de Monto do Gozo, a las puerta de Santiago.\n" +
                        "\n" +
                        "Así contaremos con tiempo suficiente para llegar a la misa del peregrino y tener todo un día para disfrutar de todo lo que Santiago de Compostela ofrece, tanto al peregrinos como a los turistas en general.")
                .setNegativeButton(
                    "Cierra"
                ) { dialog, _ -> dialog.dismiss() }
                .create()
            alertDialog.show()
        }

        val verEnMapaButton: Button=findViewById(R.id.button_mostrar_mapa_camino_frances)
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
        val addButton: ImageButton= findViewById(R.id.icon_add_camino_frances)
        addButton.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

            // Set the onClickListener for the icon_profile button (opens the profile drawer)
        val iconProfileButton: ImageButton = findViewById(R.id.icon_profile_camino_frances)
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
