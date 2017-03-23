package ru.rassvetmedia.totalconrolbeta;

/**
 * Created by Vasilij on 19.03.2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import ru.rassvetmedia.totalconrolbeta.adapters.TabsPagerAdapter;
import ru.rassvetmedia.totalconrolbeta.pojo.Constans;
import ru.rassvetmedia.totalconrolbeta.ui.FirstSettiningApp;

public class MainActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private Context context;
    public ViewPager getViewPager() {
        return viewPager;
    }

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initTabs();

    }

    /**
     * Инициализация табов
     */
    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabsPagerAdapter adapter = new TabsPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }


    }

    /**
     * инициализация тул бара
     */
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Инициализация меню в тулбаре
     * @param menu - меню
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
