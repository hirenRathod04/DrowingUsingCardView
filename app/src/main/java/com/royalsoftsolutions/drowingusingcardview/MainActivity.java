package com.royalsoftsolutions.drowingusingcardview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
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
    private DatePickerDialog datepicker;
    private CheckBox checkBoxCondition;
    private Button buttonCreateaccount, buttonLogin;
    private TextInputEditText eText;
    private RadioButton radioButton;
    private Calendar calendar;
    public String setDate = "2", setYear = "1997", setMonth = "3";
    public boolean aMale = true,bFemale = false;


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
        checkBoxCondition = findViewById ( R.id.checkBoxcondition );
        buttonCreateaccount = findViewById ( R.id.buttoncreateaccount );

        radioButtonMale = findViewById(R.id.radioButtonmale);
        radioButtonFemale = findViewById ( R.id.radioButtonfemale );
        radioButtonMale.setChecked(true);
       radioButtonFemale.setChecked(false);


        buttonCreateaccount.setEnabled ( false );
        editTextDateofbirth.setInputType ( InputType.TYPE_NULL );

        editTextDateofbirth.setFocusable ( false );


        editTextDateofbirth.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                editTextDateofbirth.setInputType ( InputType.TYPE_NULL );

                /*  editTextDateofbirth.getText ().toString ().trim ();*/
                final Calendar cldr = Calendar.getInstance ( );
                int day = cldr.get ( Calendar.DAY_OF_MONTH );
                int month = cldr.get ( Calendar.MONTH );
                int year = cldr.get ( Calendar.YEAR );

                // date picker dialog
                datepicker = new DatePickerDialog ( MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener ( ) {

                            @Override

                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                /*  if (dayOfMonth<10 || (monthOfYear + 1)<10  )*/
                                setMonth = String.valueOf ( monthOfYear + 1 );
                                setDate = String.valueOf ( dayOfMonth );
                                setYear = String.valueOf ( year );

                                if ( Integer.parseInt ( setDate ) < 10 ) {
                                    setDate = "0" + setDate;
                                }
                                if ( Integer.parseInt ( setMonth ) < 10 ) {
                                    setMonth = "0" + setMonth;
                                }
                                editTextDateofbirth.setText ( setDate + "/" + setMonth + "/" + setYear );
                            }

                        }, Integer.parseInt ( setYear ), Integer.parseInt ( setMonth ) - 1, Integer.parseInt ( setDate ) );

                datepicker.getWindow ( ).setBackgroundDrawable ( new ColorDrawable ( Color.TRANSPARENT ) );
                datepicker.show ( );
            }
        } );

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


                String Fname = editTextFirstname.getText ( ).toString ( ).trim ( );
                /*  String Fname = Fname.replaceAll("\\s+","");*/


                String Lname = editTextLastname.getText ( ).toString ( ).trim ( );
                String Mname = editTextMiddlename.getText ( ).toString ( ).trim ( );
                String Email = editTextEmail.getText ( ).toString ( ).trim ( );
                String MobileNumber = editTextMobilenumber.getText ( ).toString ( ).trim ( );
                String Password = editTextPassword.getText ( ).toString ( ).trim ( );
                String ConfirmPassword = editTextConfirmPassword.getText ( ).toString ( ).trim ( );
                String DateOfbirth = editTextDateofbirth.getText ( ).toString ( ).trim ( );
                //  String  input = EditTextinput.getText().toString();


                checking ( Fname, Lname, Mname, Email, MobileNumber, Password, ConfirmPassword, DateOfbirth );


            }

            private void checking(String fname, String lname, String mname, String email, String mobileNumber, String password, String confirmPassword, String dateOfbirth) {
                /* String Email = email.getText().toString().trim();*/
                String Email = editTextEmail.getText ( ).toString ( ).trim ( );
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String MobileNumber = editTextMobilenumber.getText ( ).toString ( ).trim ( );

                String Password = editTextPassword.getText ( ).toString ( ).trim ( );
                String ConfirmPassword = editTextConfirmPassword.getText ( ).toString ( ).trim ( );
                String DateOfbirth = editTextDateofbirth.getText ( ).toString ( ).trim ( );
                editTextDateofbirth.setInputType ( InputType.TYPE_NULL );







                 if ( fname.contains ( " " ) ) {
                    /* fname.setError("No Spaces Allowed");*/
                    Toast.makeText ( MainActivity.this, "No Spaces Allowed in First Name", Toast.LENGTH_LONG ).show ( );
                    editTextFirstname.requestFocus ( );
                } else if ( TextUtils.isEmpty ( fname ) ) {// showerror ( editTextFirstname, "Please Enter FirstName" );
                    Toast.makeText ( MainActivity.this, "please enter first name", Toast.LENGTH_LONG ).show ( );
                    editTextFirstname.requestFocus ( );
                } else if ( lname.contains ( " " ) ) {/*showerror ( editTextLastname, "Please Enter LastName" );*/
                    Toast.makeText ( MainActivity.this, "No Spaces Allowed", Toast.LENGTH_LONG ).show ( );
                    editTextLastname.requestFocus ( );

                } else if ( TextUtils.isEmpty ( lname ) ) {/*showerror ( editTextLastname, "Please Enter LastName" );*/
                    Toast.makeText ( MainActivity.this, "Please Enter LastName", Toast.LENGTH_LONG ).show ( );
                    editTextLastname.requestFocus ( );

                } else if ( mname.contains ( " " ) ) {
                    /*showerror ( editTextMiddlename, "Please Enter MiddleName" );*/
                    Toast.makeText ( MainActivity.this, "No Spaces Allowed", Toast.LENGTH_LONG ).show ( );
                    editTextMiddlename.requestFocus ( );
                } else if ( TextUtils.isEmpty ( mname ) ) {
                    /*showerror ( editTextMiddlename, "Please Enter MiddleName" );*/
                    Toast.makeText ( MainActivity.this, "Please Enter MiddleName", Toast.LENGTH_LONG ).show ( );
                    editTextMiddlename.requestFocus ( );
                } else if ( ! isValidEmail ( email ) ) {
                    Toast.makeText ( MainActivity.this, " Please Enter Valid Email Address", Toast.LENGTH_SHORT ).show ( );

                }


/*

                else if( email.contains(" "))

                {
                    Toast.makeText ( MainActivity.this, " No Spaces Allowed", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextEmail.requestFocus();
                }
               */
                /* else if ( email.isEmpty ( ) || ! email.contains ( "@" ) )*//*




                else if( TextUtils.isEmpty ( Email ))

                {
                    Toast.makeText ( MainActivity.this, " Please Enter Email Address", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextEmail.requestFocus();
                }

                else if ( ! Email.matches( emailPattern ) )
                    {
                        Toast.makeText ( MainActivity.this, "Invalid email address", Toast.LENGTH_LONG ).show ( );
                        //or
                        */
                /*  textView.setText ( "invalid email" );*//*

                 */
/*   else
                        {
                            Toast.makeText ( MainActivity.this, "email address register", Toast.LENGTH_LONG ).show ( );
                        }*//*

                    }
*/


                else if ( MobileNumber.contains ( " " ) ) {
                    Toast.makeText ( MainActivity.this, " No Spaces Allowed", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextMobilenumber.requestFocus ( );
                } else if ( TextUtils.isEmpty ( MobileNumber ) ) {
                    Toast.makeText ( MainActivity.this, " Please Enter Moblie number", Toast.LENGTH_SHORT ).show ( );
                    editTextMobilenumber.requestFocus ( );
                    // am_checked=0;
                } else if ( MobileNumber.length ( ) < 10 || MobileNumber.length ( ) > 10 /*|| !number.matches(regexStr)==false */ ) {
                    Toast.makeText ( MainActivity.this, "Moblie number must be 10 digit", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextMobilenumber.requestFocus ( );
                }


               /* else if( password.contains(" "))

                {
                    Toast.makeText ( MainActivity.this, " No Spaces Allowed", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextPassword.requestFocus();
                }*/
                /////////////////password
                else if ( TextUtils.isEmpty ( password ) ) {
                    Toast.makeText ( MainActivity.this, " Please Enter Password", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextPassword.requestFocus ( );
                } else if ( password.length ( ) < 8 /*||*/ /* MobileNumber.length()>10 *//*|| !number.matches(regexStr)==false */ ) {
                    Toast.makeText ( MainActivity.this, "Please Enter minimum 8 Digits", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextPassword.requestFocus ( );
                }


               /* else if( confirmPassword.contains(" "))

                {
                    Toast.makeText ( MainActivity.this, " No Spaces Allowed", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextConfirmPassword.requestFocus();
                }*/

                else if ( TextUtils.isEmpty ( confirmPassword ) ) {
                    Toast.makeText ( MainActivity.this, " Please Enter confirmPassword", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextConfirmPassword.requestFocus ( );
                } else if ( ! confirmPassword.equals ( password ) /*||*/ /* MobileNumber.length()>10 *//*|| !number.matches(regexStr)==false */ ) {
                    Toast.makeText ( MainActivity.this, "your ConfirmPassword is not mathing", Toast.LENGTH_SHORT ).show ( );
                    // am_checked=0;
                    editTextConfirmPassword.requestFocus ( );
                } else if ( TextUtils.isEmpty ( DateOfbirth ) ) {
                    Toast.makeText ( MainActivity.this, "Please Enter Date of Birth", Toast.LENGTH_SHORT ).show ( );
                    editTextConfirmPassword.requestFocus ( );
                    /* showerror ( editTextDateofbirth, "Please Enter Date of Birth" );*/
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


    public static boolean isValidEmail(CharSequence email) {
        return (! TextUtils.isEmpty ( email ) && Patterns.EMAIL_ADDRESS.matcher ( email ).matches ( ));
    }


    private void showerror(EditText input, String s) {
        input.setError ( s );
        input.requestFocus ( );
    }


    @Override
    public void onClick(View v) {

    }
}