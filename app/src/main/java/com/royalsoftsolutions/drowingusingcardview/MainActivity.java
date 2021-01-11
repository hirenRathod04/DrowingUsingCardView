package com.royalsoftsolutions.drowingusingcardview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private EditText editText;
    private TextInputEditText eText;
    private DatePickerDialog datepicker;
    private Button button;
    private Calendar cldr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        editText = findViewById ( R.id.mtiPassword2 );
        eText = findViewById ( R.id.mtiDateofbirth2 );

        eText.setInputType ( InputType.TYPE_NULL );
        checkBox = findViewById ( R.id.tvCondition );
        button = findViewById ( R.id.buttonlast );
        button.setEnabled ( false );
    }


    public void go_to_openCalender(View view) {
        cldr = Calendar.getInstance ( );
        int day = cldr.get ( Calendar.DAY_OF_MONTH );
        int month = cldr.get ( Calendar.MONTH );
        int year = cldr.get ( Calendar.YEAR );

        // date picker dialog
        datepicker = new DatePickerDialog ( MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener ( ) {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        eText.setText ( dayOfMonth + "/" + (monthOfYear + 1) + "/" + year );
                    }

                }, year, month, day );

        datepicker.getWindow ( ).setBackgroundDrawable ( new ColorDrawable ( Color.TRANSPARENT ) );
        datepicker.show ( );
    }

    public void go_to_nextActivity(View view) {
        Intent intent = new Intent (this, CardActivity2.class);
        startActivity (intent);
    }

    public void checked(View view) {
        if ( checkBox.isChecked ( ) ) {
            button.setEnabled ( true );

        } else {
            button.setEnabled ( false );
        }
    }
}