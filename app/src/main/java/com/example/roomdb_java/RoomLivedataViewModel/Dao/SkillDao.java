package com.example.roomdb_java.RoomLivedataViewModel.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdb_java.RoomLivedataViewModel.Model.SkillModel;

import java.util.List;

@Dao
public interface SkillDao {

    @Insert
    void insert (SkillModel skillModel);

    @Query("SELECT * FROM skill")
    LiveData<List<SkillModel>> getAllSkill();

    @Query("SELECT * FROM skill WHERE id=:id")
    LiveData<SkillModel> getSelectSkill(int id);

    @Update
    void updateSkill(SkillModel skillModel);

    @Delete
    void deleteSkill(SkillModel skillModel);

}
