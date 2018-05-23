package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import info.androidhive.materialtabs.R;

import static android.content.ContentValues.TAG;


public class FiveFragment extends Fragment {


    EditText aperture_et, shutterspd_et, iso_et;
    Button calc_btn;
    boolean clicked;

    double[] fstops = {
            0.7,
            0.8,
            0.9,
            1.0,
            1.1,
            1.2,
            1.4,
            1.6,
            1.8,
            2,
            2.2,
            2.5,
            2.8,
            3.2,
            3.5,
            4,
            4.5,
            5.0,
            5.6,
            6.3,
            7.1,
            8,
            9,
            10,
            11,
            13,
            14,
            16,
            18,
            20,
            22,
            27,
            32,
            38,
            45,
            54,
            64,
            76,
            91,
            108};
    double[] shutterSpeeds = {
            1 / 8000.0,
            1 / 6400.0,
            1 / 5000.0,
            1 / 4000.0,
            1 / 3200.0,
            1 / 2500.0,
            1 / 2000.0,
            1 / 1600.0,
            1 / 1250.0,
            1 / 1000.0,
            1 / 800.0,
            1 / 640.0,
            1 / 500.0,
            1 / 400.0,
            1 / 320.0,
            1 / 250.0,
            1 / 200.0,
            1 / 160.0,
            1 / 125.0,
            1 / 100.0,
            1 / 80.0,
            1 / 60.0,
            1 / 50.0,
            1 / 40.0,
            1 / 30.0,
            1 / 25.0,
            1 / 20.0,
            1 / 15.0,
            1 / 13.0,
            1 / 10.0,
            1 / 8.0,
            1 / 6.0,
            1 / 5.0,
            1 / 4.0,
            0.3,
            0.4,
            0.5,
            0.6,
            0.8,
            1,
            1.3,
            1.6,
            2,
            2.5,
            3.2,
            4,
            5};
    double[] isos = {
            50,
            100,
            125,
            160,
            200,
            250,
            320,
            400,
            500,
            640,
            800,
            1000,
            1250,
            1600,
            2000,
            2500,
            3200,
            4000,
            5000,
            6400,
            12800,
            25600};

    double exposureValue = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_five, container, false);

        aperture_et = (EditText) view.findViewById(R.id.aperture_et);
        shutterspd_et = (EditText) view.findViewById(R.id.shutter_speed_et);
        iso_et = (EditText) view.findViewById(R.id.iso_et);

        aperture_et.setTag(1);
        shutterspd_et.setTag(2);
        iso_et.setTag(3);

        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    calculate((int) v.getTag());
            }
        };

        View.OnKeyListener listener1 = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER)
                    calculate((int) v.getTag());
                return false;
            }
        };
        aperture_et.setOnFocusChangeListener(listener);
        shutterspd_et.setOnFocusChangeListener(listener);
        iso_et.setOnFocusChangeListener(listener);
        aperture_et.setOnKeyListener(listener1);
        shutterspd_et.setOnKeyListener(listener1);
        iso_et.setOnKeyListener(listener1);

        calc_btn = (Button) view.findViewById(R.id.calcbtn);
        calc_btn.setText(R.string.calc);
        clicked = false;
        calculate(0);

        calc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = !clicked;
                if (clicked) {
                    calc_btn.setText(R.string.uncalc);
                    //startCalc();
                } else {
                    calc_btn.setText(R.string.calc);
                    return;
                }

            }
        });
        return view;
    }

    public void calculateEv(double n, double t, double s) {
        double ev = log2(n * n / t);
        exposureValue = ev - log2(s / 100);
        updateDisplay(n, t, s);
    }

    public void updateDisplay(double n, double t, double s) {
        t = roundToArray(t, shutterSpeeds);
        if (t < shutterSpeeds[0]) t = shutterSpeeds[0];
        if (t > shutterSpeeds[shutterSpeeds.length-1]) t = shutterSpeeds[shutterSpeeds.length-1];
        n = roundToArray(n, fstops);
        s = roundToArray(s, isos);
        if (t < 1)
            shutterspd_et.setText(decimalToFraction(t));
        else {
            double vt = t < 5 ? Math.round(t * 10) / 10 : Math.round(t);
            if (t == Double.POSITIVE_INFINITY)
                shutterspd_et.setText("Infinite");
            else
                shutterspd_et.setText(String.valueOf((vt - (int)vt == 0) ? (int)vt : vt));
        }
        aperture_et.setText(String.valueOf(n));
        iso_et.setText(String.valueOf(Math.round(s)));
    }


    public void updateApertureToFit(double n, double t, double s) {
        n = Math.sqrt(t * Math.pow(2, exposureValue + log2(s / 100)));
        updateDisplay(n, t, s);
    }

    public void  updateSpeedToFit(double n, double t, double s) {
        t = (n * n) / Math.pow(2, exposureValue + log2(s / 100));
        updateDisplay(n, t, s);
    }

    public double roundToArray(double val, double[] array) {
        if (val < array[0] || val > array[array.length - 1]) return val;
        double best = val;
        double bestDiff = Double.POSITIVE_INFINITY;
        for (int i = 0; i < array.length; i++) {
            double diff = Math.abs(array[i] - val);
            if (diff <= bestDiff) {
                best = array[i];
                bestDiff = diff;
            } else {
                return best;
            }
        }
        return best;
    }

    public double log2(double x) {
        return Math.log(x) / 0.693;
    }

    public String decimalToFraction(double value) {
        if (value < 0.3) {
            int v = (int) (1 / value);
            return String.format("1/%d", v);
        }
        return String.valueOf(value);
    }

    public double fractionToDecimal(String s) {
        double ret;
        if (s.equals("Infinite"))
            return Double.POSITIVE_INFINITY;
        if (!s.contains("/"))
            return Double.parseDouble(s);
        int index = s.indexOf("/");
        int numerator = Integer.parseInt(s.substring(0, index));
        int denominator = Integer.parseInt(s.substring(index + 1, s.length()));
        ret = numerator / (double)denominator;
        return ret;
    }

    void calculate(int position) {
        double n, t, s;
        n = Double.parseDouble(aperture_et.getText().toString());
        t = fractionToDecimal(shutterspd_et.getText().toString());
        s = Double.parseDouble(iso_et.getText().toString());

        if (!clicked)
            calculateEv(n, t, s);
        else {
            if (position == 1 || position == 3)
                updateSpeedToFit(n, t, s);
            else if (position == 2)
                updateApertureToFit(n, t, s);
        }
    }
    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
}
