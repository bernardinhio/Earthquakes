package bernardo.bernardinhio.retrofitgetdataearthquakeapi.service

import bernardo.bernardinhio.retrofitgetdataearthquakeapi.service.RetrofitInstance.retrofitInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // singleton
    private var retrofitInstance: Retrofit? = null
    // the baseUrl SHOULD always end with /
    private val BASE_URL = "https://earthquake.usgs.gov/"

    fun setupRetrofitCalls(): RetrofitCalls {

        // check if already single instance was initialized
        if (retrofitInstance == null) {

            // create a Retrofit Builder
            val builder1 = Retrofit.Builder()

            // set the baseUrl that will be used later by the Interface
            val builder2 = builder1.baseUrl(BASE_URL)

            // To serialise JSON we need a converter to convert it into Gson first
            // Use the Gson converter factory as factory to parse json
            val factory = GsonConverterFactory.create()

            // set the factory to the builder
            val builder3 = builder2.addConverterFactory(factory)

            // initialize Retrofit instance by building the builder
            retrofitInstance = builder3.build()
        }

        // make the retrofit instance use the Interface which
        // adds the End-point
        return retrofitInstance!!.create(RetrofitCalls::class.java)
    }
}
