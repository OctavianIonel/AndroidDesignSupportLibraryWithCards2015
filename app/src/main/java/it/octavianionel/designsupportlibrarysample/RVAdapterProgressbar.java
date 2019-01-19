package it.octavianionel.designsupportlibrarysample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Reply on 16/01/2019.
 */
public class RVAdapterProgressbar extends RecyclerView.Adapter<RVAdapterProgressbar.ModelViewHolder> {

    private Context mContext;
    private ArrayList<ProgressbarModel> mItems;

    public RVAdapterProgressbar(Context context, ArrayList<ProgressbarModel> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progressbar, parent, false);
        ModelViewHolder mvh = new ModelViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        ProgressbarModel item = mItems.get(position);
        holder.swipeLeftOrRightLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        holder.titleTv.setText(item.getTitle());
        BigDecimal amount = item.getmAmount();
        BigDecimal spentAmount = item.getmSpentAmount();
        BigDecimal threshold = item.getmThreshold();
        holder.pfmProgressbarLayout.setValues(amount, spentAmount, threshold);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ModelViewHolder extends RecyclerView.ViewHolder {

        SwipeLayout swipeLeftOrRightLayout;
        TextView titleTv;
        PfmProgressbarLayout pfmProgressbarLayout;

        public ModelViewHolder(View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv);
            swipeLeftOrRightLayout = itemView.findViewById(R.id.swipeLeftOrRight);
            pfmProgressbarLayout = itemView.findViewById(R.id.item_pfm_progressbar);
        }

    }
}
