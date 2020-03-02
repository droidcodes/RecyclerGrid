package com.recyclergrid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarHome;
    RecyclerView recyclerHome;

    List<Integer> imageList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    List<String> priceList = new ArrayList<>();

    List<HashMap<String,Object>> furnitureList = new ArrayList<>();
    FurnitureAdapter furnitureAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    private void initialize() {

        toolbarHome = (Toolbar)findViewById(R.id.toolbar_home);
        recyclerHome = (RecyclerView)findViewById(R.id.recycler_home);

        toolbarHome.setTitle("Furniture");
        toolbarHome.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbarHome);
    //    GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerHome.setLayoutManager(layoutManager);

        imageList.add(R.drawable.fridge);
        imageList.add(R.drawable.bookcase);
        imageList.add(R.drawable.chair);
        imageList.add(R.drawable.wardrobe);
        imageList.add(R.drawable.couch);
        imageList.add(R.drawable.washer);

        titleList.add("Fridge");
        titleList.add("Bookcase");
        titleList.add("Chair");
        titleList.add("Wardrobe");
        titleList.add("Couch");
        titleList.add("Washer");

        priceList.add("$200");
        priceList.add("$100");
        priceList.add("$15");
        priceList.add("$120");
        priceList.add("$400");
        priceList.add("$80");

        for(int i=0;i<imageList.size();i++){

            HashMap<String,Object> map = new HashMap<>();
            map.put("Image",imageList.get(i));
            map.put("Title",titleList.get(i));
            map.put("Price",priceList.get(i));

            furnitureList.add(map);

        }

        furnitureAdapter = new FurnitureAdapter(furnitureList);
        recyclerHome.setAdapter(furnitureAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_home,menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView)menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                furnitureAdapter.getFilter().filter(s);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
