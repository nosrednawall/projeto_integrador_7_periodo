package org.iel.codesimatic.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.iel.codesimatic.R;
import org.iel.codesimatic.Rest.BuscaRelatoriosMaquinaAsyncTask;
import org.iel.codesimatic.Rest.RelatorioFuncionamentoMaquinaRetorno;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ListaGraficosActivity extends AppCompatActivity{

    EditText dataInicial;
    EditText dataLimite;
    Boolean dataInicialOuFinal;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_graficos);

        CardView cardGraficos = (CardView) findViewById(R.id.lista_graficos_status_maquina);
        CardView cardGraficoPie = (CardView) findViewById(R.id.lista_graficos_torta);

        dataInicial = (EditText) findViewById(R.id.data_inicial);
        dataLimite = (EditText) findViewById(R.id.data_limite);

        dataInicial.setText(getDataAtual(false));
        dataLimite.setText(getDataAtual(true));


        dataInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataInicialOuFinal = true;
                selectDate();
            }
        });

        dataLimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataInicialOuFinal = false;
                selectDate();
            }
        });

        cardGraficos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent statusMaquinaIntent = new Intent(getApplicationContext(), StatusMaquinaActivity.class);
                startActivity(statusMaquinaIntent);
            }
        });

        cardGraficoPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent PieChaIntent = new Intent(getApplicationContext(), PieChartActivity.class);
                startActivity(PieChaIntent);
            }
        });
    }

    private void exibirProgress(boolean exibir) {
        mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    private void buscaRelatorioFuncionamentoPorcentagemMaquina(){
        loggerAsyncTask("Iniciando metodo busca relatorio Funcionamento Maquina");
        String retornoJson = new BuscaRelatoriosMaquinaAsyncTask();

        System.out.println(retornoJson);

    }

    private String getDataInicialToString(){
        dataInicial = (EditText) findViewById(R.id.data_inicial);
        return dataInicial.toString();
    }

    private String getDataLimiteToString(){
        dataLimite = (EditText) findViewById(R.id.data_limite);
        return dataLimite.toString();
    }
//
//    class BuscaNoServidor extends AsyncTask<Location, Void, Location>{
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            exibirProgress(true);
//        }
//
//        @Override
//        protected Location doInBackground(Location... params) {
//            return medotodoRetornaLocation();
//        }
//
//        @Override
//        protected void onPostExecute(Location location) {
//            super.onPostExecute(location);
//            exibirProgress(false);
//            metodoAtualizaAInterfaceGrafica(location);
//        }
//    }



    /**
     * Classe responsável por instanciar o datapicker e pegar a data selecionado pelo usuário
     */
    public static class SelectDateFragment  extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        EditText data;

        public SelectDateFragment(EditText data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar calendario = Calendar.getInstance();

            int yy = calendario.get(Calendar.YEAR);
            int mm =  calendario.get(Calendar.MONTH);
            int dd = calendario.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(data, yy, mm+1, dd);
        }
    }

    /**
     * funcao que instancia o fragmento e seleciona o label???
     */
    public void selectDate() {
        DialogFragment newFragment;
        if(dataInicialOuFinal) {
            newFragment = new SelectDateFragment(dataInicial);
        }else{
            newFragment = new SelectDateFragment(dataLimite);
        }
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    /**
     * Funcao que atualiza o label
     * @param mEditText
     * @param year
     * @param month
     * @param day
     */
    public static void populateSetDate(EditText mEditText, int year, int month, int day) {
        mEditText.setText(day+"/"+month+"/"+year);
    }

    /**
     * funcao retorna a data atual
     * @param dataFinal
     * @return
     */
    public static String getDataAtual(boolean dataFinal){

        final Calendar calendario = Calendar.getInstance();

        int yy = calendario.get(Calendar.YEAR);
        int mm =  calendario.get(Calendar.MONTH);
        int dd = calendario.get(Calendar.DAY_OF_MONTH);
        mm++;

        if(dataFinal) {
            return dd + "/" + mm + "/" + yy;
        }else{
            dd--;
            return dd + "/" + mm + "/" + yy;
        }
    }

    private static void loggerAsyncTask(String mensagem){
        Log.i("AsyncTask",mensagem+" Thread: " + Thread.currentThread().getName());
    }


}
