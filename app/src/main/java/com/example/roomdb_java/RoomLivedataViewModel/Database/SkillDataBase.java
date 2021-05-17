package com.example.roomdb_java.RoomLivedataViewModel.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdb_java.RoomLivedataViewModel.Dao.SkillDao;
import com.example.roomdb_java.RoomLivedataViewModel.Model.SkillModel;

@Database(entities = SkillModel.class,version = 1,exportSchema = false)
public abstract class SkillDataBase extends RoomDatabase {

    public abstract SkillDao skillDao();

    private static volatile SkillDataBase skillRoomDatabase;

    public static SkillDataBase getSkillRoomDatabase(final Context context){
        if (skillRoomDatabase == null){
            synchronized (SkillModel.class){
                skillRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        SkillDataBase.class,"skill_database").build();
            }

        }
        return skillRoomDatabase;
    }

}
