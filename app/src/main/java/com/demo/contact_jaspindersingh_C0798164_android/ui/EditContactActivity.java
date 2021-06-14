package com.demo.contact_jaspindersingh_C0798164_android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.demo.contact_jaspindersingh_C0798164_android.Constant;
import com.demo.contact_jaspindersingh_C0798164_android.R;
import com.demo.contact_jaspindersingh_C0798164_android.RoomDatabase.MyRoomDataBase;
import com.demo.contact_jaspindersingh_C0798164_android.model.ContactModel;

import java.util.ArrayList;

public class EditContactActivity extends AppCompatActivity {

    private TextView tvcancel, tvsave, tvPhoneAdd, tvEmailAdd, tvPhoneMinus2, tvPhoneMinus3, tvEmailMinus2, tvEmailMinus3;
    private EditText etFirstName, etLastName, etPhone1, etPhone2, etPhone3, etEmail1, erEmail2, etEmail3, etAddress;
    private RelativeLayout rlPhone2, rlPhone3, rlEmail2, rlEmail3;
    ArrayList<ContactModel> contactlist = new ArrayList<>();
    ContactModel contactModel;
    public MyRoomDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        init();
        contactModel = new ContactModel();
        database = MyRoomDataBase.getMyRoomInstance(this);


        if (getIntent() != null) {
            contactlist = (ArrayList<ContactModel>) getIntent().getSerializableExtra(Constant.CONTACT_LIST);
            contactModel = contactlist.get(0);
        }

        etFirstName.setText(contactModel.getFirstName());
        etLastName.setText(contactModel.getLastName());
        etPhone1.setText(contactModel.getPhone1());
        etPhone2.setText(contactModel.getPhone2());
        etEmail1.setText(contactModel.getEmail1());
        if (!contactModel.getPhone2().equals("")) {
            rlPhone2.setVisibility(View.VISIBLE);
            etPhone2.setText(contactModel.getPhone2());
        }
        if (!contactModel.getEmail2().equals("")) {
            rlEmail2.setVisibility(View.VISIBLE);
            erEmail2.setText(contactModel.getEmail2());
        }

        etAddress.setText(contactModel.getAddress());


        tvPhoneAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rlPhone2.getVisibility() == View.GONE) {
                    rlPhone2.setVisibility(View.VISIBLE);
                } else {
                    if (rlPhone3.getVisibility() == View.GONE) {
                        rlPhone3.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        tvEmailAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rlEmail2.getVisibility() == View.GONE) {
                    rlEmail2.setVisibility(View.VISIBLE);
                } else {
                    if (rlEmail3.getVisibility() == View.GONE) {
                        rlEmail3.setVisibility(View.VISIBLE);
                    }
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
                    if (etPhone1.getText().toString().trim() != null && !etPhone1.getText().toString().equals("")) {
                        if (etEmail1.getText().toString().trim() != null && !etEmail1.getText().toString().equals("")) {

                            final int sID = contactModel.getID();
                            database.mainDao().updateItem(sID, etFirstName.getText().toString(),
                                    etLastName.getText().toString()
                                    , etPhone1.getText().toString(), etPhone2.getText().toString()
                                    , etEmail1.getText().toString(), erEmail2.getText().toString(), etAddress.getText().toString());
                            Toast.makeText(EditContactActivity.this, "Contact updated successfully", Toast.LENGTH_SHORT).show();
                             finish();
                        } else {
                            Toast.makeText(EditContactActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditContactActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditContactActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
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
        erEmail2 = findViewById(R.id.et_email2);
        etEmail3 = findViewById(R.id.et_email3);
        etAddress = findViewById(R.id.et_address);
        rlPhone2 = findViewById(R.id.rl_phone2);
        rlPhone3 = findViewById(R.id.rl_phone3);
        rlEmail2 = findViewById(R.id.rl_email2);
        rlEmail3 = findViewById(R.id.rl_email3);
    }
}