package com.example.grandmassecrets.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.grandmassecrets.Constants.Keys;
import com.example.grandmassecrets.Firebase.DataManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grandmassecrets.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class Try1Fragment extends Fragment {

//    public interface CallBack_GroupClicked {
//        void GroupClicked(Group Group, int position);
//    }

    private View view;
    private RecyclerView listRecycler;

    private DataManager dataManager = DataManager.getInstance();
    private DatabaseReference userGroupsRef,GroupRef;

    public Try1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_try1, container, false);

        listRecycler = view.findViewById(R.id.listRecycler);
        listRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        userGroupsRef = dataManager.usersListReference().child(dataManager.getCurrentUser().getUid()).child(Keys.KEY_USER_GROUPS_IDS);
        GroupRef = dataManager.groupsListReference();
        GroupRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String rem = snapshot.getKey();
                dataManager.getCurrentUser().getGroupsIds().remove(rem);
                userGroupsRef.child(rem).removeValue();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(userGroupsRef,String.class)
                .build();


        FirebaseRecyclerAdapter<String,tryHolder> adapter = new FirebaseRecyclerAdapter<String, tryHolder>(options){
            @Override
            protected void onBindViewHolder(@NonNull tryHolder holder, int position, @NonNull String model) {

                String groupIDs = getRef(position).getKey();
                GroupRef.child(groupIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                           if (snapshot.exists()){
                               String name = snapshot.child(Keys.KEY_GROUP_NAME).getValue(String.class);
                               String des = snapshot.child(Keys.KEY_GROUP_DESCRIPTION).getValue(String.class);
                               String img = snapshot.child(Keys.KEY_GROUP_IMG).getValue(String.class);

                               holder.group_TXV_group_name.setText(name);
                               holder.group_TXV_subtitle_description.setText(des);
                               Glide.with(getActivity()).load(img).into(holder.group_IMG_img_ltr);

                               holder.itemView.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
//                                       Intent intent = new Intent(getContext(), RecipeListFragment.class);
                                       dataManager.setCurrentIdGroup(groupIDs);
                                       getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_FRG_container,new RecipeListFragment())
                                               .addToBackStack(null).commit();
//                                       intent.putExtra("Group_id", groupIDs);
//                                       intent.putExtra("Group_name", name);
//                                       startActivity(intent);

                                   }
                               });

                           }


                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @NonNull
            @Override
            public tryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_card_ltr,parent,false);
               tryHolder holder = new tryHolder(view);

                return holder;
            }
        };
        listRecycler.setAdapter(adapter);
        adapter.startListening();
    }


    public static class tryHolder extends RecyclerView.ViewHolder{

        public ShapeableImageView group_IMG_img_ltr;
        public MaterialTextView group_TXV_group_name;
        public MaterialTextView group_TXV_subtitle_description;

        public tryHolder(@NonNull View itemView) {
            super(itemView);
            this.group_IMG_img_ltr = itemView.findViewById(R.id.group_IMG_img_ltr);
            this.group_TXV_group_name = itemView.findViewById(R.id.group_TXV_group_name_list);
            this.group_TXV_subtitle_description = itemView.findViewById(R.id.group_TXV_subtitle_description_list);


        }
    }
}