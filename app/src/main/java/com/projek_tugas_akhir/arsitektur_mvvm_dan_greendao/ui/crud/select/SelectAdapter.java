package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.ItemEmptyViewBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.ItemViewBinding;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.base.BaseViewHolder;

import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Medical> medicalList;

    private SelectAdapterListener listener;

    public SelectAdapter(List<Medical> medicalList) {
        this.medicalList = medicalList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        switch (viewType) {
//            case VIEW_TYPE_NORMAL:
//                ItemViewBinding itemInsertViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.getContext()),
//                        parent, false);
//                return new SelectViewHolder(itemInsertViewBinding);
//            case VIEW_TYPE_EMPTY:
//            default:
//                ItemEmptyViewBinding emptyInsertViewBinding = ItemEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
//                        parent, false);
//                return new EmptyViewHolder(emptyInsertViewBinding);
//        }
        return null;
    }

    public void addItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        medicalList.clear();
    }

    public void setListener(SelectAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (medicalList != null && medicalList.size() > 0) {
            return medicalList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (medicalList != null && !medicalList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public interface SelectAdapterListener {
        void onRetryClick();
    }

    public class SelectViewHolder extends BaseViewHolder {
        private ItemViewBinding binding;

        private SelectItemViewModel itemViewModel;

        public SelectViewHolder(ItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Medical medical = medicalList.get(position);
//            itemViewModel = new SelectItemViewModel(medical);
//            binding.setViewModel(itemViewModel);
            binding.executePendingBindings();
        }
    }

//    public class EmptyViewHolder extends BaseViewHolder implements InsertEmptyItemViewModel.InsertEmptyItemViewModelListener {
//
//        private ItemEmptyViewBinding binding;
//
//        public EmptyViewHolder(ItemEmptyViewBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//
//        @Override
//        public void onBind(int position) {
//            SelectEmptyItemViewModel emptyItemViewModel = new InsertEmptyItemViewModel(this);
//            binding.setViewModel(emptyItemViewModel);
//        }
//
//        @Override
//        public void onRetryClick() {
//            listener.onRetryClick();
//        }
//    }
}
