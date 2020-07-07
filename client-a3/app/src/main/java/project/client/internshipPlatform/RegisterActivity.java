package project.client.internshipPlatform;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import project.client.internshipPlatform.DTO.RegisterDto;
import project.client.internshipPlatform.threads.ThreadRequest;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final static String TAG = RegisterActivity.class.getSimpleName();
    private final String URL_LOGIN = "http://10.0.2.2:8080/api/user/register";
    private ArrayList<String> roles;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextInputEditText username = findViewById(R.id.username_edit);
        final TextInputEditText password = findViewById(R.id.password_edit);
        final TextInputEditText firstName = findViewById(R.id.firstNameEdit);
        final TextInputEditText lastName = findViewById(R.id.lastNameEdit);
        final TextInputEditText email = findViewById(R.id.emailEdit);
        final TextInputEditText address = findViewById(R.id.addressEdit);
        final TextInputEditText phoneNumber = findViewById(R.id.phoneNumberEdit);
        final TextInputEditText dateOfBirth = findViewById(R.id.dateOfBirthEdit);

        roles = getDataSource();

        Spinner spinner = findViewById(R.id.spinnerRole);
        ArrayAdapter<String> adapter;
        adapter = getAdapter();

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        final MaterialButton button = findViewById(R.id.register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
               * If valid, sa nu fie nule campurile si sa fie de tip email, ori aici or pe server???
                */
                RegisterDto registerDto = new RegisterDto();
                registerDto.setAddress(address.getText().toString());
               // registerDto.setDateOfBirth(dateOfBirth.getText().toString());
                registerDto.setEmail(email.getText().toString());
                registerDto.setFirstName(firstName.getText().toString());
                registerDto.setLastName(lastName.getText().toString());
                registerDto.setPassword( password.getText().toString());
                registerDto.setUsername(username.getText().toString());
                registerDto.setPhoneNumber(phoneNumber.getText().toString());
                registerDto.setRole(role);
                ThreadRequest threadRequest = new ThreadRequest(registerDto, URL_LOGIN);
                Thread t = new Thread(threadRequest);
                t.start();
                Toast.makeText(getApplicationContext(),"Successfully Register", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    private ArrayList<String> getDataSource() {
        ArrayList<String> androidVersions = new ArrayList<>();
        androidVersions.add("Student");
        androidVersions.add("Company");
        return androidVersions;
    }

    private ArrayAdapter<String> getAdapter() {
        return new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(TAG, "position: " + i);
        role = adapterView.getItemAtPosition(i).toString();
        Log.d(TAG, "Selected : " +adapterView.getItemAtPosition(i).toString());
       }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
