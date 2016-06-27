package com.example.danie.todoapp2.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danie.todoapp2.R;
import com.example.danie.todoapp2.config.DB;
import com.example.danie.todoapp2.models.BuyingItem;

import java.util.List;

/**
 * Created by danie on 25/06/2016.
 */
public class BuyingItemsAdapter extends ArrayAdapter<BuyingItem> {

    private Context context;
    private List<BuyingItem> values;

    public BuyingItemsAdapter(Context context, int textViewResourceId, List<BuyingItem> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount(){
        return this.values.size();
    }

    public BuyingItem getItem(int position){
        return this.values.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.layout_buying_item, null);
        }

        final BuyingItemsAdapter self = this;

        v.setOnTouchListener(new View.OnTouchListener() {
            private int padding = 0;
            private int initialx = 0;
            private int currentx = 0;
            private boolean horizontalSwipe = false;
            private float showIconDelta = .05f;
            private float deletingDelta = .25f;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                DisplayMetrics metrics = new DisplayMetrics();
                display.getMetrics(metrics);

                TextView textAmount = (TextView)v.findViewById(R.id.textAmount);
                ImageView iconDelete = (ImageView)v.findViewById(R.id.iconDelete);

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    padding = 0;
                    initialx = (int) event.getX();
                    currentx = (int) event.getX();
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    currentx = (int) event.getX();
                    if(currentx > initialx) {
                        padding = currentx - initialx;

                        float slidePercentage = padding / (float)metrics.widthPixels;
                        System.out.println(padding);
                        System.out.println(slidePercentage);
                        if(slidePercentage >= this.showIconDelta) {
                            this.horizontalSwipe = true;
                            textAmount.setVisibility(View.INVISIBLE);
                            iconDelete.setVisibility(View.VISIBLE);
                        }
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    textAmount.setVisibility(View.VISIBLE);
                    iconDelete.setVisibility(View.INVISIBLE);

                    float slidePercentage = padding / (float)metrics.widthPixels;
                    if(this.horizontalSwipe && slidePercentage >= this.deletingDelta) {
                        self.remove(position);
                    }

                    padding = 0;
                    initialx = 0;
                    currentx = 0;
                    this.horizontalSwipe = false;
                }

                v.setPadding(padding, 0, 0, 0);

                return true;
            }
        });

        BuyingItem item = this.getItem(position);

        if (item != null) {
            TextView textNumber = (TextView)v.findViewById(R.id.textViewNumber);
            TextView textTitle = (TextView)v.findViewById(R.id.textTaskTitle);
            TextView textAmount = (TextView)v.findViewById(R.id.textAmount);

            textNumber.setText(new Integer(position + 1).toString() + "º");
            textTitle.setText(item.getName());
            textAmount.setText(item.getAmountWithGreatness());
        }

        return v;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(this.values.get(position).getName());

        return label;
    }

    public void remove(int position) {
        super.remove(this.getItem(position));

        DB.removeItem(this.context, position);
    }
}
