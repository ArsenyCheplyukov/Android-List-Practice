package com.arsenyvoid.listpractice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    // on below line we are creating variables.
    private ListView lv;
    private Button addBtn;
    private Button stopBtn;
    private Button add_actionBtn;
    private EditText enterActionName;
    private EditText enterDelay;
    private EditText enterX;
    private EditText enterY;
    private EditText enterZ;
    private TextView name;

    // DONT'T KNOW IS IT RIGHT
    private Context context_for_adapter = this;

    private ArrayList<ActionModel> actionList = new ArrayList<>();

    private int position_of_points = 0;

    private ArrayList<String> list_names = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Integer>>> data = new ArrayList<>();
    private MyListAdaper adapter;
    private ActionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on below line we are initializing our variables.
        lv = (ListView) findViewById(R.id.listview);
        stopBtn = findViewById(R.id.stop_action);
        add_actionBtn = findViewById(R.id.add_action);
        enterActionName = findViewById(R.id.Enter_action_name);
        addBtn = findViewById(R.id.idBtnAdd);
        enterDelay = findViewById(R.id.Enter_delay);
        enterX = findViewById(R.id.Enter_X);
        enterY = findViewById(R.id.Enter_Y);
        enterZ = findViewById(R.id.Enter_Z);
        name = findViewById(R.id.name);

        // on below line we are adding items to our list
        ActionModel action0 = new ActionModel("C++", 20);
        actionList.add(action0);
        ArrayList<Integer> inner11 = new ArrayList<Integer>();
        ArrayList<Integer> inner12 = new ArrayList<Integer>();
        ArrayList<Integer> inner13 = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> inner1 = new ArrayList<>();
        inner11.add(1); inner11.add(2); inner11.add(3); inner11.add(4);
        inner1.add(inner11);
        inner12.add(8); inner12.add(7); inner12.add(6); inner12.add(5);
        inner1.add(inner12);
        inner13.add(9); inner13.add(10); inner13.add(11); inner13.add(12);
        inner1.add(inner13);
        data.add(inner1);

        ActionModel action1 = new ActionModel("Python", 100);
        actionList.add(action1);
        ArrayList<Integer> inner21 = new ArrayList<Integer>();
        ArrayList<Integer> inner22 = new ArrayList<Integer>();
        ArrayList<Integer> inner23 = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> inner2 = new ArrayList<>();
        inner21.add(1); inner21.add(2); inner21.add(3); inner21.add(4);
        inner2.add(inner21);
        inner22.add(8); inner22.add(7); inner22.add(6); inner22.add(5);
        inner2.add(inner22);
        inner23.add(9); inner23.add(10); inner23.add(11); inner23.add(12);
        inner2.add(inner23);
        data.add(inner2);

        // on the below line we are initializing the adapter for our list view.
        adapter = new MyListAdaper(this, R.layout.customlayout, data.get(position_of_points));
        lv.setAdapter(adapter);

        // on below line we are setting adapter for our list view.
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new ActionAdapter(actionList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        // on below line we are adding click listener for our button.
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting text from edit text
                String staff_x = enterX.getText().toString();
                String staff_y = enterY.getText().toString();
                String staff_z = enterZ.getText().toString();
                String staff_delay = enterDelay.getText().toString();
                int x = 0, y = 0, z = 0, delay = 0;
                if ((!staff_x.isEmpty()) && (!staff_y.isEmpty()) && (!staff_z.isEmpty()) &&
                        (!staff_delay.isEmpty())) {
                    x = Integer.parseInt(staff_x);
                    y = Integer.parseInt(staff_y);
                    z = Integer.parseInt(staff_z);
                    delay = Integer.parseInt(staff_delay);
                }

                // on below line we are checking if item is not empty
                ArrayList<Integer> staff = new ArrayList<Integer>();
                staff.add(x); staff.add(y); staff.add(z); staff.add(delay);
                data.get(position_of_points).add(staff);
                // on below line we are notifying adapter
                // that data in list is updated to
                // update our list view.
                adapter.notifyDataSetChanged();
            }
        });

        add_actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting text from edit text
                String item = enterActionName.getText().toString();
                if (!item.isEmpty()) {
                    // on below line we are checking if item is not empty
                    ActionModel model0 = new ActionModel(item, 100);
                    actionList.add(model0);
                    position_of_points = actionList.size() - 1;
                    // on below line we are notifying adapter
                    // that data in list is updated to
                    // update our list view.
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private class MyListAdaper extends ArrayAdapter<ArrayList<Integer>> {
        private int layout;
        private ArrayList<ArrayList<Integer>> mObjects;
        private MyListAdaper(Context context, int resource, ArrayList<ArrayList<Integer>> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title_x = (TextView) convertView.findViewById(R.id.x_axis_title);
                viewHolder.title_y = (TextView) convertView.findViewById(R.id.y_axis_title);
                viewHolder.title_z = (TextView) convertView.findViewById(R.id.z_axis_title);
                viewHolder.title_delay = (TextView) convertView.findViewById(R.id.delay_title);
                viewHolder.delay_dim = (TextView) convertView.findViewById(R.id.delay_dim);
                viewHolder.data_delay = (TextView) convertView.findViewById(R.id.delay_data);
                viewHolder.data_x = (TextView) convertView.findViewById(R.id.x_data);
                viewHolder.data_y = (TextView) convertView.findViewById(R.id.y_data);
                viewHolder.data_z = (TextView) convertView.findViewById(R.id.z_data);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
                viewHolder.up = (Button) convertView.findViewById(R.id.up_buttons);
                viewHolder.down = (Button) convertView.findViewById(R.id.down_buttons);
                viewHolder.delete = (Button) convertView.findViewById(R.id.delete_buttons);
                viewHolder.edit = (Button) convertView.findViewById(R.id.edit_buttons);
                convertView.setTag(viewHolder);
            }
            mainViewholder = (ViewHolder) convertView.getTag();
            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
                }
            });
            mainViewholder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mObjects.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });

            mainViewholder.up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position > 0 && mObjects.size() > 1) {
                        Collections.swap(mObjects, position, position-1);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

            mainViewholder.down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((position < (mObjects.size() - 1)) && mObjects.size() > 1) {
                        Collections.swap(mObjects, position, position+1);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

            mainViewholder.data_x.setText(mObjects.get(position).get(0).toString());
            mainViewholder.data_y.setText(mObjects.get(position).get(1).toString());
            mainViewholder.data_z.setText(mObjects.get(position).get(2).toString());
            mainViewholder.data_delay.setText(mObjects.get(position).get(3).toString());

            return convertView;
        }
    }

    public class ViewHolder {
        ImageView thumbnail;
        TextView title_x;
        TextView title_y;
        TextView title_z;
        TextView title_delay;
        TextView delay_dim;
        TextView data_x;
        TextView data_y;
        TextView data_z;
        TextView data_delay;
        Button button;
        Button up;
        Button down;
        Button delete;
        Button edit;
    }

    public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.MyViewHolder> {
        private List<ActionModel> actionList;
        TextView action_name, action_full_delay; Button button;
        class MyViewHolder extends RecyclerView.ViewHolder {
            MyViewHolder(View view) {
                super(view);
                action_name = view.findViewById(R.id.action_item_name);
                action_full_delay = view.findViewById(R.id.action_item_total_time);
                button = view.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position_of_points = getAdapterPosition();
                        adapter = new MyListAdaper(context_for_adapter, R.layout.customlayout, data.get(position_of_points));
                        lv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        }
        public ActionAdapter(List<ActionModel> actionList) {
            this.actionList = actionList;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list, parent, false);
            return new MyViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ActionModel movie = actionList.get(position);
            action_name.setText(movie.getName());
            action_full_delay.setText(Integer.toString(movie.getDelay()));
        }
        @Override
        public int getItemCount() {
            return actionList.size();
        }
    }


    public class ActionModel {
        private String name;
        private int delay;
        public ActionModel() {
        }
        public ActionModel(String title, int delay) {
            this.name = title;
            this.delay = delay;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getDelay() {
            return delay;
        }
        public void setDelay(int delay) {
            this.delay = delay;
        }
    }
    

}