package bernardo.bernardinhio.retrofitgetdataearthquakeapi.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import bernardo.bernardinhio.retrofitgetdataearthquakeapi.R;
import bernardo.bernardinhio.retrofitgetdataearthquakeapi.model.EarthquakesRoot;
import bernardo.bernardinhio.retrofitgetdataearthquakeapi.service.RetrofitCalls;
import bernardo.bernardinhio.retrofitgetdataearthquakeapi.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){
        super.onCreateOptionsMenu(m);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId){
            case R.id.show_result:
                getDataFromApiUrl();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void getDataFromApiUrl() {
        RetrofitCalls retrofitCalls
                = RetrofitInstance.INSTANCE.setupRetrofitCalls();

        Call<EarthquakesRoot> call
                = retrofitCalls.getEarthquakes();

        call.enqueue(new Callback<EarthquakesRoot>() {
            @Override
            public void onResponse(Call<EarthquakesRoot> call, Response<EarthquakesRoot> response) {

                // get the returned Object from the API
                EarthquakesRoot earthquakesRoot
                        = response.body();

                EarthquakesRoot.Features[] features = earthquakesRoot.getFeatures();

                ArrayList<EarthquakeDataModel> arrayListEarthquakes = new ArrayList<EarthquakeDataModel>();
                String place;
                String time;
                String title;

                if (features != null) {
                    for (int i = 0; i< features.length; i++){
                        place = features[i].getProperties().getPlace();
                        time = features[i].getProperties().getTime();
                        title = features[i].getProperties().getTitle();

                        arrayListEarthquakes.add(new EarthquakeDataModel(
                                place,
                                time,
                                title
                        ));
                    }
                }


                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                // set RecyclerView
                setupRecyclerView(arrayListEarthquakes);

            }

            @Override
            public void onFailure(Call<EarthquakesRoot> call, Throwable t) {
                Toast.makeText(MainActivity.this, "API call failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void setupRecyclerView(ArrayList<EarthquakeDataModel> arrayListEarthquakes) {

    }

}
