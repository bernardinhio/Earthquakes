package bernardo.bernardinhio.retrofitgetdataearthquakeapi.view

import android.text.format.DateFormat
import java.util.*

data class EarthquakeDataModel(
        val place : String? = "",
        val title : String? = "",
        val time : String? = ""
){
    companion object {
        public fun getFormattedTime(time: String): String {
            val calendar = Calendar.getInstance(Locale.ENGLISH)
            calendar.timeInMillis = time?.toLong()
            return DateFormat.format("'On: 'EEE dd, MMM yyyy'  /  at: 'HH:mm:ss'/  Zone: 'z", calendar).toString()
        }
    }
}