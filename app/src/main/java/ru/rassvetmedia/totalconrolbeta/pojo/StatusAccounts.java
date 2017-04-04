package ru.rassvetmedia.totalconrolbeta.pojo;

import java.util.ArrayList;
import java.util.List;

import ru.rassvetmedia.totalconrolbeta.R;

public class StatusAccounts {
    private List<Integer> integerList = null;

    public StatusAccounts() {
        this.integerList = new ArrayList<>();
    }

    public int getRESOURCE(int i) {

        integerList.add(R.drawable.disconnected_48);
        integerList.add(R.drawable.checkmark_48);
        integerList.add(R.drawable.error_48);
        integerList.add(R.drawable.error_48);//заменить
        integerList.add(R.drawable.disconnected_48);//replace
        integerList.add(R.drawable.error_48);//replace
        return integerList.get(i);
    }

    public int getLenghtStatusList(){
        return this.integerList.size();
    }
}