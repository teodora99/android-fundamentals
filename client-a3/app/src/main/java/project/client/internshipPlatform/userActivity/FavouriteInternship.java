package project.client.internshipPlatform.userActivity;

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

import java.util.ArrayList;

import project.client.internshipPlatform.DTO.InternshipOverviewDto;
import project.client.internshipPlatform.ErrorPageStudent;
import project.client.internshipPlatform.R;
import project.client.internshipPlatform.RecycleViewInternshipActivity;
import project.client.internshipPlatform.adaptors.CustomAdaptorFavourite;

public class FavouriteInternship extends AppCompatActivity {

    private final static String TAG = FavouriteInternship.class.getSimpleName();

    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static InternshipOverviewDto data;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<String> removedItems;
    private static LinearLayoutManager linearLayoutManager;
    private RequestQueue requestQueue;
    private TextView role;
    String id;

    private final String URL_FAV = "http://10.0.2.2:8080/api/internship/favourite/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_internship);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("idUser");

        role = findViewById(R.id.role);
        myOnClickListener = new FavouriteInternship.MyOnClickListener(this, id);

        recyclerView = findViewById(R.id.my_recycler_view);

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        data = new InternshipOverviewDto();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonParse();

        recyclerView.setLayoutManager(linearLayoutManager);
        setUpToolbar();

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        removedItems = new ArrayList<>();
    }

    public FavouriteInternship getInstance(){
        return this;
    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;
        private final String id;

        private MyOnClickListener(FavouriteInternship context, String id) {
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
        role.setText("Student -- Favourite Internships");
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
            Toast.makeText(this, "Here are your applications", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void jsonParse(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_FAV + id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        data = gson.fromJson(String.valueOf(response), InternshipOverviewDto.class);
                        if(data.getInternshipDtoList().isEmpty()){
                            startNewActivityError();
                        }
                            adapter = new CustomAdaptorFavourite(data, id, getInstance());
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
        Intent i = new Intent(getApplicationContext(), FavouriteInternship.class);
        i.putExtra("idUser", id);
        startActivity(i);
    }

    public void startNewActivityError(){
        Intent i = new Intent(getApplicationContext(), ErrorPageStudent.class);
        i.putExtra("idUser", id);
        Integer integer = R.menu.menu_company;
        i.putExtra("menuType", integer.toString());
        startActivity(i);
    }


}

