package br.com.tonismar.dwiii_pg1_tonismar_rachaaconta;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class CalculoPorDivisao extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_por_divisao);
        
        final EditText edtTotal = (EditText) findViewById(R.id.edtTotal);
        final EditText edtNroParticipantes = (EditText) findViewById(R.id.edtNroParticipantes);
        final TextView txtResultado = (TextView) findViewById(R.id.txtResultado);
        final CheckBox chkGarcom = (CheckBox) findViewById(R.id.chkGarcon);
        
        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if( (!edtTotal.getText().toString().isEmpty()) && (!edtNroParticipantes.getText().toString().isEmpty()) ){
					Float resultado = Float.parseFloat(edtTotal.getText().toString()) / Float.parseFloat(edtNroParticipantes.getText().toString());
					if( chkGarcom.isChecked() ){
						resultado = (float) (resultado + ( resultado * 0.1));	
					}
					txtResultado.setText("Valor por participante:\n " + resultado.toString());
				}
				edtTotal.setText("");
				edtNroParticipantes.setText("");
			}
		});
        
        Button btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				edtTotal.setText("");
				edtNroParticipantes.setText("");
				txtResultado.setText("");
			}
		});
        
        Button btnItens = (Button) findViewById(R.id.btnItens);
        btnItens.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(CalculoPorDivisao.this, CalculoPorItens.class);
				startActivity(i);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calculo_por_divisao, menu);
        return true;
    }
}
