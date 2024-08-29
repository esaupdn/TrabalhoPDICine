package br.edu.ifsuldeminas.mch.trabalhocinemapdi;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.CinemaBranch;
import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db.CinemaBranchDAO;
import br.edu.ifsuldeminas.mch.trabalhocinemapdi.model.db.DAOObserver;

public class ListCinemaBranchesActivity extends AppCompatActivity implements DAOObserver {

    ListView cinemabranchesList;

    ImageView btnLogo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cinema_branches);

        cinemabranchesList = findViewById(R.id.cinemabranches_list);
        registerForContextMenu(cinemabranchesList);


        Button btnAdicionar = findViewById(R.id.listTransactionBtnAdd);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCinemaBranchesActivity.this, AddCinemaBranchActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        cinemabranchesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CinemaBranch cinemabranch = (CinemaBranch) cinemabranchesList.getItemAtPosition(position);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTransactions();
    }

    private void updateTransactions() {
        CinemaBranchDAO dao = new CinemaBranchDAO(this);
        dao.loadCinemaBranches();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem itemDelete = menu.add("Apagar Cinema");
        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo itemClicado =
                        (AdapterView.AdapterContextMenuInfo) menuInfo;
                CinemaBranch task = (CinemaBranch)
                        cinemabranchesList.getItemAtPosition(itemClicado.position);

                CinemaBranchDAO dao = new CinemaBranchDAO(ListCinemaBranchesActivity.this);
                dao.delete(task);

                updateTransactions();

                return true;
            }
        });

        MenuItem itemEdit = menu.add("Editar Cinema");
        itemEdit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                AdapterView.AdapterContextMenuInfo itemClicado =
                        (AdapterView.AdapterContextMenuInfo) menuInfo;
                CinemaBranch transaction = (CinemaBranch)
                        cinemabranchesList.getItemAtPosition(itemClicado.position);

                Intent intent = new Intent(ListCinemaBranchesActivity.this, AddCinemaBranchActivity.class);
                intent.putExtra("transacaoEdicao", transaction);
                startActivity(intent);

                return true;
            }
        });
    }

    @Override
    public void loadOk(List<CinemaBranch> cinemabranch) {
        ArrayAdapter<CinemaBranch> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cinemabranch);

        cinemabranchesList.setAdapter(arrayAdapter);
    }

    @Override
    public void loadError() {
        Toast.makeText(this, "Erro ao carregar dados", Toast.LENGTH_LONG).show();
    }

    @Override
    public void deleteOk() {
        Toast t = Toast.makeText(ListCinemaBranchesActivity.this, "O Cinema foi excluído com sucesso!", Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public void deleteError() {
        Toast t = Toast.makeText(ListCinemaBranchesActivity.this, "O Cinema não pode ser excluído!", Toast.LENGTH_LONG);
        t.show();
    }

}