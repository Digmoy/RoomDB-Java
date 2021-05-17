package com.example.roomdb_java.RoomLivedataViewModel.ViewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roomdb_java.RoomLivedataViewModel.Dao.SkillDao;
import com.example.roomdb_java.RoomLivedataViewModel.Database.SkillDataBase;
import com.example.roomdb_java.RoomLivedataViewModel.Model.SkillModel;

import java.util.List;

public class SkillViewModel extends AndroidViewModel {

    private SkillDao skillDao;
    private MutableLiveData<String> callBacks;

    public SkillViewModel(@Nullable Application application){
        super(application);

        SkillDataBase skillDB = SkillDataBase.getSkillRoomDatabase(application);
        skillDao = skillDB.skillDao();
        callBacks = new MutableLiveData<>();
    }

    public MutableLiveData<String> insert(SkillModel skillModel){
        new ExecuteAsyncTask(skillDao, "insert",callBacks).execute(skillModel);
        return callBacks;
    }

    public LiveData<List<SkillModel>> getAllSkill(){
        return skillDao.getAllSkill();
    }

    public LiveData<SkillModel> getSkill (int id){
        return skillDao.getSelectSkill(id);
    }

    public MutableLiveData<String> update (SkillModel skillModel){
        new ExecuteAsyncTask(skillDao, "update",callBacks).execute(skillModel);
        return callBacks;
    }

    public MutableLiveData<String> delete (SkillModel skillModel){
        new ExecuteAsyncTask(skillDao, "delete",callBacks).execute(skillModel);
        return callBacks;
    }

    private class ExecuteAsyncTask extends AsyncTask<SkillModel,Void,Void>{
        SkillDao skillDao;
        private String type;
        private MutableLiveData<String> callBacks;

        public ExecuteAsyncTask(SkillDao skillDao, String type, MutableLiveData<String> callBacks) {
            this.skillDao = skillDao;
            this.type = type;
            this.callBacks = callBacks;
        }

        @Override
        protected Void doInBackground(SkillModel... skillModels) {
            switch (type)
            {
                case "insert":
                    skillDao.insert(skillModels[0]);
                    break;
                case "update":
                    skillDao.updateSkill(skillModels[0]);
                    break;
                case "delete":
                    skillDao.deleteSkill(skillModels[0]);
                    break;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            switch (type) {
                case "insert":
                    callBacks.setValue("Skill inserted");
                    break;
                case "update":
                    callBacks.setValue("Skill updated");
                    break;
                case "delete":
                    callBacks.setValue("Skill deleted");
                    break;
            }
        }
    }
}
