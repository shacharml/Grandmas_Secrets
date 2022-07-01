package com.example.grandmassecrets.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.grandmassecrets.Objects.Group;
import com.example.grandmassecrets.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    //    private HashMap<String,Group> Groups = new HashMap<>();
    private ArrayList<Group> groups = new ArrayList<>();
    private CallBack_GroupClicked listener;
    //Constructor
    public GroupAdapter(Activity activity, ArrayList<Group> Groups) {
        this.activity = activity;
        this.groups = Groups;
    }

    public GroupAdapter setListener(CallBack_GroupClicked listener) {
        this.listener = listener;
        return this;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_card_ltr, parent, false);
        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        GroupHolder holder = (GroupHolder) viewHolder;
        //get the Group in the specific position
        Group group = getGroup(position);
        Log.d("pttt", "group- " + group.toString());
        //Update the ui
        holder.group_TXV_group_name.setText(group.getName());
        holder.group_TXV_subtitle_description.setText(group.getDescription());

        // Get the image of the Group dish from Data Storage WIth Glide
        // TODO: 29/06/2022 Check if glide work with thr uri/l image
        Glide.with(activity).load(group.getImgGroup()).into(holder.group_IMG_img_ltr);

//                Check if don't work otherwise
//                int resourceId = activity.getResources().getIdentifier(Group.getImage(), "drawable", activity.getPackageName());
//                holder.Group_IMG_image.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    // Get the Group in the specific position
    public Group getGroup(int position) {
        return groups.get(position);
    }

    public interface CallBack_GroupClicked {
        void GroupClicked(Group Group, int position);
    }

    // External class - HOLDER
    private class GroupHolder extends RecyclerView.ViewHolder {

        public ShapeableImageView group_IMG_img_ltr;
        public MaterialTextView group_TXV_group_name;
        public MaterialTextView group_TXV_subtitle_description;

        public GroupHolder(View itemView) {
            super(itemView);
            this.group_IMG_img_ltr = itemView.findViewById(R.id.group_IMG_img_ltr);
            this.group_TXV_group_name = itemView.findViewById(R.id.group_TXV_group_name_list);
            this.group_TXV_subtitle_description = itemView.findViewById(R.id.group_TXV_subtitle_description_list);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.GroupClicked(getGroup(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}