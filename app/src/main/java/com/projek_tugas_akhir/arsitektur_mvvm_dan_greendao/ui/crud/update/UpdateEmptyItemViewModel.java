package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.update;

public class UpdateEmptyItemViewModel {

    private UpdateEmptyItemViewModelListener listener;

    public UpdateEmptyItemViewModel(UpdateEmptyItemViewModelListener listener) {
        this.listener = listener;
    }

    public void onRetryClick() {
        listener.onRetryClick();
    }

    public interface UpdateEmptyItemViewModelListener {
        void onRetryClick();
    }

}
