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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by Reply on 16/01/2019.
 */
public class Fragment5 extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<ProgressbarModel> items;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        items = new ArrayList<ProgressbarModel>();

        ProgressbarModel p1 = new ProgressbarModel("Title 1", BigDecimal.valueOf(200), BigDecimal.valueOf(90), BigDecimal.valueOf(100));
        ProgressbarModel p2 = new ProgressbarModel("Title 2", BigDecimal.valueOf(196), BigDecimal.valueOf(10), BigDecimal.valueOf(130));
        ProgressbarModel p3 = new ProgressbarModel("Title 3", BigDecimal.valueOf(164), BigDecimal.valueOf(40), BigDecimal.valueOf(30));
        ProgressbarModel p4 = new ProgressbarModel("Title 4", BigDecimal.valueOf(89), BigDecimal.valueOf(30), BigDecimal.valueOf(70));
        ProgressbarModel p5 = new ProgressbarModel("Title 5", BigDecimal.valueOf(120), BigDecimal.valueOf(50), BigDecimal.valueOf(20));
        ProgressbarModel p6 = new ProgressbarModel("Title 6", BigDecimal.valueOf(200), BigDecimal.valueOf(90), BigDecimal.valueOf(100));
        ProgressbarModel p7 = new ProgressbarModel("Title 7", BigDecimal.valueOf(196), BigDecimal.valueOf(10), BigDecimal.valueOf(130));
        ProgressbarModel p8 = new ProgressbarModel("Title 8", BigDecimal.valueOf(164), BigDecimal.valueOf(40), BigDecimal.valueOf(30));
        ProgressbarModel p9 = new ProgressbarModel("Title 9", BigDecimal.valueOf(89), BigDecimal.valueOf(30), BigDecimal.valueOf(70));
        ProgressbarModel p10 = new ProgressbarModel("Title 10", BigDecimal.valueOf(120), BigDecimal.valueOf(50), BigDecimal.valueOf(20));
        ProgressbarModel p11 = new ProgressbarModel("Title 11", BigDecimal.valueOf(200), BigDecimal.valueOf(90), BigDecimal.valueOf(100));
        ProgressbarModel p12 = new ProgressbarModel("Title 12", BigDecimal.valueOf(196), BigDecimal.valueOf(10), BigDecimal.valueOf(130));
        ProgressbarModel p13 = new ProgressbarModel("Title 13", BigDecimal.valueOf(164), BigDecimal.valueOf(40), BigDecimal.valueOf(30));
        ProgressbarModel p14 = new ProgressbarModel("Title 14", BigDecimal.valueOf(89), BigDecimal.valueOf(30), BigDecimal.valueOf(70));
        ProgressbarModel p15 = new ProgressbarModel("Title 15", BigDecimal.valueOf(120), BigDecimal.valueOf(50), BigDecimal.valueOf(20));
        ProgressbarModel p16 = new ProgressbarModel("Title 16", BigDecimal.valueOf(200), BigDecimal.valueOf(90), BigDecimal.valueOf(100));
        ProgressbarModel p17 = new ProgressbarModel("Title 17", BigDecimal.valueOf(196), BigDecimal.valueOf(10), BigDecimal.valueOf(130));
        ProgressbarModel p18 = new ProgressbarModel("Title 18", BigDecimal.valueOf(164), BigDecimal.valueOf(40), BigDecimal.valueOf(30));
        ProgressbarModel p19 = new ProgressbarModel("Title 19", BigDecimal.valueOf(89), BigDecimal.valueOf(30), BigDecimal.valueOf(70));
        ProgressbarModel p20 = new ProgressbarModel("Title 20", BigDecimal.valueOf(120), BigDecimal.valueOf(50), BigDecimal.valueOf(20));
        ProgressbarModel p21 = new ProgressbarModel("Title 21", BigDecimal.valueOf(200), BigDecimal.valueOf(90), BigDecimal.valueOf(100));
        ProgressbarModel p22 = new ProgressbarModel("Title 22", BigDecimal.valueOf(196), BigDecimal.valueOf(10), BigDecimal.valueOf(130));
        ProgressbarModel p23 = new ProgressbarModel("Title 22", BigDecimal.valueOf(300), BigDecimal.valueOf(50), BigDecimal.valueOf(11));

        items.add(p1);
        items.add(p2);
        items.add(p3);
        items.add(p4);
        items.add(p5);
        items.add(p6);
        items.add(p7);
        items.add(p8);
        items.add(p9);
        items.add(p10);
        items.add(p11);
        items.add(p12);
        items.add(p13);
        items.add(p14);
        items.add(p15);
        items.add(p16);
        items.add(p17);
        items.add(p18);
        items.add(p19);
        items.add(p20);
        items.add(p21);
        items.add(p22);
        items.add(p23);

    }

    public BigDecimal calculateAmount(BigDecimal amount, BigDecimal totalAmount) {
        return (amount.multiply(amount).divide(totalAmount, RoundingMode.HALF_UP));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_card_layout, container, false);

        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final RVAdapterProgressbar adapter = new RVAdapterProgressbar(getActivity().getApplicationContext(), items);
        recyclerView.setAdapter(adapter);
        int position = layoutManager.findFirstVisibleItemPosition();
        recyclerView.getRecycledViewPool().setMaxRecycledViews(adapter.getItemViewType(position), 0);
        adapter.notifyDataSetChanged();

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
