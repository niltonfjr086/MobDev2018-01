package devmob2018.com.pesquisaapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public abstract class BaseActivity extends Activity {

    protected StringBuilder caller;
    private int resultCode;

//    private static HashMap<String, Object> extras;

    public BaseActivity(int resultCode) {
        this.caller = new StringBuilder();
        this.resultCode = resultCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.caller.append(this.getCallingActivity().getClassName())
                .delete(0, (this.caller.lastIndexOf(".") + 1));

//        Toast.makeText(this, this.caller.toString(), Toast.LENGTH_LONG).show();
    }

    public void salvar(View v) {

        if (this.todosPreenchidos()) {
            this.prepararResult();
        } else {
            this.confirmar();
        }

    }

    public void retornar(View v) {
        this.onBackPressed();
    }

    private void confirmar() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.confirm_alert_title)
                .setMessage(R.string.confirm_alert)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        prepararResult();
                    }
                })
                .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(BaseActivity.this, "Preencha os dados corretamente", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    private void prepararResult() {
//        Class c;
        Intent it;
        try {
//            c = Class.forName(this.caller.toString());
            it = new Intent(this, PrincipalActivity.class);

            this.implementarExtras(it);

            setResult(this.resultCode, it);

            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected abstract void implementarExtras(Intent it);

    protected abstract boolean todosPreenchidos();
}
