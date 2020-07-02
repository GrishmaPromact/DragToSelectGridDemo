package com.grishma.dragselection.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grishma.dragselection.R;
import com.grishma.dragselection.model.DataModel;
import com.grishma.dragselection.viewmodel.SelectViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    private DragSelectTouchListener touchListener;

    private Button btnSave;
    private Button btnCancel;
    private SelectViewModel viewModel;
    private static final int ITEM_COUNT = 200;
    private List<DataModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(SelectViewModel.class);


        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        items = new ArrayList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            items.add(new DataModel(false, i));
        }
        mRecyclerView = findViewById(R.id.recyclerView);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 7);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(this, items);
        mRecyclerView.setAdapter(adapter);

        //update values of adapter
        viewModel.getAllSelectedItems().observe(this, dataModels -> {
            if(!dataModels.isEmpty()){
                items.clear();
                items.addAll(dataModels);
            }
            adapter.notifyDataSetChanged();
        });

        adapter.setLongClickListener(v -> {
            int position = mRecyclerView.getChildAdapterPosition(v);
            adapter.setSelected(position, true);
            touchListener.setStartSelectPosition(position);
            return false;
        });

        adapter.setClickListener(v -> {

            int position = mRecyclerView.getChildAdapterPosition(v);
            DataModel dataModel = adapter.getItem(position);
            if (dataModel.isSelected()) {
                adapter.setSelected(position, false);
            } else {
                adapter.setSelected(position, true);
            }
        });

        touchListener = new DragSelectTouchListener();

        mRecyclerView.addOnItemTouchListener(touchListener);


        touchListener.setSelectListener((start, end, isSelected) -> {
            adapter.selectRangeChange(start, end, isSelected);
        });


        btnSave.setOnClickListener(v -> {
            viewModel.insert(adapter.getModifyList());
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }

}
