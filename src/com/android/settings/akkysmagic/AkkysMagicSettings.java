package com.android.settings.akkysmagic;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.preference.Preference;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.development.KeyboxDataPreference;
import com.android.settings.development.PifDataPreference;

public class AkkysMagicSettings extends SettingsPreferenceFragment {

    private static final String KEYBOX_DATA_KEY = "keybox_data_setting";
    private static final String PIF_DATA_KEY = "pif_data_setting";

    private ActivityResultLauncher<Intent> mKeyboxFilePickerLauncher;
    private ActivityResultLauncher<Intent> mPifFilePickerLauncher;

    private KeyboxDataPreference mKeyboxDataPreference;
    private PifDataPreference mPifDataPreference;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // === identical lifecycle pattern to DevelopmentSettingsDashboardFragment ===

        mKeyboxFilePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (mKeyboxDataPreference != null) {
                        mKeyboxDataPreference.handleFileSelected(uri);
                    }
                }
            }
        );

        mPifFilePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    if (mPifDataPreference != null) {
                        mPifDataPreference.handleFileSelected(uri);
                    }
                }
            }
        );
    }

    @Override
    public void onViewCreated(android.view.View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // === find preferences after XML inflation ===
        mKeyboxDataPreference = findPreference(KEYBOX_DATA_KEY);
        mPifDataPreference = findPreference(PIF_DATA_KEY);

        // === attach launchers *exactly* like PixelOS Dev Settings ===
        if (mKeyboxDataPreference != null) {
            mKeyboxDataPreference.setFilePickerLauncher(mKeyboxFilePickerLauncher);
        }

        if (mPifDataPreference != null) {
            mPifDataPreference.setFilePickerLauncher(mPifFilePickerLauncher);
        }
    }

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.akkys_magic_settings;
    }

    @Override
    public int getMetricsCategory() {
        return 0;
    }
}
