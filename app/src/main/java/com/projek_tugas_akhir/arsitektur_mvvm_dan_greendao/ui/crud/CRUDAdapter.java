package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.ItemViewBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewHolder;

import java.util.List;

public class CRUDAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Medical> medicalList;

    private CRUDAdapterListener listener;

    public CRUDAdapter(List<Medical> medicalList) {
        this.medicalList = medicalList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        switch (viewType) {
//            case VIEW_TYPE_NORMAL:
//                ItemViewBinding itemInsertViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.getContext()),
//                        parent, false);
//                return new InsertViewHolder(itemInsertViewBinding);
//            case VIEW_TYPE_EMPTY:
//            default:
//                ItemEmptyViewBinding emptyInsertViewBinding = ItemEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
//                        parent, false);
//                return new EmptyViewHolder(emptyInsertViewBinding);
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void addItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        medicalList.clear();
    }

    public void setListener(CRUDAdapterListener listener) {
        this.listener = listener;
    }

    public interface CRUDAdapterListener {
        void onRetryClick();
    }

    public class CRUDViewHolder extends BaseViewHolder {
        private ItemViewBinding binding;
        private CRUDViewModel crudViewModel;

        public CRUDViewHolder(View itemView, ItemViewBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Medical medical = medicalList.get(position);
//            crudViewModel = new CRUDViewModel(medical);
//            binding.setViewModel(crudViewModel);
            binding.executePendingBindings();
        }
    }
}
