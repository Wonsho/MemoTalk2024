package com.wons.memotalk.memotalkactivity.viewmodel;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Transaction;

import com.wons.memotalk.Database;
import com.wons.memotalk.dao.MemoDataDao;
import com.wons.memotalk.entity.memo_data.File;
import com.wons.memotalk.entity.memo_data.Img;
import com.wons.memotalk.entity.memo_data.MemoData;
import com.wons.memotalk.entity.memo_data.MemoType;
import com.wons.memotalk.entity.memo_data.Text;
import com.wons.memotalk.entity.memo_data.Url;
import com.wons.memotalk.entity.memo_data.todo.TodoList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MemoRoomItemViewModel extends AndroidViewModel {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private MutableLiveData<List<MemoData>> memoDataLiveData;
    private MutableLiveData<List<Text>> textLiveData;
    private MutableLiveData<List<Url>> urlLiveData;
    private MutableLiveData<List<Img>> imgLiveData;
    private MutableLiveData<List<File>> fileLiveData;
    private MutableLiveData<List<TodoList>> todoListLiveData;


    public MemoRoomItemViewModel(@NonNull Application application) {
        super(application);
        this.memoDataLiveData = new MutableLiveData<>();
        this.textLiveData = new MutableLiveData<>();
        this.urlLiveData = new MutableLiveData<>();
        this.imgLiveData = new MutableLiveData<>();
        this.fileLiveData = new MutableLiveData<>();
        this.todoListLiveData = new MutableLiveData<>();
    }

    public void loadData(long roomId) {
        executor.execute(() -> {
            memoDataLiveData.postValue(Database.getDatabase(getApplication()).memoDataDao().getAllById(roomId).getValue());
            loadChildData();
        });
    }

    private void loadChildData() {
        ArrayList<Long> textId = new ArrayList<>();
        ArrayList<Long> urlId = new ArrayList<>();
        ArrayList<Long> imgId = new ArrayList<>();
        ArrayList<Long> fileId = new ArrayList<>();
        ArrayList<Long> todoId = new ArrayList<>();

        if (memoDataLiveData == null || memoDataLiveData.getValue() == null) {
            textId.add(-1L);
            urlId.add(-1L);
            imgId.add(-1L);
            fileId.add(-1L);
            todoId.add(-1L);

            executor.execute(() -> {
                MemoDataDao dao = Database.getDatabase(getApplication()).memoDataDao();
                textLiveData.postValue(dao.getAllTextById(textId).getValue());
            });
            return;
        }

        for (MemoData data : memoDataLiveData.getValue()) {

            switch (MemoType.getType(data.memoType)) {
                case TODO: {
                    todoId.add(data.id);
                    break;
                }

                case URL: {
                    urlId.add(data.id);
                    break;
                }

                case FILE: {
                    fileId.add(data.id);
                    break;
                }

                case TEXT: {
                    textId.add(data.id);
                    break;
                }

                case IMG: {
                    imgId.add(data.id);
                    break;
                }
            }
        }
        executor.execute(() -> {
            MemoDataDao dao = Database.getDatabase(getApplication()).memoDataDao();
            textLiveData.postValue(dao.getAllTextById(textId).getValue());
        });
    }

    public void insertText(String text) {

    }

    public void insertUrl() {

    }
    public void insertFile() {

    }
    public void insertTodo() {

    }
    public void insertImg() {

    }
}
