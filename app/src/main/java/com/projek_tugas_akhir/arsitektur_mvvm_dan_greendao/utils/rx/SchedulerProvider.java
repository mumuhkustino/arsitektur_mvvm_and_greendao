package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler ui();

    Scheduler io();
}
