package project.client.internshipPlatform.adminActivity;

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

import project.client.internshipPlatform.DTO.InternshipDetailsDto;
import project.client.internshipPlatform.DTO.InternshipEmailDto;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.threads.ThreadRequest;

public class InternshipDetailsAdmin extends AppCompatActivity {
    private TextView title;
    private TextView  description;
    private TextView  company;
    private TextView  period;
    private TextView  domain;
    private TextInputEditText detailsMail;
    private String idAdmin;
    private String idInternship;
    private RequestQueue requestQueue;
    private MaterialButton deleteButton;
    private InternshipDetailsDto data;

    private final String URL_DETAILS = "http://10.0.2.2:8080/api/internship/details/";
    private final String URL_DELETE = "http://10.0.2.2:8080/api/admin/delete";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship_details_admin);

        Bundle bundle = getIntent().getExtras();
        idAdmin = bundle.getString("idAdmin");
        idInternship = bundle.getString("idInternship");

        title = findViewById(R.id.titleText);
        description = findViewById(R.id.descriptionText);
        company = findViewById(R.id.companyText);
        period = findViewById(R.id.periodText);
        domain = findViewById(R.id.domainText);
        detailsMail = findViewById(R.id.detailsMailEdit);


        data = new InternshipDetailsDto();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonParse();

        deleteButton = findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(detailsMail.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(),"YYou have to specify the reason", Toast.LENGTH_LONG).show();
                }else {
                    InternshipEmailDto internshipEmailDto = new InternshipEmailDto();
                    internshipEmailDto.setId(data.getId());
                    internshipEmailDto.setMessage(detailsMail.getText().toString());
                    ThreadRequest threadRequest = new ThreadRequest(internshipEmailDto, URL_DELETE);
                    Thread t = new Thread(threadRequest);
                    t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "You have deleted the internship", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), AllInternshipsOverview.class);
                    i.putExtra("idAdmin", idAdmin);
                    startActivity(i);
                }
            }
        });

        setUpToolbar();


    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.app_bar);
        this.setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.unapproved) {
            Intent i = new Intent(getApplicationContext(), UnapprovedInternships.class);
            i.putExtra("idAdmin", idAdmin);
            startActivity(i);
        }else  if (item.getItemId() == R.id.allInternships) {
            Intent i = new Intent(getApplicationContext(), AllInternshipsOverview.class);
            i.putExtra("idAdmin", idAdmin);
            startActivity(i);
        }
        return true;
    }

    private void jsonParse(){
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
