package app.bajiru.ir;


import android.app.Application;
import android.content.ContextWrapper;
import android.content.res.Configuration;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.Locale;

public class MainAplication extends Application {
	public static String token;

    @Override
    public void onCreate() {
        super.onCreate();

        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        // Initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }
}
