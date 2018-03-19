package devmob2018.com.aprovadoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import devmob2018.com.aprovadoapp.filter.CustomRangeInputFilter;

public class PrincipalActivity extends AppCompatActivity {


    private CustomRangeInputFilter inputFilter;

    private EditText editNome, editNota1, editNota2;
    private TextView tvNome, tvMedia, tvSituacao;

    private LinearLayout layoutResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        inputFilter = new CustomRangeInputFilter(0f,10.0f);

        editNome = findViewById(R.id.editNome);

        editNota1 = findViewById(R.id.editNota1);
        editNota1.setFilters(new InputFilter[]{inputFilter});

        editNota2 = findViewById(R.id.editNota2);
        editNota2.setFilters(new InputFilter[]{inputFilter});

        tvNome = findViewById(R.id.tvNome);
        tvMedia = findViewById(R.id.tvMedia);
        tvSituacao = findViewById(R.id.tvSituacao);

        layoutResultado = findViewById(R.id.layoutResultado);
    }

    public void calcular(View v){

        if(editNome.getText().length() > 0 && editNota1.getText().length() > 0 && editNota2.length() > 0) {

            try {

                double n1 = Double.parseDouble(editNota1.getText().toString());
                double n2 = Double.parseDouble(editNota2.getText().toString());

                double media = (n1 + n2) / 2;
                String situacao;

                if (media < 7) {
                    situacao = "Reprovado";
                } else {
                    situacao = "Aprovado";
                }

                tvNome.setText(editNome.getText());
                tvMedia.setText(String.valueOf(media));
                tvSituacao.setText(situacao);

                layoutResultado.setVisibility(View.VISIBLE);

            } catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                return;
            }

        } else {
            Toast.makeText(this,"Favor preencher os dados",Toast.LENGTH_LONG).show();
        }

    }

    public void limpar(View v){
        Toast.makeText(this,"Limpando",Toast.LENGTH_LONG).show();

        layoutResultado.setVisibility(View.GONE);

        editNome.setText("");
        editNota1.setText("");
        editNota2.setText("");
        tvNome.setText("");
        tvMedia.setText("");
        tvSituacao.setText("");

    }
}
