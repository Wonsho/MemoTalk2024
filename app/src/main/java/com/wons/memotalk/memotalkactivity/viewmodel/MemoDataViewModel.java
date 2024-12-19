package com.wons.memotalk.memotalkactivity.viewmodel;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
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
import com.wons.memotalk.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MemoDataViewModel extends ViewModel {

}
