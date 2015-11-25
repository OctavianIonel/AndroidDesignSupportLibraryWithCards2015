package it.octavianionel.designsupportlibrarysample;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by octavian on 5/5/15.
 */
public class RVAdapterCardWithExpand extends RecyclerView.Adapter<RVAdapterCardWithExpand.ModelViewHolder> {

    ArrayList<CardInfoModel> cards;
    Context context;

    static class ModelViewHolder extends RecyclerView.ViewHolder {

        Context context;
        CardView cardViewCv=null;
        TextView titleTv=null;
        TextView dateTv =null;
        TextView timetableTv =null;
        TextView roomTv =null;
        TextView address1Tv =null;
        TextView address2Tv =null;
        ImageButton arrowIb=null;
        RelativeLayout relativeLayout=null;

        public ModelViewHolder(Context context, View itemView) {

            super(itemView);
            this.context=context;

            cardViewCv=(CardView)itemView.findViewById(R.id.cv_expand);
            titleTv=(TextView)itemView.findViewById(R.id.id_title_expand);
            dateTv =(TextView)itemView.findViewById(R.id.id_date_expand);
            timetableTv =(TextView)itemView.findViewById(R.id.id_timetable_expand);
            roomTv =(TextView)itemView.findViewById(R.id.id_room_expand);
            address1Tv =(TextView)itemView.findViewById(R.id.id_address1_expand);
            address2Tv =(TextView)itemView.findViewById(R.id.id_address2_expand);
            arrowIb=(ImageButton)itemView.findViewById(R.id.keyboard_arrow_down);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.card_expand_inner_list);

        }

        public void setBackgroundColorByModel(CardInfoModel model) {

            switch (model.getStatus()) {
                case 1:
                    relativeLayout.setBackgroundResource(R.color.custom_teal);
                    break;
                case 2:
                    relativeLayout.setBackgroundResource(R.color.custom_red);
                    break;
                case 3:
                    relativeLayout.setBackgroundResource(R.color.custom_yellow);
                    dateTv.setTextColor(context.getResources().getColor(android.R.color.black));
                    timetableTv.setTextColor(context.getResources().getColor(android.R.color.black));
                    roomTv.setTextColor(context.getResources().getColor(android.R.color.black));
                    address1Tv.setTextColor(context.getResources().getColor(android.R.color.black));
                    address2Tv.setTextColor(context.getResources().getColor(android.R.color.black));
                    break;
                default:
                    relativeLayout.setBackgroundResource(R.color.defaultColor);
                    break;
            }
        }

    }

    public RVAdapterCardWithExpand(Context context, ArrayList<CardInfoModel> cards) {
        this.context=context;
        this.cards=cards;
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i("HELLO", String.valueOf(viewType));

        //int layoutType = viewType == 1 ? R.layout.card_sample_layout_1:R.layout.card_sample_layout_2;

        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_expand, parent, false);
        ModelViewHolder mvh = new ModelViewHolder(context, v);
        return mvh;


    }

    @Override
    public void onBindViewHolder(final ModelViewHolder holder, final int position) {

        holder.titleTv.setText(cards.get(position).getTitle());
        holder.dateTv.setText(cards.get(position).getDate());
        holder.timetableTv.setText(cards.get(position).getTimetable());
        holder.roomTv.setText(cards.get(position).getRoom());
        holder.address1Tv.setText(cards.get(position).getAddress1());
        holder.address2Tv.setText(cards.get(position).getAddress2());
        holder.arrowIb.setImageResource(R.drawable.ic_keyboard_arrow_down_grey600_24dp);

        CardInfoModel model = cards.get(position);

        holder.setBackgroundColorByModel(model);

        holder.relativeLayout.setVisibility(View.GONE);

        holder.arrowIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardInfoModel model = cards.get(position);
                if (holder.relativeLayout.getVisibility() == View.GONE) {
                    holder.relativeLayout.setVisibility(View.VISIBLE);
                    holder.arrowIb.setImageResource(R.drawable.ic_keyboard_arrow_up_grey600_24dp);
                } else if (holder.relativeLayout.getVisibility() == View.VISIBLE) {
                    holder.relativeLayout.setVisibility(View.GONE);
                    holder.arrowIb.setImageResource(R.drawable.ic_keyboard_arrow_down_grey600_24dp);
                }
            }
        });

    }

    @Override
    public int getItemViewType(int position) {

        if (position % 2 == 0) {
            return 2;
        }

        return 1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



}


