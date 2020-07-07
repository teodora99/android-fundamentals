package project.client.internshipPlatform;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import project.client.internshipPlatform.companyActivity.InternshipAdd;
import project.client.internshipPlatform.companyActivity.MyInternships;

public class ErrorPageCompany extends AppCompatActivity {
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_page);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("idUser");

        setUpToolbar();
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

}
