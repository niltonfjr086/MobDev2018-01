package devmob2018.com.appinternacional;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TerceiraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceira);

        Toast.makeText(this, "TERCEIRA TELA", Toast.LENGTH_SHORT).show();
    }


    public void fecharTela(View v){
        this.finish();
    }
}
