package org.iel.codesimatic.Dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.iel.codesimatic.model.Usuario;

public class UsuarioDao {
    private SQLiteDatabase database;
    private UsuarioDatabaseHelper sqliteUsuario;
    private String[] columns = {
            UsuarioDatabaseHelper.USUARIO_CAMPO_ID,
            UsuarioDatabaseHelper.USUARIO_CAMPO_VERSION,
            UsuarioDatabaseHelper.USUARIO_CAMPO_NOME,
            UsuarioDatabaseHelper.USUARIO_CAMPO_CPF,
            UsuarioDatabaseHelper.USUARIO_CAMPO_EMAIL,
            UsuarioDatabaseHelper.USUARIO_CAMPO_SENHA,
            UsuarioDatabaseHelper.USUARIO_CAMPO_DATA_CRIACAO,
            UsuarioDatabaseHelper.USUARIO_CAMPO_RAMAL,
            UsuarioDatabaseHelper.USUARIO_CAMPO_SETOR,
            UsuarioDatabaseHelper.USUARIO_CAMPO_SEXO,
            UsuarioDatabaseHelper.USUARIO_CAMPO_STATUS
    };

    public UsuarioDao(Context context){
        sqliteUsuario = new UsuarioDatabaseHelper(context);
    }

    public void open(){
        try {
            database = sqliteUsuario.getWritableDatabase();
        }catch (SQLException exception){
            System.out.println("Execeao de sql - "+exception.toString());
        }
    }

    public void close(){
        sqliteUsuario.close();
    }

    public Usuario create (Usuario)
}
