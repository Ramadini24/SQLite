package id.ac.polinema.notesapp.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import id.ac.polinema.notesapp.models.Note;


public interface NoteDao {
    @Insert
    void insert(Note...note);
    @Delete
    void delete(Note...note);
    @Update
    void update(Note...note);


    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAll();


}
