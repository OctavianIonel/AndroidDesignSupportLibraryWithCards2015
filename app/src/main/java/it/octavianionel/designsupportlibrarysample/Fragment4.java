package it.octavianionel.designsupportlibrarysample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by octavian on 11/25/15.
 */
public class Fragment4 extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CardInfoModel> cards;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        cards=new ArrayList<CardInfoModel>();
        for (int i=0;i<15;i++) {
            CardInfoModel cardInfoModel = new CardInfoModel(0, "The quick brown fox jumps over the lazy dog #" + Integer.toString(i), "Date", "Timetable", "Room X", "Address 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            if (i<=3) {
                cardInfoModel.setStatus(2);
            } else if (i==4) {
                cardInfoModel.setStatus(3);
            } else {
                cardInfoModel.setStatus(1);
            }
            cards.add(cardInfoModel);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_card_layout, container, false);

        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final RVAdapterCardWithExpand adapter = new RVAdapterCardWithExpand(getActivity().getApplicationContext(), cards);
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark, android.R.color.holo_purple, android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                }, 3000);


            }
        });


        return view;
    }
}
