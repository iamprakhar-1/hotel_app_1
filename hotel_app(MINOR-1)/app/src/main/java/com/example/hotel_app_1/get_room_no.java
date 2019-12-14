package com.example.hotel_app_1;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

public class get_room_no extends AppCompatActivity {

    TextView res1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_room_no);

        res1=(TextView)findViewById(R.id.tv1);


        class call_api
        {
            public void postRequest() throws IOException {

                MediaType MEDIA_TYPE = MediaType.parse("application/json");
                String url = "https://utgz0jd2ak.execute-api.eu-central-1.amazonaws.com/Production/getroomno";

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
                        res1.setText("Your Room No. is "+ans);
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

