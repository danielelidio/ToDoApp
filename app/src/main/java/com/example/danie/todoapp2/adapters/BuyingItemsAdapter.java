package com.example.danie.todoapp2.adapters;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.danie.todoapp2.R;
import com.example.danie.todoapp2.models.BuyingItem;

/**
 * Created by danie on 25/06/2016.
 */
public class BuyingItemsAdapter extends ArrayAdapter<BuyingItem> {

    private Context context;
    private BuyingItem[] values;

    public BuyingItemsAdapter(Context context, int textViewResourceId, BuyingItem[] values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount(){
        return values.length;
    }

    public BuyingItem getItem(int position){
        return values[position];
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.layout_buying_item, null);
        }
        v.setOnTouchListener(new View.OnTouchListener() {
            private int padding = 0;
            private int initialx = 0;
            private int currentx = 0;
            private  ViewHolder viewHolder;
            private boolean running = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ( event.getAction() == MotionEvent.ACTION_DOWN) {
                    padding = 0;
                    initialx = (int) event.getX();
                    currentx = (int) event.getX();
                    viewHolder = ((ViewHolder) v.getTag());
                }
                if ( event.getAction() == MotionEvent.ACTION_MOVE) {
                    currentx = (int) event.getX();
                    padding = currentx - initialx;
                }
                if ( event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    padding = 0;
                    initialx = 0;
                    currentx = 0;
                }
                if(viewHolder != null) {
                    v.setPadding(padding, 0,0, 0);
                    Log.v("", (new Integer(padding)).toString());
                }

                return true;
            }
        });

        BuyingItem item = this.getItem(position);

        if (item != null) {
            TextView textNumber = (TextView)v.findViewById(R.id.textViewNumber);
            TextView textTitle = (TextView)v.findViewById(R.id.textTaskTitle);
            //TextView textDescription = (TextView)v.findViewById(R.id.textTaskDescription);
            EditText textAmount = (EditText)v.findViewById(R.id.editTextAmount);

            textNumber.setText(new Integer(position + 1).toString() + "º");
            textTitle.setText(item.getName());
            //textDescription.setText(item.getDescription());
            textAmount.setText("1");
        }

        return v;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getName());

        return label;
    }

}
