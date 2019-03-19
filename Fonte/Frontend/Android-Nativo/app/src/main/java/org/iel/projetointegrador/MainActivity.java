package org.iel.projetointegrador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] alunos = {"Daniel","Ronaldo","Jeferson","Felipe" };

        ListView listaAlunos = (ListView) findViewById(R.id.lista_aluno);

        //responsável por colocar as strings dentro das views
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alunos);

        //lista usa esse adapter
        listaAlunos.setAdapter(adapter);

    }
}
