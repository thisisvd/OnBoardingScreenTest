package com.vdcodeassociate.onbroadingscreentest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.onBoardingViewHolder>{

    private List<OnBoardingItem> OnBoardingItems;

    public OnBoardingAdapter(List<OnBoardingItem> OnBoardingItems) {
        this.OnBoardingItems = OnBoardingItems;
    }

    @NonNull
    @Override
    public onBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new onBoardingViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_container_onboarding,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull onBoardingViewHolder holder, int position) {
        holder.setOnBoardingData(OnBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return OnBoardingItems.size();
    }

    public class onBoardingViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView description;


        public onBoardingViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.onBoardingImageView);
            title = itemView.findViewById(R.id.onBoardingTextTitle);
            description = itemView.findViewById(R.id.onBoardingTextDescription);

        }

        public void setOnBoardingData(OnBoardingItem onBoardingItem){
            imageView.setImageResource(onBoardingItem.getImage());
            title.setText(onBoardingItem.getTitle());
            description.setText(onBoardingItem.getDescription());
        }

    }

}
