package app.reyhoon.ir.View.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pixplicity.easyprefs.library.Prefs;

import app.reyhoon.ir.Interface.FinalString;
import app.reyhoon.ir.R;
import app.reyhoon.ir.View.Fragment.LoginFragment;
import app.reyhoon.ir.View.Fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        if (Prefs.getBoolean(FinalString.LOGIN_USER, false)) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace( R.id.fragment_holder, new MainFragment() );
            transaction.commit();

        } else {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace( R.id.fragment_holder, new LoginFragment() );
            transaction.commit();

        }
    }
}
