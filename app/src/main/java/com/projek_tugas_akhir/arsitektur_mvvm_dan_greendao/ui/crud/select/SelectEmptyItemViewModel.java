package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select;

public class SelectEmptyItemViewModel {

    private SelectEmptyItemViewModelListener listener;

    public SelectEmptyItemViewModel(SelectEmptyItemViewModelListener listener) {
        this.listener = listener;
    }

    public void onRetryClick() {
        listener.onRetryClick();
    }

    public interface SelectEmptyItemViewModelListener {
        void onRetryClick();
    }

}
