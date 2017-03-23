package ru.rassvetmedia.totalconrolbeta.viewmodels;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import ru.rassvetmedia.totalconrolbeta.R;
import ru.rassvetmedia.totalconrolbeta.fragments.AccountsFragment;
import ru.rassvetmedia.totalconrolbeta.pojo.AndroidInfo;
import ru.rassvetmedia.totalconrolbeta.pojo.Constans;


public class AndroidListAccountsViewModel {

    public ObservableArrayList<AndroidInfo> list = new ObservableArrayList<>();
    private int mTotalCount;
    private Context context;
    private ListView lv;


    public AndroidListAccountsViewModel(Context context) {
        for (mTotalCount = 1; mTotalCount < 11; ++mTotalCount) {
            add(new AndroidInfo(android.R.drawable.ic_lock_idle_low_battery, "icon_" + (mTotalCount), false));
        }
        this.context = context;
//        registeredListenersOnLongClickItem();
//        registeredListenersOnCheckBoxClick();
    }


    public boolean registeredListenersOnLongClickItem() {
        this.lv.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                initContextMenu(contextMenu);
            }

            private MenuItem.OnMenuItemClickListener mMenuItemClickListener = new MenuItem.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    int listPosition = info.position;
                    switch (item.getItemId()) {
                        case 0:
                            add();
                            Log.i(Constans.LOG_TAG, String.valueOf(listPosition));
                            return true;

                        case 1:
                            remove(listPosition);
                            Log.i(Constans.LOG_TAG, String.valueOf(listPosition));
                            return true;

                        case 2:
                            Toast.makeText(context, "Checked ID: " + String.valueOf(listPosition), Toast.LENGTH_SHORT).show();
                            Log.i(Constans.LOG_TAG, String.valueOf(listPosition));
                            return true;
                    }

                    return false;
                }
            };

            private void initContextMenu(ContextMenu contextMenu) {
                contextMenu.setHeaderTitle("Опции аккаунта");
                contextMenu.add(0, 0, 0, "Добавить").setOnMenuItemClickListener(mMenuItemClickListener);
                contextMenu.add(0, 1, 0, "Удалить").setOnMenuItemClickListener(mMenuItemClickListener);
                contextMenu.add(0, 2, 0, "cancel action").setOnMenuItemClickListener(mMenuItemClickListener);
            }
        });

        this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                Log.i(Constans.LOG_TAG, String.valueOf(i));
                if (checkBox.isChecked()){
                    checkBox.setChecked(false);
                    list.get(i).checked = false;
                } else{
                    checkBox.setChecked(true);
                    list.get(i).checked = true;
                }
            }
        });
        return true;
    }

    public boolean registeredListenersOnCheckBoxClick() {
        //Log.d("tfa", "Size listView: " + String.valueOf(this.lv.getCount()));
        for (int i = 0; this.lv.getCount() > 0; i++) {
            View view = this.lv.getChildAt(i);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            final int finalI = i;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Checked ID: " + finalI, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return true;
    }

    public AndroidListAccountsViewModel setContext(Context mContext) {
        this.context = mContext;
        return this;
    }

    public AndroidListAccountsViewModel setLv(ListView lv) {
        this.lv = lv;
        return this;
    }

    //@android:drawable/btn_star_big_on
    public void add(View v) {
        list.add(new AndroidInfo(android.R.drawable.btn_star_big_on, "icon_" + mTotalCount++, false));
    }

    public void add() {
        list.add(new AndroidInfo(android.R.drawable.checkbox_on_background, "icon_" + mTotalCount++, false));
    }

    public void remove(View v) {
        if (!list.isEmpty()) {
            list.remove(0);
        }
    }

    public void remove(int listPosition) {
        if (!list.isEmpty()) {
            list.remove(listPosition);
        }
    }

    private void add(AndroidInfo info) {
        list.add(info);
    }

}
