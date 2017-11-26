package br.iesb.escolasbrasil;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.iesb.escolasbrasil.model.Pessoa;

public class Cadastro extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText txnome, txsobrenome, txemailcadastro, txsenhacadastro, txconfirmasenhacadastro;
    Button btncadastrocadastro;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databasePessoa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        databasePessoa = FirebaseDatabase.getInstance().getReference("pessoas");

        txnome = (EditText)findViewById(R.id.txnome);
        txsobrenome = (EditText)findViewById(R.id.txsobrenome);
        txemailcadastro = (EditText) findViewById(R.id.txemailcadastro);
        txsenhacadastro = (EditText)findViewById(R.id.txsenhacadastro);
        txconfirmasenhacadastro = (EditText)findViewById(R.id.txconfirmasenhacadastro);
        btncadastrocadastro = (Button) findViewById(R.id.btncadastrocadastro);

        mAuth = FirebaseAuth.getInstance();

        btncadastrocadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    /*if (!senha.equals(confirmasenha)) {
                        Toast.makeText(Cadastro.this, "Senhas diferentes!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    CONTINUA CRIANDO O USUÁRIO COM SENHA ERRADA E O NOVO USUÁRIO NÃO CONSEGUE LOGAR*/
                mAuth.createUserWithEmailAndPassword(txemailcadastro.getText().toString(), txsenhacadastro.getText().toString())
                        .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Cadastro.this, "USUÁRIO CRIADO COM SUCESSO!", Toast.LENGTH_SHORT);
                                    finish();
                                } else {
                                    Toast.makeText(Cadastro.this, "FALHA NA AUTENTICAÇÃO.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                Inserir();
            }
        });

    }

    private void Inserir() {
        String nome = txnome.getText().toString().trim();
        String sobrenome = txsobrenome.getText().toString();
        String email = txemailcadastro.getText().toString();

        if (!TextUtils.isEmpty(nome)){
            String id = databasePessoa.push().getKey();

            Pessoa pessoa = new Pessoa(id, nome, sobrenome, email);

            databasePessoa.child(id).setValue(pessoa);

            Toast.makeText(this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Voce deve preencher o nome", Toast.LENGTH_LONG).show();
        }

    }

    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}