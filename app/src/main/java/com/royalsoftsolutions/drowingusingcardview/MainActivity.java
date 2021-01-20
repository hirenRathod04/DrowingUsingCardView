package com.royalsoftsolutions.drowingusingcardview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import forApi.Common;
import forApi.StringRequestVolley;
import forApi.VolleyResponseListener;

import org.json.*;


public class MainActivity extends AppCompatActivity {
    //declaration
    private EditText editTextFirstname, editTextLastname, editTextMiddlename, editTextMobilenumber, editTextEmail, editTextPassword, editTextConfirmPassword, editTextDateofbirth;
    private RadioButton radioButtonMale, radioButtonFemale;
    private DatePickerDialog datepicker;
    private CheckBox checkBoxCondition;
    private Button buttonCreateaccount;
    private Calendar calendar;
    private String setDate = "30", setYear = "1997", setMonth =
            "12";
    private ProgressDialog dialog; //declaration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        Toast.makeText ( MainActivity.this,
                setYear + "-" + setMonth + "-" + setDate,
                Toast.LENGTH_LONG ).show ();


        dialog = new ProgressDialog ( this );
        dialog.setMessage ( "Please wait..." );
        dialog.setCanceledOnTouchOutside ( false );
        // initialization
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
        radioButtonMale = findViewById ( R.id.radioButtonmale );
        radioButtonFemale = findViewById ( R.id.radioButtonfemale );

        editTextDateofbirth.setInputType ( InputType.TYPE_NULL );


        editTextDateofbirth.setOnClickListener ( v -> {

           /* calendar = Calendar.getInstance ( );
            int day = calendar.get ( Calendar.DAY_OF_MONTH );
            int month = calendar.get ( Calendar.MONTH );
            int year = calendar.get ( Calendar.YEAR );*/

            // date picker dialog
            datepicker = new DatePickerDialog ( MainActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    new DatePickerDialog.OnDateSetListener ( ) {

                        @Override

                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
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
        } );

        checkBoxCondition.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                if ( checkBoxCondition.isChecked ( ) ) {
                    buttonCreateaccount.setEnabled ( true );
                    buttonCreateaccount.setBackgroundResource ( R.drawable.edit_style_corner_blue );
                    validation ( );
                } else {
                    buttonCreateaccount.setEnabled ( false );
                    buttonCreateaccount.setBackgroundResource ( R.drawable.edit_style_corner );

                }
            }
        } );
    }


    private void Login(String firstName,
                       String middleName, String lastName,
                       String email,
                       String mobileNumber, String password,
                       String dob,
                       String gender,
                       String profileImageName,
                       String attachmentBase64Str) {
        dialog.show ( );

        Map<String, String> params = new HashMap ( );
        params.put ( "ACTION_CALL_FUNCTION", "registration" );
        params.put ( "firstName", firstName );
        params.put ( "middleName", middleName );
        params.put ( "lastName", lastName );
        params.put ( "email", email );
        params.put ( "mobile", mobileNumber );
        params.put ( "password", password );
        params.put ( "dob", dob );
        params.put ( "gender", gender );
        params.put ( "profileImageName", profileImageName );
        params.put ( "attachment", attachmentBase64Str );

        Log.d ( "Params=", "ACTION_CALL_FUNCTION : registration" );
        Log.d ( "Params=", "firstName : " + firstName );
        Log.d ( "Params=", "middleName : " + middleName );
        Log.d ( "Params=", "lastName : " + lastName );
        Log.d ( "Params=", "mobileNumber : " + mobileNumber );
        Log.d ( "Params=", "password : " + password );
        Log.d ( "Params=", "dob : " + dob );
        Log.d ( "Params=", "gender : " + gender );
        Log.d ( "Params=", "profileImageName : " + profileImageName );
        Log.d ( "Params=", "attachmentBase64Str : " + attachmentBase64Str );

        new StringRequestVolley ( MainActivity.this, params,
                new VolleyResponseListener ( ) {

                    @Override
                    public void onResponse(@Nullable String response) {


                        try {
                            JSONObject json = new JSONObject ( response );
                            String responseCode = json.getString ( "responseCode" );
                            String message = json.getString ( "message" );
                            Log.d ( "Hiren=", "ResponseCode ="+responseCode);
                            Log.d ( "Hiren1=", "message " +
                                    "="+message);

                            Toast.makeText ( MainActivity.this,
                                     message ,
                                    Toast.LENGTH_LONG ).show ( );


                        } catch (JSONException e) {
                            e.printStackTrace ( );
                        }




                        /* gotoConvert();*/
                        //if(responseCode is 0) show success
                        //if(responseCode is 1) show error
                        dialog.dismiss ( );
                      /*  Toast.makeText ( MainActivity.this,
                                "Success..",
                                Toast.LENGTH_LONG ).show ( );*/
                    }

                    @Override
                    public void onError(@Nullable String errorMessage) {
                        //show error
                        dialog.dismiss ( );
                        Toast.makeText ( MainActivity.this,
                                "Error..",
                                Toast.LENGTH_LONG ).show ( );
                    }

                } );
    }


    private void validation() {
        buttonCreateaccount.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {


                String Fname = editTextFirstname.getText ( ).toString ( ).trim ( );


                String Lname = editTextLastname.getText ( ).toString ( ).trim ( );
                String Mname = editTextMiddlename.getText ( ).toString ( ).trim ( );
                String Email = editTextEmail.getText ( ).toString ( ).trim ( );
                String MobileNumber = editTextMobilenumber.getText ( ).toString ( ).trim ( );
                String Password = editTextPassword.getText ( ).toString ( ).trim ( );
                String ConfirmPassword = editTextConfirmPassword.getText ( ).toString ( ).trim ( );
                String DateOfbirth = editTextDateofbirth.getText ( ).toString ( ).trim ( );


                checking ( Fname, Lname, Mname, Email, MobileNumber, Password, ConfirmPassword, DateOfbirth );
            }

            private void checking(String fname, String lname, String mname, String email, String mobileNumber, String password, String confirmPassword, String dateOfbirth) {
                String Email = editTextEmail.getText ( ).toString ( ).trim ( );
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String MobileNumber = editTextMobilenumber.getText ( ).toString ( ).trim ( );

                String Password = editTextPassword.getText ( ).toString ( ).trim ( );
                String ConfirmPassword = editTextConfirmPassword.getText ( ).toString ( ).trim ( );
                String DateOfbirth = editTextDateofbirth.getText ( ).toString ( ).trim ( );
                editTextDateofbirth.setInputType ( InputType.TYPE_NULL );


                if ( fname.contains ( " " ) ) {
                    Toast.makeText ( MainActivity.this, "No Spaces Allowed in First Name", Toast.LENGTH_LONG ).show ( );
                    editTextFirstname.requestFocus ( );

                } else if ( TextUtils.isEmpty ( fname ) ) {
                    Toast.makeText ( MainActivity.this, "please enter first name", Toast.LENGTH_LONG ).show ( );
                    editTextFirstname.requestFocus ( );

                } else if ( lname.contains ( " " ) ) {
                    Toast.makeText ( MainActivity.this, "No Spaces Allowed", Toast.LENGTH_LONG ).show ( );
                    editTextLastname.requestFocus ( );

                } else if ( TextUtils.isEmpty ( lname ) ) {
                    Toast.makeText ( MainActivity.this, "Please Enter LastName", Toast.LENGTH_LONG ).show ( );
                    editTextLastname.requestFocus ( );

                } else if ( mname.contains ( " " ) ) {
                    Toast.makeText ( MainActivity.this, "No Spaces Allowed", Toast.LENGTH_LONG ).show ( );
                    editTextMiddlename.requestFocus ( );

                } else if ( TextUtils.isEmpty ( mname ) ) {
                    Toast.makeText ( MainActivity.this, "Please Enter MiddleName", Toast.LENGTH_LONG ).show ( );
                    editTextMiddlename.requestFocus ( );

                } else if ( ! isValidEmail ( email ) ) {
                    Toast.makeText ( MainActivity.this, " Please Enter Valid Email Address", Toast.LENGTH_SHORT ).show ( );
                } else if ( MobileNumber.contains ( " " ) ) {
                    Toast.makeText ( MainActivity.this, " No Spaces Allowed", Toast.LENGTH_SHORT ).show ( );
                    editTextMobilenumber.requestFocus ( );

                } else if ( TextUtils.isEmpty ( MobileNumber ) ) {
                    Toast.makeText ( MainActivity.this, " Please Enter Moblie number", Toast.LENGTH_SHORT ).show ( );
                    editTextMobilenumber.requestFocus ( );

                } else if ( MobileNumber.length ( ) < 10 ) {
                    Toast.makeText ( MainActivity.this, "Moblie number must be 10 digit", Toast.LENGTH_SHORT ).show ( );
                    editTextMobilenumber.requestFocus ( );
                } else if ( TextUtils.isEmpty ( password ) ) {
                    Toast.makeText ( MainActivity.this, " Please Enter Password", Toast.LENGTH_SHORT ).show ( );
                    editTextPassword.requestFocus ( );

                } else if ( password.length ( ) < 8 ) {
                    Toast.makeText ( MainActivity.this, "Please Enter minimum 8 Digits", Toast.LENGTH_SHORT ).show ( );
                    editTextPassword.requestFocus ( );
                } else if ( TextUtils.isEmpty ( confirmPassword ) ) {
                    Toast.makeText ( MainActivity.this, " Please Enter confirmPassword", Toast.LENGTH_SHORT ).show ( );
                    editTextConfirmPassword.requestFocus ( );

                } else if ( ! confirmPassword.equals ( password ) ) {
                    Toast.makeText ( MainActivity.this, "your ConfirmPassword is not mathing", Toast.LENGTH_SHORT ).show ( );
                    editTextConfirmPassword.requestFocus ( );

                } else if ( TextUtils.isEmpty ( DateOfbirth ) ) {
                    Toast.makeText ( MainActivity.this, "Please Enter Date of Birth", Toast.LENGTH_SHORT ).show ( );
                    editTextConfirmPassword.requestFocus ( );

                } else {
                    /*  Toast.makeText ( MainActivity.this, "ok done", Toast.LENGTH_LONG ).show ( );*/
                    String gender = "male";
                    String profileImage = "";
                    String attachment = "";
                    String DOB = setYear+"-"+setMonth+"-"+setDate;
                    Toast.makeText ( MainActivity.this,
                            setYear + "-" + setMonth + "-" + setDate,
                            Toast.LENGTH_LONG ).show ();
                    Login ( fname, mname, lname, email, mobileNumber
                            , password, DOB, gender,
                            profileImage, attachment );



                   /* try {


                        JSONObject jsonObject = new JSONObject ( "responseCode" );
                        String jsonObjectstring =
                                jsonObject.getString("responseCode");
                        if ( jsonObjectstring.equals ( "0" ) ) {
                            Toast.makeText ( MainActivity.this
                                    , "yes",
                                    Toast.LENGTH_LONG );
                        }
                        else
                        {
                            Toast.makeText ( MainActivity.this
                                    ,"no",
                                    Toast.LENGTH_LONG );
                        }

                    } catch (Exception e)
                    {
                        e.printStackTrace ( );
                    }*/


                }
            }
        } );


    }


    private void gotoConvert(String response) {
        /*   response*/

        //String "responseCode" = response.getString("responseCode");
        /*  System.out.println(technology);*/

  /*  Toast.makeText ( MainActivity.this,responseCode,
            Toast.LENGTH_SHORT ).show ();*/
        try {
            JSONObject jsonObject = new JSONObject ( "responseCode" );
            String jsonObjectstring = jsonObject.getString ( "message" );
            if ( jsonObjectstring.equals ( "0" ) ) {
                Toast.makeText ( MainActivity.this, jsonObjectstring,
                        Toast.LENGTH_LONG );
            } else {
                Toast.makeText ( MainActivity.this, jsonObjectstring,
                        Toast.LENGTH_LONG );
            }

        } catch (Exception e) {
            e.printStackTrace ( );
        }

    }

    public static boolean isValidEmail(CharSequence email) {
        return (! TextUtils.isEmpty ( email ) && Patterns.EMAIL_ADDRESS.matcher ( email ).matches ( ));
    }


}