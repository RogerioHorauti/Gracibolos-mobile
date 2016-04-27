package br.com.gracibolos;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String msg = "";
    private List<Produto> listaProdutos = null;
    private String [] nomeProdutos = null;
    private ProgressDialog pDialog;
    private ListView listView;
    Produto p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pDialog = new ProgressDialog(this);
        // Mostrando diálogo de progresso antes de fazer pedido http
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(new Url().getUrl()+"/rest-produtos", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                //diálogo de progresso
                hidePDialog();

                //Visualizar a List de produtos - concatenação de string
                listaProdutos = getListaProdutos(response);

                //neste método concatena a string de msg e retorna a lista de produtos List<Produto>
                for(Produto p : listaProdutos)
                {
                    msg += "Nome : "+ p.getNome()+", ";
                }
                //textView.setText(msg);
                Log.i("rest", msg);

                //Converte List<Produto> em ListView com o adaptador
                /*final ArrayAdapter<Produto> produtosAdapter =
                        new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1, listaProdutos);*/

                final ListaProdutosAdapter produtosAdapter = new ListaProdutosAdapter(MainActivity.this,  listaProdutos);

                listView = (ListView)findViewById(R.id.listViewProdutos);
                //Setando as informações - o q será exibido esta no toString() do model
                listView.setAdapter(produtosAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Produto produto = produtosAdapter.getItem(position);
                        //alerta(produto.getNome());

                        Intent it = new Intent(MainActivity.this, ProdutoActivity.class);
                        Bundle params = new Bundle();
                        params.putInt("id", produto.getId());
                        it.putExtras(params);
                        startActivity(it);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                alerta("Sem conexão com o web service.");
                hidePDialog();
            }
        });

        //Enfileiramento
        ApplicationController.getInstance().addToRequestQueue(req);

    }

    //Convertendo a lista de objeto json em List<Produto>
    public List<Produto> getListaProdutos(JSONArray response)
    {
        //Lista de produtos em List
        List produtos = new ArrayList<Produto>();

        //Percorrer por cada objeto e settar os valores
        for (int i=0; i < response.length(); i++){
            try {

                JSONObject jsonObject = (JSONObject) response.get(i);
                Produto produto = new Produto();
                produto.setId(jsonObject.getInt("id"));
                produto.setValor(jsonObject.getDouble("valor"));
                produto.setNome(jsonObject.getString("nome"));
                produto.setFoto(jsonObject.getString("foto"));
                produto.setObs(jsonObject.getString("obs"));
                //Adicionar produto no List
                produtos.add(produto);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return produtos;
    }

    private void alerta(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null)
        {
            //Demitir
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
