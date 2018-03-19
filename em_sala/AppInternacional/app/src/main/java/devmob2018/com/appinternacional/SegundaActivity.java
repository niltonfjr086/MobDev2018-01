package devmob2018.com.appinternacional;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import devmob2018.com.appinternacional.bean.Pessoa;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        int idade = getIntent().getIntExtra("idade",0);
        Pessoa pp = (Pessoa) getIntent().getSerializableExtra("pessoa");

        Toast.makeText(this, "SEGUNDA TELA, veio a idade: "+idade, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, pp.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        msg(R.string.onstart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        msg(R.string.onresume);
    }

    @Override
    protected void onPause() {
        super.onPause();
        msg(R.string.onpause);
    }

    @Override
    protected void onStop() {
        super.onStop();
        msg(R.string.onstop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        msg(R.string.ondestroy);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        msg(R.string.onrestart);
    }

    protected void msg(int resId){
        Toast.makeText(this, "T2: "+getString(resId), Toast.LENGTH_SHORT).show();
    }

    public void chamarTela(View v){
        Intent it = new Intent(this, TerceiraActivity.class);
        this.startActivity(it);
    }

    public void fecharTela(View v){
        this.finish();
    }
}
