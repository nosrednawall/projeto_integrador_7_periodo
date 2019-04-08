package org.iel.codesimatic.Dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.iel.codesimatic.model.SexoEnum;
import org.iel.codesimatic.model.StatusEnum;

import java.time.LocalDateTime;

public class UsuarioDao extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String USUARIO_DAO_NOME_TABELA = "usuario";
    private static final String USUARIO_CAMPO_NOME = "nomeCompleto";
    private static final String USUARIO_CAMPO_VERSION = "version";
    private static final String USUARIO_CAMPO_CPF = "cpf";
    private static final String USUARIO_CAMPO_EMAIL = "email";
    private static final String USUARIO_CAMPO_SENHA = "senha";
    private static final String USUARIO_CAMPO_SEXO = "sexo";
    private static final String USUARIO_CAMPO_SETOR = "setor";
    private static final String USUARIO_CAMPO_RAMAL = "ramal";
    private static final String USUARIO_CAMPO_DATA_CRIACAO = "dataCriacao";
    private static final String USUARIO_CAMPO_STATUS = "status";

    private static final String USUARIO_DAO_TABLE_CRIACAO = "CREATE TABLE " +
            USUARIO_DAO_NOME_TABELA + "";

    UsuarioDao(Context context){
        super(context, USUARIO_DAO_NOME_TABELA, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
