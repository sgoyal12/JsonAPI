package com.example.shubham.apiassi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Details extends AppCompatActivity implements AdapterView.OnItemClickListener {
Bundle bundle=new Bundle();
ListView listView;
Button button;
TextView textView;
ProgressBar progressBar;
ArrayList<Photos> photos=new ArrayList<>();
public static  final String imageUrl="url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        button=findViewById(R.id.button);
        progressBar=findViewById(R.id.progressBar2);
        textView=findViewById(R.id.textView);
        listView=findViewById(R.id.listView2);
        Intent intent=getIntent();
        bundle=intent.getExtras();
        final CustomAdapter2 customAdapter=new CustomAdapter2(this,photos);
        listView.setAdapter(customAdapter);
        textView.setText(bundle.getString(ScrollingActivity.Title));
//        PhotosAsyncTask photosAsyncTask=new PhotosAsyncTask(new ListenerPhotos() {
//            @Override
//            public void onDownload(ArrayList<Photos> titles) {
//                photos.clear();
//                photos.addAll(titles);
//                customAdapter.notifyDataSetChanged();
//                listView.setVisibility(View.VISIBLE);
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//        },bundle.getInt(ScrollingActivity.AlbumId));
//        photosAsyncTask.execute("https://jsonplaceholder.typicode.com/albums/"+bundle.getInt(ScrollingActivity.AlbumId)+"/photos");
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        CoursesService service=retrofit.create(CoursesService.class);
        Call<ArrayList<Photos>> call=service.getPhotos(bundle.getInt(ScrollingActivity.AlbumId));
        call.enqueue(new Callback<ArrayList<Photos>>() {
            @Override
            public void onResponse(Call<ArrayList<Photos>> call, Response<ArrayList<Photos>> response) {
                photos.clear();
                photos.addAll(response.body());
                customAdapter.notifyDataSetChanged();
                listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<Photos>> call, Throwable t) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Details.this,User.class);
                intent1.putExtra("UserId",bundle.getInt(ScrollingActivity.UserId));
                startActivity(intent1);
            }
        });
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,Show.class);
        intent.putExtra(imageUrl,photos.get(position).getUrl());
        startActivity(intent);
    }
}
