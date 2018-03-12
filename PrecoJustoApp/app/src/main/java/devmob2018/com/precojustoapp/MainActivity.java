package devmob2018.com.precojustoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout masterLayout;

    private EditText editNome, editValor, editQtdParcelas, editJuros;
    private TextView tvNome, tvValorInicial, tvValorParcelas, tvValorTotal, tvTotalJuros;
    private LinearLayout layoutResultado;


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

    }


    public void calcular(View v) {

        double valorInicial = Double.valueOf(this.editValor.getText().toString());
        int qtdParcelas = Integer.valueOf(this.editQtdParcelas.getText().toString());
        double juros = Double.valueOf(this.editJuros.getText().toString());

        double valorTotal = valorInicial * (1 + (juros / 100));
        double totalJuros = valorTotal - valorInicial;
        double valorParcelas = valorTotal / qtdParcelas;

        this.tvNome.setText(this.editNome.getText());
        this.tvValorInicial.setText(this.editValor.getText());
        this.tvValorParcelas.setText(String.valueOf(valorParcelas));
        this.tvValorTotal.setText(String.valueOf(valorTotal));
        this.tvTotalJuros.setText(String.valueOf(totalJuros));
    }

    public void limpar(View v) {
        Toast.makeText(this,"Limpando", Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }
}
