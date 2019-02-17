package bernardo.bernardinhio.retrofitgetdataearthquakeapi.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
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

    private RecyclerView recyclerView;
    private ArrayList<EarthquakeDataModel> arrayListEarthquakes = new ArrayList<EarthquakeDataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
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

                // set RecyclerView
                setupRecyclerView();
                setAdapter();

            }

            @Override
            public void onFailure(Call<EarthquakesRoot> call, Throwable t) {
                Toast.makeText(MainActivity.this, "API call failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setAdapter(){
        AdapterRV adapterRV = new AdapterRV(arrayListEarthquakes);
        recyclerView.setAdapter(adapterRV);
        adapterRV.notifyDataSetChanged();
    }
}
