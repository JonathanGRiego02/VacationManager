package pgl.vacationmanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pgl.vacationmanager.R

data class Reservation(val houseName: String, val name: String, val email: String, val date: String, val time: String, val checkoutDate: String)

class ReservationsAdapter(
    private val context: Context,
    private val reservations: MutableList<Reservation>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<ReservationsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reservationDetails: TextView = view.findViewById(R.id.reservation_details)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_reservation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.reservationDetails.text = """
            Casa: ${reservation.houseName}
            Nombre: ${reservation.name}
            Email: ${reservation.email}
            Fecha de entrada: ${reservation.date}
            Fecha de salida: ${reservation.checkoutDate}
            Hora de entrada: ${reservation.time}
        """.trimIndent()
        holder.deleteButton.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int = reservations.size

    fun removeItem(position: Int) {
        reservations.removeAt(position)
        notifyItemRemoved(position)
    }
}