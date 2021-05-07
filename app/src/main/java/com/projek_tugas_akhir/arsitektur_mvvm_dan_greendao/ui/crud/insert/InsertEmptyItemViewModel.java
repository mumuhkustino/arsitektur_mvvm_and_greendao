package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert;

public class InsertEmptyItemViewModel {

    private InsertEmptyItemViewModelListener listener;

    public InsertEmptyItemViewModel(InsertEmptyItemViewModelListener listener) {
        this.listener = listener;
    }

    public void onRetryClick() {
        listener.onRetryClick();
    }

    public interface InsertEmptyItemViewModelListener {
        void onRetryClick();
    }

}
