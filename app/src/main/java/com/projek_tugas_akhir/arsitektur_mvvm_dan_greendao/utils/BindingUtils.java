package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.CRUDAdapter;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {

    }

    @BindingAdapter({"adapterInsert"})
    public static void addInsertItems(RecyclerView recyclerView, List<Medical> medicalList) {
        CRUDAdapter adapter = (CRUDAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(medicalList);
        }
    }

    @BindingAdapter({"adapterSelect"})
    public static void addSelectItems(RecyclerView recyclerView, List<Medical> medicalList) {
        CRUDAdapter adapter = (CRUDAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.selectItems(medicalList);
        }
    }

    @BindingAdapter({"adapterUpdate"})
    public static void addUpdateItems(RecyclerView recyclerView, List<Medical> medicalList) {
        CRUDAdapter adapter = (CRUDAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.updateItems(medicalList);
        }
    }

    @BindingAdapter({"adapterDelete"})
    public static void addDeleteItems(RecyclerView recyclerView, List<Medical> medicalList) {
        CRUDAdapter adapter = (CRUDAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.deleteItems(medicalList);
        }
    }

}
