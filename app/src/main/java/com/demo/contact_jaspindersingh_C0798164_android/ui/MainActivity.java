package com.demo.contact_jaspindersingh_C0798164_android.ui;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.contact_jaspindersingh_C0798164_android.Constant;
import com.demo.contact_jaspindersingh_C0798164_android.R;
import com.demo.contact_jaspindersingh_C0798164_android.RoomDatabase.MyRoomDataBase;
import com.demo.contact_jaspindersingh_C0798164_android.adapter.ContactAdapter;
import com.demo.contact_jaspindersingh_C0798164_android.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyRoomDataBase myRoomDataBase;
    private EditText etSearch;
    private TextView tvCount;
    private RelativeLayout rlCreateContact;
    private RecyclerView rvContact;
    private ContactAdapter contactAdapter;
    private List<ContactModel> contactList = new ArrayList<>();
    private List<ContactModel> searchingList = new ArrayList<>();
    private List<ContactModel> tmpList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlCreateContact = findViewById(R.id.rl_create_contact);
        rvContact = findViewById(R.id.rv_contact);
        etSearch = findViewById(R.id.search_et);
        tvCount = findViewById(R.id.tv_count);

        MyRoomDataBase myRoomDataBaseinstance = MyRoomDataBase.getMyRoomInstance(this);
        myRoomDataBase = myRoomDataBaseinstance;

        rvContact.setLayoutManager(new LinearLayoutManager(this));
        rvContact.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));

        contactList.addAll(myRoomDataBase.mainDao().getAllContact());
        tmpList.addAll(contactList);

        if (contactList.size() > 0) {
            tvCount.setText(String.valueOf(contactList.size()));
        } else {
            tvCount.setText("No contacts");
        }

        contactAdapter = new ContactAdapter(MainActivity.this, contactList, new ContactAdapter.OnClickMenuListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                ArrayList<ContactModel> cntList = new ArrayList<>();
                cntList.add(contactList.get(position));
                intent.putExtra(Constant.CONTACT_LIST, cntList);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position) {
                openDialog(position);
            }
        });
        rvContact.setAdapter(contactAdapter);

        rlCreateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateContactActivity.class);
                startActivity(intent);
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = String.valueOf(charSequence);
                if (searchingList.size() > 0) {
                    searchingList.clear();
                }
                for (int j = 0; j < tmpList.size(); j++) {
                    if (tmpList.get(j).getFirstName().contains(searchText)) {
                        searchingList.add(tmpList.get(j));
                    }
                }
                contactList.clear();
                contactList.addAll(searchingList);
                contactAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void openDialog(int position) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.call_message_dailog);
        RelativeLayout rlphone1 = dialog.findViewById(R.id.rl_phone_container1);
        RelativeLayout rlphone2 = dialog.findViewById(R.id.rl_phone_container2);
        RelativeLayout rlemail1 = dialog.findViewById(R.id.rl_email_container1);
        RelativeLayout rlemail2 = dialog.findViewById(R.id.rl_email_container2);
        ImageView phone1 = dialog.findViewById(R.id.iv_phone1);
        ImageView phone2 = dialog.findViewById(R.id.iv_phone2);
        ImageView message1 = dialog.findViewById(R.id.iv_message1);
        ImageView message2 = dialog.findViewById(R.id.iv_message2);
        ImageView email1 = dialog.findViewById(R.id.iv_email1);
        ImageView email2 = dialog.findViewById(R.id.iv_email2);
        TextView tvEmail1 = dialog.findViewById(R.id.tv_email1);
        TextView tvEmail2 = dialog.findViewById(R.id.tv_email2);
        TextView tvPhone1 = dialog.findViewById(R.id.tv_mobile1);
        TextView tvPhone2 = dialog.findViewById(R.id.tv_mobile2);
        if (!contactList.get(position).getPhone2().equals("")) {
            rlphone2.setVisibility(View.VISIBLE);
            tvPhone2.setText(contactList.get(position).getPhone2());
        }
        if (!contactList.get(position).getEmail2().equals("")) {
            rlemail2.setVisibility(View.VISIBLE);
            tvEmail2.setText(contactList.get(position).getEmail2());
        }
        tvPhone1.setText(contactList.get(position).getPhone1());
        tvEmail1.setText(contactList.get(position).getEmail1());

        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + tvPhone1.getText().toString()));
                startActivity(intent);
                dialog.dismiss();
            }
        });
        phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + tvPhone2.getText().toString()));
                startActivity(intent);
                dialog.dismiss();
            }
        });
        message1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address", "" + tvPhone1.getText().toString());
                    startActivity(smsIntent);
                    dialog.dismiss();
                }catch(Exception e){
                    dialog.dismiss();
                }


            }
        });
        message2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address", "" + tvPhone2.getText().toString());
                    startActivity(smsIntent);
                    dialog.dismiss();
                }catch (Exception e){
                    dialog.dismiss();
                }

            }
        });
        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", tvEmail1.getText().toString(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text");
                startActivity(Intent.createChooser(emailIntent, null));
                dialog.dismiss();
            }
        });
        email2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", tvEmail2.getText().toString(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is my subject text");
                startActivity(Intent.createChooser(emailIntent, null));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void sendSMS(String phone) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) // At least KitKat
        {
            String defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(this); // Need to change the build to API 19

            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "text");

            {
                sendIntent.setPackage(defaultSmsPackageName);
            }
            startActivity(sendIntent);

        } else // For early versions, do what worked for you before.
        {
            Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", "phoneNumber");
            smsIntent.putExtra("sms_body", "message");
            startActivity(smsIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactList.clear();
        tmpList.clear();
        etSearch.setText("");
        contactList.addAll(myRoomDataBase.mainDao().getAllContact());
        if (contactList.size() > 0) {
            tvCount.setText(String.valueOf(contactList.size()));
        } else {
            tvCount.setText("No contacts");
        }
        tmpList.addAll(contactList);
        contactAdapter.notifyDataSetChanged();
    }
}