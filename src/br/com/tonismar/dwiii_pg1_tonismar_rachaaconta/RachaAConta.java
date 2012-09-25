package br.com.tonismar.dwiii_pg1_tonismar_rachaaconta;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RachaAConta extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racha_aconta);
        
        Button btnCI = (Button) findViewById(R.id.btnCi);
        btnCI.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(RachaAConta.this, CalculoPorItens.class);
				startActivity(i);
				//debub("Cliquei no CI");
			}
		});
        
        Button btnDT = (Button) findViewById(R.id.btnDt);
        btnDT.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(RachaAConta.this, CalculoPorDivisao.class);
				startActivity(i);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_racha_aconta, menu);
        return true;
    }
    
    private void debub( String msg ){
    	Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
