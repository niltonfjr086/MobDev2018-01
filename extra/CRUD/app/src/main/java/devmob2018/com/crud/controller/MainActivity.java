package devmob2018.com.crud.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import devmob2018.com.crud.R;
import devmob2018.com.crud.model.daos.PessoaDAO;
import devmob2018.com.crud.model.data_base.Create;
import devmob2018.com.crud.model.entities.Pessoa;

public class MainActivity extends Activity {

    private Pessoa pessoa;
    private PessoaDAO pessoaDAO;

    private EditText editPosicao, editNome, editIdade, editPeso;
    private Switch switchDeficiencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.pessoa = new Pessoa(Pessoa.criarUID());
        this.pessoaDAO = new PessoaDAO();

        this.editPosicao = findViewById(R.id.editPosicao);
        this.editNome = findViewById(R.id.editNome);
        this.editIdade = findViewById(R.id.editIdade);
        this.editPeso = findViewById(R.id.editPeso);

        this.switchDeficiencia = findViewById(R.id.toggleDeficiencia);


        new Create().createTable();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2000) {

        }
    }

    public void onButtonAction(View v) {
        Editable posicao, nome, idade, peso;

        posicao = this.editPosicao.getText();
        nome = this.editNome.getText();
        idade = this.editIdade.getText();
        peso = this.editPeso.getText();


//        if (posicao.length() > 0) this.pessoa.setId(Long.valueOf(posicao.toString()));
//        else this.pessoa.setId(null);

        if (nome.length() > 0) this.pessoa.setNome(nome.toString());
        else this.pessoa.setNome(null);

        if (idade.length() > 0) this.pessoa.setIdade(Integer.valueOf(idade.toString()));
        else this.pessoa.setIdade(null);

        if (peso.length() > 0) this.pessoa.setPeso(Double.valueOf(peso.toString()));
        else this.pessoa.setPeso(null);

        try {

            String buttonId = getResources().getResourceEntryName(v.getId());

            Object object = null;
            Class c = null;
            Method method = null;

            if (buttonId.equalsIgnoreCase("toggleDeficiencia")) {
                object = this;

            } else {
                object = this.pessoaDAO;
            }

            c = object.getClass();


            String retorno = "";
            if (buttonId.equalsIgnoreCase("garregar")) {
                method = c.getMethod(buttonId, Integer.class);

                Integer pos = Integer.valueOf(this.editPosicao.getText().toString());
                this.pessoa = (Pessoa) method.invoke(object, pos);

                retorno = "Pessoa carregada";

            } else {

                if (buttonId.equalsIgnoreCase("adicionarPessoa")) {

                    method = c.getMethod(buttonId, Pessoa.class);

                    retorno = (String) method.invoke(object, this.pessoa);

                } else {

                    if (buttonId.equalsIgnoreCase("verRegistros")) {
                        method = c.getMethod(buttonId);
                        retorno = method.invoke(object).toString();

                    } else {
                        method = c.getMethod(buttonId);
                        retorno = (String) method.invoke(object);
                    }

                }

            }


            Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();

        } catch (NoSuchMethodException e) {
            Toast.makeText(this, "NoSuchMethodException", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Toast.makeText(this, "IllegalAccessException", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Toast.makeText(this, "InvocationTargetException", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    private void viewToBean() {

//        ATRIBUTO DEFICIENCIA SE O SWITCH NÃO FOR CLICADO FICARÁ NULO

        Editable posicao, nome, idade, peso;

        posicao = this.editPosicao.getText();
        nome = this.editNome.getText();
        idade = this.editIdade.getText();
        peso = this.editPeso.getText();


//        if (posicao.length() > 0) this.pessoa.setId(Long.valueOf(posicao.toString()));
//        else this.pessoa.setId(null);

        if (nome.length() > 0) this.pessoa.setNome(nome.toString());
        else this.pessoa.setNome(null);

        if (idade.length() > 0) this.pessoa.setIdade(Integer.valueOf(idade.toString()));
        else this.pessoa.setIdade(null);

        if (peso.length() > 0) this.pessoa.setPeso(Double.valueOf(peso.toString()));
        else this.pessoa.setPeso(null);

    }

    private void beanToView() {

//        ATRIBUTO DEFICIENCIA SE O SWITCH NÃO FOR CLICADO FICARÁ NULO

        Editable posicao, nome, idade, peso;

        posicao = this.editPosicao.getText();
        nome = this.editNome.getText();
        idade = this.editIdade.getText();
        peso = this.editPeso.getText();


//        if (posicao.length() > 0) this.pessoa.setId(Long.valueOf(posicao.toString()));
//        else this.pessoa.setId(null);

        if (nome.length() > 0) this.pessoa.setNome(nome.toString());
        else this.pessoa.setNome(null);

        if (idade.length() > 0) this.pessoa.setIdade(Integer.valueOf(idade.toString()));
        else this.pessoa.setIdade(null);

        if (peso.length() > 0) this.pessoa.setPeso(Double.valueOf(peso.toString()));
        else this.pessoa.setPeso(null);

    }


    public String toggleDeficiencia() {
        this.pessoa.setDeficiencia(this.switchDeficiencia.isChecked());

        return "toggleDeficiencia INVOCADO: ";
    }


}
