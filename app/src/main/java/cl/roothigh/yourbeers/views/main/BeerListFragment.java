package cl.roothigh.yourbeers.views.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cl.roothigh.yourbeers.R;


public class BeerListFragment extends Fragment {


    public BeerListFragment() {
        // Required empty public constructor
    }

    public static BeerListFragment newInstance() {
      return new BeerListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beer_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("beers");
        RecyclerView recycler = (RecyclerView) view;
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<String, BeersHolder>(String.class, android.R.layout.simple_list_item_1, BeersHolder.class, reference) {
            @Override
            protected void populateViewHolder(BeersHolder viewHolder, String model, int position) {
                viewHolder.setName(model);
            }
        };

        recycler.setAdapter(adapter);


    }
    public static class BeersHolder extends RecyclerView.ViewHolder {
        private final TextView mNameField;
        private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("favorites").child(uid);


        public BeersHolder(View itemView) {
            super(itemView);
            mNameField = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public void setName(final String name) {
            mNameField.setText(name);
            mNameField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reference.child(name).setValue(name);


                }
            });
        }

    }
}
