package com.feicuiedu.gitdroid.utils;

import java.util.List;

/**
 * Created by TJ on 2016/8/2.
 */
public class UsersResult {
    private int total_count;
    private boolean incomplete_results;
    private List<User> items;

    public int getTotal_count() {
        return total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public List<User> getItems() {
        return items;
    }

}
