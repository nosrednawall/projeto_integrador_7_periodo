package org.iel.codesimatic.Dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.iel.codesimatic.model.SexoEnum;
import org.iel.codesimatic.model.StatusEnum;

import java.time.LocalDateTime;

/**
 * Classe cria um database de usuário
 */
public class UsuarioDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String USUARIO_NOME_BANCO = "usuario.db";
    public static final String USUARIO_NOME_TABELA = "usuario";
    public static final String USUARIO_CAMPO_ID = "id";
    public static final String USUARIO_CAMPO_VERSION = "version";
    public static final String USUARIO_CAMPO_NOME = "nomeCompleto";
    public static final String USUARIO_CAMPO_CPF = "cpf";
    public static final String USUARIO_CAMPO_EMAIL = "email";
    public static final String USUARIO_CAMPO_SENHA = "senha";
    public static final String USUARIO_CAMPO_SEXO = "sexo";
    public static final String USUARIO_CAMPO_SETOR = "setor";
    public static final String USUARIO_CAMPO_RAMAL = "ramal";
    public static final String USUARIO_CAMPO_DATA_CRIACAO = "dataCriacao";
    public static final String USUARIO_CAMPO_STATUS = "status";

    //Fonte https://sqlite.org/datatype3.html
    private static final String TIPO_NULL = "NULL";
    private static final String TIPO_INTEGER = "INTEGER";
    private static final String TIPO_REAL = "REAL";
    private static final String TIPO_TEXT = "TEXT";
    private static final String TIPO_BLOB = "BLOB";
    private static final String TIPO_NUMERIC = "NUMERIC";
    private static final String VIRGULA = ",";


    //cria tabela
    private static final String USUARIO_DAO_TABLE_CRIACAO = "CREATE TABLE " +
            USUARIO_NOME_TABELA + "("
            + USUARIO_CAMPO_ID + TIPO_INTEGER + " primary key autoincrement" + VIRGULA
            + USUARIO_CAMPO_NOME + TIPO_TEXT + VIRGULA
            + USUARIO_CAMPO_CPF + TIPO_TEXT + VIRGULA
            + USUARIO_CAMPO_EMAIL + TIPO_TEXT + VIRGULA
            + USUARIO_CAMPO_SENHA + TIPO_TEXT + VIRGULA
            + USUARIO_CAMPO_SEXO + TIPO_TEXT + VIRGULA
            + USUARIO_CAMPO_SETOR + TIPO_TEXT + VIRGULA
            + USUARIO_CAMPO_RAMAL + TIPO_TEXT + VIRGULA
            + USUARIO_CAMPO_DATA_CRIACAO + TIPO_NUMERIC + VIRGULA
            + USUARIO_CAMPO_STATUS + TIPO_INTEGER
            + ");";

    //remove tabela
    private static final String USUARIO_DROP_TABLE = "DROP TABLE IF EXISTS " + USUARIO_NOME_TABELA;

    UsuarioDatabaseHelper(Context context){
        super(context, USUARIO_NOME_BANCO, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USUARIO_DAO_TABLE_CRIACAO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(USUARIO_DROP_TABLE);
        onCreate(db);
    }
}
