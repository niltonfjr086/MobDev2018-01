package devmob2018.com.minhasnotasapp;

import android.content.Intent;
import android.os.Bundle;

public class DadosAvaliacaoActivity extends BaseActivity {

    public DadosAvaliacaoActivity(int resultCode) {
        super(resultCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_avaliacao);
    }

    @Override
    protected void implementarExtras(Intent it) {

    }

    @Override
    protected boolean todosPreenchidos() {
        return false;
    }


}
