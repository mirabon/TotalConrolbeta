package ru.rassvetmedia.totalconrolbeta.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Vasilij on 20.03.2017.
 */

public abstract class AbstractTabFragment extends Fragment {
    private String title;
    protected Context context;
    protected View view;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
