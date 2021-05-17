package com.example.roomdb_java.RoomLivedataViewModel.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "skill")
public class SkillModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "skill_name")
    private String skill_name;

    @ColumnInfo(name = "experience")
    private String experience;



    public SkillModel(String skill_name, String experience)
    {
        this.skill_name = skill_name;
        this.experience = experience;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

}
