package com.example.nawras.smartroute;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nawras.smartroute.Beans.Covoiturage;
import com.example.nawras.smartroute.WebMethods.ListeCovoiturageService;
import com.google.gson.Gson;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    String HeureDep,Dep,Arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        HeureDep = getIntent().getStringExtra("time");
        Dep = getIntent().getStringExtra("dep");
        Arr= getIntent().getStringExtra("arr");
        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        try
        {
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this,
                    new ListeCovoiturageService(this).execute(new Covoiturage(Dep,Arr,HeureDep)).get(), mTwoPane));
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Erreur : "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final List<Covoiturage> covoiturageList;
        private final boolean mTwoPane;

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<Covoiturage> items,
                                      boolean twoPane) {
            covoiturageList = items;
            System.out.println("DEBUG : LIST COVOITURAGE SIZE : "+items.size());
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.dep.setText(covoiturageList.get(position).getVilleDep());
            holder.arrive.setText(covoiturageList.get(position).getVilleArr());
            holder.date.setText(covoiturageList.get(position).getDateHeureDep().toString());
            holder.prix.setText(covoiturageList.get(position).getPrix().toString());
            final Covoiturage currItem = covoiturageList.get(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Covoiturage item = currItem;

                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString("currItem",new Gson().toJson(currItem));
                        //arguments.putInt(ItemDetailFragment.ARG_ITEM_ID, item.getId());
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        mParentActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra("currItem", new Gson().toJson(currItem));
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return covoiturageList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView dep;
            final TextView arrive;
            final TextView date;
            final TextView prix;

            ViewHolder(View view) {
                super(view);
                dep = (TextView) view.findViewById(R.id.dep);
                arrive = (TextView) view.findViewById(R.id.dest);
                date = (TextView) view.findViewById(R.id.date);
                prix = (TextView) view.findViewById(R.id.prix);
            }
        }
    }
}
