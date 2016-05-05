package br.com.gracibolos;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TwoFragment extends Fragment {

    private ImageView imageView;
    private TextView textViewReceita;

    public TwoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_two_fragment, container, false);

        imageView = (ImageView) view.findViewById(R.id.imageViewReceita);
        textViewReceita = (TextView) view.findViewById(R.id.textViewTeceita);

        Bundle params = this.getArguments();
        //Bundle não nulo
        if (params != null) {
            Produto p = (Produto) params.getSerializable("produto");

            Picasso.with(getContext())
                    .load(new Url().getUrl() + "/resources/img/produtos/capa2.png")
                    .into(imageView);

            textViewReceita.setText(p.getReceita());
        }

        return view;
    }
}
