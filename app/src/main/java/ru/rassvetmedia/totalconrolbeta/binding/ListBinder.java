package ru.rassvetmedia.totalconrolbeta.binding;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import ru.rassvetmedia.totalconrolbeta.adapters.ListAdapter;
import ru.rassvetmedia.totalconrolbeta.pojo.AndroidInfo;
import ru.rassvetmedia.totalconrolbeta.viewmodels.AndroidListAccountsViewModel;

public class ListBinder {

    @BindingAdapter("bind:imageRes")
    public static void bindImage(ImageView view, int r) {
        view.setImageResource(r);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @BindingAdapter("bind:items")
    public static void bindList(final ListView view, ObservableArrayList<AndroidInfo> list) {
        ListAdapter adapter = new ListAdapter(list);
        view.setAdapter(adapter);
    }

}
