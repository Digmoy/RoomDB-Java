package com.example.roomdb_java.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdb_java.Dao.UserDao;
import com.example.roomdb_java.Model.UserModel;

@Database(entities = UserModel.class,version = 1,exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDao userDAO();

    public static volatile UserDataBase userDataBase;

    public static UserDataBase getDatabase(Context context){
        if (userDataBase == null){
            synchronized (UserModel.class){
                userDataBase = Room.databaseBuilder(context, UserDataBase.class,"user_database").build();
            }
        }
        return userDataBase;
    }
}
