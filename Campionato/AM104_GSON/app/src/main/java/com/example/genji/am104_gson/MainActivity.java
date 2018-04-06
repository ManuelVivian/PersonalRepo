package com.example.genji.am104_gson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genji.am104_gson.ExtraActivities.ResultsActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView;
    private RFService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "call RFInterface.getEmbeddedService()");
        mService = RFService.retrofit.create(RFService.class);

        scrollView = (ScrollView) (ScrollView) findViewById(R.id.scroll);
        scrollView.removeAllViews();
        TextView tv = new TextView(this);
        tv.setText(getResources().getString(R.string.header));
        scrollView.addView(tv);


        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener((View view) -> {
            Log.d("MainActivity", "getRFResponse()");
            getRFResponse();
        });
    }

    public void getRFResponse() {
        mService.getPojo().enqueue(new Callback<Pojo>() {

            private ArrayList<Round> rounds = new ArrayList<>();

            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {

                Log.d("MainActivity", "onresponse()");

                if (response.isSuccessful()) {
                    Log.d("MainActivity", "onresponse.isSuccessful()");
                    Toast.makeText(MainActivity.this, "get response", Toast.LENGTH_SHORT);
                    rounds = (ArrayList<Round>) response.body().getRounds();
                    MainActivity.this.showRounds(rounds);

                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                    Log.d("MainActivity", "handle request errors");
                }
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    void showRounds(ArrayList<Round> rounds) {

        scrollView.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(this);
        //linearLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        params.setMargins(40, 10, 10, 10);
        for (final Round round : rounds) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setText(round.getName());
            tv.setOnClickListener((View view) -> {
                        Toast.makeText(MainActivity.this, round.getName(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this, ResultsActivity.class);
                        Bundle b = new Bundle();
                        ArrayList<String> s = new ArrayList<>();
                        ArrayList<Match> m = (ArrayList<Match>) round.getMatches();
                        for(Match h : m){
                            String a = h.getDate()+"\n  "+h.getTeam1().getName()+": "+h.getScore1()+"\n  "+h.getTeam2().getName()+": "+h.getScore2()+"\n";
                            s.add(a);
                        }
                        s.add(round.getMatches().toString());
                        b.putStringArrayList("array",s);
                        i.putExtras(b);
                        startActivity(i);
                    }
            );
            linearLayout.addView(tv);
        }
        scrollView.addView(linearLayout);
    }

    void showErrorMessage() {
        Log.d("MainActivity", "Error");
    }
}
