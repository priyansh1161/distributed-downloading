package avinash.distributeddownloadingsystem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class DownloadPart extends AppCompatActivity {

    private EditText url,key;
    private Button download;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_part);
        context=this;
        url = (EditText) findViewById(R.id.link);
        key = (EditText) findViewById(R.id.key);
        download = (Button) findViewById(R.id.download);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!url.getText().toString().contains(" ")&&!key.getText().toString().contains(" ")&&!url.getText().toString().equals("")&&!key.getText().toString().equals(""))
                {
                    Toast.makeText(context, "Download has started", Toast.LENGTH_SHORT).show();
                    String JSON_LinkKey = "{\"LinkKey\":{\"link\":\" Link  \",\"Key\":Key}}";
                    try {
                        JSONObject LinkKey = (new JSONObject(JSON_LinkKey)).getJSONObject("LinkKey");

                        //Communicate with the API here.

                    }catch (Exception e){e.printStackTrace();}
                }
                else
                {
                    if(url.getText().toString().contains(" ")||url.getText().toString().equals(""))
                        url.setError("url cannot contain blank spaces");
                    if(key.getText().toString().contains(" ")||key.getText().toString().equals(""))
                        key.setError("key cannot contain blank spaces");
                }
            }
        });
    }
}
