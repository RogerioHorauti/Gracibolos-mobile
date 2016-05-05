package br.com.gracibolos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ProdutoActivity extends AppCompatActivity {

    Intent it;
    ImageView imageView;
    TextView textViewObs;
    TextView textViewPreco;
    TextView textViewCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        it = getIntent();
        imageView = (ImageView) findViewById(R.id.imageViewProduto);
        textViewObs = (TextView) findViewById(R.id.textViewObs);
        textViewCodigo = (TextView) findViewById(R.id.textViewCodigo);
        textViewPreco = (TextView) findViewById(R.id.textViewPreco);

        if(it != null) {
            Bundle params = it.getExtras();
            if (params != null)
            {
                //Converter int para string
//                alerta(String.valueOf(params.getInt("id"))+
//                        "\n"+params.getString("foto")+
//                        "\n"+params.getString("codigo")+
//                        "\n"+String.valueOf(params.getDouble("valor")));

                //Carregar a foto
                Picasso.with(ProdutoActivity.this)
                        .load(new Url().getUrl() + "/resources/img/produtos/" + params.getString("foto"))
                        .into(imageView);

                textViewObs.setText(params.getString("obs"));
                textViewCodigo.setText(params.getString("codigo"));
                textViewPreco.setText(String.valueOf(params.getDouble("valor")));
            }
        }
    }

    private void alerta(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
