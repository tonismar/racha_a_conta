package br.com.tonismar.dwiii_pg1_tonismar_rachaaconta;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CalculoPorItens extends Activity {
	
	List<String> itens = new ArrayList<String>();
	List<String> subTotal = new ArrayList<String>();
	ArrayList<String> arrItens = new ArrayList<String>();
	ArrayList<String> arrSubTotal = new ArrayList<String>();
	ArrayAdapter<String> adapterItens;
	ArrayAdapter<String> adapterSubTotal;
	int requestCode;
	float totalConta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_por_itens);
        
        Button btnInserir = (Button) findViewById(R.id.btnInserir);
        btnInserir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(CalculoPorItens.this, InsereItem.class);
				startActivityForResult(i, requestCode);
				
			}
		});
        
        Button btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapterItens.clear();
				adapterSubTotal.clear();
			}
		});
        
        Button btnDividir = (Button) findViewById(R.id.btnDividir);
        btnDividir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(CalculoPorItens.this, CalculoPorDivisao.class);
				startActivity(i);
			}
		});
        
        ListView lstViewItens = (ListView) findViewById(R.id.lstItens);
        adapterItens = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);
        adapterItens.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lstViewItens.setAdapter(adapterItens);
        
        ListView lstViewSubTotal = (ListView) findViewById(R.id.lstSubTotal);
        adapterSubTotal = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, subTotal);
        adapterSubTotal.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lstViewSubTotal.setAdapter(adapterSubTotal);
        adapterSubTotal.notifyDataSetChanged();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	String[] strItem = data.getExtras().getString("returnKey1").split(" ", 3);
    	//Toast.makeText(this, strItem[0], Toast.LENGTH_LONG).show();
    	
    	if((!strItem[0].isEmpty()) && (!strItem[1].isEmpty()) && (!strItem[2].isEmpty())){
    		itens.add(strItem[0]+" "+strItem[1]+" "+strItem[2]);
    		adapterItens.notifyDataSetChanged();
    		//Toast.makeText(this, data.getExtras().getString("returnKey1")  , Toast.LENGTH_LONG).show();
    		atualizaDivida(strItem[0], strItem[2]);
    		float x = atualizaTotalGeral();
    		x = (float) (x + (x * 0.1));
    		
    		TextView txtTotal = (TextView) findViewById(R.id.txtTotal);
    		txtTotal.setText("Total geral: "+ String.format("%.5f",x));
    	} else {
    		Toast.makeText(this, "Valore inv‡lidos ou nulos", Toast.LENGTH_LONG).show();
    	}
    }
    
    public float atualizaTotalGeral(){
    	float tot = 0;
    	for(int i=0; i<subTotal.size(); i++){
    		String [] lin = subTotal.get(i).split(" ", 2);
    		tot = tot + Float.parseFloat(lin[1]);
    	}
		return tot;
    }
    
    public void atualizaDivida( String nome, String valor ){
    	for(int i=0; i<subTotal.size(); i++){
    		String[] linha = subTotal.get(i).split(" ", 2);
    		//Toast.makeText(this, linha[0]+" "+linha[1], Toast.LENGTH_LONG).show();
    		if( linha[0].equals(nome) ){
    			Float total = Float.parseFloat(linha[1].replace(',', '.')) + Float.parseFloat(valor.replace(',', '.')); 
    			subTotal.set(i, nome+" "+total.toString());
    			//Toast.makeText(this, "ContŽm", Toast.LENGTH_LONG).show();
    			adapterSubTotal.notifyDataSetChanged();
    			return;
    		}
    	}
    	subTotal.add(nome +" "+ valor);
    	adapterSubTotal.notifyDataSetChanged();
    	return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calculo_por_itens, menu);
        return true;
    }
}
