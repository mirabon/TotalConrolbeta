package ru.rassvetmedia.totalconrolbeta.pojo;

/**
 * модель данных списка аккаунтов со статусами, описанием состояния и набора поле состояния
 */
public class AndroidInfo {
    public int iconResource;
    public String name;
    public boolean checked;

    public AndroidInfo(int r, String n, boolean c) {
        iconResource = r;
        name = n;
        checked = c;
    }
}
