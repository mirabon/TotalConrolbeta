package ru.rassvetmedia.totalconrolbeta.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import ru.rassvetmedia.totalconrolbeta.R;

/**
 * Created by Vasilij on 22.03.2017.
 */

public class FirstSettiningApp extends DialogFragment {

    private Spinner spinner_social, spinner_game;

    private OnCallBackForResultDialog callback;

    public interface OnCallBackForResultDialog {
        public void isClickPositive(boolean check);
        public void returnDataFromDialogAddAccout(String nickname, String login, String password);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            callback = (OnCallBackForResultDialog) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement OnCallBackForResultDialog");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View form = getActivity().getLayoutInflater().inflate(R.layout.first_setting_app, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        spinner_social = (Spinner) form.findViewById(R.id.spinner_social_type);
        spinner_game = (Spinner) form.findViewById(R.id.spinner_game_type);

        // Настраиваем адаптер
        ArrayAdapter<?> adapter_social =
                ArrayAdapter.createFromResource(
                        getActivity(),
                        R.array.spinner_social_type,
                        android.R.layout.simple_spinner_item);
        adapter_social.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<?> adapter_game = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.spinner_game_type,
                android.R.layout.simple_spinner_item);
        adapter_social.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        // Вызываем адаптер
        spinner_social
                .setAdapter(adapter_social);
        spinner_game
                .setAdapter(adapter_game);

        builder
                .setTitle("Настройка приложения")
                .setView(form)
                .setPositiveButton("Далее", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int selectedItemPositionSpinnerGame = spinner_game.getSelectedItemPosition();
                        int selectedItemPositionSpinnerSocialType = spinner_social.getSelectedItemPosition();
                        final String selected_game = spinner_game.getSelectedItem().toString();
                        final String selected_social = spinner_social.getSelectedItem().toString();

                        callback.isClickPositive(true);

                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.isClickPositive(false);
                        Toast.makeText(getContext(), "Настройка приложения была отменена - повторите снова", Toast.LENGTH_LONG).show();
                    }
                });
        return builder.create();
    }
}
