package project.client.internshipPlatform;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import project.client.internshipPlatform.adminActivity.AllInternshipsOverview;
import project.client.internshipPlatform.adminActivity.UnapprovedInternships;

public class ErrorPageAdmin extends AppCompatActivity {
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

}
