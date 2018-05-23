package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import info.androidhive.materialtabs.R;


public class SixFragment extends Fragment{

    EditText input_width_et, input_height_et;
    EditText mega_pixel_et;
    EditText op_w1_et, op_h1_et;
    EditText op_w2_et, op_h2_et;
    EditText op_w3_et, op_h3_et;
    double sizemb,printw, printh, printw2, printh2, printw3, printh3;
    double wpix, hpix;
    Button cal_btn;
    public SixFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_six, container, false);

        input_width_et = (EditText) view.findViewById(R.id.inputw_et);
        input_height_et = (EditText)view.findViewById(R.id.inputh_et);

        cal_btn = (Button) view.findViewById(R.id.six_calcbtn);

        mega_pixel_et = (EditText)view.findViewById(R.id.megapixel_et);

        op_w1_et = (EditText) view.findViewById(R.id.inches_wide1_et);
        op_h1_et = (EditText) view.findViewById(R.id.inches_high1_et);
        op_w2_et = (EditText) view.findViewById(R.id.inches_wide2_et);
        op_h2_et = (EditText) view.findViewById(R.id.inches_high2_et);
        op_w3_et = (EditText) view.findViewById(R.id.inches_wide3_et);
        op_h3_et = (EditText) view.findViewById(R.id.inches_high3_et);

        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    wpix = Double.parseDouble(input_width_et.getText().toString());
                    hpix = Double.parseDouble(input_height_et.getText().toString());
                }catch (NumberFormatException e)
                {
                    wpix = 0;
                    hpix = 0;
                }
                sizemb = (((wpix * hpix) / 1000000) *100)/100;
                printw = (((wpix / 300) *10))/10;
                printh = (((hpix / 300) *10))/10;
                printw2 = (((wpix / 150) *10))/10;
                printh2 = (((hpix / 150) *10))/10;
                printw3 = (((wpix / 115) *10))/10;
                printh3 = (((hpix / 115) *10))/10;
                mega_pixel_et.setText(String.format( "%.2f", sizemb));
                op_w1_et.setText(String.format( "%.2f", printw));
                op_h1_et.setText(String.format( "%.2f", printh));
                op_w2_et.setText(String.format( "%.2f", printw2));
                op_h2_et.setText(String.format( "%.2f", printh2));
                op_w3_et.setText(String.format( "%.2f", printw3));
                op_h3_et.setText(String.format( "%.2f", printh3));
            }
        });


        return view;
    }

}
