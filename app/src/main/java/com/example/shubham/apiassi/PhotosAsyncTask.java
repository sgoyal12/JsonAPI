package com.example.shubham.apiassi;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by shubham on 7/10/2018.
 */

public class PhotosAsyncTask extends AsyncTask<String,Void,ArrayList<Photos>> {
   ListenerPhotos listenerPhotos;
   int albumId;

    public PhotosAsyncTask(ListenerPhotos listenerPhotos, int albumId) {
        this.listenerPhotos = listenerPhotos;
        this.albumId = albumId;
    }


    @Override
    protected ArrayList<Photos> doInBackground(String... strings) {
        ArrayList<Photos> photos=new ArrayList<>();
        String urlString=strings[0];
        Log.d("Async",albumId+"");
        Boolean found=false;
        try {
            URL url=new URL(urlString);
            HttpsURLConnection urlConnection= (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream=urlConnection.getInputStream();
            Scanner scanner=new Scanner(inputStream);
            String result="";
            while (scanner.hasNext())
            {
                result=result+scanner.next();
            }
            Gson gson=new Gson();
            photos=gson.fromJson(result,new TypeToken<List<Photos>>(){}.getType());
//            JSONArray root=new JSONArray(result);
//
//            for (int i=0;i<root.length();i++)
//            {
//                JSONObject photoObject=root.getJSONObject(i);
//                Photos photo = new Photos(photoObject.getString("title"), photoObject.getInt("id"), photoObject.getInt("albumId"), photoObject.getString("url"));
//                photos.add(photo);
//
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
        return photos;
    }

    @Override
    protected void onPostExecute(ArrayList<Photos> photos) {
        super.onPostExecute(photos);
        listenerPhotos.onDownload(photos);
    }
}
