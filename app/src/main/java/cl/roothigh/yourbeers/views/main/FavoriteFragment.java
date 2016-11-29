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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    private String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public FavoriteFragment() {
        // Required empty public constructor
    }


    public static FavoriteFragment newInstance() {

        return new FavoriteFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorite, container, false);

        //TODO do exatcly the same than in the other fragment, but change the reference using the uid
        //TODO for removing favorites do this inside the click listener reference.child(name).removeValue();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("favorites").child(uid);
        RecyclerView recycler = (RecyclerView) view;
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<String, BeersHolder>(String.class, android.R.layout.simple_list_item_1,BeersHolder.class, reference) {
            @Override
            protected void populateViewHolder(BeersHolder viewHolder, String model, int position) {
                viewHolder.setName(model,uid);
            }
        };

        recycler.setAdapter(adapter);
    }

    public static class BeersHolder extends RecyclerView.ViewHolder {
        private final TextView mNameField;
        private DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("favorites");


        public BeersHolder(View itemView) {
            super(itemView);
            mNameField = (TextView) itemView.findViewById(android.R.id.text1);
        }

        public void setName(final String name, final String uid) {
            mNameField.setText(name);
            mNameField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reference.child(uid).child(name).removeValue();
                }
            });
        }
    }
}
