package bernardo.bernardinhio.retrofitgetdataearthquakeapi.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

import bernardo.bernardinhio.retrofitgetdataearthquakeapi.R

class AdapterRV internal constructor(private val arrayListEarthquakes: ArrayList<EarthquakeDataModel>) : RecyclerView.Adapter<AdapterRV.ViewHolderRV>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRV {

        val layoutInflater = LayoutInflater.from(parent.context)
        val viewInflated = layoutInflater.inflate(R.layout.fragment_earthquake_item, parent, false)
        return ViewHolderRV(viewInflated)
    }

    override fun onBindViewHolder(holder: ViewHolderRV, position: Int) {

        val (place, time, title) = arrayListEarthquakes[position]

        holder.tvPlace.text = place
        holder.tvTime.text = time
        holder.tvTitle.text = title
    }

    override fun getItemCount(): Int {
        return arrayListEarthquakes.size
    }

    inner class ViewHolderRV(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPlace: TextView = itemView.findViewById<View>(R.id.tv_place) as TextView
        val tvTime: TextView = itemView.findViewById<View>(R.id.tv_times) as TextView
        val tvTitle: TextView = itemView.findViewById<View>(R.id.tv_title) as TextView
    }
}