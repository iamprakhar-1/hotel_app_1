package com.example.hotel_app_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.auth.core.IdentityHandler;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
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

public class order_food extends AppCompatActivity {

    private CheckBox t1,t2,t3,t4,t5;
    private Button b1;
    private TextView tv;
    private ArrayList<String> order_list;
    String food,s_empty="",currentDate,currentTime,user,ans;

    //################
    private AWSCredentialsProvider credentialsProvider;
    private AWSConfiguration configuration;
    // Declare a DynamoDBMapper object
    DynamoDBMapper dynamoDBMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        t1=findViewById(R.id.checkBox1);
        t2=findViewById(R.id.checkBox2);
        t3=findViewById(R.id.checkBox3);
        t4=findViewById(R.id.checkBox4);
        t5=findViewById(R.id.checkBox5);

        b1=findViewById(R.id.button);
        tv=findViewById(R.id.tv1);
        tv.setEnabled(false);
        order_list = new ArrayList<>();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.isChecked()) { order_list.add("Chole Bhature"); }
                else { order_list.remove("Chole Bhature"); }
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t2.isChecked()) { order_list.add("Coke"); }
                else { order_list.remove("Coke"); }
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t3.isChecked()) { order_list.add("Shaahi Paneer"); }
                else { order_list.remove("Shaahi Paneer"); }
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t4.isChecked()) { order_list.add("Dal Makhani"); }
                else { order_list.remove("Dal Makhani"); }
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t5.isChecked()) { order_list.add("Butter Naan"); }
                else { order_list.remove("Butter Naan"); }
            }
        });

        //##########################################################
        AWSMobileClient.getInstance().initialize(this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {

                // Obtain the reference to the AWSCredentialsProvider and AWSConfiguration objects
                credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
                configuration = AWSMobileClient.getInstance().getConfiguration();

                // Use IdentityManager#getUserID to fetch the identity id.
                IdentityManager.getDefaultIdentityManager().getUserID(new IdentityHandler() {
                    @Override
                    public void onIdentityId(String identityId) {
                        Log.d("YourMainActivity", "Identity ID = " + identityId);

                        // Use IdentityManager#getCachedUserID to
                        //  fetch the locally cached identity id.
                        final String cachedIdentityId =
                                IdentityManager.getDefaultIdentityManager().getCachedUserID();
                    }

                    @Override
                    public void handleError(Exception exception) {
                        Log.d("YourMainActivity", "Error in retrieving the identity" + exception);
                    }
                });
            }
        }).execute();

        /* .. more code */

        // AWSMobileClient enables AWS user credentials to access your table
        AWSMobileClient.getInstance().initialize(this).execute();

        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        user = AWSMobileClient.getInstance().getUsername();

        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();


        // Add code to instantiate a AmazonDynamoDBClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();

        // other activity code ...

        //Table class
        @DynamoDBTable(tableName = "hotelapp-mobilehub-1500633856-food_orders")

        class FoodOrdersDO {
            private String _userId;
            private String _date;
            private String _delivered;
            private String _order;
            private String _roomNo;
            private String _time;
            private String _username;

            @DynamoDBHashKey(attributeName = "userId")
            @DynamoDBAttribute(attributeName = "userId")
            public String getUserId() {
                return _userId;
            }

            public void setUserId(final String _userId) {
                this._userId = _userId;
            }
            @DynamoDBAttribute(attributeName = "date")
            public String getDate() {
                return _date;
            }

            public void setDate(final String _date) {
                this._date = _date;
            }
            @DynamoDBAttribute(attributeName = "delivered")
            public String getDelivered() {
                return _delivered;
            }

            public void setDelivered(final String _delivered) {
                this._delivered = _delivered;
            }
            @DynamoDBAttribute(attributeName = "order")
            public String getOrder() {
                return _order;
            }

            public void setOrder(final String _order) {
                this._order = _order;
            }
            @DynamoDBAttribute(attributeName = "room_no")
            public String getRoomNo() {
                return _roomNo;
            }

            public void setRoomNo(final String _roomNo) {
                this._roomNo = _roomNo;
            }
            @DynamoDBAttribute(attributeName = "time")
            public String getTime() {
                return _time;
            }

            public void setTime(final String _time) {
                this._time = _time;
            }
            @DynamoDBAttribute(attributeName = "username")
            public String getUsername() {
                return _username;
            }

            public void setUsername(final String _username) {
                this._username = _username;
            }

            //Functions-----------------------------
            public void order() {
                final FoodOrdersDO add = new FoodOrdersDO();

                add.setUserId(currentDate+currentTime);
                add.setDate(currentDate);
                add.setTime(currentTime);
                add.setRoomNo(ans);
                add.setUsername("devesh");
                add.setOrder(food);
                add.setDelivered("No");
                Log.d("t2","tick");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dynamoDBMapper.save(add);
                        // Item saved
                        Log.d("t1","food_is_ordered");


                    }
                }).start();
            }

        }


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
                        ans= mMessage.substring(index1+7,index1+10);

                    }
                });
            }
        }

        //##########################################################

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                call_api o1 = new call_api();
                try {
                    o1.postRequest();
                }catch (Exception e)
                {

                }

                int i=0;
                while (i==0)
                {
                    if(ans==null) {}
                    else {i=1;}
                }


                currentDate = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());
                currentTime = new SimpleDateFormat("HHmmss", Locale.getDefault()).format(new Date());

                Log.d("yu","name = "+user);

                StringBuilder stringBuilder = new StringBuilder();
                for(String s : order_list)
                {
                    stringBuilder.append(s).append(",");
                }

                //tv.setText(stringBuilder.toString());
                food=stringBuilder.toString();

                if(food.equals(s_empty)) {
                    tv.setText("Please select some item to order");
                    tv.setEnabled(false);
                }
                else {
                    tv.setText("Your Order of "+food+" is successfully placed!");
                    tv.setEnabled(false);

                    //###################
                    Runnable runnable = new Runnable() {
                        public void run() {
                            //DynamoDB calls go here
                            FoodOrdersDO o1 = new FoodOrdersDO();
                            o1.order();
                        }
                    };
                    Thread mythread = new Thread(runnable);
                    mythread.start();
                }

                //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                //sending on https://webhook.site/#!/24996fd9-fe05-414f-94c7-feab0ace50e3/e6bb8967-e18a-4078-b24c-f744dc1b5c9e/1

                try {

                    Runnable runnable2 = new Runnable() {
                        public void run() {
                            OkHttpClient client = new OkHttpClient();

                            RequestBody formBody = new FormBody.Builder()
                                    .add("food_message", food)
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
                    Thread mythread = new Thread(runnable2);
                    mythread.start();





                }
                catch (Exception e) {
                    Log.d("y","some error");
                }

                //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^



            }
        });
    }
}
