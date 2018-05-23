package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;

import static android.content.ContentValues.TAG;
import static java.lang.Double.isNaN;


public class FourFragment extends Fragment {

    double mm_2_feet = (1 / 304.8);
    double feet_2_mm = 304.8;
    EditText dist_et, flen_et, aperature_et, coc_et;
    EditText near_dist_et, far_dist_et, depth_of_field_et, depth_of_focus_et;

    String d_near_dist = "", d_far_dist = "", d_depth_of_field = "", d_depth_of_focus = "";
    Button calbtn;

    int selected = 0;

    public FourFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_four, container, false);

        dist_et = (EditText) view.findViewById(R.id.dist_et);
        flen_et = (EditText) view.findViewById(R.id.flen_et);
        aperature_et = (EditText) view.findViewById(R.id.aperature_et);
        coc_et = (EditText) view.findViewById(R.id.coc_et);

        near_dist_et = (EditText) view.findViewById(R.id.near_dist_et);
        far_dist_et = (EditText) view.findViewById(R.id.far_dist_et);
        depth_of_field_et = (EditText) view.findViewById(R.id.depth_of_field_et);
        depth_of_focus_et = (EditText) view.findViewById(R.id.depth_of_focus_et);

        calbtn = (Button) view.findViewById(R.id.four_calcbtn);
        Spinner spinner = (Spinner) view.findViewById(R.id.fm_spinner);
        spinner.setOnItemSelectedListener(new FourFragment.MyOnItemSelectedListener());
        createSpinnerDropDown(view);

        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        return view;
    }

    private void createSpinnerDropDown(View view) {

        Spinner spinner = (Spinner) view.findViewById(R.id.fm_spinner);
        List<String> list = new ArrayList<String>();
        list.add("Feet");
        list.add("Meters");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            String selectedItem = parent.getItemAtPosition(pos).toString();
            switch (parent.getId()) {

                case R.id.fm_spinner:
                    if (selectedItem == "Feet") {
                        selected = 0;

                    } else {
                        selected = 1;
                    }
                    break;
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }

    public void calculate() {
        double dist, coc_mm, coc, flen_mm, flen, aperture;
        if (selected == 0) {
            try {
                dist = Double.parseDouble(dist_et.getText().toString());
                coc_mm = Double.parseDouble(coc_et.getText().toString());
                coc = coc_mm * mm_2_feet;
                flen_mm = Double.parseDouble(flen_et.getText().toString());
                flen = flen_mm * mm_2_feet;
                aperture = Double.parseDouble(aperature_et.getText().toString());
            } catch (NumberFormatException e) {
                dist = 0;
                coc_mm = 0;
                coc = coc_mm * mm_2_feet;
                flen_mm = 0;
                flen = flen_mm * mm_2_feet;
                aperture = 0;
            }
            if (isNaN(flen_mm) || isNaN(coc_mm) || isNaN(dist) || isNaN(aperture)) {
                Toast.makeText(getContext(), "Error: At least one of the input fields contains non-numeric characters", Toast.LENGTH_LONG).show();
                return;
            }
            double tmp = (aperture * coc * (dist - flen)) / (flen * flen);
            String dist_far = (tmp >= 1) ? "inf" : String.valueOf(dist / (1 - tmp));
            double dist_near = dist / (1 + tmp);
            String depth_of_field = (tmp < 1) ? String.valueOf(Double.parseDouble(dist_far) - dist_near) : "inf";
            double hyper_focal = (flen * flen) / (aperture * coc);

            double depth_of_focus = (flen * flen * dist) / (hyper_focal * (dist - flen));
            d_near_dist = feet_inches(dist_near);
            if (depth_of_field == "inf")
                d_far_dist = "inf";
            else
                d_far_dist = feet_inches(Double.parseDouble(dist_far));
            if (depth_of_field == "inf")
                d_depth_of_field = "inf";
            else
                d_depth_of_field = feet_inches(Double.parseDouble(depth_of_field));
            d_depth_of_focus = dec4(depth_of_focus * 12) + "\"";
        } else {
            try {
                dist = Double.parseDouble(dist_et.getText().toString());
                dist = dist * 1000 * mm_2_feet;
                coc_mm = Double.parseDouble(coc_et.getText().toString());
                coc = coc_mm * mm_2_feet;
                flen_mm = Double.parseDouble(flen_et.getText().toString());
                flen = flen_mm * mm_2_feet;
                aperture = Double.parseDouble(aperature_et.getText().toString());
            } catch (NumberFormatException e) {
                dist = 0;
                coc_mm = 0;
                coc = coc_mm * mm_2_feet;
                flen_mm = 0;
                flen = flen_mm * mm_2_feet;
                aperture = 0;
            }
            if (isNaN(flen_mm) || isNaN(coc_mm) || isNaN(dist) || isNaN(aperture)) {
                Toast.makeText(getContext(), "Error: At least one of the input fields contains non-numeric characters", Toast.LENGTH_LONG).show();
                return;
            }
            double tmp = (aperture * coc * (dist - flen)) / (flen * flen);
            String dist_far = (tmp >= 1) ? "inf" : String.valueOf(dist / (1 - tmp));
            double dist_near = dist / (1 + tmp);
            String depth_of_field = (tmp < 1) ? String.valueOf(Double.parseDouble(dist_far) - dist_near) : "inf";
            double hyper_focal = (flen * flen) / (aperture * coc);

            double depth_of_focus = (flen * flen * dist) / (hyper_focal * (dist - flen));
            d_near_dist = meters(dist_near);
            if (depth_of_field == "inf")
                d_far_dist = "inf";
            else
                d_far_dist = meters(Double.parseDouble(dist_far));
            if (depth_of_field == "inf")
                d_depth_of_field = "inf";
            else
                d_depth_of_field = meters(Double.parseDouble(depth_of_field));
            d_depth_of_focus = dec4((feet_2_mm * depth_of_focus)/10) + "cm";
        }
        near_dist_et.setText(d_near_dist);
        far_dist_et.setText(d_far_dist);
        depth_of_field_et.setText(d_depth_of_field);
        depth_of_focus_et.setText(d_depth_of_focus);
        Log.d(TAG, "calculate: sdfsdfsdfsdf");

    }

    public String meters(double aNumber) {
        if (isNaN(aNumber)) {
            return aNumber + "";
        }
        double mm = aNumber * feet_2_mm;
        double m = Math.floor(mm / 1000);
        double cm = dec2((mm / 10) % 100);
        return (int)m + "m" + " " + cm + "cm";
    }

    public String feet_inches(double aNumber) {
        if (isNaN(aNumber)) {
            return aNumber + "";
        }
        double feet = Math.floor(aNumber);
        double inches = dec1((aNumber % 1) * 12);
        return ((int)feet + "' " + (int)inches + "\"");
    }

    public double dec0(double aNumber) {
        return (rnd(aNumber, 0));
    }

    public double dec1(double aNumber) {
        return (rnd(aNumber, 1));
    }

    public double dec2(double aNumber) {
        return (rnd(aNumber, 2));
    }

    public double dec3(double aNumber) {
        return (rnd(aNumber, 3));
    }

    public double dec4(double aNumber) {
        return (rnd(aNumber, 4));
    }

    public double dec5(double aNumber) {
        return (rnd(aNumber, 5));
    }

    public double dec6(double aNumber) {
        return (rnd(aNumber, 6));
    }

    public double rnd(double aNumber, double decimal_places) {
        if (isNaN(aNumber)) {
            return aNumber;
        }

        double mult = Math.pow(10, decimal_places);

        return (Math.round(aNumber * mult)) / mult;
    }

    public void compute_dof() {


    }
}
