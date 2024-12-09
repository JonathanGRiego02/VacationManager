package pgl.vacationmanager

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pgl.vacationmanager.adapters.Reservation
import java.util.*

class CasaDetailActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casa)

        sharedPreferences = getSharedPreferences("reservations", Context.MODE_PRIVATE)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.abrir, R.string.cerrar)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_reservas -> {
                    startActivity(Intent(this, ReservationsActivity::class.java))
                }
                R.id.nav_options -> {
                    val menu = navigationView.menu
                    val temaClaroItem = menu.findItem(R.id.nav_tema_claro)
                    val temaOscuroItem = menu.findItem(R.id.nav_tema_oscuro)

                    val isVisible = temaClaroItem.isVisible
                    temaClaroItem.isVisible = !isVisible
                    temaOscuroItem.isVisible = !isVisible
                }
                R.id.nav_tema_claro -> {
                    // Handle "Tema Claro" action
                }
                R.id.nav_tema_oscuro -> {
                    // Handle "Tema Oscuro" action
                }
                R.id.nav_salir -> {
                    finish()
                }
            }
            true
        }

        val imageView: ImageView = findViewById(R.id.detail_image)
        val titleView: TextView = findViewById(R.id.detail_title)
        val locationView: TextView = findViewById(R.id.detail_location)
        val ratingBar: RatingBar = findViewById(R.id.detail_rating)
        val descriptionView: TextView = findViewById(R.id.detail_description)
        val reserveButton: Button = findViewById(R.id.reserve_button)

        val imageResId = intent.getIntExtra("imageResId", 0)
        val houseName = intent.getStringExtra("house_name")
        val location = intent.getStringExtra("location")
        val rating = intent.getFloatExtra("rating", 0f)
        val description = intent.getStringExtra("longDescription")

        imageView.setImageResource(imageResId)
        titleView.text = houseName
        locationView.text = getString(R.string.location_text, location)
        ratingBar.rating = rating
        descriptionView.text = description

        reserveButton.setOnClickListener {
            showReservationDialog(houseName)
        }
    }

    private fun showReservationDialog(houseName: String?) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.reserva, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.name_edit_text)
        val emailEditText = dialogView.findViewById<EditText>(R.id.email_edit_text)
        val dateEditText = dialogView.findViewById<EditText>(R.id.date_edit_text)
        val timeEditText = dialogView.findViewById<EditText>(R.id.time_edit_text)
        val checkoutDateEditText = dialogView.findViewById<EditText>(R.id.checkout_date_edit_text)

        dateEditText.setOnClickListener {
            showDatePickerDialog(dateEditText)
        }

        timeEditText.setOnClickListener {
            showTimePickerDialog(timeEditText)
        }

        checkoutDateEditText.setOnClickListener {
            showDatePickerDialog(checkoutDateEditText)
        }

        AlertDialog.Builder(this)
            .setTitle("Introduce tus datos")
            .setView(dialogView)
            .setPositiveButton("Reservar") { _, _ ->
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val date = dateEditText.text.toString()
                val time = timeEditText.text.toString()
                val checkoutDate = checkoutDateEditText.text.toString()
                saveReservation(houseName, name, email, date, time, checkoutDate)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun showDatePickerDialog(dateEditText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            dateEditText.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
        }, year, month, day).show()
    }

    private fun showTimePickerDialog(timeEditText: EditText) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            timeEditText.setText("$selectedHour:$selectedMinute")
        }, hour, minute, true).show()
    }

    private fun saveReservation(houseName: String?, name: String, email: String, date: String, time: String, checkoutDate: String) {
        val reservationsJson = sharedPreferences.getString("reservations_list", "[]")
        val reservationsType = object : TypeToken<MutableList<Reservation>>() {}.type
        val reservations: MutableList<Reservation> = gson.fromJson(reservationsJson, reservationsType)
        reservations.add(Reservation(houseName ?: "", name, email, date, time, checkoutDate))
        sharedPreferences.edit().putString("reservations_list", gson.toJson(reservations)).apply()
    }
}