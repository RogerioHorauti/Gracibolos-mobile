package br.com.gracibolos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
    private ProgressDialog pDialog;
    private ListView listView;
    Produto p;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        //
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

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
                    msg += "Nome : "+ p.getNome()+"\n";
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

                        Intent it = new Intent(MainActivity.this, SaidaActivity.class);
                        Bundle params = new Bundle();
                        params.putInt("id", produto.getId());
                        params.putString("foto", produto.getFoto());
                        params.putString("obs", produto.getObs());
                        params.putDouble("valor", produto.getValor());
                        params.putString("codigo", produto.getCodigo());
                        params.putString("receita", produto.getReceita());
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
                produto.setCodigo(jsonObject.getString("codigo"));
                produto.setReceita(jsonObject.getString("receita"));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
