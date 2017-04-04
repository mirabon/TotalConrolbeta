package ru.rassvetmedia.totalconrolbeta.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ru.rassvetmedia.totalconrolbeta.R;
import ru.rassvetmedia.totalconrolbeta.db.DataBaseContract;
import ru.rassvetmedia.totalconrolbeta.pojo.Constans;
import ru.rassvetmedia.totalconrolbeta.pojo.StatusAccounts;

/**
 * Created by Vasilij on 02.04.2017.
 */

public class AccountsAdapter extends SimpleCursorAdapter {
    private LayoutInflater inflater;
    private Cursor cursor;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AccountsAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        inflater = LayoutInflater.from(context);
        this.cursor = c;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static final class ViewHolder {
        // CheckBox crdcheckbox;
        TextView nickName;
        ImageView statusAccount;
        int markerNickName, markerStatusAccount;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return super.newView(context, cursor, parent);

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        super.getView(position, convertView, parent);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.nickName = (TextView) convertView.findViewById(R.id.login_text);
            viewHolder.statusAccount = (ImageView) convertView.findViewById(R.id.imageView1);

            viewHolder.markerNickName = this.cursor.getColumnIndexOrThrow(DataBaseContract.AccountsEntry.KEY_NICKNAME);
            viewHolder.markerStatusAccount = this.cursor.getColumnIndexOrThrow(DataBaseContract.AccountsEntry.KEY_ACCOUNT_STATUS);
            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        cursor.moveToPosition(position);
        if(cursor != null && cursor.getCount() > position){
            holder.nickName.setText(cursor.getString(holder.markerNickName));
            holder.statusAccount.setImageResource(new StatusAccounts().getRESOURCE(cursor.getInt(holder.markerStatusAccount)));
        }
        return convertView;
    }
}
