package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.androidhive.materialtabs.R;

import static android.content.ContentValues.TAG;


public class TwoFragment extends Fragment {


    int decPartsecnum;

    EditText etatop, etabot, etttop, ettbot;
    Button calbtn, clearvaluebtn;
    int curFocus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        etatop = (EditText) view.findViewById(R.id.aspectwidth);
        etabot = (EditText) view.findViewById(R.id.aspectheight);
        etttop = (EditText) view.findViewById(R.id.imagepixt);
        ettbot = (EditText) view.findViewById(R.id.lasteditbox);
        etatop.setTag(1);
        etabot.setTag(2);
        etttop.setTag(3);
        ettbot.setTag(4);

        calbtn = (Button) view.findViewById(R.id.calculate_btn);
        clearvaluebtn = (Button) view.findViewById(R.id.clear_btn);
        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    calculate((int) v.getTag());
                else
                    curFocus = (int) v.getTag();
            }
        };
        etatop.setOnFocusChangeListener(listener);
        etabot.setOnFocusChangeListener(listener);
        etttop.setOnFocusChangeListener(listener);
        ettbot.setOnFocusChangeListener(listener);
        clearvaluebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etatop.setText("4");
                etabot.setText("3");
                etttop.setText("800");
                ettbot.setText("600");
            }
        });
        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate(curFocus);
            }
        });
        return view;
    }

    void calculate(int position){
        int at, ab, tt, tb;
        at = Integer.parseInt(etatop.getText().toString());
        ab = Integer.parseInt(etabot.getText().toString());
        tt = etttop.getText().equals("NaN") ? Integer.MAX_VALUE : Integer.parseInt(etttop.getText().toString());
        tb = ettbot.getText().equals("NaN") ? Integer.MAX_VALUE : Integer.parseInt(ettbot.getText().toString() );
        if (position == 1 || position == 4)
        {
            if (ab == 0)
                tt = Integer.MAX_VALUE;
            else
                tt = tb * at / ab;
            etttop.setText(tt == Integer.MAX_VALUE ? "NaN" : String.valueOf(tt));
        }
        else if (position == 2 || position == 3)
        {
            if (at == 0)
                tb = Integer.MAX_VALUE;
            else
                tb = tt * ab / at;
            ettbot.setText(tb == Integer.MAX_VALUE ? "NaN" : String.valueOf(tb));
        }
    }

}
