package devmob2018.com.comandaapp.component;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import devmob2018.com.comandaapp.controller.MainActivity;
import devmob2018.com.comandaapp.model.entity.Produto;

public class ResponseHandler<T> extends AsyncHttpResponseHandler {

    private Gson gson = new Gson();
    private Activity a;
    private T t = null;
    private Boolean isList;

    public ResponseHandler(Activity a, T t) {
        this.a = a;
        this.t = t;
    }

    public ResponseHandler(Activity a, T t, Boolean isList) {
        this.a = a;
        this.t = t;
        this.isList = isList;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        String s = new String(responseBody);

        this.setTag(s);

/*
        Toast.makeText(a, s, Toast.LENGTH_LONG).show();
*/

        List<T> list = new ArrayList<>();

        List<Object> voidList = gson.fromJson(s, List.class);
        for (Object o : voidList) {
            T ot = (T) gson.fromJson(gson.toJson(o), t.getClass());
            list.add(ot);
        }
/*
        AlertDialog.Builder a = new AlertDialog.Builder(this.a);
        a.setMessage(list.toString());
        a.show();
*/
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        Toast.makeText(a, "Ocorreu um erro: " + responseBody, Toast.LENGTH_LONG);
    }
}
