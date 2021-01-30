package com.example.buoi11;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FnVolley {
    String jsonStr = null;
    public void  getJSONObject_GET(Context context, TextView textView)
    {
        RequestQueue queue = Volley.newRequestQueue(context);//khởi tạo volley
        String url = String.format("https://batdongsanabc.000webhostapp.com/mob403lab5/get_product_detail.php?pid=%1$s",116);
        //truyen tham so
        HashMap<String, String> param = new HashMap<>();
        param.put("pid","115");
        //khơi tạo đối tượng
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(param),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arr = response.getJSONArray("products");
                    for (int i = 0; i<arr.length(); i++){
                        String pid = arr.getJSONObject(i).getString("pid");
                        String name = arr.getJSONObject(i).getString("name");
                        String price = arr.getJSONObject(i).getString("price");
                        String des = arr.getJSONObject(i).getString("description");
                        jsonStr += "pid" + pid+"\n\n";
                        jsonStr += "name" + name+"\n\n";
                        jsonStr += "price" + price+"\n\n";
                        jsonStr += "des" + pid+"\n\n";
                    }
                    textView.setText(jsonStr);
                    VolleyLog.v(response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.v(error.getMessage());
            }
        });
        queue.add(req);
    }
}
