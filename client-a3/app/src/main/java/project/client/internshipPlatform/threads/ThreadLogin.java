package project.client.internshipPlatform.threads;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.client.internshipPlatform.DTO.ResponseLoginDto;

public class ThreadLogin implements Runnable{
    private final static String TAG = ThreadLogin.class.getSimpleName();


    private Object loginDTO;
    private String URL;
    private ResponseLoginDto responseLoginDto;

    public ThreadLogin (Object loginDTO, String URL){
        this.loginDTO = loginDTO;
        this.URL = URL;
    }
    @Override
    public void run() {
        Gson gson = new Gson();
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoInput(true);
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(gson.toJson(loginDTO));

            conn.connect();
            BufferedReader bufferedReader;
            if (conn.getResponseCode() / 100 == 2)
                bufferedReader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));
            else
                bufferedReader = new BufferedReader(new
                        InputStreamReader(conn.getErrorStream()));

            String result = bufferedReader.readLine();
            responseLoginDto = gson.fromJson(result, ResponseLoginDto.class);
            conn.disconnect();

        } catch (IOException ex ) {
            ex.printStackTrace();
        }
    }

    public ResponseLoginDto getResponseDto() {
        return responseLoginDto;
    }
}
