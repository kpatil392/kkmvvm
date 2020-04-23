package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


import androidx.appcompat.widget.AppCompatEditText;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class InputValidations implements AppConstants
{
    private Context context;

    public InputValidations(Context context) {
        this.context = context;
    }


    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }


    public boolean isInputEditTextName(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (!value.matches(String.valueOf(PERSON_NAME_PATTERN))) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    public boolean isInputEditTextDateOfBirth(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if(getAge(value)<15) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }



     

    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty())
            textInputLayout.setErrorEnabled(false);
        else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else textInputLayout.setErrorEnabled(false);
        return true;
    }

    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

   

    public void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    public boolean isInputEditTextPhone(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || !isValidNumber(value) || value.length()<6 || value.length()>13) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextPassAndConfirmPass(TextInputEditText textInputEditText, TextInputLayout textInputLayout,
                                                     String message, String password) {
        String value = textInputEditText.getText().toString().trim();
        if (!value.equals(password)) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputSpinnerFilled(String textInputString, TextInputLayout textInputLayout, String message) {
        if (textInputString.equals(context.getResources().getString(R.string.app_name))) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputLayout);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }


    public static boolean isValidNumber(String number) {
        return !TextUtils.isEmpty(number) && Patterns.PHONE.matcher(number).matches();
    }


    public boolean isStringInvalid(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher f_name = ps.matcher(value);
        boolean f_Name = f_name.matches();
        if (!f_Name) {
            hideKeyboardFrom(textInputEditText);
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);

            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    public boolean isInputEditTextPhone(AppCompatEditText editText, String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty() || !isValidNumber(value) || value.length() != 10) {
            editText.setError(message);
            hideKeyboardFrom(editText);
            return false;
        } else {

        }
        return true;
    }


    public boolean isEditTextFilled(AppCompatEditText editText, String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty()) {
            editText.setError(message);
            hideKeyboardFrom(editText);
            return false;
        } else {

        }

        return true;
    }

//    public boolean isEditTextFilledToastMsg(AppCompatEditText editText, String message) {
//        String value = editText.getText().toString().trim();
//        if (value.isEmpty()) {
//            AppUtils.showToast(message);
//            hideKeyboardFrom(editText);
//            return false;
//        } else {
//
//        }
//
//        return true;
//    }


    public boolean isEditTextValidEmail(AppCompatEditText editText, String message) {
        String value = editText.getText().toString().trim();
        if(!EMAIL_ADDRESS_PATTERN.matcher(value).matches()) {
            editText.setError(message);
            hideKeyboardFrom(editText);
            return false;
        }
        else return true;

    }


//    public boolean isInputSpinnerFilled(String textInputString, TextInputLayout textInputLayout, String message) {
//        if (textInputString.equals(context.getResources().getString(R.string.select_gender))) {
//            textInputLayout.setErrorEnabled(true);
//            textInputLayout.setError(message);
//            hideKeyboardFrom(textInputLayout);
//            return false;
//        } else {
//            textInputLayout.setErrorEnabled(false);
//        }
//
//        return true;
//    }


    public boolean isInputAutoCompleteFilled(String textInputString, TextInputLayout textInputLayout, String message) {
        if (textInputString==null || textInputString.equals("")) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputLayout);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }



    public boolean isInputEditTextValidAddress(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (!value.matches(String.valueOf(ADDRESS_PATTERN))) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }


    //Method for getting age from date of birth
    public Integer getAge(String date){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        try {
            dob.setTime(YYYY_MM_DD.parse(date));
            Log.i("Age",YYYY_MM_DD.parse(date)+"");
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("Age",e.toString()+"");
        }

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return age;
    }

}
