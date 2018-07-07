package com.guto.pousadaapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import static com.guto.pousadaapplication.NavigationActivity.wsAddress;

public class LoginActivity extends Activity {
    EditText editEmail;
    EditText editSenha;
    Button btAcessar;
    Button btLimpar;

    AsyncHttpClient client = new AsyncHttpClient();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        btAcessar = (Button) findViewById(R.id.btAcessar);
        btLimpar = (Button) findViewById(R.id.btLimpar);
        if(NavigationActivity.loggedUser!=null){
            this.finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(NavigationActivity.loggedUser!=null){
            this.finish();
        }
    }

    public void login (View v) {
        String stremail = editEmail.getText().toString();
        String strsenha = editSenha.getText().toString();
        client.get("http://" + wsAddress + "/ws_pousada/usuario/login?email=" + stremail + "+&senha=" + strsenha, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                if (!result.equals("Usuário Inexistente")) {
                    Toast.makeText(LoginActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                    Usuario u = gson.fromJson(result, Usuario.class);
                    NavigationActivity.loggedUser = u;

                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuário inexistente ou senha incorreta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(LoginActivity.this, "Conexão Falhou", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void limpar (View v) {
        editEmail.setText("");
        editSenha.setText("");
    }

    public void cadastrar (View v) {
        Intent i = new Intent(this, CadastroActivity.class);
        startActivity(i);
    }
}