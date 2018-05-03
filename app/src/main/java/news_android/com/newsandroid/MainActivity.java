package news_android.com.newsandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ListView mlistView;
    private ListNewsAdapter listNewsAdapter;

    ArrayList<HashMap<String, String>> list_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_news);

        String url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=18ff2b1838f844fe8ceeb687e886a496";

        mlistView = (ListView) findViewById(R.id.CurrencyName);

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        list_data = new ArrayList<HashMap<String, String>>();

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        HashMap<String, String> map  = new HashMap<String, String>();
                        map.put("author", json.getString("author"));
                        map.put("title", json.getString("title"));
                        map.put("description", json.getString("description"));
                        map.put("url", json.getString("url"));
                        map.put("urlToImage", json.getString("urlToImage"));
                        map.put("publishedAt", json.getString("publishedAt"));
                        list_data.add(map);
                    }
                    listNewsAdapter = new ListNewsAdapter(MainActivity.this,list_data);

                    mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent DataIntent = new Intent(MainActivity.this,ListNewsAdapter.class);
                            DataIntent.putExtra("author",list_data.get(position).get("author"));
                            DataIntent.putExtra("title",list_data.get(position).get("title"));
                            DataIntent.putExtra("description",list_data.get(position).get("description"));
                            DataIntent.putExtra("url",list_data.get(position).get("url"));
                            DataIntent.putExtra("urlToImage",list_data.get(position).get("urlToImage"));
                            DataIntent.putExtra("publishedAt",list_data.get(position).get("publishedAt"));
                            startActivity(DataIntent);
                        }
                    });

                    mlistView.setAdapter(listNewsAdapter);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    Log.d(TAG, "Response: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}