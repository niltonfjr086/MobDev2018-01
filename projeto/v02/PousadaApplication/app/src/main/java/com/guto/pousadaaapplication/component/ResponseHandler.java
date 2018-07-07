package com.guto.pousadaaapplication.component;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.google.gson.Gson;
import com.guto.pousadaapplication.ModuloActivity;
import com.guto.pousadaapplication.NavigationActivity;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

public class ResponseHandler<T> extends AsyncHttpResponseHandler {

    private Gson gson = new Gson();
    private ModuloActivity a;
    private T t;

    public ResponseHandler(ModuloActivity activity, T t) {
        this.a = activity;
        this.t = t;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String s = new String(responseBody);
        List<Object> list = gson.fromJson(s, List.class);
        a.popularListView(list);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(a, "Ocorreu um erro: " + error.toString(), Toast.LENGTH_LONG).show();
        try {
            throw error;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
