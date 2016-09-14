package util;

import com.google.gson.Gson;

import java.util.ArrayList;

public final class MyArrayList<E> extends ArrayList<E> {
    public String toJson() {
        return new Gson().toJson(this);
    }
}
