package project.client.internshipPlatform.adminActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;

import project.client.internshipPlatform.R;

public class AdminHomepage extends AppCompatActivity {

    private MaterialButton unApprovedInternships;
    private MaterialButton allInternships;
    private String adminId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hompage);

        Bundle bundle = getIntent().getExtras();
        adminId = bundle.getString("idAdmin");

        unApprovedInternships = findViewById(R.id.approveButton);
        allInternships =  findViewById(R.id.allInternships);

        unApprovedInternships.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        allInternships.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            i.putExtra("idAdmin", adminId);
            startActivity(i);
        }else  if (item.getItemId() == R.id.allInternships) {
            Intent i = new Intent(getApplicationContext(), AllInternshipsOverview.class);
            i.putExtra("idAdmin", adminId);
            startActivity(i);
        }
        return true;
    }

}
