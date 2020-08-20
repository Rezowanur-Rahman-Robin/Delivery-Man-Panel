package com.robin.dmpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DmRegisterActivity extends AppCompatActivity {

   private EditText dmName,dmPhone,dmEmail,dmNId,dmLicece,dmAddress_details;
   private RadioGroup radioGroupVeh_type;
   private RadioButton radioButtonVeh_type;
   private Button apply;

   private ProgressDialog loadingBar;

   private FirebaseAuth mAuth;

   private DatabaseReference rootRef;

   private Spinner district_Spinner;
   private Spinner thana_Spinner;
   private Spinner service_district_Spinner;
   private Spinner service_thana_Spinner;

   private ArrayAdapter<District> districtArrayAdapter;
   private ArrayAdapter<Thana> thanaArrayAdapter;
   private ArrayAdapter<District> service_districtArrayAdapter;
   private ArrayAdapter<Thana> service_thanaArrayAdapter;

   private ArrayList<District> Districts;
   private ArrayList<Thana> Thanas;
   private ArrayList<District> Service_Districts;
   private ArrayList<Thana> Service_Thanas;

    private boolean check = false;




   private String selecteddistrict="",selectedThana="",selected_service_district="",selected_service_thana="";
   private String Name,Phone,EMail,NID,Lisence,AddressDetails,VehType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm_register);

        mAuth=FirebaseAuth.getInstance();

        rootRef= FirebaseDatabase.getInstance().getReference();

        loadingBar = new ProgressDialog(this);

        InitializeAllFields();

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDataInFirebase();
            }
        });


    }



    private void InitializeAllFields() {

        dmName=findViewById(R.id.dm_name_registration);
        dmPhone=findViewById(R.id.dm_phone_registration);
        dmEmail=findViewById(R.id.dm_email_registration);
        dmNId=findViewById(R.id.dm_nid_registration);
        dmLicece=findViewById(R.id.dm_licence_registration);
        dmAddress_details=findViewById(R.id.address_Details_add_dm);
        radioGroupVeh_type=findViewById(R.id.vehicle_type_Group);
        district_Spinner=findViewById(R.id.districts_add_dm);
        thana_Spinner=findViewById(R.id.thana_add_dm);
        service_district_Spinner=findViewById(R.id.service_location_districts_add_dm);
        service_thana_Spinner=findViewById(R.id.service_location_thana_add_dm);
        apply=findViewById(R.id.apply_dm);

        Districts=new ArrayList<>();
        Thanas=new ArrayList<>();
        Service_Districts=new ArrayList<>();
        Service_Thanas=new ArrayList<>();

        createLists();

        districtArrayAdapter=new ArrayAdapter<District>(getApplicationContext(),R.layout.simple_spinner_layout,Districts);
        districtArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_layout);
        district_Spinner.setAdapter(districtArrayAdapter);

        thanaArrayAdapter =new ArrayAdapter<Thana>(getApplicationContext(),R.layout.simple_spinner_layout,Thanas);
        thanaArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_layout);
        thana_Spinner.setAdapter(thanaArrayAdapter);


        service_districtArrayAdapter=new ArrayAdapter<District>(getApplicationContext(),R.layout.simple_spinner_layout,Districts);
        service_districtArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_layout);
        service_district_Spinner.setAdapter(service_districtArrayAdapter);

        service_thanaArrayAdapter =new ArrayAdapter<Thana>(getApplicationContext(),R.layout.simple_spinner_layout,Thanas);
        service_thanaArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_layout);
        service_thana_Spinner.setAdapter(service_thanaArrayAdapter);

        district_Spinner.setOnItemSelectedListener(district_listener);
        thana_Spinner.setOnItemSelectedListener(thana_listener);
        service_district_Spinner.setOnItemSelectedListener(service_district_listener);
        service_thana_Spinner.setOnItemSelectedListener(service_thana_listener);





    }

    private AdapterView.OnItemSelectedListener district_listener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(position>0){
                final District district=(District) district_Spinner.getItemAtPosition(position);
                selecteddistrict=district.getDistrictName();

                ArrayList<Thana> tempThanas=new ArrayList<>();

                tempThanas.add(new Thana(0,new District(0,"Choose a District"),"Choose a Thana"));

                for( Thana singleThana : Thanas){
                    if(singleThana.getDistrict().getDistrictId()==district.getDistrictId()){
                        tempThanas.add(singleThana);
                    }
                }

                thanaArrayAdapter =new ArrayAdapter<Thana>(getApplicationContext(),R.layout.simple_spinner_layout,tempThanas);
                thanaArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_layout);
                thana_Spinner.setAdapter(thanaArrayAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    private AdapterView.OnItemSelectedListener thana_listener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position>0){
                final Thana thana=(Thana) thana_Spinner.getItemAtPosition(position);
                selectedThana=thana.getThanaName();

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };



    private AdapterView.OnItemSelectedListener service_district_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position>0){
                final District district=(District) service_district_Spinner.getItemAtPosition(position);
                selected_service_district=district.getDistrictName();
                ArrayList<Thana> tempThanas=new ArrayList<>();

                tempThanas.add(new Thana(0,new District(0,"Choose a District"),"Choose a Thana"));

                for(Thana singlethana: Thanas){
                    if(singlethana.getDistrict().getDistrictId()==district.getDistrictId()){
                        tempThanas.add(singlethana);

                    }
                }

                service_thanaArrayAdapter=new ArrayAdapter<Thana>(getApplicationContext(),R.layout.simple_spinner_layout,tempThanas);
                service_thanaArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_layout);
                service_thana_Spinner.setAdapter(service_thanaArrayAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener service_thana_listener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position>0){
                final Thana thana=(Thana)service_thana_Spinner.getItemAtPosition(position);
                selected_service_thana=thana.getThanaName();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };






    private void createLists() {

        District district0=new District(0,"Choose a District");
        District district1=new District(1,"Dhaka");
        District district2=new District(2,"Chittagong");


        Districts.add(district0);
        Districts.add(district1);
        Districts.add(district2);

        Thana thana0=new Thana(0,district0,"Choose a Thana");
        Thana thana1=new Thana(1,district1,"Mirpur");
        Thana thana2=new Thana(2,district1,"Gulshan");
        Thana thana3=new Thana(3,district2,"Agrabad");
        Thana thana4=new Thana(4,district2,"Pahartoli");

        Thanas.add(thana0);
        Thanas.add(thana1);
        Thanas.add(thana2);
        Thanas.add(thana3);
        Thanas.add(thana4);


    }

    private class District implements Comparable<District>{

        private int districtId;
        private String districtName;

        public District(int districtId, String districtName) {
            this.districtId = districtId;
            this.districtName = districtName;
        }

        public int getDistrictId() {
            return districtId;
        }

        public String getDistrictName() {
            return districtName;
        }

        @Override
        public String toString() {
            return districtName;
        }


        @Override
        public int compareTo(District o) {
            return this.getDistrictId()-o.getDistrictId();
        }
    }

    private  class Thana implements Comparable<Thana>{

        private int thanaId;
        private District district;
        private String thanaName;

        public Thana(int thanaId, District district, String thanaName) {
            this.thanaId = thanaId;
            this.district = district;
            this.thanaName = thanaName;
        }

        public int getThanaId() {
            return thanaId;
        }

        public District getDistrict() {
            return district;
        }

        public String getThanaName() {
            return thanaName;
        }

        @Override
        public String toString() {
            return thanaName;
        }


        @Override
        public int compareTo(Thana o) {
            return this.getThanaId()-o.getThanaId();
        }
    }

    private void SaveDataInFirebase() {

        int radioid =radioGroupVeh_type.getCheckedRadioButtonId();
        radioButtonVeh_type=findViewById(radioid);
        if(radioButtonVeh_type==null){
            Toast.makeText(this, "Please Check Vehicle Type.", Toast.LENGTH_SHORT).show();
            return;
        }
        VehType=radioButtonVeh_type.getText().toString();

        Name=dmName.getText().toString();
        Phone=dmPhone.getText().toString();
        EMail=dmEmail.getText().toString();
        NID=dmNId.getText().toString();
        AddressDetails=dmAddress_details.getText().toString();
        Lisence=dmLicece.getText().toString();

        if(Name.isEmpty()){
            dmName.setError("Enter Your Name");
            dmName.requestFocus();
            return;
        }
        if(Phone.isEmpty()){
            dmPhone.setError("Enter Your Phone");
            dmPhone.requestFocus();
            return;
        }
        if(EMail.isEmpty()){
            dmEmail.setError("Enter Your Email");
            dmEmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(EMail).matches())
        {
            Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
            dmEmail.requestFocus();
            return;
        }
        if(NID.isEmpty()){
            dmNId.setError("Enter Your NID Number");
            dmNId.requestFocus();
            return;
        }
        if(AddressDetails.isEmpty()){
            dmAddress_details.setError("Enter Your Details Address");
            dmAddress_details.requestFocus();
            return;
        }

        if(Lisence.isEmpty()){
            dmLicece.setError("Enter Your Vehicle License Number");
            dmLicece.requestFocus();
            return;
        }
        if(selecteddistrict.isEmpty()){
            Toast.makeText(this, "Select Your District", Toast.LENGTH_SHORT).show();
            return;
        }
        if(selectedThana.isEmpty()){
            Toast.makeText(this, "Select Your Thana", Toast.LENGTH_SHORT).show();
            return;
        }
        if(selected_service_district.isEmpty()){
            Toast.makeText(this, "Select Your Service District", Toast.LENGTH_SHORT).show();
            return;
        }
        if(selected_service_thana==null&&selected_service_thana.isEmpty()){
            Toast.makeText(this, "Select Your Service Thana", Toast.LENGTH_SHORT).show();
            return;
        }


        loadingBar.setTitle("Apply For Delivery Man");
        loadingBar.setMessage("Please Wait Until the Process is Successfully Completed...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();



        rootRef.child("DM Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    for(DataSnapshot model:dataSnapshot.getChildren()){
                        DmRequest dmRequest=model.getValue(DmRequest.class);

                        if(dmRequest!=null){


                            if(dmRequest.getEmail().equals(EMail)){

                                check =true;
                                break;
                            }
                        }

                    }

                    if(check){


                        loadingBar.dismiss();
                        Intent loginIntent=new Intent(getApplicationContext(),DmLoginActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(loginIntent);

                    }
                    else{

                        DatabaseReference dmRef=rootRef.child("DM Requests");
                        String uniqueKey=dmRef.push().getKey();
                        DmRequest dmRequest=new DmRequest(Name,Phone,EMail,NID,VehType,Lisence,selecteddistrict,AddressDetails,selectedThana,selected_service_district,selected_service_thana,"Pending",uniqueKey);

                        dmRef.child(uniqueKey).setValue(dmRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    loadingBar.dismiss();

                                    Toast.makeText(DmRegisterActivity.this, "You have successfully applied for delivery man.", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(DmRegisterActivity.this, "We will reply you on Mail.", Toast.LENGTH_SHORT).show();

                                    Intent loginIntent=new Intent(getApplicationContext(),DmLoginActivity.class);
                                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(loginIntent);
                                }
                                else{
                                    Toast.makeText(DmRegisterActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    loadingBar.dismiss();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(DmRegisterActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        });
                    }

                }
                else{
                    DatabaseReference dmRef=rootRef.child("DM Requests");
                    String uniqueKey=dmRef.push().getKey();
                    DmRequest dmRequest=new DmRequest(Name,Phone,EMail,NID,VehType,Lisence,selecteddistrict,AddressDetails,selectedThana,selected_service_district,selected_service_thana,"Pending",uniqueKey);

                    dmRef.child(uniqueKey).setValue(dmRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                loadingBar.dismiss();

                                Toast.makeText(DmRegisterActivity.this, "You have successfully applied for delivery man.", Toast.LENGTH_SHORT).show();
                                Toast.makeText(DmRegisterActivity.this, "We will reply you on Mail.", Toast.LENGTH_SHORT).show();

                                Intent loginIntent=new Intent(getApplicationContext(),DmLoginActivity.class);
                                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(loginIntent);
                            }
                            else{
                                Toast.makeText(DmRegisterActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(DmRegisterActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    });
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                loadingBar.dismiss();
            }
        });




    }




}
