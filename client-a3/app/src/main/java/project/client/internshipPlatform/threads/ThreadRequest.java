package project.client.internshipPlatform.threads;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.client.internshipPlatform.DTO.ResponseDto;

public class ThreadRequest implements Runnable {

    private final static String TAG = ThreadRequest.class.getSimpleName();


    private Object loginDTO;
    private String URL;
    private ResponseDto responseDto;

    public ThreadRequest (Object loginDTO, String URL){
        this.loginDTO = loginDTO;
        this.URL = URL;
    }
    @Override
    public void run() {
        Gson gson = new Gson();
        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoInput(true);
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(gson.toJson(loginDTO));
            Log.d(TAG, "Am ajuns aici cu URL-ul asta " + URL);
            conn.connect();
            BufferedReader bufferedReader;
            if (conn.getResponseCode() / 100 == 2)
                bufferedReader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));
             else
                bufferedReader = new BufferedReader(new
                        InputStreamReader(conn.getErrorStream()));

            String result = bufferedReader.readLine();
            responseDto = gson.fromJson(result, ResponseDto.class);
            conn.disconnect();

        } catch (IOException ex ) {
            ex.printStackTrace();
        }
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }
}
