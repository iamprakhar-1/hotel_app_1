package com.example.hotel_app_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class call_the_waiter extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_the_waiter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        try {

            Runnable runnable1 = new Runnable() {
                public void run() {
                    OkHttpClient client = new OkHttpClient();

                    RequestBody formBody = new FormBody.Builder()
                            .add("call_message", "Please call the waiter")
                            .build();
                    Request request = new Request.Builder()
                            .url("https://webhook.site/24996fd9-fe05-414f-94c7-feab0ace50e3")
                            .post(formBody)
                            .build();

                    try {
                        Response response = client.newCall(request).execute();

                        // Do something with the response.
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            };
            Thread mythread = new Thread(runnable1);
            mythread.start();





        }
        catch (Exception e) {
            Log.d("y","some error");
        }


        //############################
        class call_api
        {
            public void postRequest() throws IOException {

                MediaType MEDIA_TYPE = MediaType.parse("application/json");
                String url = "https://yok9rw34gk.execute-api.eu-central-1.amazonaws.com/Production/callwaiter2";

                OkHttpClient client = new OkHttpClient();

                JSONObject postdata = new JSONObject();
                try {
                    postdata.put("username", "aneh");
                    postdata.put("password", "12345");
                } catch(JSONException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                String s="{\"body\":\"{\\\"username\\\": \\\"devesh\\\"}\"}";
                RequestBody body = RequestBody.create(MEDIA_TYPE, s);

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        String mMessage = e.getMessage().toString();
                        Log.w("failure Response", mMessage);
                        //call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String mMessage = response.body().string();
                        Log.d("tag1", mMessage);

                        int index1=mMessage.indexOf("body");
                        String ans= mMessage.substring(index1+7,index1+10);
                        Log.d("msgtg",ans);
                        //res1.setText("Your Room No. is "+ans);
                    }
                });
            }
        }

        call_api o1 = new call_api();
        try {
            o1.postRequest();
        }catch (Exception e)
        {

        }


    }
}
