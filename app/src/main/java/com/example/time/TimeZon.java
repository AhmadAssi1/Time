package com.example.time;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class TimeZon extends AppCompatActivity {
    private TextView Time;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_zon);

        Time = findViewById(R.id.TimeZone);

        // Define the runnable to update time
        final Runnable updateTime = new Runnable() {
            @Override
            public void run() {
                String url = "https://timeapi.io/api/Time/current/zone?timeZone=Asia/jerusalem";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String hour = response.getString("hour");
                                    String minute = response.getString("minute");
                                    String seconds = response.getString("seconds");
                                    String time = hour + ":" + minute + ":" + seconds;
                                    Time.setText(time);
                                } catch (Exception e) {
                                    Log.d("volley_error", e.getMessage());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("volley_errorResponse Fail", error.toString());
                            }
                        });

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(request);

                // Schedule the next execution
                handler.postDelayed(this, 1000); // 1000 milliseconds delay
            }
        };

        // Start the Runnable for the first time
        handler.post(updateTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null); // This is important to avoid memory leaks
    }
}
