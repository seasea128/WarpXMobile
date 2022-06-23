package com.example.warpxmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import androidx.lifecycle.ViewModelProvider
import com.example.warpxmobile.R
import com.example.warpxmobile.viewmodel.SiteViewModel
import com.google.android.material.textfield.TextInputLayout

class SiteActivity : AppCompatActivity() {
    private lateinit var viewModel: SiteViewModel
    private lateinit var dropdownLocation: AutoCompleteTextView
    private lateinit var dropdownSite: AutoCompleteTextView
    private lateinit var dropdownGateID: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site)

        init()
        setListener()
        //TODO: Get all the data
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[SiteViewModel::class.java]
        dropdownLocation = findViewById(R.id.dropdown_location)
        dropdownSite = findViewById(R.id.dropdown_site)
        dropdownGateID = findViewById(R.id.dropdown_gate_id)
    }

    private fun setListener() {
    }
}