package pgl.vacationmanager

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pgl.vacationmanager.adapters.Reservation
import pgl.vacationmanager.adapters.ReservationsAdapter

class ReservationsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var reservationsAdapter: ReservationsAdapter
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations)

        sharedPreferences = getSharedPreferences("reservations", Context.MODE_PRIVATE)
        val reservationsJson = sharedPreferences.getString("reservations_list", "[]")
        val reservationsType = object : TypeToken<MutableList<Reservation>>() {}.type
        val reservations: MutableList<Reservation> = gson.fromJson(reservationsJson, reservationsType)

        val reservationsRecyclerView: RecyclerView = findViewById(R.id.reservations_recycler_view)
        reservationsRecyclerView.layoutManager = LinearLayoutManager(this)
        reservationsAdapter = ReservationsAdapter(this, reservations) { position ->
            deleteReservation(position)
        }
        reservationsRecyclerView.adapter = reservationsAdapter
    }

    private fun deleteReservation(position: Int) {
        val reservationsJson = sharedPreferences.getString("reservations_list", "[]")
        val reservationsType = object : TypeToken<MutableList<Reservation>>() {}.type
        val reservations: MutableList<Reservation> = gson.fromJson(reservationsJson, reservationsType)
        reservations.removeAt(position)
        sharedPreferences.edit().putString("reservations_list", gson.toJson(reservations)).apply()
        reservationsAdapter.removeItem(position)
    }
}