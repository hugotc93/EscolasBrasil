package br.iesb.escolasbrasil;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Cadastro extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final EditText email = (EditText) findViewById(R.id.txemailcadastro);
        final EditText senha = (EditText) findViewById(R.id.txsenhacadastro);
        final EditText confirmasenha = (EditText) findViewById(R.id.txconfirmasenhacadastro);
        Button cadastrar = (Button) findViewById(R.id.btncadastrocadastro);


        mAuth = FirebaseAuth.getInstance();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    /*if (!senha.equals(confirmasenha)) {
                        Toast.makeText(Cadastro.this, "Senhas diferentes!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    CONTINUA CRIANDO O USUÁRIO COM SENHA ERRADA E O NOVO USUÁRIO NÃO CONSEGUE LOGAR*/
                mAuth.createUserWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
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
            }
        });
    }

    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}