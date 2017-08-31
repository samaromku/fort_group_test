package ru.tander.parsejson.interfaces;

import android.content.Context;

/**
 * Created by savchenko on 31.08.17.
 */

public interface OnItemClickListener {
    void onClick(int position);

    Context getContext();

    void updateData();
}
