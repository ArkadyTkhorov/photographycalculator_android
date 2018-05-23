package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import info.androidhive.materialtabs.R;


public class CODBCalculator extends Fragment {
	
	EditText etxtOffice,etxtPhone,etxtPhoto,etxtEquipment,etxtComputer,etxtBroad,etxtWebhostin,etxtVehicle;
	EditText etxtOfficeSupplies,etxtPostage,etxtProfessional,etxtAdvertising,etxtSubscriptions,etxtEquipmentBusiness;
	EditText etxtHealth,etxtLegal,etxtTaxes,etxtOfficeAssistance,etxtUtilities,etxtTravel,etxtDesired,etxtNonassignment,etxtNumberofdays;
	TextView txtTotalAnnualExpenses,txtWeeklyCost,txtOverheadCost;
	Button defualtButton,calculateButton;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.activity_codb_calculator, container, false);

		etxtOffice=(EditText) v.findViewById(R.id.etxtOffice);
		etxtPhone=(EditText) v.findViewById(R.id.etxtPhone);
		etxtPhoto=(EditText) v.findViewById(R.id.etxtPhoto);
		etxtEquipment=(EditText) v.findViewById(R.id.etxtEquipment);
		etxtComputer=(EditText) v.findViewById(R.id.etxtComputer);
		etxtBroad=(EditText) v.findViewById(R.id.etxtBroad);
		etxtWebhostin=(EditText) v.findViewById(R.id.etxtWebhostin);
		etxtVehicle=(EditText) v.findViewById(R.id.etxtVehicle);
		etxtOfficeSupplies=(EditText) v.findViewById(R.id.etxtOfficeSupplies);
		etxtPostage=(EditText) v.findViewById(R.id.etxtPostage);
		etxtProfessional=(EditText) v.findViewById(R.id.etxtProfessional);
		etxtAdvertising=(EditText) v.findViewById(R.id.etxtAdvertising);
		etxtSubscriptions=(EditText) v.findViewById(R.id.etxtSubscriptions);
		etxtEquipmentBusiness=(EditText) v.findViewById(R.id.etxtEquipmentBusiness1);
		etxtHealth=(EditText) v.findViewById(R.id.etxtHealth);
		etxtLegal=(EditText) v.findViewById(R.id.etxtLegal);
		etxtTaxes=(EditText) v.findViewById(R.id.etxtTaxes);
		etxtOfficeAssistance=(EditText) v.findViewById(R.id.etxtOfficeAssistance);
		etxtUtilities=(EditText) v.findViewById(R.id.etxtUtilities);
		etxtTravel=(EditText) v.findViewById(R.id.etxtTravel);
		etxtDesired=(EditText)v.findViewById(R.id.etxtDesired);
		etxtNonassignment=(EditText)v.findViewById(R.id.etxtNonassignment);
		etxtNumberofdays=(EditText) v.findViewById(R.id.etxtNumberofdays);

		txtTotalAnnualExpenses=(TextView)v.findViewById(R.id.txtTotalAnnualExpenses);
		txtWeeklyCost=(TextView)v.findViewById(R.id.txtWeeklyCost);
		txtOverheadCost=(TextView)v.findViewById(R.id.txtOverheadCost);

		defualtButton = (Button) v.findViewById(R.id.defaultValueButton);
		calculateButton = (Button) v.findViewById(R.id.calculateButton);


		setDefaultValues();

		defualtButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				setDefaultValues();
			}
		});

		calculateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				cal();
			}
		});

		return v;

	}

	

	private void cal()
	{
		float total= Float.parseFloat(etxtOffice.getText().toString());
		total=total+ Float.parseFloat(etxtPhone.getText().toString());
		total=total+ Float.parseFloat(etxtPhoto.getText().toString());
		total=total+ Float.parseFloat(etxtEquipment.getText().toString());
		total=total+ Float.parseFloat(etxtComputer.getText().toString());
		
		total=total+ Float.parseFloat(etxtBroad.getText().toString());
		total=total+ Float.parseFloat(etxtWebhostin.getText().toString());
		total=total+ Float.parseFloat(etxtVehicle.getText().toString());
		total=total+ Float.parseFloat(etxtOfficeSupplies.getText().toString());
		total=total+ Float.parseFloat(etxtPostage.getText().toString());
		
		total=total+ Float.parseFloat(etxtProfessional.getText().toString());
		total=total+ Float.parseFloat(etxtAdvertising.getText().toString());
		total=total+ Float.parseFloat(etxtSubscriptions.getText().toString());
		total=total+ Float.parseFloat(etxtEquipmentBusiness.getText().toString());
		total=total+ Float.parseFloat(etxtHealth.getText().toString());
		
		total=total+ Float.parseFloat(etxtLegal.getText().toString());
		total=total+ Float.parseFloat(etxtTaxes.getText().toString());
		total=total+ Float.parseFloat(etxtOfficeAssistance.getText().toString());
		total=total+ Float.parseFloat(etxtUtilities.getText().toString());
		total=total+ Float.parseFloat(etxtTravel.getText().toString());
		
		total=total+ Float.parseFloat(etxtDesired.getText().toString());
		total=total- Float.parseFloat(etxtNonassignment.getText().toString());
		
		float weaklycost=total/50;
		int noofDays= Integer.parseInt(etxtNumberofdays.getText().toString());
		txtTotalAnnualExpenses.setText("$ "+ String.format("%.2f", total));
		txtWeeklyCost.setText("$ "+ String.format("%.2f", weaklycost));
		txtOverheadCost.setText("$ "+ String.format("%.2f", total / noofDays));
		
		
		
	}
	private void setDefaultValues()
	{
		etxtOffice.setText("6000");
		etxtPhone.setText("2400");
		etxtPhoto.setText("8000");
		etxtEquipment.setText("500");
		etxtComputer.setText("500");
		
		etxtBroad.setText("1200");
		etxtWebhostin.setText("200");
		etxtVehicle.setText("5000");
		etxtOfficeSupplies.setText("1500");
		etxtPostage.setText("300");
		
		etxtProfessional.setText("500");
		etxtAdvertising.setText("2500");
		etxtSubscriptions.setText("300");
		etxtEquipmentBusiness.setText("1200");
		etxtHealth.setText("3600");
		
		etxtLegal.setText("300");
		etxtTaxes.setText("5000");
		etxtOfficeAssistance.setText("1000");
		etxtUtilities.setText("1800");
		etxtTravel.setText("1500");
		
		etxtDesired.setText("20000");
		etxtNonassignment.setText("0");
		etxtNumberofdays.setText("40");
		
		txtTotalAnnualExpenses.setText("$ "+"63300.00");
		txtWeeklyCost.setText("$ "+"1217.30");
		txtOverheadCost.setText("$ "+"1582.50");

	}



}

