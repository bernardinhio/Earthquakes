package bernardo.bernardinhio.retrofitgetdataearthquakeapi.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;

public class OnRecyclerItemInteractionListener extends ItemTouchHelper.SimpleCallback{

    private ArrayList<EarthquakeDataModel> arrayListEarthquakes;
    private AdapterRV adapterRV;

    public OnRecyclerItemInteractionListener(int dragDirs, int swipeDirs, ArrayList<EarthquakeDataModel> arrayListEarthquakes, AdapterRV adapterRV) {
        super(dragDirs, swipeDirs);
        this.arrayListEarthquakes = arrayListEarthquakes;
        this.adapterRV = adapterRV;
    }

    // This method is triggered when a long press occurs on an Item
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosion = viewHolder.getAdapterPosition();
        int toPosision = target.getAdapterPosition();

        Collections.swap(arrayListEarthquakes, fromPosion, toPosision);

        adapterRV.notifyDataSetChanged();

        return true;
    }

    // This method is triggered when a Swipe occurs on an Item
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int positionToBeDeleted = viewHolder.getAdapterPosition();

        arrayListEarthquakes.remove(positionToBeDeleted);

        adapterRV.notifyItemRemoved(positionToBeDeleted);
    }
}
