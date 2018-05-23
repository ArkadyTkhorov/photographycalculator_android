package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.materialtabs.R;


public class DoFCalculator extends Fragment {

    EditText txtF;
    Spinner aSpinner, cSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.calculators_fragment_dof, container, false);


        Button button = (Button) rootView.findViewById(R.id.btn_Cal);
        aSpinner = (Spinner) rootView.findViewById(R.id.aSpinner);
        txtF = (EditText) rootView.findViewById(R.id.focal_length);
        cSpinner = (Spinner) rootView.findViewById(R.id.coc);
        final TextView txtAnswer = (TextView) rootView.findViewById(R.id.txt_H);



        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String F = txtF.getText().toString();
                String A = aSpinner.getSelectedItem().toString();
                String C = cSpinner.getSelectedItem().toString();
                if (!F.isEmpty() )
                {
                    txtAnswer.setText("H = %.2f" + Calcullate_H(F, A, C) + "");
                }
                else
                {
                    Toast.makeText(getActivity(), "All data Required", Toast.LENGTH_LONG).show();
                }
            }
        });


        return rootView;
    }

    private double Calcullate_H(String txtF, String txtA, String txtC) {
        double f = Double.parseDouble(txtF.toString());
        double A = Double.parseDouble(txtA.toString());
        double C = Double.parseDouble(txtC.toString());
        double h = ((f * f) / (A * C)) + f;
        //h = (double) Math.round(h * 100000) / 100000;
        h = (double) Math.round(h * 1000) / 1000;
        //h = (double) (Math.round((h * 100000) / 100000));
        return h;
    }


}


