package com.spacex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class Thunderdome
{
    public Fighter getFighter() {
        Gson gson = new Gson();
        Fighter fighter = new Fighter();

        try {
            URL url = new URL("http://localhost:8080/fighter/1");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            fighter = gson.fromJson(sb.toString(), Fighter.class);

            bufferedReader.close();
        } catch (Exception e) {
            //TODO: handle exception
        }

        return fighter;
    }
}
