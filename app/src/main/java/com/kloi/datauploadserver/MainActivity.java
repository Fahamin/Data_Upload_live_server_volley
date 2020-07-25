package com.kloi.datauploadserver;

import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public String url = "http://addonsconfig.com/test/record.php";
    EditText nameEDT, emailEDT, passEDT;
    Button registerBtn;
    public static final String name = "username";
    public static final String pass = "password";
    public static final String email = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerBtnClick();
            }
        });

    }

    private void init() {
        nameEDT = findViewById(R.id.userNAMEid);
        emailEDT = findViewById(R.id.emailid);
        passEDT = findViewById(R.id.passid);
        registerBtn = findViewById(R.id.registerid);
    }

    public void registerBtnClick() {
        final String namedata = nameEDT.getText().toString().trim();
        final String passdata = passEDT.getText().toString().trim();
        final String emaildata = emailEDT.getText().toString().trim();


        RequestQueue queue = Volley.newRequestQueue(this);


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put(name,namedata);
                param.put(pass,passdata);
                param.put(email,emaildata);

                return param;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
