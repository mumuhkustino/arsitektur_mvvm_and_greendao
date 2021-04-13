package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.delete;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Hospital;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewHolder;

import java.util.List;

public class DeleteAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Hospital> hospitalList;

    public DeleteAdapter(List<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (hospitalList != null && hospitalList.size() > 0) {
            return hospitalList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hospitalList != null && !hospitalList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }


}
