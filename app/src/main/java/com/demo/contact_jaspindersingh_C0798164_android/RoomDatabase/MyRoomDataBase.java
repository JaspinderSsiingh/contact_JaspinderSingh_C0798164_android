package com.demo.contact_jaspindersingh_C0798164_android.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.demo.contact_jaspindersingh_C0798164_android.Dao.MainDao;
import com.demo.contact_jaspindersingh_C0798164_android.model.ContactModel;

@Database(entities = {ContactModel.class}, version = 1, exportSchema = false)
public abstract class MyRoomDataBase extends RoomDatabase {

    public abstract MainDao mainDao();

    private static MyRoomDataBase myRoomInstance;
    public static String DATABASE_NAME = "Contact_info";

    public static MyRoomDataBase getMyRoomInstance(Context context) {
        if (myRoomInstance == null) {
            myRoomInstance = Room.databaseBuilder(context.getApplicationContext(), MyRoomDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return myRoomInstance;
    }
}