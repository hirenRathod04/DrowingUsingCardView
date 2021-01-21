package com.royalsoftsolutions.drowingusingcardview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import forApi.StringRequestVolley;
import forApi.VolleyResponseListener;

public class LoginActivity extends AppCompatActivity
{
    private EditText editText_email_or_mobile, editText_Password;
    private Button button_Login;
    private ProgressDialog dialog2; //declaration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        dialog2 = new ProgressDialog (LoginActivity.this);

        editText_email_or_mobile = findViewById ( R.id.login_email );
        editText_Password = findViewById ( R.id.login_Password );
        button_Login = findViewById ( R.id.button_login );

        button_Login.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                login_validation ( );

                login2 ();

            }
        } );
    }
    private void tost_msg(){
        Toast.makeText ( this,"Validation Complite",
                Toast.LENGTH_SHORT ).show ();
    }
    private void login_validation() {
        String emailormobile = editText_email_or_mobile.getText ( ).toString ( ).trim ( );
        String loginpassword = editText_Password.getText ( ).toString ( ).trim ( );
        if ( emailormobile.contains ( " " ) ) {
            Toast.makeText ( this, "Space is not Allowed " +
                            "email or Mobile number",
                    Toast.LENGTH_LONG ).show ( );
            editText_email_or_mobile.requestFocus ( );

        } else if ( TextUtils.isEmpty ( emailormobile ) ) {
            Toast.makeText ( this, "please enter Register " +
                    "email or Mobile number", Toast.LENGTH_LONG ).show ( );
            editText_email_or_mobile.requestFocus ( );


        } else if ( loginpassword.length ( ) < 8 ) {
            Toast.makeText ( this, "Please Enter minimum 8 Digits", Toast.LENGTH_SHORT ).show ( );
            editText_Password.requestFocus ( );


        }
        else {
            tost_msg();
        }


    }
    private void login2 ()
    {

        String email_mobile = editText_email_or_mobile.getText ( ).toString ( ).trim ( );
        String password = editText_Password.getText ( ).toString ( ).trim ( );
        String login = "";
        String userId ="0";
        String deviceId = "";
        String token =  "";
        dialog2.show ( );

        Map<String, String> params = new HashMap ( );
        params.put ( "ACTION_CALL_FUNCTION", "login" );
        params.put ( "action", "login" );
        params.put ( "email_mobile", email_mobile );
        params.put ( "password", password );

        params.put ( "userId", userId );
        params.put ( "deviceId", deviceId );

        params.put ( "token", token );



        Log.d ( "Params=", "ACTION_CALL_FUNCTION : login" );
        Log.d ( "Params=", "email_mobile : " + email_mobile );
        Log.d ( "Params=", "password : " + password );

        new StringRequestVolley ( this, params,
                new VolleyResponseListener ( ) {

                    @Override
                    public void onResponse(@Nullable String response) {

                       /*   //IMPORTANT

                        try {
                            JSONObject json = new JSONObject ( response );
                            String responseCode = json.getString ( "responseCode" );
                            String message = json.getString ( "message" );
                            String result = json.getString ( "result" );

                            Log.d ( "Hiren=", "ResponseCode ="+responseCode);
                            Log.d ( "Hiren1=", "message " + "="+message);
                            Log.d ( "Hiren1=", "result " + "="+result);


                            //String getting_data =json.getstring("responseCode");
                            //String Todays= json.getstring("Todays");









                            Log.d ( "Hiren=", "userId ="+userId);



                            Toast.makeText ( LoginActivity.this,
                                    message ,
                                    Toast.LENGTH_LONG ).show ( );



                        } catch (JSONException e)
                        {
                            e.printStackTrace ( );
                        }*/
                        try {
                            JSONObject responseDetails = new JSONObject ( response );

                            String message = responseDetails.getString ( "message" );
                            String responseCode = responseDetails.getString ( "responseCode" );
                            Log.d ( "Hiren=", "ResponseCode ="+responseCode);
                            Log.d ( "Hiren=", "message ="+message);
                            Toast.makeText ( LoginActivity.this, message , Toast.LENGTH_LONG ).show ( );


                            if (responseCode.equals ( "0" ) )
                            {
                               JSONObject result_object = responseDetails.getJSONObject("result");



                              JSONArray json_User_Detail = result_object.getJSONArray("login");

                                if (json_User_Detail.length() > 0)
                                {
                                    JSONObject json_login_details = json_User_Detail.getJSONObject(0);

                                    User objUser = new User ();
                                    objUser.setUserId ( json_login_details.getString( "userId") );
                                    objUser.setFirstName ( json_login_details.getString( "firstName") );
                                    objUser.setFirstName ( json_login_details.getString( "middleName") );
                                    objUser.setUserId ( json_login_details.getString( "lastName") );
                                    objUser.setFirstName ( json_login_details.getString( "email") );
                                    objUser.setFirstName ( json_login_details.getString( "mobile") );
                                    objUser.setUserId ( json_login_details.getString( "dob") );
                                    objUser.setFirstName ( json_login_details.getString( "gender") );
                                    objUser.setFirstName ( json_login_details.getString( "profileImageName") );
                                    objUser.setFirstName ( json_login_details.getString( "profileImageUrl") );

                                    String  firstName = json_login_details.getString( "firstName");
                                    String  middleName =json_login_details.getString( "middleName");
                                    String  lastName = json_login_details.getString( "lastName");
                                    String  email = json_login_details.getString( "email");
                                    String  mobile = json_login_details.getString( "mobile");

                                    String  dob = json_login_details.getString( "dob");
                                    String  gender = json_login_details.getString( "gender");

                                    String  profileImageName = json_login_details.getString( "profileImageName");
                                    String  profileImageUrl = json_login_details.getString( "profileImageUrl");

                                    Toast.makeText ( LoginActivity.this, email , Toast.LENGTH_LONG ).show ( );


                                    Log.d ( "Hiren=", "userId ="+userId);
                                    Log.d ( "Hiren=","firstName ="+firstName);
                                    Log.d ( "Hiren=", "middleName ="+middleName);
                                    Log.d ( "Hiren=", "lastName ="+lastName);
                                    Log.d ( "Hiren=", "email ="+email);
                                    Log.d ( "Hiren=", "mobile ="+mobile);
                                    Log.d ( "Hiren=", "mobile ="+dob);
                                    Log.d ( "Hiren=", "gender ="+gender);
                                    Log.d ( "Hiren=", "profileImageName ="+profileImageName);
                                    Log.d ( "Hiren=", "profileImageUrl ="+profileImageUrl);





                                }





                            }

                        } catch (JSONException e) {
                            e.printStackTrace ( );
                        }





                            /* gotoConvert();*/
                        //if(responseCode is 0) show success
                        //if(responseCode is 1) show error

                        dialog2.dismiss ( );
                      /*  Toast.makeText ( MainActivity.this,
                                "Success..",
                                Toast.LENGTH_LONG ).show ( );*/
                    }

                    @Override
                    public void onError(@Nullable String errorMessage) {
                        //show error
                        dialog2.dismiss ( );
                        Toast.makeText ( LoginActivity.this,
                                "Error..",
                                Toast.LENGTH_LONG ).show ( );
                    }

                } );
    }



}