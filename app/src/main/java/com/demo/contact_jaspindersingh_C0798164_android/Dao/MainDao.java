package com.demo.contact_jaspindersingh_C0798164_android.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.demo.contact_jaspindersingh_C0798164_android.model.ContactModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {

    //todo: to Insert data in data base
    @Insert(onConflict = REPLACE)
    void insertUser(ContactModel contactModel);

    //todo: to delete the particular data from data base
    @Delete
    void deleteUser(ContactModel contactModel);

    //todo: update Data
    @Query("UPDATE contact SET firstName = :fName, lastName = :lName, phone1 = :phone1, phone2 = :phone2, email1 = :email1, email2 = :email2,address = :address  Where ID = :sID")
    int updateItem(int sID,String fName,String lName,String phone1,String phone2,String email1,String email2,String address);

    //todo:get all the data from database
    @Query("SELECT * FROM contact")
    List<ContactModel> getAllContact();

}
