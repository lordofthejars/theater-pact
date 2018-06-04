package com.theater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class Theater
{
    public Movie getMovie() {
        Gson gson = new Gson();
        Movie movie = new Movie();

        try {
            URL url = new URL("http://localhost:8080/movie/1");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            movie = gson.fromJson(sb.toString(), Movie.class);

            bufferedReader.close();
        } catch (Exception e) {
            //TODO: handle exception
        }

        return movie;
    }
}
