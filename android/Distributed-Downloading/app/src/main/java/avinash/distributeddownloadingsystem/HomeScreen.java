package avinash.distributeddownloadingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void InitiateDownload(View view) {
        Intent i = new Intent(this, InitiateDownload.class);
        startActivity(i);
    }

    public void DownloadPartition(View view) {
        Intent i = new Intent(this, DownloadPart.class);
        startActivity(i);
    }
}
