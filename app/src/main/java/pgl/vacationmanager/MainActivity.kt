package pgl.vacationmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ListView
import pgl.vacationmanager.adapters.CasasListViewAdapter
import pgl.vacationmanager.adapters.ListItem
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
            Casa(R.drawable.image1, "Casa 1", "Descripción de la casa 1", 4.5f),
            Casa(R.drawable.image2, "Casa 2", "Descripción de la casa 2", 3.0f),
            // Añade más objetos Casa aquí
        )
        val adapter = CasasListViewAdapter(this, casas)
        listView.adapter = adapter
    }
}