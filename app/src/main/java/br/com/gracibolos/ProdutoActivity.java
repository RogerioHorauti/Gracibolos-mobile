package br.com.gracibolos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        Intent it = getIntent();
        if(it != null) {
            Bundle params = it.getExtras();
            if (params != null) {
                //Converter int para string
                alerta(String.valueOf(params.getInt("id")));
            }
        }
    }

    private void alerta(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
