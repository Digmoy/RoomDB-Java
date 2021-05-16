package com.example.roomdb_java.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdb_java.Model.UserModel;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void addUser(UserModel users);

    @Query("SELECT * FROM user_crud")
    List<UserModel> getAllUsers();

    @Query("SELECT * FROM user_crud where id=:id")
    UserModel getSelectedUser(String id);

    @Update
    void updateUser (UserModel user_crud);

    @Delete
    void deleteUser (UserModel user_crud);
}
