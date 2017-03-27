package ru.rassvetmedia.totalconrolbeta.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import ru.rassvetmedia.totalconrolbeta.R;
import ru.rassvetmedia.totalconrolbeta.databinding.AccountsFragmentBinding;
import ru.rassvetmedia.totalconrolbeta.db.DataBaseContract;
import ru.rassvetmedia.totalconrolbeta.db.impl.OperateAccountsDaoImpl;
import ru.rassvetmedia.totalconrolbeta.pojo.Constans;
import ru.rassvetmedia.totalconrolbeta.ui.DialogAddAccount;
import ru.rassvetmedia.totalconrolbeta.ui.FirstSettiningApp;
import ru.rassvetmedia.totalconrolbeta.viewmodels.AndroidListAccountsViewModel;

/**
 * Created by Vasilij on 19.03.2017.
 */

public class AccountsFragment extends AbstractTabFragment implements View.OnClickListener, FirstSettiningApp.OnCallBackForResultDialog, DialogAddAccount.OnCallBackForResultDialog {
    private static final int LAYOUT = R.layout.accounts_fragment;

    private AndroidListAccountsViewModel infos;
    private String[] from = new String[]{DataBaseContract.AccountsEntry.KEY_NICKNAME};
    private int[] to = new int[]{R.id.login_text};
    private FloatingActionButton fab;
    private SimpleCursorAdapter scAdapter;
    private Spinner spinner_social, spinner_game;
    private AccountsFragmentBinding accountsFragmentBinding;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        accountsFragmentBinding = DataBindingUtil.inflate(inflater, LAYOUT, container, false);
        view = accountsFragmentBinding.getRoot();
//        infos = new AndroidListAccountsViewModel(context);
//        accountsFragmentBinding.setInfos(infos);
        ListView lv = (ListView) view.findViewById(R.id.listView);
//        infos.setLv(lv).setContext(context);
        scAdapter = new SimpleCursorAdapter(context, R.layout.list_item, null, from, to, 0);
        lv.setAdapter(scAdapter);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View rootView) {
                Log.d(Constans.LOG_TAG, "FloatingActionButton onClick");
                openDialogFirstSettingsApp();
            }


        });
        return view;
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

    @Override
    public void onResume() {
        super.onResume();
//        infos.registeredListenersOnLongClickItem();
//        infos.registeredListenersOnCheckBoxClick();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "Hello World", Toast.LENGTH_LONG).show();
    }

    public void setContext(Context mContext) {
        this.context = mContext;
    }

    /**
     * Реализация колбека для возврата результата или данных работы с диалогом настройки приложения.
     *
     * @param c
     */
    @Override
    public void isClickPositive(boolean c) {
        Toast.makeText(getActivity(), Boolean.toString(c), Toast.LENGTH_LONG).show();
        if (c) {
            /*
              Открываем диалоговое окно для ввода данных аккаунта
             */
            openDialogAddAccout();

        }
    }

    @Override
    public void returnDataFromDialogAddAccout(String nickname, String login, String password) {
            OperateAccountsDaoImpl o = new OperateAccountsDaoImpl();
            long result = o.addAccountFromDialog(getActivity(), nickname, login, password);
            if (result > 0) {
                Toast.makeText(getActivity(), "Добавлено", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Ошибка", Toast.LENGTH_LONG).show();
            }
    }
}