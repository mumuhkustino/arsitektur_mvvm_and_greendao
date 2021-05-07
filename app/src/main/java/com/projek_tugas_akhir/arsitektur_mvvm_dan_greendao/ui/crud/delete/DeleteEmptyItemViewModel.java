package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.delete;

public class DeleteEmptyItemViewModel {

    private DeleteEmptyItemViewModelListener listener;

    public DeleteEmptyItemViewModel(DeleteEmptyItemViewModelListener listener) {
        this.listener = listener;
    }

    public void onRetryClick() {
        listener.onRetryClick();
    }

    public interface DeleteEmptyItemViewModelListener {
        void onRetryClick();
    }

}
