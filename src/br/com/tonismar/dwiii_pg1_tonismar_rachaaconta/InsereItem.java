package br.com.tonismar.dwiii_pg1_tonismar_rachaaconta;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InsereItem extends Activity {
	String[] item = new String[] {"Refri", "Água", "Chopp", "Drink", "Petisco", "Lanche", "Refeição", "Outros"};
	String[] NOMES = new String[] {"Tonismar", "Regis", "Bernardo"};
	EditText valor; 
	//nome2;
	Spinner spinner;
	AutoCompleteTextView nome;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_item);

       	spinner = (Spinner) findViewById(R.id.cmbItem);
       	nome = (AutoCompleteTextView) findViewById(R.id.edtNome);
    	valor = (EditText) findViewById(R.id.edtValor);
    	//nome2 = (EditText) findViewById(R.id.edtNome);

    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner.setAdapter(adapter);
    	
    	Button btnInserer = (Button) findViewById(R.id.btnInsere);
    	
    	btnInserer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    	
    	ArrayAdapter<String> cmpAdapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, NOMES);
    	nome.setAdapter(cmpAdapter);
    }
    
    public void finish() {    	
    	Intent data = new Intent();
    	data.putExtra("returnKey1", nome.getText().toString().trim() + " " + spinner.getSelectedItem().toString() + " " + valor.getText());
    	setResult(1, data);
    	super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_insere_item, menu);
        return true;
    }
}
