package pgl.vacationmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ListView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import pgl.vacationmanager.adapters.CasasListViewAdapter
import pgl.vacationmanager.modelos.Casa
import androidx.core.view.GravityCompat
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import pgl.vacationmanager.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        // Agregar el ActionBarDrawerToggle
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.open_drawer, R.string.close_drawer
        )

        // Sincronizar el estado del toggle
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Configurar la Toolbar (si aún no la tienes)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Otros ajustes de la actividad
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView: ListView = findViewById(R.id.casasListView)
        val casas = listOf(
            Casa(imageResId = R.drawable.image1, house_name = "La Cabaña", country = "Argentina", location = "Bariloche", rating = 4.5f),
            Casa(imageResId = R.drawable.image2, house_name = "El Refugio", country = "Argentina", location = "Mendoza", rating = 4.0f),
            Casa(imageResId = R.drawable.image3, house_name = "Tokyo Loft", country = "Japón", location = "Shibuya, Tokio", rating = 4.8f),
            Casa(imageResId = R.drawable.image4, house_name = "Villa Sakura", country = "Japón", location = "Kioto", rating = 4.6f),
            Casa(imageResId = R.drawable.image5, house_name = "Maison des Rêves", country = "Francia", location = "París", rating = 4.7f),
            Casa(imageResId = R.drawable.image6, house_name = "Château Provence", country = "Francia", location = "Aix-en-Provence", rating = 4.9f),
            Casa(imageResId = R.drawable.image7, house_name = "Casa del Sol", country = "España", location = "Sevilla", rating = 4.4f),
            Casa(imageResId = R.drawable.image8, house_name = "Villa Mediterránea", country = "España", location = "Mallorca", rating = 4.3f),
            Casa(imageResId = R.drawable.image9, house_name = "Cozy Cottage", country = "Estados Unidos", location = "Vermont", rating = 4.2f),
            Casa(imageResId = R.drawable.image10, house_name = "Modern Retreat", country = "Estados Unidos", location = "Los Ángeles, California", rating = 4.6f)
            // Añade más objetos Casa aquí
        )
        val adapter = CasasListViewAdapter(this, casas)
        listView.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_reservations -> {
                // Handle "Mis reservas" click
                Toast.makeText(this, "Mis reservas", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_dark_theme -> {
                // Handle "Tema oscuro" click
                Toast.makeText(this, "Tema oscuro", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_light_theme -> {
                // Handle "Tema claro" click
                Toast.makeText(this, "Tema claro", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_about -> {
                // Handle "Acerca de" click
                Toast.makeText(this, "Acerca de", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}