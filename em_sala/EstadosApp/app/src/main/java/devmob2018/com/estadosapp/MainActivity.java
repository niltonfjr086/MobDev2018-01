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
    Estado estado = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editNome = findViewById(R.id.editNome);
        this.editSigla = findViewById(R.id.editSigla);

        //SELECT DO BANCO
        this.estados = new ArrayList<>();


        this.adapterEstados = new ArrayAdapter<Estado>(this, android.R.layout.simple_list_item_1, estados);

        this.listViewEstados = findViewById(R.id.estados);
        this.listViewEstados.setAdapter(this.adapterEstados);


        this.listViewEstados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                estado = adapterEstados.getItem(position);

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Removendo Estado");
                alerta.setMessage("Deseja remover o estado " + estado.getNome());
                alerta.setIcon(android.R.drawable.ic_delete);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //DELETE NO BANCO

                        //DELETE NA lISTA
                        adapterEstados.remove(estado);

                    }
                });
                alerta.setNeutralButton("Não", null);
                alerta.show();

                Toast.makeText(MainActivity.this, estado.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        this.listViewEstados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                estado= adapterEstados.getItem(position);
                editNome.setText(estado.getNome());
                editSigla.setText(estado.getSigla());
                Toast.makeText(MainActivity.this, "Longo"+estado.toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });



    }

    public void salvar(View v) {
        Editable nome = this.editNome.getText();
        Editable sigla = this.editSigla.getText();

        if(estado == null) {
            //Insert BD

            //INSERT NA LISTA
            estado = new Estado();
            if (nome.length() > 0) estado.setNome(nome.toString());
            if (sigla.length() > 0) estado.setSigla(sigla.toString());
            this.adapterEstados.add(estado);


        }else{
            //UPDATE BD

            //UPDATE NA LISTA
            if (nome.length() > 0) estado.setNome(nome.toString());
            if (sigla.length() > 0) estado.setSigla(sigla.toString());
            this.adapterEstados.notifyDataSetChanged();
        }


        this.editNome.setText("");
        this.editSigla.setText("");

        this.editNome.requestFocus();

        estado = null;

    }

    public void limpar(View v) {
        this.editNome.setText("");
        this.editSigla.setText("");

        this.adapterEstados.clear();

        this.editNome.requestFocus();
    }
}
