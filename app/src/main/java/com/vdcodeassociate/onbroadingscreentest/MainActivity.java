package com.vdcodeassociate.onbroadingscreentest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout linearLayoutIndicators;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutIndicators = findViewById(R.id.onBoardingLinearLayoutIndicator);
        button = findViewById(R.id.onBoardingButton);

        setUpOnBoardingData();

        ViewPager2 onBoardingViewPager2 = findViewById(R.id.onBoardingViewPager);
        onBoardingViewPager2.setAdapter(onBoardingAdapter);

        setLinearLayoutIndicators();
        setCurrentOnBoardingIndicator(0);

        onBoardingViewPager2.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        setCurrentOnBoardingIndicator(position);
                    }
                }
        );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onBoardingViewPager2.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()){
                    onBoardingViewPager2.setCurrentItem(onBoardingViewPager2.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();
                }
            }
        });

    }

    private void setUpOnBoardingData(){

        List<OnBoardingItem> onBoardingItemList = new ArrayList<>();

        OnBoardingItem onBoardingItem1 = new OnBoardingItem(R.drawable.img1,"Easy Payment's",
                "Electric payment is feature of online, mobile and telephone banking.");

        OnBoardingItem onBoardingItem2 = new OnBoardingItem(R.drawable.img2,"One click Statement's!",
                "Debit or reversal validation. If you're the sender, match the UPI transaction ID seen in the " +
                        "Google Pay app to your bank statement..");

        OnBoardingItem onBoardingItem3 = new OnBoardingItem(R.drawable.img3,"24/7 contact...",
                "24/7 or 24-7 service (usually pronounced \"twenty-four seven\") is service that is available " +
                        "at any time and usually, every day.");

        onBoardingItemList.add(onBoardingItem1);
        onBoardingItemList.add(onBoardingItem2);
        onBoardingItemList.add(onBoardingItem3);

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItemList);

    }

    private void setLinearLayoutIndicators(){

        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8,0,8,0);
        for(int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            linearLayoutIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnBoardingIndicator(int index){
        int childCount = linearLayoutIndicators.getChildCount();
        for (int i=0;i<childCount;i++){
            ImageView imageView = (ImageView) linearLayoutIndicators.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active));
            }else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
            }
        }

        if(index == onBoardingAdapter.getItemCount() - 1){
            button.setText("Start");
        }else {
            button.setText("Next");
        }

    }

}