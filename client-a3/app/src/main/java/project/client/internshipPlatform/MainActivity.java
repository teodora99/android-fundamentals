package project.client.internshipPlatform;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import project.client.internshipPlatform.DTO.LoginDTO;
import project.client.internshipPlatform.DTO.ResponseLoginDto;
import project.client.internshipPlatform.adminActivity.AdminHomepage;
import project.client.internshipPlatform.companyActivity.MyInternships;
import project.client.internshipPlatform.threads.ThreadLogin;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private final String URL_LOGIN = "http://10.0.2.2:8080/api/user/login";
    private ResponseLoginDto responseLoginDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputEditText username = findViewById(R.id.username_edit_text);
        final TextInputEditText password = findViewById(R.id.password_edit_text);

        final MaterialButton button = findViewById(R.id.login_button);
        final MaterialButton register = findViewById(R.id.register_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "username  " + username.getText() + "   password  " + password.getText());

                final LoginDTO loginDTO = new LoginDTO();
                loginDTO.setPassword(password.getText().toString());
                loginDTO.setUsernameOrEmail(username.getText().toString());

                ThreadLogin threadLogin = new ThreadLogin(loginDTO, URL_LOGIN);
                Thread t = new Thread(threadLogin);

                 t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                responseLoginDto = new ResponseLoginDto();
                responseLoginDto = threadLogin.getResponseDto();

                validateResponse(responseLoginDto);

            }
        });

    }

    public void validateResponse(ResponseLoginDto responseLoginDto){
        if(responseLoginDto.getSeverity().equals("SUCCESS")){
            if(responseLoginDto.getRole().equals("Student")) {
                Intent i = new Intent(getApplicationContext(), RecycleViewInternshipActivity.class);
                i.putExtra("idUser", responseLoginDto.getUserId());
                i.putExtra("roleUser", responseLoginDto.getRole());
                startActivity(i);
            }else  if(responseLoginDto.getRole().equals("Company")){
                Intent i = new Intent(getApplicationContext(), MyInternships.class);
                i.putExtra("idUser", responseLoginDto.getUserId());
                i.putExtra("roleUser", responseLoginDto.getRole());
                startActivity(i);
            }else if (responseLoginDto.getRole().equals("Admin")){
                Intent i = new Intent(getApplicationContext(), AdminHomepage.class);
                i.putExtra("idAdmin", responseLoginDto.getUserId());
                i.putExtra("roleAdmin", responseLoginDto.getRole());
                startActivity(i);
            }

        }else {
            Toast.makeText(this, responseLoginDto.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
