package br.com.gracibolos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rogerio on 21/04/2016.
 */
public class ListaProdutosAdapter extends ArrayAdapter<Produto>
{
    private Context context;
    private List<Produto> produtoList = null;

    public ListaProdutosAdapter(Context context,  List<Produto> produtoList)
    {
        super(context, 0, produtoList);
        this.produtoList = produtoList;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        Produto produto = produtoList.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_produto, null);

        //Imagem com Picasso
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        Picasso.with(context)
                .load(new Url().getUrl() + "/resources/img/produtos/" + produto.getFoto())
                .into(imageView);

        //Nome
        TextView textViewNome = (TextView) view.findViewById(R.id.text_view_nome);
        textViewNome.setText(produto.getNome());

        //Valor
        TextView textViewValor = (TextView)view.findViewById(R.id.text_view_obs);
        String textoValor = String.valueOf("R$ " + produto.getValor());
        textViewValor.setText(textoValor);

        return view;
    }

}
