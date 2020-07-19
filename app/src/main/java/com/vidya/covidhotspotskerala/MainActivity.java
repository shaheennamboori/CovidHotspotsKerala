package com.vidya.covidhotspotskerala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<hotspots> hotspotsList;
    HotspotListAdapter adapter;

    private static String URL_HOTSPOTS = "https://keralastats.coronasafe.live/hotspots.json";
    Button refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.myrecycleview);
        hotspotsList = new ArrayList<>();
        refreshBtn = findViewById(R.id.refresh_button);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);








        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_HOTSPOTS, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray hotspotarray = null;
                        try {
                            hotspotarray = response.getJSONArray("hotspots");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < hotspotarray.length(); i++) {
                            hotspots hotspots = new hotspots();
                            JSONObject hotspotobject = null;
                            try {
                                hotspotobject = hotspotarray.getJSONObject(i);

                                hotspots.setWards(hotspotobject.getString("wards"));
                                hotspots.setDistrict(hotspotobject.getString("district"));
                                hotspots.setLsgd(hotspotobject.getString("lsgd"));

                                hotspotsList.add(hotspots);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        adapter = new HotspotListAdapter(MainActivity.this,hotspotsList);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", "onErrorResponse: " + error.getMessage() );
                    }
                });

                queue.add(jsonObjectRequest);



                    }
                });


    }
}