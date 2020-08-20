package com.robin.dmpanel;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView topDmName,changePassword;
    private ImageButton editButton;

    private TextView dmName,dmPhone,dmEmail,dmNid,vehType,vehLicenseNo,serviceDistrict,serviceThana;

    private View profileView;

    private FirebaseAuth mAuth;
    private DatabaseReference rootRef;
    private String currentDmId;

    private ProgressDialog loadingBar;

    private FirebaseUser user;

    private AlertDialog.Builder dialogueBuilder;
    private AlertDialog dialogue;
    private EditText dmName_E,dm_phone_E;
    private Button Update_B,Cancel_B;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        profileView = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth=FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        rootRef= FirebaseDatabase.getInstance().getReference();
        currentDmId=user.getUid();

        InitializeAllFields();

        loadingBar=new ProgressDialog(getContext());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenEditNewDialogBox();
            }
        });



        return profileView;
    }



    @Override
    public void onStart() {
        super.onStart();

        loadingBar.setTitle("My Profile");
        loadingBar.setMessage("Please Wait Until Your Profile Loading Successfully Completed...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        rootRef.child("Drivers")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                            for(DataSnapshot model:dataSnapshot.getChildren()){

                                DM dm=model.getValue(DM.class);

                                if(dm!=null){

                                    if(dm.getDriverId().equals(currentDmId)){
                                        topDmName.setText(dm.getName());
                                        dmName.setText(dm.getName());
                                        dmPhone.setText(dm.getPhoneNumber());
                                        dmEmail.setText(user.getEmail());
                                        dmNid.setText(dm.getnId());
                                        serviceDistrict.setText(dm.getJobLocationCity());
                                        serviceThana.setText(dm.getJobLocationArea());
                                        vehType.setText(dm.getVehicleType());
                                        vehLicenseNo.setText(dm.getLicenceNumber());

                                        break;
                                    }



                                }
                            }
                            loadingBar.dismiss();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        loadingBar.dismiss();
                    }
                });
    }

    private void InitializeAllFields() {

        topDmName=profileView.findViewById(R.id.dm_name_profile_top);
        dmName=profileView.findViewById(R.id.dm_name_profile_TextView);
        dmEmail=profileView.findViewById(R.id.dm_email_profile);
        dmPhone=profileView.findViewById(R.id.dm_phone_profile);
        dmNid=profileView.findViewById(R.id.dm_nid_profile);
        vehType=profileView.findViewById(R.id.vehicle_type_profile_TextView);
        vehLicenseNo=profileView.findViewById(R.id.license_no_profile);
        serviceDistrict=profileView.findViewById(R.id.district_dm_profile_TextView);
        serviceThana=profileView.findViewById(R.id.thana_dm_profile);
        editButton=profileView.findViewById(R.id.edit_profile_dm_button);

        changePassword=profileView.findViewById(R.id.change_password_dm_profile);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasswordPopUp();
            }
        });

    }


    private void OpenEditNewDialogBox() {
        dialogueBuilder=new AlertDialog.Builder(getContext());
        final View EditProfileView=getLayoutInflater().inflate(R.layout.edit_profile_layout,null);

        dmName_E=(EditText) EditProfileView.findViewById(R.id.dm_name_profile_EditText);
        dm_phone_E=(EditText) EditProfileView.findViewById(R.id.dm_Phone_profile_EditText);

        Update_B=(Button) EditProfileView.findViewById(R.id.update_popup_dm);
        Cancel_B=(Button) EditProfileView.findViewById(R.id.cancel_popup_dm);

        dialogueBuilder.setView(EditProfileView);
        dialogue=dialogueBuilder.create();
        dialogue.show();

        Cancel_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogue.dismiss();
            }
        });

        Update_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDmProfileInfo();
            }


        });



    }

    private void UpdateDmProfileInfo() {

        String setDmName=dmName_E.getText().toString();
        String setDmPhone=dm_phone_E.getText().toString();


        DatabaseReference DmRef=rootRef.child("Drivers").child(currentDmId);

        if(!TextUtils.isEmpty(setDmName)){
            DmRef.child("name").setValue(setDmName);
        }

        if(!TextUtils.isEmpty(setDmPhone)){
            DmRef.child("phoneNumber").setValue(setDmPhone);
        }

        dialogue.dismiss();

        Toast.makeText(getContext(), "Successfully Updated Your Info", Toast.LENGTH_SHORT).show();
    }

    private void ChangePasswordPopUp() {

        dialogueBuilder =new AlertDialog.Builder(getContext());
        View ChangePasswordView =getLayoutInflater().inflate(R.layout.change_password_popup_layout,null);

        final EditText oldPass,newPass,againNewPass;
        Button changePasswordButton;

        oldPass=(EditText)ChangePasswordView.findViewById(R.id.old_pass_dm);
        newPass=(EditText)ChangePasswordView.findViewById(R.id.new_pass_dm);
        againNewPass=(EditText)ChangePasswordView.findViewById(R.id.again_old_pass_dm);
        changePasswordButton=(Button)ChangePasswordView.findViewById(R.id.change_pass_dm_button);

        dialogueBuilder.setView(ChangePasswordView);
        dialogue=dialogueBuilder.create();
        dialogue.show();

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String o_pass,n_pass,a_n_pass;

                o_pass=oldPass.getText().toString();
                n_pass=newPass.getText().toString();
                a_n_pass=againNewPass.getText().toString();

                if(o_pass.isEmpty()){
                    oldPass.setError("Enter Old Password");
                    oldPass.requestFocus();
                    return;
                }

                if(n_pass.isEmpty()){
                    newPass.setError("Enter New Password");
                    newPass.requestFocus();
                    return;
                }
                if(a_n_pass.isEmpty()){
                    againNewPass.setError("Retype Old Password");
                    againNewPass.requestFocus();
                    return;
                }
                if(!n_pass.equals(a_n_pass)){
                    Toast.makeText(getContext(), "Your new password doesn't match with the retype new password.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(n_pass.length()<6){
                    Toast.makeText(getContext(), "Your new password length should be at least 6 letters.", Toast.LENGTH_LONG).show();
                    return;

                }

                if (user!=null){
                    loadingBar.setTitle("Reset Password");
                    loadingBar.setMessage("Please Wait Until The Loading Successfully Completed...");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    user.updatePassword(n_pass).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "Successfully Updated Password", Toast.LENGTH_SHORT).show();
                            dialogue.dismiss();
                            loadingBar.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.i("Error",e.getMessage());
                            loadingBar.dismiss();
                            dialogue.dismiss();
                        }
                    });
                }
            }
        });

    }

}
