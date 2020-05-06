package com.example.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.base.BaseActivity2;
import com.example.myapplication.base.BaseViewModel;
import com.example.myapplication.permissiio.CheckPermissionUtil;
import com.example.myapplication.permissiio.LocationPresenter;
import com.example.myapplication.permissiio.PermissionUtil;
import com.example.myapplication.utils.ImagePicker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ImageDemo extends BaseActivity2
        implements LocationPresenter.LocationView {

    private LocationPresenter locationPresenter;
    private TextView tvLocation, tvSaveResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

        initViews();
        locationPresenter = new LocationPresenter(this, this);
    }

    private void initViews() {
        tvLocation = (TextView) findViewById(R.id.tv_location);
        showLocationResult("unknown!");

        tvSaveResult = (TextView) findViewById(R.id.tv_save_result);
        showSaveResult("unknown!");
    }

    @Override
    public void showLocationResult(String locationResult) {
        tvLocation.setText(getString(R.string.your_location, locationResult));
    }

    private void showSaveResult(String saveResult) {
        tvSaveResult.setText(getString(R.string.save_result, saveResult));
    }

    ////////////////////////////////////////////
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_location:
                locationPresenter.requestLocation(this);
                break;
            case R.id.btn_save_location:
                saveLocation();
                break;
        }
    }

    private void saveLocation() {
        CheckPermissionUtil.checkWriteSd(this,
                new PermissionUtil.ReqPermissionCallback() {
                    @Override
                    public void onResult(boolean success) {
                        if (success) {
                            saveLocationToFile();
                        } else {
                            showSaveResult("disallowed!");
                        }
                    }
                });
    }

    private void saveLocationToFile() {
        String content = tvLocation.getText().toString();
        String filePath =
                Environment.getExternalStorageDirectory().getPath() + "/location.txt";
        PrintWriter out = null;
        try {
            out = new PrintWriter(filePath);
            out.println(content);
            showSaveResult(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            showSaveResult(e.getLocalizedMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}