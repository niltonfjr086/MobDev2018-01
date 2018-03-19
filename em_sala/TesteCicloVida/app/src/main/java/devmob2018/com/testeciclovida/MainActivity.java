package devmob2018.com.testeciclovida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "DALHE AGORA EU VI", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume(){
        super.onResume();

        Toast.makeText(this, "Deu resume", Toast.LENGTH_LONG).show();
    }
}
