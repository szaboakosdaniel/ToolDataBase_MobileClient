package com.mobiletooldatabaseclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {

    private TextView sampleid,scode,samplePhase,location,projectName,assemblyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Initialize your TextViews
        sampleid = findViewById(R.id.sampleid);
        scode = findViewById(R.id.scode);
        samplePhase = findViewById(R.id.samplePhase);
        location = findViewById(R.id.location);
        projectName = findViewById(R.id.projectName);
        assemblyId = findViewById(R.id.assemblyId);
    }



}