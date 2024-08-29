package br.edu.ifsuldeminas.mch.trabalhocinemapdi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.CinemaBranch;
import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db.CinemaBranchDAO;
import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db.DAOObserver;

public class AddCinemaBranchActivity extends AppCompatActivity implements DAOObserver {

    private CinemaBranch cinemabranch;

    private Button btnAdd;

    private ImageView btnLogo;

    private int isTaxExempt = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cinema_branch);

        Intent chooserIntent = getIntent();
        cinemabranch = (CinemaBranch) chooserIntent.getSerializableExtra("transacaoEdicao");

        EditText amountTextInput = findViewById(R.id.editTextCinemaName);
        EditText descTextInput = findViewById(R.id.editTextDesc);
        EditText typeTextInput = findViewById(R.id.editTextType);

        btnAdd = findViewById(R.id.btnAddTransaction);
        btnAdd.setOnClickListener(view -> {

            String name = amountTextInput.getText().toString().replace(",", ".");
            String location = descTextInput.getText().toString();
            String group = typeTextInput.getText().toString();

            if (name.isEmpty() || location.isEmpty() || group.isEmpty()) {
                Toast toast = Toast.makeText(AddCinemaBranchActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                CinemaBranchDAO dao = new CinemaBranchDAO(this);
                if (this.cinemabranch == null) {
                    cinemabranch = new CinemaBranch();
                    cinemabranch.setName(name);
                    cinemabranch.setLocation(location);
                    cinemabranch.setGroup(group);

                    dao.save(cinemabranch);
                } else {
                    cinemabranch.setName(name);
                    cinemabranch.setLocation(location);
                    cinemabranch.setGroup(group);


                    dao.update(cinemabranch);
                }
                finish();
            }
        });

    }

    @Override
    public void saveOk() {
        Toast toast = Toast.makeText(this,
                "Cinema salvo com sucesso!", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void saveError() {
        Toast toast = Toast.makeText(this,
                "Cinema não pode ser salvo!", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void updateOk() {
        Toast toast = Toast.makeText(this,
                "Cinema atualizado com sucesso!", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void updateError() {
        Toast toast = Toast.makeText(this,
                "Cinema não pode ser atualizado!", Toast.LENGTH_LONG);
        toast.show();
    }
}