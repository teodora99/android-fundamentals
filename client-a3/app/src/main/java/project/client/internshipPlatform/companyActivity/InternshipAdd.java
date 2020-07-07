package project.client.internshipPlatform.companyActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
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

import java.util.Calendar;

import project.client.internshipPlatform.DTO.DomainDto;
import project.client.internshipPlatform.DTO.InternshipNewDto;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.threads.ThreadRequest;

public class InternshipAdd extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private final static String TAG = InternshipAdd.class.getSimpleName();

    private final String URL_DOMAIN = "http://10.0.2.2:8080/api/domain/all";
    private final String URL_ADD = "http://10.0.2.2:8080/api/internship/add/";

    private RequestQueue requestQueue;
    private DomainDto data;
    private  Spinner domain;
    private String selectedDomain;
    private InternshipNewDto internshipNewDto;
    private DatePickerDialog.OnDateSetListener startDateListener;
    private DatePickerDialog.OnDateSetListener endDateListener;
    private DatePickerDialog.OnDateSetListener untilDateListener;
    private TextView startText;
    private TextView endText;
    private TextView dateUntilText;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship_add);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("idUser");

        final TextInputEditText title = findViewById(R.id.titleEdit);
        final TextInputEditText description = findViewById(R.id.detailsEdit);
        final MaterialButton startDate = findViewById(R.id.startData);
        final MaterialButton endDate = findViewById(R.id.EndData);
        final MaterialButton applyUntil = findViewById(R.id.applyUntilDateButton);

        startText = findViewById(R.id.textStart);
        endText = findViewById(R.id.textEnd);
        dateUntilText = findViewById(R.id.applyUntilDate);

        domain= findViewById(R.id.domainType);
        final MaterialButton addBtn = findViewById(R.id.add);
        setUpToolbar();
        domain.setOnItemSelectedListener(this);

        data = new DomainDto();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonParse();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        InternshipAdd.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        startDateListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyyy-dd-mm: " + month + "-" + dayOfMonth + "-" + year);

                String date;
                if(month < 10 && dayOfMonth < 10)
                    date = year+ "-0" + month  + "-0" + dayOfMonth;
                else if(dayOfMonth < 10)
                    date = year + "-" + month  + "-0" + dayOfMonth;
                else if(month < 10)
                    date = year  + "-0" + month  + "-" + dayOfMonth;
                else
                    date = year  + "-" + month  + "-" +  dayOfMonth;


                startText.setText(date);
                startText.setVisibility(View.VISIBLE);
            }

        };

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        InternshipAdd.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        endDateListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyyy-mm-dd: " + month + "-" + dayOfMonth + "-" + year);

                String date;
                if(month < 10 && dayOfMonth < 10)
                    date = year+ "-0" + month  + "-0" + dayOfMonth;
                else if(dayOfMonth < 10)
                    date = year + "-" + month  + "-0" + dayOfMonth;
                else if(month < 10)
                    date = year  + "-0" + month  + "-" + dayOfMonth;
                else
                    date = year  + "-" + month  + "-" +  dayOfMonth;

                endText.setText(date);
                endText.setVisibility(View.VISIBLE);
            }

        };

        applyUntil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        InternshipAdd.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        untilDateListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        untilDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyyy-mm-dd: " + month + "-" + dayOfMonth + "-" + year);

                String date;
                if(month < 10 && dayOfMonth < 10)
                    date = year+ "-0" + month  + "-0" + dayOfMonth;
                else if(dayOfMonth < 10)
                    date = year + "-" + month  + "-0" + dayOfMonth;
                else if(month < 10)
                    date = year  + "-0" + month  + "-" + dayOfMonth;
                else
                    date = year  + "-" + month  + "-" +  dayOfMonth;


                dateUntilText.setText(date);
                dateUntilText.setVisibility(View.VISIBLE);
            }

        };


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internshipNewDto = new InternshipNewDto(title.getText().toString(),
                        description.getText().toString(),
                        startText.getText().toString(),
                        endText.getText().toString(),
                        dateUntilText.getText().toString(),
                        selectedDomain);

                ThreadRequest threadRequest = new ThreadRequest(internshipNewDto, URL_ADD + id);
                Thread t = new Thread(threadRequest);
                t.start();

                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(getApplicationContext(), MyInternships.class);
                i.putExtra("idUser", id);
                startActivity(i);
            }
        });


    }


        private ArrayAdapter<String> getAdapter() {
        return new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data.getDomains());
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.app_bar);
        this.setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_company, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.addInternship) {
            Intent i = new Intent(getApplicationContext(), InternshipAdd.class);
            i.putExtra("idUser", id);
            startActivity(i);
        }else  if (item.getItemId() == R.id.myInternships) {
            Intent i = new Intent(getApplicationContext(), MyInternships.class);
            i.putExtra("idUser", id);
            startActivity(i);
            Toast.makeText(this, "Here are the internships that you post", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    private void jsonParse(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_DOMAIN, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        data = gson.fromJson(String.valueOf(response), DomainDto.class);
                        ArrayAdapter<String> adapter;
                        adapter = getAdapter();

                        domain.setAdapter(adapter);

                        Log.d(TAG, "Response : " + data.getDomains());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        requestQueue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedDomain = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
