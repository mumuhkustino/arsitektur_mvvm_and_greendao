package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

public class CRUDEmptyItemViewModel {

    private CRUDEmptyItemViewModelListener listener;

    public CRUDEmptyItemViewModel(CRUDEmptyItemViewModelListener listener) {
        this.listener = listener;
    }

    public void onRetryClick() {
        listener.onRetryClick();
    }

    public interface CRUDEmptyItemViewModelListener {
        void onRetryClick();
    }

}
