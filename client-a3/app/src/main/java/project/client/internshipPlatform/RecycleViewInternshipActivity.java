package project.client.internshipPlatform;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import project.client.internshipPlatform.adaptors.CustomAdapter;
import project.client.internshipPlatform.userActivity.ApplicationActivity;
import project.client.internshipPlatform.userActivity.FavouriteInternship;
import project.client.internshipPlatform.userActivity.InternshipDetails;

public class RecycleViewInternshipActivity extends AppCompatActivity {

    private final static String TAG = RecycleViewInternshipActivity.class.getSimpleName();

    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static InternshipOverviewDto data;
    public static View.OnClickListener myOnClickListener;
     private static LinearLayoutManager linearLayoutManager;
    private RequestQueue requestQueue;
    TextView role;
    String id;

    private final String URL_ALL = "http://10.0.2.2:8080/api/internship/all/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_internship);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("idUser");

        role = findViewById(R.id.role);
        myOnClickListener = new MyOnClickListener(this, id);

        recyclerView = findViewById(R.id.my_recycler_view);

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        setUpToolbar();

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new InternshipOverviewDto();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonParse();

    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;
        private final String id;

        private MyOnClickListener(RecycleViewInternshipActivity context, String id) {
            this.context = context;
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, InternshipDetails.class);
            TextView idInternship = v.findViewById(R.id.id);
            i.putExtra("idInternship", idInternship.getText().toString());
            i.putExtra("idUser", id);
            context.startActivity(i);
        }

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
        } else  if (item.getItemId() == R.id.allInternshipsUser) {
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

    private void jsonParse(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_ALL + id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        data = gson.fromJson(String.valueOf(response), InternshipOverviewDto.class);
                        adapter = new CustomAdapter(data, id);
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
}

