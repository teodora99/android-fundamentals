package project.client.internshipPlatform.adminActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import project.client.internshipPlatform.DTO.InternshipOverviewDto;
import project.client.internshipPlatform.ErrorPageAdmin;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.adaptors.CustomAdapterUnapproved;

public class UnapprovedInternships extends AppCompatActivity {

    private final static String TAG = UnapprovedInternships.class.getSimpleName();

    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static InternshipOverviewDto data;
    public static View.OnClickListener myOnClickListener;
    private static LinearLayoutManager linearLayoutManager;
    private RequestQueue requestQueue;
    TextView role;
    String id;

    private final String URL_UNAPPROVE = "http://10.0.2.2:8080/api/admin/unapproved";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unapproved_internships);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("idAdmin");

        role = findViewById(R.id.role);
        myOnClickListener = new UnapprovedInternships.MyOnClickListener(this, id);

        recyclerView = findViewById(R.id.my_recycler_view);

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        setUpToolbar();

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new InternshipOverviewDto();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonParse();

    }

    public UnapprovedInternships getInstance(){
        return this;
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;
        private final String id;

        private MyOnClickListener(UnapprovedInternships context, String id) {
            this.context = context;
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, InternshipDetailsAdmin.class);
            TextView idInternship = v.findViewById(R.id.id);
            i.putExtra("idInternship", idInternship.getText().toString());
            i.putExtra("idUser", id);
            context.startActivity(i);
        }

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
            i.putExtra("idAdmin", id);
            startActivity(i);
        }else  if (item.getItemId() == R.id.allInternships) {
            Intent i = new Intent(getApplicationContext(), AllInternshipsOverview.class);
            i.putExtra("idAdmin", id);
            startActivity(i);
        }
        return true;
    }
    private void jsonParse(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_UNAPPROVE, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        data = gson.fromJson(String.valueOf(response), InternshipOverviewDto.class);
                        if(data.getInternshipDtoList().isEmpty()){
                            startNewActivityError();
                        }
                        adapter = new CustomAdapterUnapproved(data, id, getInstance());
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        requestQueue.add(request);
    }

    public void startNewActivity(){
        Intent i = new Intent(getApplicationContext(), UnapprovedInternships.class);
        i.putExtra("idAdmin", id);
        startActivity(i);
    }

    public void startNewActivityError(){
        Intent i = new Intent(getApplicationContext(), ErrorPageAdmin.class);
        i.putExtra("idUser", id);
        startActivity(i);
    }
}
