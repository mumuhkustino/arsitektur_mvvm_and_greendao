package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.db.model.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert.InsertAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select.SelectAdapter;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {

    }

    @BindingAdapter({"adapter"})
    public static void addInsertItems(RecyclerView recyclerView, List<Medical> medicalList) {
        InsertAdapter adapter = (InsertAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(medicalList);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addSelectItems(RecyclerView recyclerView, List<Medical> medicalList) {
        SelectAdapter adapter = (SelectAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(medicalList);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addUpdateItems(RecyclerView recyclerView, List<Medical> medicalList) {
        InsertAdapter adapter = (InsertAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(medicalList);
        }
    }

}
