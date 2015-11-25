package it.octavianionel.designsupportlibrarysample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by octavian on 7/29/15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ModelViewHolder> {

    ArrayList<CardInfoModel> cards;

    static class ModelViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewCv=null;
        View photoIv=null;
        TextView titleTv=null;
        TextView dateTv =null;
        TextView timetableTv =null;
        TextView roomTv =null;
        TextView address1Tv =null;
        TextView address2Tv =null;
        ImageButton emailIb;
        ImageButton deleteIb;

        public ModelViewHolder(View itemView) {

            super(itemView);


            cardViewCv=(CardView)itemView.findViewById(R.id.cv);
            photoIv=itemView.findViewById(R.id.id_photo);
            titleTv=(TextView)itemView.findViewById(R.id.id_title);
            dateTv =(TextView)itemView.findViewById(R.id.id_date);
            timetableTv =(TextView)itemView.findViewById(R.id.id_timetable);
            roomTv =(TextView)itemView.findViewById(R.id.id_room);
            address1Tv =(TextView)itemView.findViewById(R.id.id_address1);
            address2Tv =(TextView)itemView.findViewById(R.id.id_address2);
            emailIb=(ImageButton)itemView.findViewById(R.id.id_email);
            deleteIb=(ImageButton)itemView.findViewById(R.id.id_delete);


        }

        public void setBackgroundColorByModel(CardInfoModel model) {

            switch (model.getStatus()) {
                case 1:
                    photoIv.setBackgroundResource(android.R.color.holo_blue_dark);
                    break;
                case 2:
                    photoIv.setBackgroundResource(R.color.custom_red);
                    break;
                case 3:
                    photoIv.setBackgroundResource(R.color.custom_yellow);
                    break;
                default:
                    photoIv.setBackgroundResource(R.color.defaultColor);
                    break;
            }
        }
    }

    public RVAdapter(ArrayList<CardInfoModel> cards) {
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

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        ModelViewHolder mvh = new ModelViewHolder(v);
        return mvh;


    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, final int position) {

        holder.titleTv.setText(cards.get(position).getTitle());
        holder.dateTv.setText(cards.get(position).getDate());
        holder.timetableTv.setText(cards.get(position).getTimetable());
        holder.roomTv.setText(cards.get(position).getRoom());
        holder.address1Tv.setText(cards.get(position).getAddress1());
        holder.address2Tv.setText(cards.get(position).getAddress2());

        CardInfoModel model = cards.get(position);

        holder.setBackgroundColorByModel(model);

        holder.emailIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardInfoModel model = cards.get(position);
                Toast.makeText(v.getContext(), "Hello, this is email icon of card " + position + " with Title: " + model.getTitle(), Toast.LENGTH_SHORT).show();

            }
        });
        holder.deleteIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Hello, this is delete icon of card " + position, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                builder1.setMessage("Are you sure you want to cancel card number #"+position+"?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                CardInfoModel model = cards.get(position);

                                // deleting from model
                                cards.remove(position);

                                //
                                notifyItemRemoved(position);

                            }
                        });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();


            }
        });

//        if (position%2==0) {
//            holder.photoIv.setBackgroundResource(R.color.yellow);
//        }
//        else {
//            holder.photoIv.setBackgroundResource(R.color.blue);
//        }

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


