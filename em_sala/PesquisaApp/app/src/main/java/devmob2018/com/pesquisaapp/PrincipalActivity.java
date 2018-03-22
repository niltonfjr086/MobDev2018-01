package devmob2018.com.pesquisaapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import devmob2018.com.pesquisaapp.entities.Pessoa;

public class PrincipalActivity extends Activity {

    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        setTitle(getString(R.string.app_name) + " | " + getString(R.string.mainActivityName));

        this.pessoa = new Pessoa();
    }

    public void chamar(View v) {

        try {

            String referencia = "devmob2018.com.pesquisaapp.";
            referencia += getResources().getResourceEntryName(v.getId());
            Class c = Class.forName(referencia);

            Intent it = new Intent(this, c);
            startActivityForResult(it, 500);

        } catch (ClassNotFoundException e) {

            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle p = data.getExtras();

        String caller = p.getString("caller");
        if(caller.equalsIgnoreCase("Resultado")){
            finish();
        }

    }


    private static void confirmar() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.confirm_alert_title)
                .setMessage(R.string.confirm_alert)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent it = new Intent(Disciplina2Activity.this, ResultadoActivity.class);
//                        it.putExtra("d1", d1);
//                        it.putExtra("d2", d2);
//                        startActivityForResult(it, 1);
                    }
                })
                .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(Disciplina2Activity.this, "Preencha as notas corretamente", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }
}
