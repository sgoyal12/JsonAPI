package com.example.shubham.apiassi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by shubham on 7/11/2018.
 */

public interface CoursesService {
    @GET("albums")
    Call<ArrayList<Albums>> getAlbums();
    @GET("albums/(id)/photos")
    Call<ArrayList<Photos>> getPhotos(@Path("id") int albumId);
}
