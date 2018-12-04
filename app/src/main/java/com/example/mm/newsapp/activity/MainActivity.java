package com.example.mm.newsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mm.newsapp.R;
import com.example.mm.newsapp.Model.NewsAdapter;
import com.example.mm.newsapp.Model.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.mm.newsapp.Model.Constants.MY_GITHUB_REPOSITORIES;
import static com.example.mm.newsapp.Model.Constants.NEWS_CONTENT;
import static com.example.mm.newsapp.Model.Constants.NEWS_DETAIL;
import static com.example.mm.newsapp.Model.Constants.NEWS_IMAGE_URL;
import static com.example.mm.newsapp.Model.Constants.NEWS_JSON_ARRAY;
import static com.example.mm.newsapp.Model.Constants.NEWS_TITLE;
import static com.example.mm.newsapp.Model.Constants.NEWS_URL;
import static com.example.mm.newsapp.Model.Constants.WALL_STREET_JOURNAL_URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<NewsData> newsList ;
    private RequestQueue mRequestQueue;
    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsList = new ArrayList<>();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRequestQueue = Volley.newRequestQueue(this);
        jsonParse();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.share:
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                String shareBody = "\nApp project is available at :" + "\n\nGitHub : " + MY_GITHUB_REPOSITORIES ;
                intentShare.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                intentShare.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intentShare, "Share via"));
                break;

            case R.id.developer:
                startActivity(new Intent(this, AboutDeveloper.class));
                break;

            case R.id.setting:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void jsonParse() {
        //String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=713465b432e64b05bec7c2b3d4f309dc";
        //String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=7cf0d1ad0710446cbdb35e424acf5699";
        //String url = "https://newsapi.org/v2/everything?domains=wsj.com&apiKey=7cf0d1ad0710446cbdb35e424acf5699";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, WALL_STREET_JOURNAL_URL,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArr = response.getJSONArray(NEWS_JSON_ARRAY);

                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject newsDetail = jsonArr.getJSONObject(i);

                        String imtUrl = newsDetail.getString(NEWS_IMAGE_URL);
                        String title = newsDetail.getString(NEWS_TITLE);
                        String detail = newsDetail.getString(NEWS_DETAIL);
                        String newsUrl = newsDetail.getString(NEWS_URL);
                        String content = newsDetail.getString(NEWS_CONTENT);


                        newsList.add(new NewsData(title, imtUrl, detail, newsUrl, content));
                        NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, newsList);
                        mRecyclerView.setAdapter(newsAdapter);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

}
