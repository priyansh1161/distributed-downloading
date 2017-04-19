package avinash.distributeddownloadingsystem;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InitiateDownload extends Activity {

    EditText et_url,et_numOfRequests;
    Button download;
    Context context;
    ProgressDialog progressDialog;
    String url;
    int numOfRequests;
    private String REQUEST_URL = "localhost:3000/download";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_initiate);
        context=this;
        et_url = (EditText) findViewById(R.id.link);
        et_numOfRequests = (EditText) findViewById(R.id.key);
        download = (Button) findViewById(R.id.download);
        progressDialog = new ProgressDialog(context);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!et_url.getText().toString().contains(" ")&&
                        !et_numOfRequests.getText().toString().contains(" ")&&
                        !et_url.getText().toString().equals("")&&
                        !et_numOfRequests.getText().toString().equals(""))
                {
                    url = et_url.getText().toString();
                    numOfRequests = Integer.parseInt(et_numOfRequests.getText().toString());

                    progressDialog.setMessage("Please wait...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    makeRequest();
                    progressDialog.dismiss();
                }
                else
                {
                    if(et_url.getText().toString().contains(" ")||et_url.getText().toString().equals("")){
                        et_url.setError("enter a valid url!");
                    }
                    if(et_numOfRequests.getText().toString().contains(" ")||et_numOfRequests.getText().toString().equals("")){
                        et_numOfRequests.setError("enter a valid number");
                    }
                }
            }
        });
    }

    private void makeRequest() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        //JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, REQUEST_URL, )
    }
}
