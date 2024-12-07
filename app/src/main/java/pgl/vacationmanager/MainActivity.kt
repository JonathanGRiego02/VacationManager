package pgl.vacationmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ListView
import pgl.vacationmanager.adapters.CasasListViewAdapter
import pgl.vacationmanager.modelos.Casa

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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
}