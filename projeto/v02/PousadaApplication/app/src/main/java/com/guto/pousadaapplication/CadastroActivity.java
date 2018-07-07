package com.guto.pousadaapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

import static com.guto.pousadaapplication.NavigationActivity.wsAddress;

public class CadastroActivity extends Activity {

    EditText editNome;
    EditText editEmail;
    EditText editSenha;
    Button btConfirmar;
    Button btCancelar;

    AsyncHttpClient client = new AsyncHttpClient();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        editNome = (EditText) findViewById(R.id.editNome);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        btConfirmar = (Button) findViewById(R.id.btConfirmar);
        btCancelar = (Button) findViewById(R.id.btCancelar);
    }

    public void cadastrar (View v) {
        Usuario u = new Usuario();
        u.setNome(editNome.getText().toString());
        u.setEmail(editEmail.getText().toString());
        u.setSenha(editSenha.getText().toString());
        String json = gson.toJson(u);
        StringEntity entity = null;
        try {
            entity = new StringEntity(json);
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(CadastroActivity.this, "Erro ao converter entidade para httpentity.", Toast.LENGTH_SHORT).show();
        }

        client.post(this,"http://" + wsAddress + "/ws_pousada/usuario/save", entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(CadastroActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                String result = new String(responseBody);
                Usuario retorno = gson.fromJson(result, Usuario.class);
                NavigationActivity.loggedUser = retorno;
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(CadastroActivity.this, "Erro ao contatar ws", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cancelar (View v) {
        finish();
    }
}
