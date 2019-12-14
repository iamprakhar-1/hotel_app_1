package com.example.hotel_app_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onClickB4(View view)
    {
        Intent i4 = new Intent(this,get_room_no.class);
        startActivity(i4);
    }
    public void onClickB1(View view)
    {
        Intent i1 = new Intent(this,call_the_waiter.class);
        startActivity(i1);
    }
    public void onClickB2(View view)
    {
        Intent i2 = new Intent(this,see_camera.class);
        startActivity(i2);
    }
    public void onClickB3(View view)
    {
        Intent i3 = new Intent(this,order_food.class);
        startActivity(i3);
    }
}
