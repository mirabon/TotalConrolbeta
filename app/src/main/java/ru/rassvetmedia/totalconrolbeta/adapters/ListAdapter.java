package ru.rassvetmedia.totalconrolbeta.adapters;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import ru.rassvetmedia.totalconrolbeta.R;
import ru.rassvetmedia.totalconrolbeta.pojo.AndroidInfo;

public class ListAdapter extends BaseAdapter {
    private ObservableArrayList<AndroidInfo> list;
    private LayoutInflater inflater;

    public ListAdapter(ObservableArrayList<AndroidInfo> l) {
        list = l;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // TODO Auto-generated method stub
        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_item, null);
        //find elements on item/row
        holder.tv = (TextView) rowView.findViewById(R.id.login_text);
        holder.img = (ImageView) rowView.findViewById(R.id.imageView1);
        holder.checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);

        //сопоставляем данные к найденным элементам
        holder.tv.setText(list.get(position).name);
        holder.img.setImageResource(list.get(position).iconResource);
        holder.checkBox.setChecked(list.get(position).checked);

        //вешаем слушатель на чек бокс. обновляем данные
//        holder.checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean isChecked = ((CheckBox) view).isChecked();
//                if (isChecked) {
//                    list.get(position).checked = true;
//                    Log.d("tfa", "--------------");
//                    Toast.makeText(parent.getContext(), "Checked ID: " + position, Toast.LENGTH_SHORT).show();
//                    notifyDataSetChanged();
//                } else {
//                    list.get(position).checked = false;
//                    Toast.makeText(parent.getContext(), "Checked ID: " + position, Toast.LENGTH_SHORT).show();
//                    notifyDataSetChanged();
//                }
//            }
//        });
        return rowView;
    }

    private class Holder {
        TextView tv;
        ImageView img;
        CheckBox checkBox;
    }
}
