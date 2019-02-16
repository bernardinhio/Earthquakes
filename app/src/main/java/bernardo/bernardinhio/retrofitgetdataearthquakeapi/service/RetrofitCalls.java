package bernardo.bernardinhio.retrofitgetdataearthquakeapi.service;

import bernardo.bernardinhio.retrofitgetdataearthquakeapi.model.EarthquakesRoot;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitCalls {
    // when we don't have specific end point
    @GET("fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02")
    Call<EarthquakesRoot> getEarthquakes();
}
