package ru.rassvetmedia.totalconrolbeta.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import ru.rassvetmedia.totalconrolbeta.R;

/**
 * Created by Vasilij on 27.03.2017.
 */

public class DialogAddAccount extends DialogFragment {
    private OnCallBackForResultDialog callback;
    public interface OnCallBackForResultDialog {
        public void returnDataFromDialogAddAccout(String nickname, String login, String password);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (DialogAddAccount.OnCallBackForResultDialog) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnCallBackForResultDialog");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View loginForm = getActivity().getLayoutInflater().inflate(R.layout.loginform, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder
                .setTitle("Форма авторизации")
                .setView(loginForm)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText nicknameBox = (EditText) loginForm.findViewById(R.id.nickname);
                        EditText loginBox = (EditText) loginForm.findViewById(R.id.login);
                        EditText passwordBox = (EditText) loginForm.findViewById(R.id.password);
                        String login = loginBox.getText().toString();
                        String password = passwordBox.getText().toString();
                        String nickname = nicknameBox.getText().toString();
                        callback.returnDataFromDialogAddAccout(nickname, login, password);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setNeutralButton(R.string.clear, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create();
        return builder.create();
    }
}
