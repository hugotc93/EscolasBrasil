package br.iesb.escolasbrasil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Gabriel on 26/11/2017.
 */

public class BoasVindas extends AppCompatActivity{

    Button btnVerEscolas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.iesb.escolasbrasil.R.layout.boas_vindas);

        btnVerEscolas = (Button)findViewById(R.id.btnVerEscolas);

        btnVerEscolas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BoasVindas.this, MainActivity.class);
                startActivity(it);
            }
        });

    }


}
