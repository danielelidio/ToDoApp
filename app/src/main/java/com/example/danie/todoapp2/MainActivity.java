package com.example.danie.todoapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.danie.todoapp2.adapters.BuyingItemsAdapter;
import com.example.danie.todoapp2.config.Config;
import com.example.danie.todoapp2.config.DB;
import com.example.danie.todoapp2.models.BuyingItem;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listViewTasks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Config.tryInitializeDBSampleData(this);

        this.listViewTasks = (ListView)findViewById(R.id.listViewTasks);

        this.prepareList();

        final MainActivity self = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(self, AddProductActivity.class);
                startActivity(i);
            }
        });
    }

    private void prepareList() {
        try {
            JSONArray json = DB.loadDB(this);

            ArrayList<BuyingItem> items = new ArrayList<BuyingItem>();
            for (int i = 0; i < json.length(); i++) {
                items.add(BuyingItem.fromJSON(json.getJSONObject(i)));
            }

            BuyingItemsAdapter buyingItemsAdapter = new BuyingItemsAdapter(this, 0, items);

            this.listViewTasks.setAdapter(buyingItemsAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.prepareList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}