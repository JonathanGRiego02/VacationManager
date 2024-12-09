package pgl.vacationmanager

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import pgl.vacationmanager.adapters.CasasListViewAdapter
import pgl.vacationmanager.modelos.Casa

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var adapter: CasasListViewAdapter
    lateinit var casas: List<Casa>
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val spinner: Spinner = findViewById(R.id.spinner)
        val listView: ListView = findViewById(R.id.casasListView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.abrir, R.string.cerrar)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "" // Remove the title from the toolbar

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_reservas -> {
                    // Handle "Reservas" action
                }
                R.id.nav_options -> {
                    val menu = navigationView.menu
                    val temaClaroItem = menu.findItem(R.id.nav_tema_claro)
                    val temaOscuroItem = menu.findItem(R.id.nav_tema_oscuro)

                    // Toggle visibility of the submenu items
                    val isVisible = temaClaroItem.isVisible
                    temaClaroItem.isVisible = !isVisible
                    temaOscuroItem.isVisible = !isVisible
                }
                R.id.nav_tema_claro -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    sharedPreferences.edit().putBoolean("dark_mode", false).apply()
                }
                R.id.nav_tema_oscuro -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    sharedPreferences.edit().putBoolean("dark_mode", true).apply()
                }
                R.id.nav_salir -> {
                    finish()
                }
            }
            true
        }

        casas = listOf(
            Casa(
                imageResId = R.drawable.image1,
                houseName = "La Cabaña",
                country = "Argentina",
                location = "Bariloche",
                rating = 4.5f,
                longDescription = "Un refugio acogedor ubicado en el corazón de Bariloche, rodeado por bosques y montañas. Ideal para disfrutar de la naturaleza, con chimenea y vistas impresionantes al lago Nahuel Huapi."
            ),
            Casa(
                imageResId = R.drawable.image2,
                houseName = "El Refugio",
                country = "Argentina",
                location = "Mendoza",
                rating = 4.0f,
                longDescription = "Situada en Mendoza, esta casa combina comodidad y estilo, perfecta para amantes del vino y la montaña. Ofrece fácil acceso a bodegas y rutas de trekking."
            ),
            Casa(
                imageResId = R.drawable.image3,
                houseName = "Tokyo Loft",
                country = "Japón",
                location = "Shibuya, Tokio",
                rating = 4.8f,
                longDescription = "Un loft moderno en el vibrante barrio de Shibuya. Diseñado con elegancia minimalista japonesa, es perfecto para explorar la cultura, gastronomía y vida nocturna de Tokio."
            ),
            Casa(
                imageResId = R.drawable.image4,
                houseName = "Villa Sakura",
                country = "Japón",
                location = "Kioto",
                rating = 4.6f,
                longDescription = "Encantadora villa tradicional en Kioto, rodeada de jardines y cerezos en flor. Ofrece una experiencia auténtica de la cultura japonesa en un ambiente sereno y relajante."
            ),
            Casa(
                imageResId = R.drawable.image5,
                houseName = "Maison des Rêves",
                country = "Francia",
                location = "París",
                rating = 4.7f,
                longDescription = "Una elegante casa parisina situada cerca de la Torre Eiffel. Perfecta para disfrutar de la moda, la gastronomía y la historia de la Ciudad de la Luz."
            ),
            Casa(
                imageResId = R.drawable.image6,
                houseName = "Château Provence",
                country = "Francia",
                location = "Aix-en-Provence",
                rating = 4.9f,
                longDescription = "Un castillo pintoresco en el corazón de la Provenza. Rodeado de viñedos y campos de lavanda, es un lugar ideal para relajarse y disfrutar del encanto rural francés."
            ),
            Casa(
                imageResId = R.drawable.image7,
                houseName = "Casa del Sol",
                country = "España",
                location = "Sevilla",
                rating = 4.4f,
                longDescription = "Una casa tradicional andaluza situada en el corazón de Sevilla. Combina una arquitectura clásica con todas las comodidades modernas, cerca de la Catedral y la Giralda."
            ),
            Casa(
                imageResId = R.drawable.image8,
                houseName = "Villa Mediterránea",
                country = "España",
                location = "Mallorca",
                rating = 4.3f,
                longDescription = "Una villa luminosa con vistas al mar Mediterráneo. Perfecta para disfrutar de playas de ensueño, deportes acuáticos y la gastronomía mallorquina."
            ),
            Casa(
                imageResId = R.drawable.image9,
                houseName = "Cozy Cottage",
                country = "Estados Unidos",
                location = "Vermont",
                rating = 4.2f,
                longDescription = "Una cabaña acogedora rodeada de bosques en Vermont. Ideal para escapadas románticas o para disfrutar de actividades al aire libre como senderismo y esquí."
            ),
            Casa(
                imageResId = R.drawable.image10,
                houseName = "Modern Retreat",
                country = "Estados Unidos",
                location = "Los Ángeles, California",
                rating = 4.6f,
                longDescription = "Una casa contemporánea en Los Ángeles, con diseño moderno y vistas panorámicas. Perfecta para disfrutar de la vida urbana y el glamour de Hollywood."
            )
        )

        adapter = CasasListViewAdapter(this, casas)
        listView.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCountry = parent.getItemAtPosition(position).toString()
                filterCasasByCountry(selectedCountry)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun filterCasasByCountry(country: String) {
        val filteredCasas = if (country == "Ver todos") {
            casas
        } else {
            casas.filter { it.country == country }
        }
        adapter = CasasListViewAdapter(this, filteredCasas)
        findViewById<ListView>(R.id.casasListView).adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

