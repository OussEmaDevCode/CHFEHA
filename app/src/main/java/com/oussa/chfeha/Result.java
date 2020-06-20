package com.oussa.chfeha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Result extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView name = findViewById(R.id.name);
        TextView bath = findViewById(R.id.bath);
        TextView days = findViewById(R.id.daily);
        TextView water = findViewById(R.id.water);

        RequestQueue queue = Volley.newRequestQueue(this);

        products.add(new Product(new String[]{"milk", "lait"}, 1020));
        products.add(new Product(new String[]{"riz", "rice"}, 2497));
        products.add(new Product(new String[]{"harissa", "harissa"}, 7365));
        products.add(new Product(new String[]{"pasta", "pâtes"}, 1849));
        products.add(new Product(new String[]{"flour", "farine"}, 1849));
        products.add(new Product(new String[]{"cocoa", "cacao"}, 19928));

        String code = getIntent().getStringExtra("code");
        String url = "https://world.openfoodfacts.org/api/v0/Product/" + code + ".json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject reader = new JSONObject(response);
                        JSONArray keywords = reader.getJSONObject("Product").getJSONArray("_keywords");
                        if (keywords != null && keywords.length() > 0) {
                            for (Product product : products) {
                                for (int i = 0; i < keywords.length(); i++) {
                                    if (keywords.getString(i).toLowerCase().equals(product.getNames()[0])
                                            || keywords.getString(i).toLowerCase().equals(product.getNames()[1])) {
                                        name.setText(product.getNames()[0]);
                                        water.setText("It requires : " + String.valueOf(product.getWater()) +"l/kg"+ " of water to be produced");
                                        bath.setText("That's equivalent to "+ String.valueOf(product.getWater()/80) +" bathtubs");
                                        days.setText("That's equivalent to"+ String.valueOf(product.getWater()/302) +" days of water use");
                                    }
                                }
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }

                }, error -> Log.d("LAMBA", error.toString()));
        queue.add(stringRequest);
        findViewById(R.id.check).setOnClickListener(v -> startActivity(new Intent(Result.this, MainActivity.class)));
    }
}
