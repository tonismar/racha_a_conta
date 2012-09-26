package br.com.tonismar.dwiii_pg1_tonismar_rachaaconta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InsereItem extends Activity {
	String[] item = new String[] {"Refri", "Água", "Chopp", "Drink", "Petisco", "Lanche", "Refeição", "Outros"};
	String[] NOMES = new String[] {};
	EditText valor; 
	//nome2;
	Spinner spinner;
	AutoCompleteTextView nome;
	
	String filename = "names.raw";
	Context context;
	Nomes lstNome = new Nomes();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_item);
        
        context = this.getApplicationContext();
        
       	spinner = (Spinner) findViewById(R.id.cmbItem);
       	nome = (AutoCompleteTextView) findViewById(R.id.edtNome);
    	valor = (EditText) findViewById(R.id.edtValor);

    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner.setAdapter(adapter);
    	
    	Button btnInserer = (Button) findViewById(R.id.btnInsere);
    	
    	btnInserer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String toFile = readNome();
				if( !toFile.toLowerCase().contains(nome.getText().toString().toLowerCase()) ){
					saveNome(readNome() + nome.getText().toString().trim());
				}
				finish();
			}
		});
    	    	
    	NOMES = readNome().split("\\|");
    	
    	ArrayAdapter<String> cmpAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, NOMES);
    	
    	nome.setAdapter(cmpAdapter);
    	
    	Button btnCancelar = (Button) findViewById(R.id.btnCancela);
    	btnCancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nome.setText("");
				valor.setText("");
				spinner.setSelection(1);
			}
		});
    	
    }
    
    public void saveNome(String nome) {
    	lstNome.nomes = nome + "|";
    	
    	try {
    		FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
    		ObjectOutputStream os = new ObjectOutputStream(fos);
        	os.writeObject(lstNome);
        	os.close();
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public String readNome() {
    	String str = "";
    	try {
    		FileInputStream fis = context.openFileInput( filename );
    		ObjectInputStream is = new ObjectInputStream(fis);
    		lstNome = (Nomes) is.readObject();
    		is.close();
    		if( lstNome != null ){
    			str += lstNome.nomes;
    		}
    		//Toast.makeText(this, "Objeto lstNome" + lstNome.toString(), Toast.LENGTH_LONG).show();
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    	}
    	//Toast.makeText(this, str, Toast.LENGTH_LONG).show();
		return str;
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
