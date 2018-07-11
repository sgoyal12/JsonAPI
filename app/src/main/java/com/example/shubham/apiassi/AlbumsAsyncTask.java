package com.example.shubham.apiassi;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.TypeVariable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by shubham on 7/10/2018.
 */

public class AlbumsAsyncTask extends AsyncTask<String,Void,ArrayList<Albums>> {
    ListenerAlbum listenerAlbum;
    public AlbumsAsyncTask(ListenerAlbum listenerAlbum) {
        this.listenerAlbum=listenerAlbum;
    }

    @Override
    protected ArrayList<Albums> doInBackground(String... strings) {
        ArrayList<Albums> albums=new ArrayList<>();
        String urlString=strings[0];
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
            albums=gson.fromJson(result, new TypeToken<List<Albums>>(){}.getType());
//            JSONArray root=new JSONArray(result);
//            for (int i=0;i<root.length();i++)
//            {
//                JSONObject albumObject=root.getJSONObject(i);
//                Albums album=new Albums(albumObject.getString("title"),albumObject.getInt("userId"),albumObject.getInt("id"));
//                albums.add(album);
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }

        return albums;

    }

    @Override
    protected void onPostExecute(ArrayList<Albums> albums) {
        super.onPostExecute(albums);
        listenerAlbum.onDownload(albums);
    }
}
