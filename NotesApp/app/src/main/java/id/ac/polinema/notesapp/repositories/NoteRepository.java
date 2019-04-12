package id.ac.polinema.notesapp.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Update;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.List;

import id.ac.polinema.notesapp.dao.NoteDao;
import id.ac.polinema.notesapp.dao.UserDao;
import id.ac.polinema.notesapp.db.AppDatabase;
import id.ac.polinema.notesapp.models.Note;
import id.ac.polinema.notesapp.models.User;

public class NoteRepository {
    private NoteDao dao;
    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    public NoteRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        noteDao = db.noteDao();
        notes = noteDao.getAll();
    }

    public LiveData<List<Note>> getNotes(){
        return notes;
    }

    public void insert(Note note){
        new InsertAsyncTask(dao)
                .execute(note);
    }
    public void update(Note note){
        new UpdateAsyncTask(dao)
                .execute(note);
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void>{

        @Override
        protected Void doInBackground(Note... notes) {
            asyncTaskDao.insert(notes);
            return null;
        }


        private NoteDao asyncTaskDao;
        InsertAsyncTask(NoteDao dao){
            asyncTaskDao = dao;
        }

    }

    private static class UpdateAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao asyncTaskDao;

        public UpdateAsyncTask(NoteDao Dao) {
            asyncTaskDao = Dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            asyncTaskDao.update(notes);
            return null;
        }

    }
}





//private static class UpdateAsyncTask extends AsyncTask<Note, Void, Void>{
//    private NoteDao AsyncTaskDao;
//
//    public UpdateAsyncTask(NoteDao noteDao) {
//        AsyncTaskDao = noteDao;
//
//
//        @Override
//        protected Void doInBackground (Note...notes){
//            asyncTaskDao.update(notes);
//            return null;
//        }
//    }
//
//}