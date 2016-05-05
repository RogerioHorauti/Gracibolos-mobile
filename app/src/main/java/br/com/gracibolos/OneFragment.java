package br.com.gracibolos;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OneFragment extends Fragment {

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

        Bundle params = this.getArguments();
        //Bundle não nulo
        if (params != null) {
            //this.msg = params.getString("num1");
            //this.msg = params.getString("mensagem");
            //this.num2 = params.getString("numero2");
            //Log.i("exemplo", msg);
            //TextView textViewNum1 = (TextView) view.findViewById(R.id.textViewSaida1);
            //textViewNum1.setText(msg);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
