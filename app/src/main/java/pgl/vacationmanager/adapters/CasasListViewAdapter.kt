package pgl.vacationmanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import pgl.vacationmanager.R
import pgl.vacationmanager.modelos.Casa

class CasasListViewAdapter(private val context: Context, private val dataSource: List<Casa>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = dataSource.size

    override fun getItem(position: Int): Any = dataSource[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(R.layout.casas_listview, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.item_image)
        val titleView = view.findViewById<TextView>(R.id.item_title)
        val subtitleView = view.findViewById<TextView>(R.id.item_subtitle)

        val item = getItem(position) as Casa

        imageView.setImageResource(item.imageResId)
        titleView.text = item.house_name
        subtitleView.text = item.location
        return view
    }
}