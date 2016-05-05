package br.com.gracibolos;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TwoFragment extends Fragment {

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

        Bundle params = this.getArguments();
        //Bundle não nulo
        if (params != null) {
            Produto p = (Produto) params.getSerializable("produto");
            Log.i("rest", p.getReceita());
        }

        return view;
    }
}
