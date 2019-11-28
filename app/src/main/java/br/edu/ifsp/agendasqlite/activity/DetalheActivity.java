package br.edu.ifsp.agendasqlite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.agendasqlite.R;
import br.edu.ifsp.agendasqlite.data.ContatoDAO;
import br.edu.ifsp.agendasqlite.model.Contato;

public class DetalheActivity extends AppCompatActivity {

    Contato c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        if (getIntent().hasExtra("contato"))
        {
            this.c = (Contato)getIntent().getSerializableExtra("contato");

            EditText nome = findViewById(R.id.editTextNome);
            nome.setText(c.getNome());

            EditText fone = findViewById(R.id.editTextFone);
            fone.setText(c.getFone());

            EditText email = findViewById(R.id.editTextEmail);
            email.setText(c.getEmail());

            EditText celular = findViewById(R.id.editTextCelular);
            celular.setText(c.getCelular());

            EditText diaMesAniv = findViewById(R.id.editTextDiaMesAnviv);
            diaMesAniv.setText(c.getDiaMesAniv());
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_alterarContato) {
            ContatoDAO dao = new ContatoDAO(this);

            String nome = ((EditText) findViewById(R.id.editTextNome)).getText().toString();
            String fone = ((EditText) findViewById(R.id.editTextFone)).getText().toString();
            String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
            String celular = ((EditText) findViewById(R.id.editTextCelular)).getText().toString();
            String diaMesAniv = ((EditText) findViewById(R.id.editTextDiaMesAnviv)).getText().toString();

            c.setNome(nome);
            c.setFone(fone);
            c.setEmail(email);
            c.setCelular(celular);
            c.setDiaMesAniv(diaMesAniv);

            dao.alterarContato(c);
            Log.d("ID: ", Integer.toString(c.getId()));
            Log.d("NOME: ",c.getNome());

            MainActivity.adapter.atualizaContatoAdapter(c);

            Toast.makeText(getApplicationContext(),"Contato alterado",Toast.LENGTH_LONG).show();

            finish();
        }

        if (id ==R.id.action_excluirContato) {
            ContatoDAO dao = new ContatoDAO(this);
            dao.excluirContato(c);
            MainActivity.adapter.apagaContatoAdapter(c);

            Toast.makeText(getApplicationContext(),"Contato excluído",Toast.LENGTH_LONG).show();
            finish();

        }


        return super.onOptionsItemSelected(item);
    }




}
