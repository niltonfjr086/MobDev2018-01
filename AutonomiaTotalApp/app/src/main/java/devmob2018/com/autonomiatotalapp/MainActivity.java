package devmob2018.com.autonomiatotalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvNomeCarro, tvKmPercorrido, tvQtdCombustivel, tvConsumoMedio;

    private List<Double> consumos;

    private LinearLayout layoutResultado;

    private TextView toView;

    private DecimalFormat df = new DecimalFormat("#,##0.00" + "km/L", new DecimalFormatSymbols(new Locale("pt", "BR")));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvNomeCarro = findViewById(R.id.nomeCarro);
        this.tvKmPercorrido = findViewById(R.id.kmPercorrido);
        this.tvQtdCombustivel = findViewById(R.id.qtdCombustivel);
        this.tvConsumoMedio = findViewById(R.id.consumoMedio);

        this.consumos = new ArrayList<>();

        this.layoutResultado = findViewById(R.id.layoutResultado);
    }

    public void calcular(View v) {
        double kmPercorrido = Double.valueOf(this.tvKmPercorrido.getText().toString());
        double qtdCombustivel = Double.valueOf(this.tvQtdCombustivel.getText().toString());
        double consumoMedio = qtdCombustivel / kmPercorrido;

        this.consumos.add(consumoMedio);


        this.tvConsumoMedio.setText(df.format(consumoMedioTotal()));

        this.toView = new TextView(this);
        this.toView.setGravity(1);
        this.toView.setText(this.tvNomeCarro.getText() + " - " + df.format(consumoMedio));

        this.layoutResultado.addView(this.toView);

    }

    public void limpar(View v) {

        this.tvNomeCarro.setText("");
        this.tvKmPercorrido.setText("");
        this.tvQtdCombustivel.setText("");
        this.tvConsumoMedio.setText("");

        this.layoutResultado.removeAllViews();
        this.consumos.clear();
    }

    private double consumoMedioTotal() {
        double somaTotal = 0;
        for (Double c : consumos) {
            somaTotal += c;
        }
        return somaTotal / consumos.size();
    }
}
