package info.androidhive.materialtabs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import info.androidhive.materialtabs.R;


public class FlashRangeCalculator extends Fragment {

    RelativeLayout rlRange,rlAperture,rlGuide;
    Spinner spinner_iso,spinner_iso1,spinner_iso2;
    Spinner spinner_unit,spinner_guide_unit1,spinner_unit_range1,spinner_unit_range2,spinner_unit2;
    EditText etxtGuideNumber,etxtGuideNumber1,etxtGuideNumber2;
    EditText etxtApeture,etxtAperture1,etxtAperture2;
    EditText etxtRange,etxtRange1,etxtRange2;
    Button rangeButton,apertureButton,guideButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_flash_calculator, container, false);


        rlRange=(RelativeLayout)v.findViewById(R.id.rlCalRange);
        rlAperture=(RelativeLayout)v.findViewById(R.id.rlCalAperture);
        rlGuide=(RelativeLayout)v.findViewById(R.id.rlCalGuide);

        spinner_iso  = (Spinner) v.findViewById(R.id.spinnerIso);
        spinner_iso1= (Spinner) v.findViewById(R.id.spinnerIso1);
        spinner_iso2= (Spinner) v.findViewById(R.id.spinnerIso2);
        spinner_unit =(Spinner) v.findViewById(R.id.spinner_unit);
        spinner_guide_unit1=(Spinner) v.findViewById(R.id.spinner_guide_unit1);
        spinner_unit_range1=(Spinner) v.findViewById(R.id.spinner_unit_range);
        spinner_unit_range2=(Spinner) v.findViewById(R.id.spinner_unit_range2);
        spinner_unit2=(Spinner) v.findViewById(R.id.spinner_unit_guide2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.fpc_iso, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.fpc_guide_units, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_iso.setAdapter(adapter);
        spinner_iso1.setAdapter(adapter);
        spinner_iso2.setAdapter(adapter);

        spinner_unit.setAdapter(adapter1);
        spinner_guide_unit1.setAdapter(adapter1);
        spinner_unit_range1.setAdapter(adapter1);
        spinner_unit_range2.setAdapter(adapter1);
        spinner_unit2.setAdapter(adapter1);

        etxtGuideNumber=(EditText) v.findViewById(R.id.etxtGuideNumber);
        etxtGuideNumber1=(EditText) v.findViewById(R.id.etxtGuideNumber1);
        etxtGuideNumber2=(EditText) v.findViewById(R.id.etxtGuideNumber2);
        etxtApeture=(EditText)v.findViewById(R.id.etxtAperture);
        etxtAperture1=(EditText)v.findViewById(R.id.etxtAperture1);
        etxtAperture2=(EditText)v.findViewById(R.id.etxtAperture2);

        etxtRange=(EditText)v.findViewById(R.id.etxtRange);
        etxtRange1=(EditText)v.findViewById(R.id.etxtRange1);
        etxtRange2=(EditText)v.findViewById(R.id.etxtRange2);

        rangeButton = (Button)v.findViewById(R.id.rangeCalcButton);
        apertureButton = (Button) v.findViewById(R.id.apertureCalcButton);
        guideButton = (Button) v.findViewById(R.id.guideCalcButton);


        rangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String val1 = etxtGuideNumber.getText().toString();
                String val2 = etxtApeture.getText().toString();
                if (val1.matches("")) {
                    Toast.makeText(getActivity(), "Enter Guide number ", Toast.LENGTH_SHORT).show();

                } else if (val2.matches("")) {
                    Toast.makeText(getActivity(), "Enter Apeture", Toast.LENGTH_SHORT).show();
                } else {
                    float fpcGuide = Float.parseFloat(etxtGuideNumber.getText().toString());
                    int fpcIso = Integer.parseInt(spinner_iso.getSelectedItem().toString());
                    float fpcAperture = Float.parseFloat(etxtApeture.getText().toString());
                    cal(fpcGuide, fpcIso, fpcAperture);
                }

            }
        });

        apertureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String val1=etxtRange1.getText().toString();
                String val2=etxtGuideNumber1.getText().toString();
                if(val1.matches("") )
                {
                    Toast.makeText(getActivity(), "Enter Ranger ", Toast.LENGTH_SHORT).show();

                }
                else if(val2.matches(""))
                {
                    Toast.makeText(getActivity(), "Enter Guide", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    float fpcGuide= Float.parseFloat(etxtRange1.getText().toString());
                    int fpcIso= Integer.parseInt(spinner_iso.getSelectedItem().toString());
                    float fpcRange= Float.parseFloat(etxtGuideNumber1.getText().toString());
                    calAperture(fpcGuide, fpcIso, fpcRange);
                }

            }
        });

        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String val1=etxtRange2.getText().toString();
                String val2=etxtAperture2.getText().toString();
                if(val1.matches("") )
                {
                    Toast.makeText(getActivity(), "Enter Range ", Toast.LENGTH_SHORT).show();

                }
                else if(val2.matches(""))
                {
                    Toast.makeText(getActivity(), "Enter Apeture", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    float fpcRange= Float.parseFloat(etxtRange2.getText().toString());
                    int fpcIso= Integer.parseInt(spinner_iso.getSelectedItem().toString());
                    float fpcApertur= Float.parseFloat(etxtAperture2.getText().toString());
                    calGuide(fpcRange,fpcIso,fpcApertur);
                }

            }
        });

        return v;
    }



    private void cal(float fpcGuide,int fpcIso,float fpcAperture)
    {
        float r = (float) (fpcGuide * Math.sqrt(fpcIso / 100)/fpcAperture);
        Log.e("", "" + r);
        Log.e("", "" + fpcGuide);
        Log.e("", "" + fpcIso);
        Log.e("", "" + fpcAperture);
        etxtRange.setText(String.format("%.2f", r));
    }

    private void calAperture(float fpcGuide,int fpcIso,float fpcRange)
    {
        float r = (float) (fpcGuide * Math.sqrt(fpcIso / 100)/fpcRange);
        Log.e("", "" + r);
        Log.e("", "" + fpcGuide);
        Log.e("", "" + fpcIso);
        Log.e("", "" + fpcRange);
        etxtAperture1.setText(String.format("%.2f", r));
    }

    private void calGuide(float fpcRange,int fpcIso,float fpcApertur)
    {
        float r = (float) (fpcRange * fpcApertur/ Math.sqrt(fpcIso / 100));
        Log.e("", "" + r);
        Log.e("", "" + fpcApertur);
        Log.e("", "" + fpcIso);
        Log.e("", "" + fpcRange);
        etxtGuideNumber2.setText(String.format("%.2f", r));
    }


}
