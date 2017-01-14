package com.luca_innocenti.courtboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView mTextView;
    private Button pointbuttonleft;
    private Button pointbuttonright;
    private int gamesinistra = 0;
    private int gamedestra = 0;
    private int puntisinistra = 0;
    private int puntidestra = 0;
    private int setdestra = 0;
    private int setsinistra = 0;

    private int old_gamesinistra = 0;
    private int old_gamedestra = 0;
    private int old_puntisinistra = 0;
    private int old_puntidestra = 0;
    private int old_setdestra = 0;
    private int old_setsinistra = 0;


    private TextView gameleft;
    private TextView gameright;
    private TextView setleft;
    private TextView setright;
    private TextView tiebreak;
    private Button serviziodestra;
    private Button serviziosinistra;
    private float y1,y2;
    private int undo = 0;

    private void cambio_palla(){
        if (serviziosinistra.getVisibility() == View.VISIBLE)
        {
            serviziosinistra.setVisibility(View.INVISIBLE);
            serviziodestra.setVisibility(View.VISIBLE);
        }
        else
        {
            serviziosinistra.setVisibility(View.VISIBLE);
            serviziodestra.setVisibility(View.INVISIBLE);
        }
    }

    private void stampa_log()
    {
        Log.d("Punti", Integer.toString(puntisinistra)+"-"+Integer.toString(puntidestra));
        Log.d("Games", Integer.toString(gamesinistra)+"-"+Integer.toString(gamedestra));
        Log.d("Set", Integer.toString(setsinistra)+"-"+Integer.toString(setdestra));
    }

    private void undo_point(){
        if (undo ==0)
        {
        new AlertDialog.Builder(this)
                .setTitle("Undo Point?")
                //.setMessage("Clear all?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        undo = 1;
                        stampa_log();
                        gamedestra = old_gamedestra;
                        gamesinistra = old_gamesinistra;
                        puntidestra = old_puntidestra;
                        puntisinistra = old_puntisinistra;
                        setdestra = old_setdestra;
                        setsinistra = old_setsinistra;
                        undo = 1;
                        stampa_log();

                        switch (puntidestra){
                            case 0 : pointbuttonright.setText("00");
                                break;
                            case 1: pointbuttonright.setText("15");
                                break;
                            case 2: pointbuttonright.setText("30");
                                break;
                            case 3: pointbuttonright.setText("40");
                                break;
                            case 4: pointbuttonright.setText("AD");
                                break;

                        }

                        switch (puntisinistra){
                            case 0 : pointbuttonleft.setText("00");
                                break;
                            case 1: pointbuttonleft.setText("15");
                                break;
                            case 2: pointbuttonleft.setText("30");
                                break;
                            case 3: pointbuttonleft.setText("40");
                                break;
                            case 4: pointbuttonleft.setText("AD");
                                break;

                        }

                        gameleft.setText(Integer.toString(gamesinistra));
                        gameright.setText(Integer.toString(gamedestra));

                        setleft.setText((Integer.toString(setsinistra)));
                        setright.setText(Integer.toString(setdestra));

                    }
                })
                .setNegativeButton(android.R.string.no,null).show();
        }
    }



    private void salva_stato()
    {
        old_gamedestra = gamedestra;
        old_gamesinistra = gamesinistra;
        old_puntidestra = puntidestra;
        old_puntisinistra = puntisinistra;
        old_setdestra = setdestra;
        old_setsinistra = setsinistra;
    }


    private void aggiorna()
    {


        // gestisce il vantaggio pari
        if ((puntisinistra == 4) && (puntidestra == 4))
        {
            puntidestra = 3;
            puntisinistra = 3;
        }

        // vince ai vantaggi
        if (puntisinistra == 5){
            gamesinistra = gamesinistra + 1;
            puntidestra = 0;
            puntisinistra = 0;
            cambio_palla();
        }

        if (puntidestra == 5){
            gamedestra = gamedestra + 1;
            puntidestra = 0;
            puntisinistra = 0;
            cambio_palla();
        }


        // vince prima dei vantaggi
        if ((puntisinistra == 4) && (puntidestra < 3)){
            gamesinistra = gamesinistra + 1;
            puntidestra = 0;
            puntisinistra = 0;
            cambio_palla();
        }

        //
        if ((puntidestra == 4) && (puntisinistra < 3)){
            gamedestra = gamedestra + 1;
            puntidestra = 0;
            puntisinistra = 0;
            cambio_palla();
        }


            // gestione dei game se non siamo al 5° set
            if ((gamedestra == 6) && (gamesinistra <= 4)) {
                gamedestra = 0;
                gamesinistra = 0;
                setdestra = setdestra + 1;
            }

            if ((gamedestra == 7) && (gamesinistra <= 6)) {
                gamedestra = 0;
                gamesinistra = 0;
                setdestra = setdestra + 1;
            }

            if ((gamesinistra == 6) && (gamedestra <= 4)) {
                gamedestra = 0;
                gamesinistra = 0;
                setsinistra = setsinistra + 1;
            }
            if ((gamesinistra == 7) && (gamedestra <= 6)) {
                gamedestra = 0;
                gamesinistra = 0;
                setsinistra = setsinistra + 1;
            }

        switch (puntidestra){
            case 0 : pointbuttonright.setText("00");
                    break;
            case 1: pointbuttonright.setText("15");
                    break;
            case 2: pointbuttonright.setText("30");
                    break;
            case 3: pointbuttonright.setText("40");
                    break;
            case 4: pointbuttonright.setText("AD");
                break;

        }

        switch (puntisinistra){
            case 0 : pointbuttonleft.setText("00");
                break;
            case 1: pointbuttonleft.setText("15");
                break;
            case 2: pointbuttonleft.setText("30");
                break;
            case 3: pointbuttonleft.setText("40");
                break;
            case 4: pointbuttonleft.setText("AD");
                break;

        }

        gameleft.setText(Integer.toString(gamesinistra));
        gameright.setText(Integer.toString(gamedestra));

        setleft.setText((Integer.toString(setsinistra)));
        setright.setText(Integer.toString(setdestra));

        if ((gamedestra == 6) && (gamesinistra == 6))
        {
            tiebreak.setText("TB");
        }
        else
        {
            tiebreak.setText("");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointbuttonleft = (Button) findViewById(R.id.button_left);
        pointbuttonright = (Button) findViewById(R.id.button_right);

        gameleft = (TextView) findViewById(R.id.gameleft_txt);
        gameright = (TextView) findViewById(R.id.gameright_txt);

        setleft = (TextView) findViewById(R.id.setleft_txt);
        setright = (TextView) findViewById(R.id.setright_txt);

        tiebreak = (TextView) findViewById(R.id.tiebreaktxt);

        serviziodestra = (Button) findViewById(R.id.service_right) ;
        serviziosinistra = (Button) findViewById(R.id.service_left);
        serviziosinistra.setVisibility(View.GONE);


        pointbuttonleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salva_stato();
                puntisinistra = puntisinistra + 1;
                undo = 0;
                aggiorna();
            }
        });

        //corregge a destra
        pointbuttonleft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                undo_point();
                return true; //mettendo a true viene generato il solo evento LongClick senza che prima venga generato il Click solo
            }
        });

        pointbuttonright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salva_stato();
                puntidestra = puntidestra + 1;
                undo = 0;
                aggiorna();
            }
        });

        //corregge l'errore a sinistra
        pointbuttonright.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                undo_point();
                return true; //mettendo a true viene generato il solo evento LongClick senza che prima venga generato il Click solo
            }
        });

    }
}
