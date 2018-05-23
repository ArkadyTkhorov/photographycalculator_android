package info.androidhive.materialtabs.fragments;


/**
 * Created by Greg.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.materialtabs.R;


public class TimeLapseCalculator extends Fragment {

    boolean one = true, two = true, three = false;
    Context thisContext;
    private EditText duration, fps, frames;
    private Button calculate;
    private TextView output;
    private double Min, Fps, Frames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View windows = inflater.inflate(R.layout.calculators_timelapse, container, false);
        thisContext = getActivity();
        duration = (EditText) windows.findViewById(R.id.num1);
        String a = "1";
        Min = 1;
        duration.setText(a);
        fps = (EditText) windows.findViewById(R.id.num2);
        String b = "60";
        Fps = 60;
        fps.setText(b);
        frames = (EditText) windows.findViewById(R.id.num3);
        String c = "";
        frames.setText(c);
        //frames.setEnabled(false);
        calculate = (Button) windows.findViewById(R.id.calcbuttontimelapse);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int total = totalNum();
                //Log.d("A B C IS", ""+a + " " + b + " " + c);
                if (total != 1) {
                    Toast.makeText(thisContext, "Make Only The Field You Want To Calculate Cleared", Toast.LENGTH_SHORT).show();
                } else {
                    if (one == false) {
                        String ans = "" + Frames / Fps;
                        output.setText(ans);
                    } else if (two == false) {
                        String ans = "" + Frames / Min;
                        output.setText(ans);
                    } else if (three == false) {
                        String ans = "" + Min * Fps;
                        output.setText(ans);
                    }
                }
            }
        });
        output = (TextView) windows.findViewById(R.id.output1);
        setupEditTexts();
        return windows;
    }

    public int totalNum() {
        int a = 0, b = 0, c = 0;
        if (one == true) {
            a = 0;
        } else {
            a = 1;
        }
        if (two == true) {
            b = 0;
        } else {
            b = 1;
        }
        if (three == true) {
            c = 0;
        } else {
            c = 1;
        }
        int total = a + b + c;
        return total;
    }

    public void setupEditTexts() {
        duration.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cur = duration.getText().toString().trim();
                if (cur.trim().equals("") || cur.trim().equals(" ")) {
                    Min = -1;
                } else if (cur.equals(".")) {
                    Min = .5;
                } else
                    Min = Double.parseDouble(cur);
                //Log.d("MI IS", "" + Min);
                one = Min != -1;
                output.setText("___________________");
                //Log.d("MIN IS", ""+Min);
                Log.d("CURR IS", cur);

            }

        });

        fps.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cur = fps.getText().toString().trim();
                if (cur.trim().equals("") || cur.trim().equals(" ")) {
                    Fps = -1;
                } else if (cur.equals(".")) {
                    Fps = .5;
                } else
                    Fps = Double.parseDouble(fps.getText().toString());
                two = Fps != -1;
                Log.d("CURR IS", cur + "HI");
                output.setText("___________________");
            }

        });

        frames.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cur = frames.getText().toString().trim();
                if (cur.trim().equals("") || cur.trim().equals(" ")) {
                    Frames = -1;
                } else if (cur.equals(".")) {
                    Frames = .5;
                } else
                    Frames = Double.parseDouble(frames.getText().toString());
                three = Frames != -1;
                Log.d("CURR IS", cur);
                output.setText("___________________");
            }

        });
    }

}
