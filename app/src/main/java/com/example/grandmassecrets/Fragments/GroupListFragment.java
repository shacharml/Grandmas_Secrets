package com.example.grandmassecrets.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.grandmassecrets.Activities.CreateGroupActivity;
import com.example.grandmassecrets.Adapters.GroupAdapter;
import com.example.grandmassecrets.Firebase.DataManager;
import com.example.grandmassecrets.Firebase.FireStorage;
import com.example.grandmassecrets.Objects.Group;
import com.example.grandmassecrets.Objects.User;
import com.example.grandmassecrets.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupListFragment extends Fragment {

    private DataManager dataManager = DataManager.getInstance();
    private FireStorage fireStorage = FireStorage.getInstance();
//    private FirebaseFirestore db = dataManager.getDbFireStore();
    private User currentUser = dataManager.getCurrentUser();

    private RecyclerView groups_fragment_RECYC_all_groups;
//    private HashMap<String ,String > groupsIds;
    private ArrayList<Group> groupsArray = new ArrayList<>();
    private GroupAdapter groupAdapter;

    private FloatingActionButton main_FAB_fab;
    private MaterialToolbar main_TOB_up;



    private Activity currentActivity;
    
    public GroupListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActivity = getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_groups, container, false);
        
        findViews(view);
        groups_fragment_RECYC_all_groups.setLayoutManager(new LinearLayoutManager(getContext()));

        createUIRecycler();
//        dataManager.readUser();
        groupListListener();
//        groupAdapter.notifyDataSetChanged();

        return view;
    }

    /**
     * When the Group list has been changed on the database
     * we need to show the new data after changes on the
     * recycle view - fragment
     */
    private void groupListListener() {
        DatabaseReference ref = dataManager.groupsListReference();
        //if something on the groups change
        // 1) need to check it is connected to current user
        // 1.2) if connected change the view
        //ADD LISTENER TO GROUPS

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                groupsArray.clear();
                for (DataSnapshot groupReference : snapshot.getChildren()){
                    Group temp = groupReference.getValue(Group.class);
                    Log.d("100", temp.toString());
//                    String groupId = groupReference.child(Keys.KEY_GROUP_ID).getValue(String.class);
//                    Log.d("100", groupId.toString());
                    groupsArray.add(temp);
//                    if (temp!=null && currentUser.getGroupsIds().containsKey(temp.getIdGroup())){
//                      }
                }

               // groupAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Group temp = snapshot.getValue(Group.class);
//                Log.d("add",temp.toString());
//
//                if (temp.getUsersIds() != null && dataManager.getCurrentUser().getGroupsIds().containsKey(temp.getIdGroup())) {
//                    //The new group contains this user -- need to update ui
//                    groupsArray.add(temp);
////                    dataManager.readUser();
//                    groupAdapter.notifyItemInserted(groupsArray.indexOf(temp));
//                    groupAdapter.notifyDataSetChanged();
//                    // TODO: 01/07/2022 check if need the otter way user contain group
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Group temp = snapshot.getValue(Group.class);
//                Log.d("chang",temp.toString());
//
//                if (dataManager.getCurrentUser().getGroupsIds().containsKey(temp.getIdGroup())) {
//                    for (int i = 0 ; i<groupsArray.size() ;i++){
//                        if(groupsArray.get(i).getIdGroup().equals(temp.getIdGroup())){
//                            groupsArray.get(i).setImgGroup(temp.getImgGroup());
//                            groupsArray.get(i).setName(temp.getName());
//                            groupsArray.get(i).setDescription(temp.getDescription());
//                            groupsArray.get(i).setRecipesIds(temp.getRecipesIds());
//                            groupsArray.get(i).setUsersIds(temp.getUsersIds());
//                            groupAdapter.notifyItemChanged(i);
//                            groupAdapter.notifyDataSetChanged();
//                            return;
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                Group temp = snapshot.getValue(Group.class);
//                Log.d("remove",temp.toString());
//                if (dataManager.getCurrentUser().getGroupsIds().containsKey(temp.getIdGroup())) {
//                    int position = groupsArray.indexOf(temp);
//                    groupsArray.remove(temp);
//                    dataManager.getCurrentUser().getGroupsIds().remove(temp.getIdGroup());
//                    dataManager.updateUserDatabase();
//                    groupAdapter.notifyItemRemoved(position);
//                    groupAdapter.notifyItemRangeChanged(position, groupsArray.size());
//                }
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        groups_fragment_RECYC_all_groups.setAdapter(new GroupAdapter(this.getActivity(),groupsArray));
        createUIRecycler();
    }

    private void createUIRecycler() {
//        groupsArray = new ArrayList<>();
//        groupsArray.add(new  Group( "name",  "description",  "groupCreator"));
        groupAdapter = new GroupAdapter(this.getActivity(),groupsArray);
//
//        groups_fragment_RECYC_all_groups.setLayoutManager(new GridLayoutManager(this.getContext(), 1));
//        groups_fragment_RECYC_all_groups.setHasFixedSize(true);
//        groups_fragment_RECYC_all_groups.setItemAnimator(new DefaultItemAnimator());
//        groups_fragment_RECYC_all_groups.setAdapter(groupAdapter);

        groupAdapter.setListener(new GroupAdapter.CallBack_GroupClicked() {

            /**
             * When the Group Were clicked we wand to show
             * All the recipes included
             * @param group
             * @param position
             */
            @Override
            public void GroupClicked(Group group, int position) {
                //update Data Manager in current Group
                dataManager.setCurrentIdGroup(group.getIdGroup());
                dataManager.setCurrentGroupCreator(group.getGroupCreator());
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_FRG_container, RecipeListFragment.class, null)
                        .addToBackStack("tag")
                        .commit();
            }
        });

        // TODO: 30/06/2022 add long press action - to delete
    }

    private void findViews(View view) {
        groups_fragment_RECYC_all_groups = view.findViewById(R.id.groups_fragment_RECYC_all_groups);
        main_FAB_fab = currentActivity.findViewById(R.id.main_FAB_fab);
        main_TOB_up = currentActivity.findViewById(R.id.main_TOB_up);
        main_TOB_up.setTitle("All Groups");
    }

    @Override
    public void onStart() {
        super.onStart();
        /**
         * Add button clicked = Move to add new Group activity
         * this activity wont close
         */
        main_FAB_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(currentActivity, "Add Group to Group Lists", Toast.LENGTH_LONG).show();
                /**
                 * Open Create new Group activity
                 */
                startActivity(new Intent(currentActivity, CreateGroupActivity.class));
            }
        });
    }


}