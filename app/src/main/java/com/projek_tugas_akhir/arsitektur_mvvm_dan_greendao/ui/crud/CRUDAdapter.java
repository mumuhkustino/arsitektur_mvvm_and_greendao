package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.databinding.ItemEmptyViewBinding;
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
        Log.d("CRUDA", "CRUDAdapter: " + medicalList.size());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemViewBinding itemInsertViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new CRUDViewHolder(itemInsertViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemEmptyViewBinding emptyInsertViewBinding = ItemEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new CRUDEmptyViewHolder(emptyInsertViewBinding);
        }
    }

    public void addItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
        Log.d("CRUDA", "addItems: " + medicalList.size());
    }

    public void selectItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
        Log.d("CRUDA", "selectItems: " + medicalList.size());
    }

    public void deleteItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
        Log.d("CRUDA", "deleteItems: " + medicalList.size());
    }

//    public void deleteItem(List<Medical> medicalList) {
//        this.medicalList.addAll(medicalList);
//        notifyDataSetChanged();
//        Log.d("CRUDA", "deleteItem: " + medicalList.size());
//    }

    public void updateItems(List<Medical> medicalList) {
        this.medicalList.addAll(medicalList);
        notifyDataSetChanged();
        Log.d("CRUDA", "updateItems: " + medicalList.size());
    }

//    public void updateItem(List<Medical> medicalList) {
//        this.medicalList.addAll(medicalList);
//        notifyDataSetChanged();
//        Log.d("CRUDA", "updateItem: " + medicalList.size());
//    }

    public void clearItems() {
        this.medicalList.clear();
    }

    public void setListener(CRUDAdapterListener listener) {
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
        if (medicalList != null && medicalList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public interface CRUDAdapterListener {
        void onRetryClick();
    }

    public class CRUDViewHolder extends BaseViewHolder {
        private ItemViewBinding binding;

        private CRUDItemViewModel itemViewModel;

        public CRUDViewHolder(ItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            final Medical medical = medicalList.get(position);
            itemViewModel = new CRUDItemViewModel((long) position, medical);
            binding.setViewModel(itemViewModel);
            binding.executePendingBindings();
        }
    }

    public class CRUDEmptyViewHolder extends BaseViewHolder implements CRUDEmptyItemViewModel.CRUDEmptyItemViewModelListener {

        private ItemEmptyViewBinding binding;

        public CRUDEmptyViewHolder(ItemEmptyViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            CRUDEmptyItemViewModel emptyItemViewModel = new CRUDEmptyItemViewModel(this);
            binding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            listener.onRetryClick();
//            binding.tvMessage.setText("The fetching process was successful");
//            binding.btnRetry.setVisibility(View.GONE);
        }
    }
}
