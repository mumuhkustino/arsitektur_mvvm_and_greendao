package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Long> mBeforeTime;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        mBeforeTime = new MutableLiveData<>();
        mBeforeTime.setValue(System.currentTimeMillis());
    }

    public HomeViewModel(Long beforeTime) {
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Long> getTime() { return mBeforeTime; }

    public LiveData<Long> calculateTime(long beforeTime, long afterTime) {
        mBeforeTime.setValue(beforeTime - afterTime);
        return mBeforeTime;
    }

    public Long calculateTime(long afterTime, long beforeTime, String test) {
        return afterTime - beforeTime;
    }

}