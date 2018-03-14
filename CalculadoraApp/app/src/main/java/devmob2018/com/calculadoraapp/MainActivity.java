package devmob2018.com.calculadoraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView tvNmb1, tvNmb2;

    LinearLayout layoutResultado;
    TextView toView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvNmb1 = findViewById(R.id.nmb1);
        this.tvNmb2 = findViewById(R.id.nmb2);

        this.layoutResultado = findViewById(R.id.layoutResultado);

//        this.tvNmb1 = findViewById(R.id.nmb1);
//        this.tvNmb1 = findViewById(R.id.nmb1);
//        this.tvNmb1 = findViewById(R.id.nmb1);
    }


    public void calcular(View v) {
        try {
            TextView tv = (TextView) v;
            char signal = tv.getText().toString().charAt(0);

            Double n1 = Double.valueOf(this.tvNmb1.getText().toString());
            Double n2 = Double.valueOf(this.tvNmb2.getText().toString());

//            HashMap<String, Object> functions = new HashMap<>();
//            functions.put("+", this.plus(n1, n2));
//            functions.put("-", this.minus(n1, n2));
//            functions.put("*", this.multi(n1, n2));
//            functions.put("/", this.division(n1, n2));
//            String.valueOf(functions.get(signal));
            Double resultado = 0.0;
            if (signal == '+') {
                resultado = this.plus(n1, n2);
            } else {
                if (signal == '-') {
                    resultado = this.minus(n1, n2);
                } else {
                    if (signal == '*') {
                        resultado = this.multi(n1, n2);
                    } else {
                        if (signal == '/') {
                            resultado = this.division(n1, n2);
                        }
                    }
                }
            }
            this.toView = new TextView(this);
            this.toView.setGravity(1);
            this.toView.setText(n1 + " " + signal + " " + n2 + " = " + resultado);
            this.layoutResultado.addView(this.toView);

            this.tvNmb1.setText("");
            this.tvNmb2.setText("");

            Toast.makeText(this, String.valueOf(resultado), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();

        }

    }

    private Double plus(Double n1, Double n2) {
        return (n1 + n2);
    }

    private Double minus(Double n1, Double n2) {
        return (n1 - n2);
    }

    private Double multi(Double n1, Double n2) {
        return (n1 * n2);
    }

    private Double division(Double n1, Double n2) {
        return (n1 / n2);
    }

    public void limpar(View v) {
        Toast.makeText(this, "Limpando", Toast.LENGTH_SHORT).show();

        this.tvNmb1.setText("");
        this.tvNmb2.setText("");
        this.layoutResultado.removeAllViews();
    }
}
