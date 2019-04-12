package id.ac.polinema.notesapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import id.ac.polinema.notesapp.dao.UserDao;
import id.ac.polinema.notesapp.db.AppDatabase;
import id.ac.polinema.notesapp.models.Note;
import id.ac.polinema.notesapp.models.User;

public class UserRepository {
    private UserDao dao;

    public UserRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        dao = db.userDao();
    }
    public LiveData<User> getUserByUsername(String username){
        return dao.getUserByUsername(username);
    }

    public void insert(User user){
        new InsertAsyncTask(dao).execute(user);

    }


private static class InsertAsyncTask extends AsyncTask<User, Void, Void>{

        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.insert(users);
            return null;
        }

        private UserDao asyncTaskDao;
        InsertAsyncTask(UserDao dao){
            asyncTaskDao = dao;
        }


    }
    

}
