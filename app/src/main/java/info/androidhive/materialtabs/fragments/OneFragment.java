package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;


public class OneFragment extends Fragment{

    double mm_2_feet = (1 / 304.8);
    double feet_2_mm = 304.8;
    EditText etflen, etflen_mult, etaspect;
    EditText ethfov, etvfov, etdfov;
    Button calbtn;

    Double selected;
    public String meters(double aNumber)
    {
        if(Double.isNaN(aNumber))
        {
            return aNumber + "";
        }
        double mm = aNumber * feet_2_mm;
        double m = Math.floor(mm/1000);
        double cm = dec2((mm/10) % 100);
        return m + "m" + cm + "cm";
    }

    public String feet_inches(double aNumber)
    {
        if(Double.isNaN(aNumber))
        {
            return aNumber + "";
        }
        double feet = Math.floor(aNumber);
        double inches = dec1((aNumber%1)*12);
        return (feet + "' " + inches + "\"");
    }

    public double dec0(double aNumber) {return (rnd(aNumber,0));}
    public double dec1(double aNumber) {return (rnd(aNumber,1));}
    public double dec2(double aNumber) {return (rnd(aNumber,2));}
    public double dec3(double aNumber) {return (rnd(aNumber,3));}
    public double dec4(double aNumber) {return (rnd(aNumber,4));}
    public double dec5(double aNumber) {return (rnd(aNumber,5));}
    public double dec6(double aNumber) {return (rnd(aNumber,6));}

    public double rnd(double aNumber,double decimal_places)
    {
        if (Double.isNaN(aNumber))
        { return aNumber; }

        double mult = Math.pow(10,decimal_places);

        return (Math.round(aNumber*mult))/mult;
    }

    public OneFragment() {
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
        View view = inflater.inflate(R.layout.fragment_one, container, false);


        etflen = (EditText)view.findViewById(R.id.focallength);
        etflen_mult = (EditText)view.findViewById(R.id.sensorcropeditor);

        ethfov = (EditText)view.findViewById(R.id.horizontalfov);
        etvfov = (EditText)view.findViewById(R.id.verticalfov);
        etdfov = (EditText)view.findViewById(R.id.diagonalfov);

        calbtn = (Button)view.findViewById(R.id.calculate_btn);

        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute_fov();
            }
        });

        Spinner etaspect = (Spinner) view.findViewById(R.id.sensor_ratio_spinner);
        //attach the listener to the spinner
        etaspect.setOnItemSelectedListener(new MyOnItemSelectedListener());
        createSpinnerDropDown(view);
        return view;
    }
    private void createSpinnerDropDown(View view) {

        //get reference to the spinner from the XML layout
        Spinner spinner = (Spinner) view.findViewById(R.id.sensor_ratio_spinner);

        //Array list of animals to display in the spinner
        List<String> list = new ArrayList<String>();

        list.add("3:2 (Most DSLRs)");
        list.add("4:3 (Some Camera Phones)");
        list.add("16:9 (Some Camera Phones)");

        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, list);
        //set the view for the Drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        spinner.setAdapter(dataAdapter);
        //attach the listener to the spinner
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            String selectedItem = parent.getItemAtPosition(pos).toString();

            //check which spinner triggered the listener
            switch (parent.getId()) {
                //country spinner
                case R.id.sensor_ratio_spinner:
                    //make sure the country was already selected during the onCreate
//                    if(selectedCountry != null){
//                        Toast.makeText(parent.getContext(), "Country you selected is " + selectedItem,
//                                Toast.LENGTH_LONG).show();
//                    }
//                    selectedCountry = selectedItem;
                    if(selectedItem == "3:2 (Most DSLRs)")
                        selected = 1.5;
                    else if(selectedItem == "4:3 (Some Camera Phones)")
                        selected = 1.3333333334;
                    else if(selectedItem == "16:9 (Some Camera Phones)")
                        selected = 1.781;
                    break;

            }


        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }


    public void compute_fov()
    {
        double film_width_35 = 36;
        double film_height_35 = 24;
        double film_diag_35 = (Math.sqrt((film_width_35 * film_width_35) + (film_height_35 * film_height_35)));

        //double f = document.fov_calculator;
        double flen = Double.parseDouble(etflen.getText().toString());
        double flen_mult = Double.parseDouble(etflen_mult.getText().toString());
        double aspect = selected;

        film_height_35 = Math.sqrt( (film_diag_35*film_diag_35)/((aspect*aspect) + 1) );
        film_width_35 = aspect * film_height_35;

        if (Double.isNaN(flen_mult) || flen_mult<=0)
        {
            flen_mult = 1;
        }

        //Account for focal length multiplier (actually, a film/sensor size multiplier)
        double sensor_width = film_width_35/flen_mult;
        double sensor_height = film_height_35/flen_mult;
        double sensor_diag = (Math.sqrt((sensor_width * sensor_width) + (sensor_height * sensor_height)));

        double fov_h = (2 * Math.atan(sensor_width / (2 * flen)) * 180 / Math.PI);
        double fov_v = (2 * Math.atan(sensor_height / (2 * flen)) * 180 / Math.PI);
        double fov_d = (2 * Math.atan(sensor_diag / (2 * flen)) * 180 / Math.PI);

        ethfov.setText(Double.toString(dec1(fov_h)));
        etvfov.setText(Double.toString(dec1(fov_v)));
        etdfov.setText(Double.toString(dec1(fov_d)));
    }
}
