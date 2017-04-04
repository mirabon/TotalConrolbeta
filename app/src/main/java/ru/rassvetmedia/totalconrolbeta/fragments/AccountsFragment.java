package ru.rassvetmedia.totalconrolbeta.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import ru.rassvetmedia.totalconrolbeta.R;
import ru.rassvetmedia.totalconrolbeta.db.DataBaseContract;
import ru.rassvetmedia.totalconrolbeta.db.GetSQLiteOpenHelper;
import ru.rassvetmedia.totalconrolbeta.db.impl.OperateAccountsDaoImpl;
import ru.rassvetmedia.totalconrolbeta.model.SharedPreferense;
import ru.rassvetmedia.totalconrolbeta.model.WriteSettingApp;
import ru.rassvetmedia.totalconrolbeta.pojo.Constans;
import ru.rassvetmedia.totalconrolbeta.pojo.StatusAccounts;
import ru.rassvetmedia.totalconrolbeta.ui.DialogAddAccount;
import ru.rassvetmedia.totalconrolbeta.ui.FirstSettiningApp;
import ru.rassvetmedia.totalconrolbeta.viewmodels.AndroidListAccountsViewModel;

/**
 * Created by Vasilij on 19.03.2017.
 */

public class AccountsFragment extends AbstractTabFragment implements
        LoaderManager.LoaderCallbacks<Cursor>,
        FirstSettiningApp.OnCallBackForResultDialog,
        DialogAddAccount.OnCallBackForResultDialog {

    private static final int LAYOUT = R.layout.accounts_fragment;
    private AndroidListAccountsViewModel infos;

    private String[] from = new String[]{
            DataBaseContract.AccountsEntry.KEY_ACCOUNT_STATUS,
            DataBaseContract.AccountsEntry.KEY_NICKNAME
    };

    private int[] to = new int[]{
            R.id.imageView1,
            R.id.login_text
    };

    private SQLiteOpenHelper db;
    private FloatingActionButton fab;
    private SimpleCursorAdapter scAdapter;
    private Loader<Cursor> cursorLoader;

    public AccountsFragment() {
    }

    public static AccountsFragment getInstance(Context context) {
        Bundle arg = new Bundle();
        AccountsFragment accountsFragment = new AccountsFragment();
        accountsFragment.setArguments(arg);
        accountsFragment.setContext(context);
        accountsFragment.setTitle(context.getString(R.string.tab_accouns));
        return accountsFragment;
    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constans.LOG_TAG, "AccountsFragment onCreate");
        if (this.context == getContext().getApplicationContext()) {
            Log.d(Constans.LOG_TAG, "AccountsFragment context is равно");
        }
        if (this.context == null) {
            this.context = getContext().getApplicationContext();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(LAYOUT, container, false);

        db = GetSQLiteOpenHelper.getHelperInstance(this.context);
        db.getWritableDatabase();
        cursorLoader = getLoaderManager().initLoader(0, null, this);

        ListView lv = (ListView) rootView.findViewById(R.id.listView);
        scAdapter = new SimpleCursorAdapter(this.context, R.layout.list_item, null, from, to, 0);
        scAdapter.setViewBinder(new CustomViewBinder());
        lv.setAdapter(scAdapter);

        infos = new AndroidListAccountsViewModel(this.context);
        infos
                .setLv(lv)
                .setTargetFragment(this);

        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View rootView) {

                SharedPreferense data = new SharedPreferense(Constans.SETTINGS_APP_NAME);
                data.init(getActivity());

                if (data.getAllPreferenses().size() > 1) {
                    openDialogAddAccout();
                } else {
                    openDialogFirstSettingsApp();
                }
            }
        });

        return rootView;
    }

    private void openDialogFirstSettingsApp() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FirstSettiningApp firstSettining = new FirstSettiningApp();
        firstSettining.setTargetFragment(this, 0);
        firstSettining.show(fm, "first_setting_app");
    }

    private void openDialogAddAccout() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        DialogAddAccount dAddAccout = new DialogAddAccount();
        dAddAccout.setTargetFragment(this, 0);
        dAddAccout.show(fm, "first_setting_app");
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        infos.registeredListenersOnLongClickItem();
        infos.registeredListenersOnCheckBoxClick();
    }

    public class CustomViewBinder implements SimpleCursorAdapter.ViewBinder {
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {

            if (view.getId() == R.id.imageView1) {
                ImageView iv = (ImageView) view;
                int value_status = cursor.getInt(columnIndex);
                iv.setImageResource(new StatusAccounts().getRESOURCE(value_status));
                return true;
            }

            return false;
        }
    }

    public Loader<Cursor> getCursorLoader() {
        return cursorLoader;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Реализация колбека для возврата результата или данных работы с диалогом настройки приложения.
     *
     * @param c
     */
    @Override
    public void isClickPositiveButtonInDialogPreferences(boolean c, String social_type, String game_type) {
        Toast.makeText(getActivity(), Boolean.toString(c), Toast.LENGTH_LONG).show();
        if (c) {
            WriteSettingApp writeSettingApp = new WriteSettingApp(context);
            writeSettingApp.setData(social_type, game_type);
            /*
              Открываем диалоговое окно для ввода данных аккаунта
             */
            openDialogAddAccout();
        }
    }

    @Override
    public void returnDataFromDialogAddAccount(String nickname, String login, String password) {
        /*
        Добавляем аккаунт на основании введенных данных в диалоговое окно класса DialogAddAccount
         */
        OperateAccountsDaoImpl o = new OperateAccountsDaoImpl();
        long result = o.addAccountFromDialog(getActivity(), nickname, login, password);

        if (result > 0) {
            Toast.makeText(getActivity(), "Добавлено", Toast.LENGTH_LONG).show();
            cursorLoader.onContentChanged();
        } else {
            Toast.makeText(getActivity(), "Ошибка", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        cursorLoader = new MyCursorLoader(getActivity());
        return cursorLoader;
    }

    /**
     * @param loader The Loader that has finished.
     * @param data   The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        scAdapter.swapCursor(data);
    }

    /**
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }

    static class MyCursorLoader extends CursorLoader {
        OperateAccountsDaoImpl dbOperation;

        public MyCursorLoader(Context context) {
            super(context);
            dbOperation = new OperateAccountsDaoImpl();
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = dbOperation.getAllAccounts(getContext());
            return cursor;
        }
    }
}
