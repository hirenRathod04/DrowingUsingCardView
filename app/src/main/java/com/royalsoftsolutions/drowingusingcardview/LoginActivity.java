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


                        try {
                            JSONObject json = new JSONObject ( response );
                            String responseCode = json.getString ( "responseCode" );
                            String message = json.getString ( "message" );
                            String result = json.getString ( "result" );

                            Log.d ( "Hiren=", "ResponseCode ="+responseCode);
                            Log.d ( "Hiren1=", "message " + "="+message);
                            Log.d ( "Hiren1=", "result " + "="+result);








                            Log.d ( "Hiren=", "userId ="+userId);



                            Toast.makeText ( LoginActivity.this,
                                    message ,
                                    Toast.LENGTH_LONG ).show ( );



                        } catch (JSONException e)
                        {
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