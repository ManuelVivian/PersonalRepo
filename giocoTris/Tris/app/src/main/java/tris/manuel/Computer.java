package tris.manuel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import tris.manuel.R;

public class Computer extends AppCompatActivity {

    private String giocatore1=" Giocatore";
    private TextView testo;
    private GridLayout gridLayout;

    private int giocatore = 1, computer = 2;
    private int turno;

    private Tris tris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tris);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tris = new Tris();

        testo = (TextView) findViewById(R.id.testoComputer);

        turno = getIntent().getIntExtra("turno", 1);
        if(turno == giocatore)
            testo.setText(getResources().getString(R.string.turno) + giocatore1);
        else testo.setText(getResources().getString(R.string.turno) + " Computer");
        if(turno == computer)
            mossaComputer();
    }

    public void checkVittoria(){
        if(tris.hasWin()){
            if(turno == computer)
                testo.setText(getResources().getString(R.string.haVinto)+giocatore1);
            else
                testo.setText(getResources().getString(R.string.haVinto)+" Computer!");
            turno = 0;
        } else if(tris.getLiberi() <= 0){
            testo.setText(getResources().getString(R.string.pareggio));
            turno = 0;
        } else if(turno == computer)
            mossaComputer();
    }


    public void bottone(View view){
        int row, column;
        String id = getResources().getResourceName(view.getId());
        id = id.substring(id.length()-3);
        row = Integer.parseInt(id.charAt(1)+"");
        column = Integer.parseInt(id.charAt(2)+"");
        if(turno == giocatore) {
            if (tris.isLibero(row, column)) {
                tris.setTurno(row, column, giocatore);
                Button b = (Button)view;
                b.setBackground(getResources().getDrawable(R.drawable.croce));
                testo.setText(getResources().getString(R.string.turno) + " Computer");
                turno = computer;
                checkVittoria();
            } else
                Toast.makeText(this, getResources().getString(R.string.nonLibera), Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, getResources().getString(R.string.nonTurno), Toast.LENGTH_SHORT).show();
    }

    public void mossaComputer(){
        int[][] matrice = tris.getMatrice();
        int posRandom = (int)(Math.random()*tris.getLiberi());
        for(int i=0, counterPos = 0; i<matrice.length; i++){
            for(int j=0; j<matrice[i].length; j++){
                if(tris.isLibero(i, j)){
                    if(counterPos == posRandom) {
                        tris.setTurno(i, j, computer);
                        final int finalI = i;
                        final int finalJ = j;
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                //Button b = (Button)gridLayout.getChildAt(gridLayout.getColumnCount()* finalI + finalJ);
                                Button b = (Button) findViewById(getResources().getIdentifier("b"+finalI+finalJ, "id", getPackageName()));
                                b.setBackground(getResources().getDrawable(R.drawable.cerchio));
                                testo.setText(getResources().getString(R.string.turno)+giocatore1);
                                turno = giocatore;
                                checkVittoria();
                            }
                        }, 500);
                        return;
                    }
                    counterPos++;
                }
            }
        }
    }

    public void restartButton(View w){
        Intent intent = new Intent(this, this.getClass());
        if(getIntent().getIntExtra("turno", giocatore) == giocatore)
            intent.putExtra("turno", computer);
        else intent.putExtra("turno", giocatore);
        startActivity(intent);
        finish();
    }
}
