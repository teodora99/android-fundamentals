package project.client.internshipPlatform.userActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONObject;

import project.client.internshipPlatform.DTO.ApplicationDto;
import project.client.internshipPlatform.DTO.InternshipDetailsDto;
import project.client.internshipPlatform.DTO.ResponseDto;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.RecycleViewInternshipActivity;
import project.client.internshipPlatform.threads.ThreadRequest;

public class InternshipDetails extends AppCompatActivity {
    private TextView title;
    private TextView description;
    private TextView company;
    private TextView period;
    private TextView domain;
    private TextView role;
    private TextInputEditText additionalNote;
    private String id;
    private String idInternship;
    private RequestQueue requestQueue;
    private MaterialButton applyButton;
    private InternshipDetailsDto data;

    private final String URL_DETAILS = "http://10.0.2.2:8080/api/internship/details/";
    private final String URL_APPLY = "http://10.0.2.2:8080/api/apply/internship";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship_details);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("idUser");
        idInternship = bundle.getString("idInternship");

        title = findViewById(R.id.titleText);
        description = findViewById(R.id.descriptionText);
        company = findViewById(R.id.companyText);
        period = findViewById(R.id.periodText);
        domain = findViewById(R.id.domainText);
        role = findViewById(R.id.role);
        additionalNote = findViewById(R.id.additionalNoteEdit);

        data = new InternshipDetailsDto();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonParse();

        applyButton = findViewById(R.id.apply_button);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationDto applicationDto = new ApplicationDto(idInternship, id, additionalNote.getText().toString());
                ThreadRequest threadRequest = new ThreadRequest(applicationDto, URL_APPLY);
                Thread t = new Thread(threadRequest);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                validateResponse(threadRequest.getResponseDto());
            }
        });

        setUpToolbar();


    }

    public void validateResponse(ResponseDto responseDto) {
            Toast.makeText(getApplicationContext(), responseDto.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.app_bar);
        this.setSupportActionBar(toolbar);
        role.setText("Student");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.favoriteInternships) {
            Intent i = new Intent(getApplicationContext(), FavouriteInternship.class);
            i.putExtra("idUser", id);
            startActivity(i);
            Toast.makeText(this, "Here are your favourites", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.allInternshipsUser) {
            Intent i = new Intent(getApplicationContext(), RecycleViewInternshipActivity.class);
            i.putExtra("idUser", id);
            startActivity(i);
            Toast.makeText(this, "Here are all the internships", Toast.LENGTH_SHORT).show();
        } if (item.getItemId() == R.id.applications) {
            Intent i = new Intent(getApplicationContext(), ApplicationActivity.class);
            i.putExtra("idUser", id);
            startActivity(i);
            Toast.makeText(this, "Here are all the internships", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void jsonParse() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_DETAILS + idInternship, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        data = gson.fromJson(String.valueOf(response), InternshipDetailsDto.class);
                        title.setText(data.getTitle());
                        description.setText(data.getDescription());
                        company.setText(data.getCompany());
                        period.setText(data.getPeriod());
                        domain.setText(data.getDomain());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
