package com.evertcode.calctip.fragments;

import com.evertcode.calctip.bean.TipRecord;

/**
 * Created by Hebert on 04/06/2016.
 */
public interface TipHistoryListFragmentListener {
    void addToList(final TipRecord record);
    void clearList();
}
