package com.demo.contact_jaspindersingh_C0798164_android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.contact_jaspindersingh_C0798164_android.R;
import com.demo.contact_jaspindersingh_C0798164_android.RoomDatabase.MyRoomDataBase;
import com.demo.contact_jaspindersingh_C0798164_android.model.ContactModel;

public class CreateContactActivity extends AppCompatActivity {

    private TextView tvcancel, tvsave, tvPhoneAdd, tvEmailAdd, tvPhoneMinus2, tvPhoneMinus3, tvEmailMinus2, tvEmailMinus3;
    private EditText etFirstName, etLastName, etPhone1, etPhone2, etPhone3, etEmail1, etEmail2, etEmail3, etAddress;
    private RelativeLayout rlPhone2, rlPhone3, rlEmail2, rlEmail3;

    private ContactModel contactModel;
    private MyRoomDataBase myRoomDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        init();

        tvPhoneAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rlPhone2.getVisibility() == View.GONE) {
                    rlPhone2.setVisibility(View.VISIBLE);
                }
            }
        });

        tvEmailAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rlEmail2.getVisibility() == View.GONE) {
                    rlEmail2.setVisibility(View.VISIBLE);
                }
            }
        });

        tvPhoneMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlPhone2.setVisibility(View.GONE);
            }
        });
        tvPhoneMinus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlPhone3.setVisibility(View.GONE);
            }
        });
        tvEmailMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlEmail2.setVisibility(View.GONE);
            }
        });

        tvEmailMinus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlEmail3.setVisibility(View.GONE);
            }
        });

        tvcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etFirstName.getText().toString().trim() != null && !etFirstName.getText().toString().equals("")) {
                    contactModel.setFirstName(etFirstName.getText().toString());
                    contactModel.setLastName(etLastName.getText().toString());

                    if (etPhone1.getText().toString().trim() != null && !etPhone1.getText().toString().equals("")) {
                        contactModel.setPhone1(etPhone1.getText().toString());
                        contactModel.setPhone2(etPhone2.getText().toString());
                        if (etEmail1.getText().toString().trim() != null && !etEmail1.getText().toString().equals("")) {
                            contactModel.setEmail1(etEmail1.getText().toString());
                            contactModel.setEmail2(etEmail2.getText().toString());
                            contactModel.setAddress(etAddress.getText().toString());

                            myRoomDataBase.mainDao().insertUser(contactModel);
                            Toast.makeText(CreateContactActivity.this, "Contact added successfully", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            Toast.makeText(CreateContactActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CreateContactActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CreateContactActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        tvcancel = findViewById(R.id.tv_cancel);
        tvsave = findViewById(R.id.tv_save);
        tvPhoneAdd = findViewById(R.id.tv_phone_add);
        tvEmailAdd = findViewById(R.id.tv_email_add);
        tvPhoneMinus2 = findViewById(R.id.tv_minus_phone2);
        tvPhoneMinus3 = findViewById(R.id.tv_minus_phone3);
        tvEmailMinus2 = findViewById(R.id.tv_minus_email2);
        tvEmailMinus3 = findViewById(R.id.tv_minus_email3);
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etPhone1 = findViewById(R.id.et_phone1);
        etPhone2 = findViewById(R.id.et_phone2);
        etPhone3 = findViewById(R.id.et_phone3);
        etEmail1 = findViewById(R.id.et_email1);
        etEmail2 = findViewById(R.id.et_email2);
        etEmail3 = findViewById(R.id.et_email3);
        etAddress = findViewById(R.id.et_address);
        rlPhone2 = findViewById(R.id.rl_phone2);
        rlPhone3 = findViewById(R.id.rl_phone3);
        rlEmail2 = findViewById(R.id.rl_email2);
        rlEmail3 = findViewById(R.id.rl_email3);

        myRoomDataBase = MyRoomDataBase.getMyRoomInstance(this);

        contactModel = new ContactModel();
    }
}