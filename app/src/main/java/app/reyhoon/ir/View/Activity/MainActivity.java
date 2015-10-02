package app.reyhoon.ir.View.Activity;

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

import app.reyhoon.ir.Adapter.DrawerAdapter;
import app.reyhoon.ir.Interface.ClickListener;
import app.reyhoon.ir.Interface.FinalString;
import app.reyhoon.ir.R;
import app.reyhoon.ir.View.Fragment.LoginFragment;
import app.reyhoon.ir.View.Fragment.MainFragment;

public class MainActivity extends AppCompatActivity implements ClickListener {
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

    @Override
    public void onClickItem(View v, int position) {

    }
}
