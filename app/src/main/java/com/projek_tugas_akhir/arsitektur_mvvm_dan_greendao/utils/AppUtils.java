package com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.projek_tugas_akhir.arsitektur_mvvm_dan_greendao.R;

public final class AppUtils {

    public AppUtils() {

    }

    public static void openPlayStoreForApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                    .getResources()
                    .getString(R.string.app_market_link) + appPackageName)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                    .getResources()
                    .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }
}
