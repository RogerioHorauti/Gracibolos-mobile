package br.com.gracibolos;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OneFragment extends Fragment {

    ImageView imageView;
    TextView textViewObs;
    TextView textViewPreco;
    TextView textViewCodigo;

    public OneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_one_fragment, container, false);

        imageView = (ImageView) view.findViewById(R.id.imageViewProduto);
        textViewObs = (TextView) view.findViewById(R.id.textViewObs);
        textViewCodigo = (TextView) view.findViewById(R.id.textViewCodigo);
        textViewPreco = (TextView) view.findViewById(R.id.textViewPreco);

        Bundle params = this.getArguments();
        //Bundle não nulo
        if (params != null) {
            Produto p = (Produto) params.getSerializable("produto");

            Picasso.with(getContext())
                    .load(new Url().getUrl() + "/resources/img/produtos/" + p.getFoto())
                    .into(imageView);

            textViewObs.setText(p.getObs());
            textViewCodigo.setText(p.getCodigo());
            textViewPreco.setText(String.valueOf(p.getValor()));
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
