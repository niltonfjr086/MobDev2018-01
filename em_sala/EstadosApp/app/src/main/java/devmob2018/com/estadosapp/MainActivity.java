package devmob2018.com.estadosapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import devmob2018.com.estadosapp.entities.Estado;

public class MainActivity extends Activity {


    private EditText editNome, editSigla;

    private ArrayList<Estado> estados;
    private ArrayAdapter<Estado> adapterEstados;

    private ListView listViewEstados;
    private Estado estado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editNome = findViewById(R.id.editNome);
        this.editSigla = findViewById(R.id.editSigla);

        //SELECT DO BANCO
        this.estados = new ArrayList<>();


        this.adapterEstados = new ArrayAdapter<Estado>(this, android.R.layout.simple_list_item_1, this.estados);

        this.listViewEstados = findViewById(R.id.estados);
        this.listViewEstados.setAdapter(this.adapterEstados);


        this.listViewEstados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.this.estado = MainActivity.this.adapterEstados.getItem(position);

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Removendo Estado");
                alerta.setMessage("Deseja remover o estado " + MainActivity.this.estado.getNome());
                alerta.setIcon(android.R.drawable.ic_delete);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //DELETE NO BANCO

                        //DELETE NA lISTA
                        MainActivity.this.adapterEstados.remove(MainActivity.this.estado);
                        MainActivity.this.estado = null;
                    }
                });
                alerta.setNeutralButton("Não", null);
                alerta.show();

                Toast.makeText(MainActivity.this, MainActivity.this.estado.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        this.listViewEstados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.this.estado = MainActivity.this.adapterEstados.getItem(position);

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Editar Estado");
                alerta.setMessage("Deseja editar o estado " + MainActivity.this.estado.getNome());
                alerta.setIcon(android.R.drawable.ic_menu_edit);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //CARREGA
                        MainActivity.this.editNome.setText(MainActivity.this.estado.getNome());
                        MainActivity.this.editSigla.setText(MainActivity.this.estado.getSigla());
                        Toast.makeText(MainActivity.this, "Longo" + MainActivity.this.estado.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                alerta.setNeutralButton("Não", null);
                alerta.show();

                return true;
            }
        });


    }

    public void salvar(View v) {
        Editable nome = this.editNome.getText();
        Editable sigla = this.editSigla.getText();

        if (this.estado == null) {
            //Insert BD

            //INSERT NA LISTA
            this.estado = new Estado();
            if (nome.length() > 0) this.estado.setNome(nome.toString());
            if (sigla.length() > 0) this.estado.setSigla(sigla.toString());
            this.adapterEstados.add(this.estado);


        } else {
            //UPDATE BD

            //UPDATE NA LISTA
            if (nome.length() > 0) this.estado.setNome(nome.toString());

            if (sigla.length() > 0) this.estado.setSigla(sigla.toString());

            this.adapterEstados.notifyDataSetChanged();
        }


        this.editNome.setText("");
        this.editSigla.setText("");

        this.editNome.requestFocus();

        this.estado = null;

    }

    public void limpar(View v) {
        this.editNome.setText("");
        this.editSigla.setText("");

        this.adapterEstados.clear();

        this.editNome.requestFocus();
    }
}
