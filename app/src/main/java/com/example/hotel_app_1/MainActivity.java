package com.example.hotel_app_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "prakharApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
    }

    public void onClickB1(View view)
    {
        Intent i1 = new Intent(this,doorLock.class);
        startActivity(i1);
    }

    public void onClickB2(View view)
    {
        Intent i2 = new Intent(this,wifi_pass.class);
        startActivity(i2);
    }

    public void onClickB3(View view)
    {
        Intent i3 = new Intent(this,callWaiter.class);
        startActivity(i3);
    }

    public void onClickB4(View view)
    {
        Intent i4 = new Intent(this,doorCamera.class);
        startActivity(i4);
    }

    public void onClickB5(View view)
    {
        Intent i5 = new Intent(this,orderFood.class);
        startActivity(i5);
    }

    public void onClickB6(View view)
    {
        Intent i6 = new Intent(this,getBill.class);
        startActivity(i6);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
