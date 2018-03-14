package devmob2018.com.precomuitojustoapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout masterLayout;

    private EditText editNome, editValor, editQtdParcelas, editJuros;
    private TextView tvNome, tvValorInicial, tvValorParcelas, tvValorTotal, tvTotalJuros;
    private LinearLayout layoutResultado;

    private CheckBox comEntrada;

    private DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.masterLayout = findViewById(R.id.masterLayout);

        this.editNome = findViewById(R.id.editNome);
        this.editValor = findViewById(R.id.editValor);
        this.editQtdParcelas = findViewById(R.id.editQtdParcelas);
        this.editJuros = findViewById(R.id.editJuros);

        this.tvNome = findViewById(R.id.tvNome);
        this.tvValorInicial = findViewById(R.id.tvValorInicial);
        this.tvValorParcelas = findViewById(R.id.tvValorParcelas);
        this.tvValorTotal = findViewById(R.id.tvValorTotal);
        this.tvTotalJuros = findViewById(R.id.tvTotalJuros);

        this.layoutResultado = findViewById(R.id.layoutResultado);

        this.comEntrada = findViewById(R.id.ckComEntrada);

        this.df = new DecimalFormat("R$ " + "#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    }


    public void calcular(View v) {

        try {
            Toast.makeText(this, String.valueOf(this.comEntrada.isChecked()), Toast.LENGTH_LONG).show();

            double valorInicial = Double.valueOf(this.editValor.getText().toString());
            int qtdParcelas = Integer.valueOf(this.editQtdParcelas.getText().toString());
            float juros = Float.valueOf(this.editJuros.getText().toString());

            if (this.comEntrada.isChecked()) {
                juros = (juros * 0.95f);
            }

            double valorTotal = valorInicial * (1 + (juros / 100));
            double totalJuros = valorTotal - valorInicial;
            double valorParcelas = valorTotal / qtdParcelas;

            this.tvNome.setText(this.editNome.getText());
            this.tvValorInicial.setText(df.format(valorInicial));
            this.tvValorParcelas.setText(df.format(valorParcelas));
            this.tvValorTotal.setText(df.format(valorTotal));
            this.tvTotalJuros.setText(df.format(totalJuros));

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


    }

    public void limpar(View v) {
        Toast.makeText(this, "Limpando", Toast.LENGTH_LONG).show();

        this.editNome.setText("");
        this.editValor.setText("");
        this.editQtdParcelas.setText("");
        this.editJuros.setText("");
        this.tvNome.setText("");
        this.tvValorInicial.setText("");
        this.tvValorParcelas.setText(String.valueOf(""));
        this.tvValorTotal.setText(String.valueOf(""));
        this.tvTotalJuros.setText(String.valueOf(""));

//        recreate();

//        Intent intent = getIntent();
//        setIntent(intent);
//        finish();
//        startActivity(intent);

    }
}