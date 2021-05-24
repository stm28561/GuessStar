package com.example.guessstar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;

import static com.example.guessstar.Stars.starsList;
import static com.example.guessstar.Stars.starsNames;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewPreview;

    private String webSiteWIthStars = "https://www.forbes.ru/rating/403469-40-samyh-uspeshnyh-zvezd-rossii-do-40-let-reyting-forbes";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewPreview = findViewById(R.id.imageViewPreview);
        DownloadTask task = new DownloadTask();
        MinimizeTask minimizeTask = new MinimizeTask();
        CreateStar createStar = new CreateStar();
        try {
            String result = task.execute(webSiteWIthStars).get();
            String minimizedResult = minimizeTask.execute(result).get();
            Log.i("URL", minimizedResult);
            createStar.execute(minimizedResult);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void onClick(View view) {
        Intent intent = new Intent(this, TheGuess.class);

        startActivity(intent);
    }

    private static class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            //Log.i("URL", strings[0]);
            return result.toString();
        }
    }

    private static class MinimizeTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder minimizedWebsite = new StringBuilder();
            Matcher matcher = Stars.getPatternForParsingSite().matcher(strings[0]); //Нужно запихнуть сюда параметр String result. Done. Входящий параметр в данный метод массив strings, соответственно первый эл-т массива это наша строка
            while (matcher.find()) {
                minimizedWebsite.append(matcher.group(1));
                Log.i("URL", "Done");
            }
            return minimizedWebsite.toString();
        }
    }

    private static class CreateStar extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String minimizedResult = strings[0];
            Matcher matcherName = Stars.getPatternForName().matcher(minimizedResult);
            Matcher matcherPhoto = Stars.getPatternForPhoto().matcher(minimizedResult);
            String name = "";
            String photoURL = "";
            Bitmap photo = null;
            int counter = 0;

            while (matcherName.find() && matcherPhoto.find()) {
                name = matcherName.group(1);
                photoURL = (matcherPhoto.group(1));
                URL url = null;
                HttpURLConnection urlConnection = null;
                try {
                    url = new  URL(photoURL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    photo = bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
                starsList.put(name, photo);
                starsNames[counter] = name;
                counter++;
                Log.i("Hash", name + " " + photoURL);
                Log.i("Hash", "Added Name and Photo in HashMap");
                Log.i("Hash", starsList.isEmpty() + " HASHMAP IS EMPTY");
            }
            return null;
        }

    }

}