package project.client.internshipPlatform.threads;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.client.internshipPlatform.DTO.InternshipNewDto;
import project.client.internshipPlatform.adaptors.IAdapterRequest;
import project.client.internshipPlatform.adaptors.JsonAdapter;

public class ThreadResponse implements Runnable {

    private final static String TAG = ThreadRequest.class.getSimpleName();


    private String URL;
    private InternshipNewDto internshipNewDto;

    public ThreadResponse (String URL){
        this.URL = URL;
    }
    @Override
    public void run() {
        Gson gson = new Gson();
        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.connect();

            BufferedReader bufferedReader;
            if (conn.getResponseCode() / 100 == 2)
                bufferedReader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));
            else
                bufferedReader = new BufferedReader(new
                        InputStreamReader(conn.getErrorStream()));

            String result = bufferedReader.readLine();
            IAdapterRequest adapterRequest = new JsonAdapter();
            System.out.println(result);

            System.out.println(adapterRequest.request(result));
            internshipNewDto = gson.fromJson(adapterRequest.request(result), InternshipNewDto.class);
            conn.disconnect();

        } catch (IOException ex ) {
            ex.printStackTrace();
        }
    }

    public InternshipNewDto getResponseDto() {
        return internshipNewDto;
    }
}
