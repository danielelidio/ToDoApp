package com.example.danie.todoapp2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.danie.todoapp2.adapters.BuyingItemsAdapter;
import com.example.danie.todoapp2.models.BuyingItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listViewTasks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.listViewTasks = (ListView)findViewById(R.id.listViewTasks);

        BuyingItemsAdapter buyingItemsAdapter = new BuyingItemsAdapter(this, 0, new ArrayList<BuyingItem>() {{
            add(new BuyingItem("Produto 1", "Descrição 1", 1, true));
            add(new BuyingItem("Produto 2", "Descrição 2", 2, true));
            add(new BuyingItem("Produto 3", "Descrição 3", 3, true));
            add(new BuyingItem("Produto 4", "Descrição 4", 4, true));
            add(new BuyingItem("Produto 5", "Descrição 5", 5, true));
            add(new BuyingItem("Produto 6", "Descrição 6", 6, true));
        }});

        this.listViewTasks.setAdapter(buyingItemsAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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