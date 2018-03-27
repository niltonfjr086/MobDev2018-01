package devmob2018.com.minhasnotasapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import devmob2018.com.minhasnotasapp.entities.Avaliacao;
import devmob2018.com.minhasnotasapp.entities.Disciplina;

public class MainActivity extends Activity {

    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.disciplina = new Disciplina();
        this.disciplina.setAvaliacao1(new Avaliacao());
        this.disciplina.setAvaliacao2(new Avaliacao());

    }

    public void apresentar(View v) {

        try {

            String referencia = "devmob2018.com.minhasnotasapp.";
            referencia += getResources().getResourceEntryName(v.getId());
            Class c = Class.forName(referencia);

            Intent it = new Intent(this, c);
            it.putExtra("disciplina", this.disciplina);
            startActivityForResult(it, 987);

        } catch (ClassNotFoundException e) {

            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Toast.makeText(this, "REQCODE: " + String.valueOf(requestCode) + " | " + "RESCODE: " + String.valueOf(resultCode), Toast.LENGTH_SHORT).show();

        if (resultCode != 0 && data.getExtras() != null) {
            Bundle p = data.getExtras();

//            if (resultCode == 789) {
//                this.pessoa.setNome(p.getString("nome"));
//                this.pessoa.setIdade(p.getInt("idade"));
//
//            } else {
//
//                if (resultCode == 790) {
//                    this.pessoa.setContato((Contato) p.getSerializable("contato"));
//
//                } else {
//
//                    if (resultCode == 791) {
//                        this.pessoa.setEndereco((Endereco) p.getSerializable("endereco"));
//                    }
//                }
//            }

            if (p.getSerializable("disciplina") != null) {
                this.disciplina = (Disciplina) p.getSerializable("disciplina");
            }

//            Toast.makeText(this, this.pessoa.toString(), Toast.LENGTH_LONG).show();
        }


    }

}
