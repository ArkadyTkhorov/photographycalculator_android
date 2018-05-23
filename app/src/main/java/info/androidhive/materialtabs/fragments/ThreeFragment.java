package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;


public class ThreeFragment extends Fragment{


    Double width=0.0, cropFactor=0.0, height=0.0;
    Double ISOFactor=0.0, recommendedISO=0.0;
    Double recommendedShutterspeed=0.0;
    String sensor_Str;
    Double focal_num=0.0, apecture_num=0.0, iso_num=0.0;

    TextView warning_tv, speed_time_tv;
    public ThreeFragment() {
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
        //get reference to the spinner from the XML layout
        View view = inflater.inflate(R.layout.fragment_three, container, false);

        warning_tv = (TextView)view.findViewById(R.id.warning_hint);
        speed_time_tv = (TextView)view.findViewById(R.id.speed_time);
        Spinner spinner = (Spinner) view.findViewById(R.id.sensor_spinner);
        //attach the listener to the spinner
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        Spinner spinner1 = (Spinner) view.findViewById(R.id.focal_spinner);
        //attach the listener to the spinner
        spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());

        Spinner spinner2 = (Spinner) view.findViewById(R.id.aperature_spinner);
        //attach the listener to the spinner
        spinner2.setOnItemSelectedListener(new MyOnItemSelectedListener());

        Spinner spinner3 = (Spinner) view.findViewById(R.id.iso_spinner);
        //attach the listener to the spinner
        spinner3.setOnItemSelectedListener(new MyOnItemSelectedListener());
        //Dynamically generate a spinner data
        createSpinnerDropDown(view);

        return view;
    }
    private void createSpinnerDropDown(View view) {

        //get reference to the spinner from the XML layout
        Spinner spinner = (Spinner) view.findViewById(R.id.sensor_spinner);

        //Array list of animals to display in the spinner
        List<String> list = new ArrayList<String>();

        list.add("Full-Frame");
        list.add("ASP-C");
        list.add("ASP-H");
        list.add("4:3");
        String string = getResources().getString(R.string.sensor_type_last);
        list.add(string);
        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, list);
        //set the view for the Drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        spinner.setAdapter(dataAdapter);
        //attach the listener to the spinner
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());



        Spinner spinner1 = (Spinner) view.findViewById(R.id.focal_spinner);

        //Array list of animals to display in the spinner
        List<String> list1 = new ArrayList<String>();
        for(int i = 8; i<=20; i++)
            list1.add(i+"mm");
        list1.add("20mm");
        list1.add("24mm");
        list1.add("28mm");
        list1.add("35mm");
        list1.add("50mm");
        list1.add("55mm");
        list1.add("70mm");
        list1.add("85mm");
        list1.add("100mm");
        list1.add("105mm");
        list1.add("135mm");
        list1.add("200mm");
        list1.add("300mm");
        list1.add("400mm");
        list1.add("500mm");
        list1.add("600mm");
        list1.add("800mm");
        list1.add("1000mm");
        list1.add("1500mm");
        list1.add("2000mm");
        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, list1);
        //set the view for the Drop down list
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        spinner1.setAdapter(dataAdapter1);
        //attach the listener to the spinner
        spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());


        Spinner spinner2 = (Spinner) view.findViewById(R.id.aperature_spinner);

        //Array list of animals to display in the spinner
        List<String> list2 = new ArrayList<String>();
        list2.add("1");
        list2.add("1.1");
        list2.add("1.2");
        list2.add("1.4");
        list2.add("1.6");
        list2.add("1.8");
        list2.add("2");
        list2.add("2.2");
        list2.add("2.5");
        list2.add("2.8");
        list2.add("3.3");
        list2.add("3.5");
        list2.add("4");
        list2.add("4.5");
        list2.add("5");
        list2.add("5.6");
        list2.add("6.3");
        list2.add("7.1");
        list2.add("8");
        list2.add("9");
        list2.add("10");
        list2.add("11");
        list2.add("13");
        list2.add("14");
        list2.add("16");
        list2.add("18");
        list2.add("20");
        list2.add("22");
        list2.add("32");
        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, list2);
        //set the view for the Drop down list
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        spinner2.setAdapter(dataAdapter2);
        //attach the listener to the spinner
        spinner2.setOnItemSelectedListener(new MyOnItemSelectedListener());


        Spinner spinner3 = (Spinner) view.findViewById(R.id.iso_spinner);
        //Array list of animals to display in the spinner
        List<String> list3 = new ArrayList<String>();
        list3.add("25");
        list3.add("50");
        list3.add("64");
        list3.add("100");
        list3.add("200");
        list3.add("400");
        list3.add("800");
        list3.add("1000");
        list3.add("1600");
        list3.add("3200");
        list3.add("6400");
        list3.add("12800");
        list3.add("25600");
        list3.add("51200");
        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, list3);
        //set the view for the Drop down list
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        spinner3.setAdapter(dataAdapter3);
        //attach the listener to the spinner
        spinner3.setOnItemSelectedListener(new MyOnItemSelectedListener());

    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            String selectedItem = parent.getItemAtPosition(pos).toString();

            //check which spinner triggered the listener
            switch (parent.getId()) {
                case R.id.sensor_spinner:

                    switch (selectedItem){
                        case "Full-Frame":
                            sensor_Str = "FX";
                            break;
                        case "APS-C":
                            sensor_Str = "CaAPSC";
                            break;
                        case "APS-H":
                            sensor_Str = "CaAPSH";
                            break;
                        case "4:3":
                            sensor_Str = "FourThirds4/3";
                            break;
                        case "1&quot;":
                            sensor_Str = "1inch";
                            break;
                    }
                    break;
                case R.id.focal_spinner:
                    switch (selectedItem){
                        case "8mm":
                            focal_num = 8.0;
                            break;
                        case "9mm":
                            focal_num = 9.0;
                            break;
                        case "10mm":
                            focal_num = 10.0;
                            break;
                        case "11mm":
                            focal_num = 11.0;
                            break;
                        case "12mm":
                            focal_num = 12.0;
                            break;
                        case "13mm":
                            focal_num = 13.0;
                            break;
                        case "14mm":
                            focal_num = 14.0;
                            break;
                        case "15mm":
                            focal_num = 15.0;
                            break;
                        case "16mm":
                            focal_num = 16.0;
                            break;
                        case "17mm":
                            focal_num = 17.0;
                            break;
                        case "18mm":
                            focal_num = 18.0;
                            break;
                        case "19mm":
                            focal_num = 19.0;
                            break;
                        case "20mm":
                            focal_num = 20.0;
                            break;
                        case "24mm":
                            focal_num = 24.0;
                            break;
                        case "28mm":
                            focal_num = 28.0;
                            break;
                        case "35mm":
                            focal_num = 35.0;
                            break;
                        case "50mm":
                            focal_num = 50.0;
                            break;
                        case "55mm":
                            focal_num = 55.0;
                            break;
                        case "70mm":
                            focal_num = 70.0;
                            break;
                        case "85mm":
                            focal_num = 85.0;
                            break;
                        case "100mm":
                            focal_num = 100.0;
                            break;
                        case "105mm":
                            focal_num = 105.0;
                            break;
                        case "135mm":
                            focal_num = 135.0;
                            break;
                        case "200mm":
                            focal_num = 200.0;
                            break;
                        case "300mm":
                            focal_num = 300.0;
                            break;
                        case "400mm":
                            focal_num = 400.0;
                            break;
                        case "500mm":
                            focal_num = 500.0;
                            break;
                        case "600mm":
                            focal_num = 600.0;
                            break;
                        case "800mm":
                            focal_num = 800.0;
                            break;
                        case "1000mm":
                            focal_num = 1000.0;
                            break;
                        case "1500mm":
                            focal_num = 1500.0;
                            break;
                        case "2000mm":
                            focal_num = 2000.0;
                            break;
                    }

                    break;
                case R.id.aperature_spinner:
                    switch (selectedItem)
                    {
                        case "1":
                            apecture_num = 1.0;
                            break;
                        case "1.1":
                            apecture_num = 1.1;
                            break;
                        case "1.2":
                            apecture_num = 1.2;
                            break;
                        case "1.4":
                            apecture_num = 1.4;
                            break;
                        case "1.6":
                            apecture_num = 1.6;
                            break;
                        case "1.8":
                            apecture_num = 1.8;
                            break;
                        case "2":
                            apecture_num = 2.0;
                            break;
                        case "2.2":
                            apecture_num = 2.2;
                            break;
                        case "2.5":
                            apecture_num = 2.5;
                            break;
                        case "2.8":
                            apecture_num = 2.8;
                            break;
                        case "3.3":
                            apecture_num = 3.3;
                            break;
                        case "3.5":
                            apecture_num = 3.5;
                            break;
                        case "4":
                            apecture_num = 4.0;
                            break;
                        case "4.5":
                            apecture_num = 4.5;
                            break;
                        case "5":
                            apecture_num = 5.0;
                            break;
                        case "5.6":
                            apecture_num = 5.6;
                            break;
                        case "6.3":
                            apecture_num = 6.3;
                            break;
                        case "7.1":
                            apecture_num = 7.1;
                            break;
                        case "8":
                            apecture_num = 8.0;
                            break;
                        case "9":
                            apecture_num = 9.0;
                            break;
                        case "10":
                            apecture_num = 10.0;
                            break;
                        case "11":
                            apecture_num = 11.0;
                            break;
                        case "13":
                            apecture_num = 13.0;
                            break;
                        case "14":
                            apecture_num = 14.0;
                            break;
                        case "16":
                            apecture_num = 16.0;
                            break;
                        case "18":
                            apecture_num = 18.0;
                            break;
                        case "20":
                            apecture_num = 20.0;
                            break;
                        case "22":
                            apecture_num = 22.0;
                            break;
                        case "32":
                            apecture_num = 32.0;
                            break;
                    }
                    break;
                case R.id.iso_spinner:
                    switch (selectedItem)
                    {
                        case "25":
                            iso_num = 25.0;
                            break;
                        case "50":
                            iso_num = 50.0;
                            break;
                        case "64":
                            iso_num = 64.0;
                            break;
                        case "100":
                            iso_num = 100.0;
                            break;
                        case "200":
                            iso_num = 200.0;
                            break;
                        case "400":
                            iso_num = 400.0;
                            break;
                        case "800":
                            iso_num = 800.0;
                            break;
                        case "1000":
                            iso_num = 1000.0;
                            break;
                        case "1600":
                            iso_num = 1600.0;
                            break;
                        case "3200":
                            iso_num = 3200.0;
                            break;
                        case "6400":
                            iso_num = 6400.0;
                            break;
                        case "12800":
                            iso_num = 12800.0;
                            break;
                        case "25600":
                            iso_num = 25600.0;
                            break;
                        case "51200":
                            iso_num = 51200.0;
                            break;
                    }
                    break;
            }
            CalcExposure();

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
    }
    public void CalcExposure()
    {
        boolean starTrails = false;
        boolean otherISO = false;
        switch (sensor_Str)
        {
            case "FX":
            case "35mm":
                width = 36.0;
                height = 24.0;
                cropFactor = 1.0;
                break;
            case "NkAPSC":
                width = 23.6;
                height = 15.8;
                cropFactor = 1.52;
                break;
            case "SoAPSC":
                width = 23.5;
                height = 15.6;
                cropFactor = 1.52;
                break;
            case "PeAPSC":
                width = 23.4;
                height = 15.6;
                cropFactor = 1.53;
                break;
            case "CaAPSC":
                width = 22.3;
                height = 14.9;
                cropFactor = 1.6;
                break;
            case "CaAPSH":
                width = 34.5;
                height = 28.7;
                cropFactor = 1.26;
                break;
            case "FourThirds4/3":
                width = 17.3;
                height = 13.0;
                cropFactor = 2.0;
                break;
            case "1inch":
                width = 13.2;
                height = 8.8;
                cropFactor = 2.7;
                break;
        }
        double maxISO = iso_num;
        double FL = focal_num;
        double aperture = apecture_num;
        recommendedShutterspeed = 500.0 / FL / cropFactor;
        if ( apecture_num <= 0.7 )

            recommendedISO = 400.0;

        else if ( apecture_num <= 1.0 )

            recommendedISO = 800.0;

        else if ( apecture_num <= 1.4 )

            recommendedISO = 1600.0;

        else if ( apecture_num <= 2.0 )

            recommendedISO = 3200.0;

        else if ( apecture_num <= 2.8 )

            recommendedISO = 6400.0;

        else if ( apecture_num <= 4.0 )

            recommendedISO = 12800.0;

        else if ( apecture_num <= 5.6 )

            recommendedISO = 25600.0;

        else

            recommendedISO = 51200.0;

        if ( recommendedShutterspeed >= 60.0 )

            ISOFactor = -1.0;

        else if ( recommendedShutterspeed >= 20.0 )

            ISOFactor = 0.0;

        else if ( recommendedShutterspeed >= 10.0 )

            ISOFactor = 1.0;

        else if ( recommendedShutterspeed >= 5.0 )

            ISOFactor = 2.0;

        else

            ISOFactor = 3.0;
        recommendedISO *= Math.pow(2, ISOFactor);
        Double Exposure = 6000.0 * (apecture_num * apecture_num) / iso_num;
        Double EV = (Math.log(apecture_num * apecture_num / Exposure) / 0.693) - (Math.log(iso_num / 100.0) / 0.693);
        while ( EV > -7.7 )
        {
            Exposure *= 1.25;
            EV = (Math.log(apecture_num * apecture_num / Exposure) / 0.693) - (Math.log(iso_num / 100.0) / 0.693);
        }
        getCameraExposure(Exposure);
        if ( Exposure > recommendedShutterspeed )
            starTrails = true;
        if ( recommendedISO > iso_num )
            otherISO = true;
        if ( Exposure > recommendedShutterspeed )
            starTrails = true;
        if ( recommendedISO > iso_num )
            otherISO = true;
        if ( starTrails == true )
        {
            if ( otherISO == true )
                warning_tv.setText("WARNING:\nThe shutter speed above will result in star trailing. Use a higher ISO or wider aperture lens. The recommended shutter speed is " + Math.round(recommendedShutterspeed) + "s or faster.For your Focal Length and Aperture, the suggested ISO value is " + recommendedISO + ".");
            else
                warning_tv.setText("WARNING:\nThe shutter speed above will result in star trailing. Use a higher ISO or wider aperture lens. The recommended shutter speed is " + Math.round(recommendedShutterspeed) + "s or faster.");
        }
        else if ( otherISO == true )
           warning_tv.setText("Notice:\nThe recommended ISO value is " + recommendedISO + ".");
        else
            warning_tv.setText("");


    }
    public void getCameraExposure(Double expo)
    {
        String exposure;

        double range = 1.0 / expo;
        if ( range > 1 )
        {
            if ( range > 8000 )
                exposure = "1/8000+";
            else if ( range > 7200 )
                exposure = "1/8000";
            else if ( range > 5700 )
                exposure = "1/6400";
            else if ( range > 4500 )
                exposure = "1/5000";
            else if ( range > 3600 )
                exposure = "1/4000";
            else if ( range > 2850 )
                exposure = "1/3200";
            else if ( range > 2250 )
                exposure = "1/2500";
            else if ( range > 1800 )
                exposure = "1/2000";
            else if ( range > 1525 )
                exposure = "1/1600";
            else if ( range > 1125 )
                exposure = "1/1250";
            else if ( range > 900 )
                exposure = "1/1000";
            else if ( range > 720 )
                exposure = "1/800";
            else if ( range > 570 )
                exposure = "1/640";
            else if ( range > 450 )
                exposure = "1/500";
            else if ( range > 360 )
                exposure = "1/400";
            else if ( range > 285 )
                exposure = "1/320";
            else if ( range > 225 )
                exposure = "1/250";
            else if ( range > 180 )
                exposure = "1/200";
            else if ( range > 142 )
                exposure = "1/160";
            else if ( range > 112 )
                exposure = "1/125";
            else if ( range > 90 )
                exposure = "1/100";
            else if ( range > 70 )
                exposure = "1/80";
            else if ( range > 55 )
                exposure = "1/60";
            else if ( range > 45 )
                exposure = "1/50";
            else if ( range > 35 )
                exposure = "1/40";
            else if ( range > 27 )
                exposure = "1/30";
            else if ( range > 22 )
                exposure = "1/25";
            else if ( range > 17 )
                exposure = "1/20";
            else if ( range > 14 )
                exposure = "1/15";
            else if ( range > 11 )
                exposure = "1/13";
            else if ( range > 9 )
                exposure = "1/10";
            else if ( range > 7 )
                exposure = "1/8";
            else if ( range > 5 )
                exposure = "1/6";
            else if ( range > 4 )
                exposure = "1/5";
            else if ( range > 3 )
                exposure = "1/4";
            else if ( range > 2.75 )
                exposure = "1/3";
            else if ( range > 2.25 )
            {
                    exposure = "1/2.5";
            }
            else if ( range > 1.8 )
                exposure = "1/2";
            else if ( range > 1.45 )
            {
                exposure = "1/1.6";
            }
            else if ( range > 1.15 )
            {
                exposure = "1/1.3";
            }
            else
                exposure = "1";	
        }
        else
            exposure = String.valueOf(Math.round(expo));

        if ( range > 1 )
            speed_time_tv.setText(exposure + " s");
        else
        {
            if ( Double.parseDouble(exposure) < 60 )
            {
                speed_time_tv.setText(String.valueOf(roundTwoDecimals(Double.parseDouble(exposure))) + "s");
            }
            else {
                speed_time_tv.setText(String.valueOf(roundTwoDecimals(Double.parseDouble(exposure) / 60)) + " min");
            }
        }
    }
    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
}
