package ru.rassvetmedia.totalconrolbeta.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.rassvetmedia.totalconrolbeta.R;

/**
 * Created by Vasilij on 20.03.2017.
 */

public class ReportFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.report_fragment;
    private View rootView;

    public ReportFragment() {

    }

    public static ReportFragment getInstance(Context context) {
        Bundle arg = new Bundle();
        ReportFragment reportFragment = new ReportFragment();
        reportFragment.setArguments(arg);
        reportFragment.setContext(context);
        reportFragment.setTitle(context.getString(R.string.tab_report));
        return reportFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(LAYOUT, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("Нет данных для отображения!");
        return rootView;
    }

    public void setContext(Context mContext) {
        this.context = mContext;
    }
}