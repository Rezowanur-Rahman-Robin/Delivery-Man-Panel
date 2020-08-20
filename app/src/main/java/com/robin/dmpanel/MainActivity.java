package com.robin.dmpanel;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{



    private AppBarConfiguration mAppBarConfiguration;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String currentUserId;
    private DatabaseReference rootRef;

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Delivery Man Panel");


        mAuth=FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        if(currentUser!=null){
            currentUserId=currentUser.getUid();
        }
        rootRef= FirebaseDatabase.getInstance().getReference();


        drawer=findViewById(R.id.drawer_layout);

        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.getHeaderView(0);

        name=headerView.findViewById(R.id.name_header_nav);



        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser==null){

            SendMerchantToLogin();
        }
        else {
            currentUserId=currentUser.getUid();

            rootRef.child("Drivers").child(currentUserId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        DM dm=dataSnapshot.getValue(DM.class);

                        name.setText(dm.getName());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }






    }

    private void SendMerchantToLogin() {
        Intent loginIntent = new Intent(getApplicationContext(),DmLoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;

            case R.id.nav_pickUp:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PickUpFragment()).commit();
                break;

            case R.id.nav_delivery:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DeliveryFragment()).commit();
                break;

            case R.id.nav_logout:
                getDmlogout();
                break;



        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void getDmlogout() {
        mAuth.signOut();
        SendMerchantToLogin();
    }


    @Override
    public void onBackPressed() {


        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{

            if (checkNavigationMenuItem() != 0)
            {
                navigationView.setCheckedItem(R.id.nav_home);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            }
            else
                super.onBackPressed();
        }





    }

    private int checkNavigationMenuItem() {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).isChecked())
                return i;
        }
        return -1;
    }


}
