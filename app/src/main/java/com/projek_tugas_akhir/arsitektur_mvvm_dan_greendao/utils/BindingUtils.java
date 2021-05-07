package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.data.others.Medical;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.delete.DeleteAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.insert.InsertAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.select.SelectAdapter;
import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.ui.crud.update.UpdateAdapter;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {

    }

    @BindingAdapter({"adapterInsert"})
    public static void addInsertItems(RecyclerView recyclerView, List<Medical> medicalList) {
        InsertAdapter adapter = (InsertAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(medicalList);
        }
    }

    @BindingAdapter({"adapterSelect"})
    public static void addSelectItems(RecyclerView recyclerView, List<Medical> medicalList) {
        SelectAdapter adapter = (SelectAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.selectItems(medicalList);
        }
    }

    @BindingAdapter({"adapterUpdate"})
    public static void addUpdateItems(RecyclerView recyclerView, List<Medical> medicalList) {
        UpdateAdapter adapter = (UpdateAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.updateItems(medicalList);
        }
    }

    @BindingAdapter({"adapterDelete"})
    public static void addDeleteItems(RecyclerView recyclerView, List<Medical> medicalList) {
        DeleteAdapter adapter = (DeleteAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.deleteItems(medicalList);
        }
    }

}
