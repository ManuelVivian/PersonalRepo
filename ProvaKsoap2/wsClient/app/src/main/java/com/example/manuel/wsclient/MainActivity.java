package com.example.manuel.wsclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    private TextView risultato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        final EditText hobby = findViewById(R.id.hobby);
        risultato = findViewById(R.id.risultato);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hobby.length() > 0) {
                    new CallWebService().execute(hobby.getText().toString());
                }
            }
        });
    }

    class CallWebService extends AsyncTask<String, String, String> {

        private final String NAMESPACE = "http://Services/";
        private final String URL = "http://192.168.56.1:8080/serviziForniti/WSClass?WSDL";
        private final String METHOD_NAME = "sayHi";
        private final String SOAP_ACTION = "";

        @Override
        protected String doInBackground(String... strings) {
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("name", strings[0]);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                try {
                    androidHttpTransport.call(SOAP_ACTION, envelope);
                    SoapObject response = (SoapObject) envelope.bodyIn;
                    return response.getProperty(0).toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String rs) {
            super.onPostExecute(rs);
            if (rs != null) {
                risultato.setText(rs);
            }
        }
    }
}