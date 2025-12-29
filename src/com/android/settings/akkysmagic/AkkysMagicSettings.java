package com.android.settings.akkysmagic;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;
import com.android.internal.logging.nano.MetricsProto;

public class AkkysMagicSettings extends SettingsPreferenceFragment {

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.akkys_magic_settings;
    }

    @Override
    public int getMetricsCategory() {
        return 0;
    }
}
