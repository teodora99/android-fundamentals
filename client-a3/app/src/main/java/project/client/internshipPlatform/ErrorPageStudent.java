package project.client.internshipPlatform;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import project.client.internshipPlatform.userActivity.ApplicationActivity;
import project.client.internshipPlatform.userActivity.FavouriteInternship;

public class ErrorPageStudent extends AppCompatActivity {
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
        }else  if (item.getItemId() == R.id.applications) {
            Intent i = new Intent(getApplicationContext(), ApplicationActivity.class);
            i.putExtra("idUser", id);
            startActivity(i);
            Toast.makeText(this, "Here are your applications", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
