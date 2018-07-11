package com.example.shubham.apiassi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScrollingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView listView;
ProgressBar progressBar;
ArrayList<Albums> albums=new ArrayList<>();
Bundle bundle=new Bundle();
public final static String Title="title",UserId="userId",AlbumId="albumId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=findViewById(R.id.listView);
        progressBar=findViewById(R.id.progressBar);
        final CustomAdapter customAdapter=new CustomAdapter(this,albums);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              progressBar.setVisibility(View.VISIBLE);
              listView.setVisibility(View.INVISIBLE);
//              AlbumsAsyncTask albumsAsyncTask=new AlbumsAsyncTask(new ListenerAlbum() {
//                  @Override
//                  public void onDownload(ArrayList<Albums> titles) {
//                      albums.clear();
//                      albums.addAll(titles);
//                      customAdapter.notifyDataSetChanged();
//                      progressBar.setVisibility(View.INVISIBLE);
//                      listView.setVisibility(View.VISIBLE);
//                  }
//              });
//              albumsAsyncTask.execute("https://jsonplaceholder.typicode.com/albums");
                Retrofit.Builder builder=new Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit=builder.build();
                CoursesService service=retrofit.create(CoursesService.class);
                retrofit2.Call<ArrayList<Albums>> call=service.getAlbums();
                call.enqueue(new Callback<ArrayList<Albums>>() {
                    @Override
                    public void onResponse(retrofit2.Call<ArrayList<Albums>> call, Response<ArrayList<Albums>> response) {
                        albums.clear();
                        albums.addAll(response.body());
                        customAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ArrayList<Albums>> call, Throwable t) {

                    }
                });
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,Details.class);
        bundle.putString(Title,albums.get(position).getTitle());
        bundle.putInt(UserId,albums.get(position).getUserId());
        bundle.putInt(AlbumId,albums.get(position).getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
