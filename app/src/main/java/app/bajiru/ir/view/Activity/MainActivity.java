package app.bajiru.ir.view.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pixplicity.easyprefs.library.Prefs;

import app.bajiru.ir.StaticFields;
import app.bajiru.ir.adapter.DrawerAdapter;
import app.bajiru.ir.appInterface.ClickListener;
import app.bajiru.ir.R;
import app.bajiru.ir.view.Fragment.CustomerFragment;
import app.bajiru.ir.view.Fragment.LoginFragment;
import app.bajiru.ir.view.Fragment.MainFragment;

public class MainActivity extends AppCompatActivity implements ClickListener {
    public static final int ORDER = 1;
    public static final int REPORT = 2;
    public static final int CUSTOMER = 3;
    public static final int ITEM = 4;
    public static final int AGENT = 5;
    public static final int USER = 6;
    public static final int SETTING = 7;

    public int menuPosition = -1;

    private Context context;
    private Toolbar toolbar;
    private RecyclerView navigation;
    private DrawerLayout drawerLayout;
    private DrawerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigation = (RecyclerView) findViewById(R.id.navigation_view);
        navigation.setHasFixedSize(true);
        adapter = new DrawerAdapter(this, this);
        navigation.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(this);
        navigation.setLayoutManager(layoutManager);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open, R.string.close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (Prefs.getBoolean(StaticFields.LOGIN_USER, false)) {
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

    @Override
    public void onClickItem(View v, int position) {
        selectItem(position);
    }

    public void selectItem(int position) {
        switch (position) {

            case ORDER:
                if (menuPosition != ORDER) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, new MainFragment())
                            .addToBackStack(null)
                            .commit();
                    menuPosition = ORDER;
                }
                break;

            case REPORT:
                if (menuPosition != REPORT) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, new MainFragment())
                            .addToBackStack(null)
                            .commit();
                    menuPosition = REPORT;
                }
                break;

            case CUSTOMER:
                if (menuPosition != CUSTOMER) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, new CustomerFragment())
                            .addToBackStack(null)
                            .commit();
                    menuPosition = CUSTOMER;
                }
                break;

            case ITEM:
                if (menuPosition != ITEM) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, new MainFragment())
                            .addToBackStack(null)
                            .commit();
                    menuPosition = ITEM;
                }
                break;

            case AGENT:
                if (menuPosition != AGENT) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, new MainFragment())
                            .addToBackStack(null)
                            .commit();
                    menuPosition = AGENT;
                }
                break;

            case USER:
                if (menuPosition != USER) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, new MainFragment())
                            .addToBackStack(null)
                            .commit();
                    menuPosition = USER;
                }
                break;

            case SETTING:
                if (menuPosition != SETTING) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, new MainFragment())
                            .addToBackStack(null)
                            .commit();
                    menuPosition = SETTING;
                }
                break;

        }
        drawerLayout.closeDrawers();
    }
}
