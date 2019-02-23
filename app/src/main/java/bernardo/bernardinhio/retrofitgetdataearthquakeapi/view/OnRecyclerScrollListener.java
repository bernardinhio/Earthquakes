package bernardo.bernardinhio.retrofitgetdataearthquakeapi.view;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class OnRecyclerScrollListener extends RecyclerView.OnScrollListener{

    private ActionBar actionBar;
    private ArrayList<EarthquakeDataModel> arrayListEarthquakes;

    public OnRecyclerScrollListener(ActionBar actionBar, ArrayList<EarthquakeDataModel> arrayListEarthquakes) {
        super();
        this.actionBar = actionBar;
        this.arrayListEarthquakes = arrayListEarthquakes;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        // use X and Y to locate the View that was touched
        View child = recyclerView.findChildViewUnder(dx, dy);

        if(child != null){

            // get the position of the row that this clicked View corresponds to
            int positionItemScrolled = recyclerView.getChildAdapterPosition(child);

            // set the Title using the ArrayList and Position
            actionBar.setTitle(arrayListEarthquakes.get(positionItemScrolled).getTitle());
        }
    }
}
