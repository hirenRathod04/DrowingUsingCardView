package com.royalsoftsolutions.drowingusingcardview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.BitSet;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /*    private CheckBox checkBox;*/
    private EditText editTextFirstname, editTextLastname, editTextMiddlename, editTextMobilenumber, editTextEmail, editTextPassword, editTextConfirmPassword, editTextDateofbirth;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxCondition;
    private Button buttonCreateaccount, buttonLogin;
    private TextInputEditText eText;

    private Calendar calendar;
    private DatePickerDialog datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        editTextFirstname = findViewById ( R.id.editTextfirstname );
        editTextMiddlename = findViewById ( R.id.editTextmiddlename );
        editTextLastname = findViewById ( R.id.editTextlastname );
        editTextEmail = findViewById ( R.id.editTextemail );
        editTextMobilenumber = findViewById ( R.id.editTextmoblienumber );
        editTextPassword = findViewById ( R.id.editTextpassword );
        editTextConfirmPassword = findViewById ( R.id.editTextconfirmpasword );
        editTextDateofbirth = findViewById ( R.id.editTextdateofbirrth );


        radioButtonMale = findViewById ( R.id.radioButtonmale );
        radioButtonFemale = findViewById ( R.id.radioButtonfemale );
        checkBoxCondition = findViewById ( R.id.checkBoxcondition );
        buttonCreateaccount = findViewById ( R.id.buttoncreateaccount );
        buttonCreateaccount.setEnabled ( false );
        editTextDateofbirth.setInputType ( InputType.TYPE_NULL );

        checkBoxCondition.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                checkCheckbox ( );

            }
        } );


    }

/*
    private void init() {


        //gh
    }*/
    /* private void addListener() {
     *//* editTextFirstname.setOnClickListener ( this );
        editTextMiddlename.setOnClickListener ( this );
        editTextLastname.setOnClickListener ( this );
        editTextEmail.setOnClickListener ( this );
        editTextMobilenumber.setOnClickListener ( this );
        editTextPassword.setOnClickListener ( this );
        editTextConfirmPassword.setOnClickListener ( this );
        editTextDateofbirth.setOnClickListener ( this );
        radioButtonMale.setOnClickListener ( this );
        radioButtonFemale.setOnClickListener ( this );
        checkBoxCondition.setOnClickListener ( this );*//*

        buttonCreateaccount.setOnClickListener ( this );
    }
*/

    private void checkCheckbox() {
        if ( checkBoxCondition.isChecked ( ) ) {
            buttonCreateaccount.setEnabled ( true );
            buttonCreateaccount.setBackgroundResource ( R.drawable.edit_style_corner_blue );
            gotoValidationfunction ( );
        } else {
            buttonCreateaccount.setEnabled ( false );
            buttonCreateaccount.setBackgroundResource ( R.drawable.edit_style_corner );

        }

    }

    private void gotoNextactivity() {
        Intent intent = new Intent ( this, CardActivity2.class );
        startActivity ( intent );
    }

    private void gotoValidationfunction() {
        buttonCreateaccount.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {


                String Fname = editTextFirstname.getText ( ).toString ( ).trim ();
                String Lname = editTextLastname.getText ( ).toString ( ).trim ();
                String Mname = editTextMiddlename.getText ( ).toString ( ).trim ();
                String Email = editTextEmail.getText ( ).toString ( ).trim ();
                String MobileNumber = editTextMobilenumber.getText ( ).toString ( ).trim ();
                String Password = editTextPassword.getText ( ).toString ( ).trim ();
                String ConfirmPassword = editTextConfirmPassword.getText ( ).toString ( ).trim ();
                String DateOfbirth = editTextDateofbirth.getText ( ).toString ( ).trim ();
                //  String  input = EditTextinput.getText().toString();

                checking ( Fname, Lname, Mname, Email, MobileNumber, Password, ConfirmPassword, DateOfbirth );


            }

            private void checking(String fname, String lname, String mname, String email, String mobileNumber, String password, String confirmPassword, String dateOfbirth) {
                if (TextUtils.isEmpty ( fname ) ) {
                    showerror ( editTextFirstname, "Please Enter FirstName" );
                } else if ( lname.isEmpty ( ) ) {

                    showerror ( editTextLastname, "Please Enter LastName" );
                    /*  Toast.makeText ( MainActivity.this, "ok", Toast.LENGTH_LONG ).show ( );*/
                } else if ( mname.isEmpty ( ) ) {
                    showerror ( editTextMiddlename, "Please Enter MiddleName" );
                    /*  Toast.makeText ( MainActivity.this, "ok", Toast.LENGTH_LONG ).show ( );*/
                } else if ( email.isEmpty ( ) || ! email.contains ( "@" ) ) {
                    if ( email.isEmpty ( ) ) {
                        showerror ( editTextEmail, "Please Enter Email Address" );
                    } else if ( ! email.contains ( "@" ) ) {
                        showerror ( editTextEmail, "Please Enter Valid Email Address" );
                    }
                    /*  Toast.makeText ( MainActivity.this, "ok", Toast.LENGTH_LONG ).show ( );*/
                } else if ( mobileNumber.isEmpty ( ) ) {
                    showerror ( editTextMobilenumber, "Please Enter Moblile Number" );
                    /*  Toast.makeText ( MainActivity.this, "ok", Toast.LENGTH_LONG ).show ( );*/
                } else if ( password.isEmpty ( ) ) {
                    showerror ( editTextPassword, "Please Enter Password" );
                    /*  Toast.makeText ( MainActivity.this, "ok", Toast.LENGTH_LONG ).show ( );*/
                } else if ( confirmPassword.isEmpty ( ) ) {
                    showerror ( editTextConfirmPassword, "Please Enter Confirm Password" );
                    /*  Toast.makeText ( MainActivity.this, "ok", Toast.LENGTH_LONG ).show ( );*/
                } else if ( dateOfbirth.isEmpty ( ) ) {
                    showerror ( editTextDateofbirth, "Please Enter Date of Birth" );
                    /*  Toast.makeText ( MainActivity.this, "ok", Toast.LENGTH_LONG ).show ( );*/
                } else {
                    Toast.makeText ( MainActivity.this, "ok done", Toast.LENGTH_LONG ).show ( );
                }
            }
        } );


    }


       /* String uname = editTextFirstname.getText ( ).toString ( );


        switch (v.getId ( )) {


            case R.id.editTextfirstname:
                if ( uname.isEmpty ( ) || uname.length ( ) < 7 ) {
                    showerror ( editTextFirstname, "enter name" );
                }
                break;
         //   case R.id.checkBoxcondition:

                //break;
           *//* case R.id.buttoncreateaccount:


                gotoNextactivity ( );
                break;*//*
            default:
                *//* gotoNextactivity ();*//*
        }*/


    private void showerror(EditText input, String s) {
        input.setError ( s );
        input.requestFocus ( );
    }


    @Override
    public void onClick(View v) {

    }
}