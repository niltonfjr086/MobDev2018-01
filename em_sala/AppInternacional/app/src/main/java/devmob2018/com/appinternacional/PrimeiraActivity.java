package devmob2018.com.appinternacional;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

import devmob2018.com.appinternacional.bean.Pessoa;

public class PrimeiraActivity extends AppCompatActivity {

    @NonNull
    private Pessoa p = new Pessoa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira);

        p = new Pessoa("Pedro", 50);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "T1: "+getString(R.string.onstart), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "T1: "+getString(R.string.onresume), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "T1: "+getString(R.string.onpause), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "T1: "+getString(R.string.onstop), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "T1: "+getString(R.string.ondestroy), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "T1: "+getString(R.string.onrestart), Toast.LENGTH_LONG).show();
    }

    public void chamarTela(View v){
        Intent it = new Intent(this, SegundaActivity.class);
        it.putExtra("idade", 50);

        it.putExtra("pessoa", p);

        this.startActivity(it);
    }

}
