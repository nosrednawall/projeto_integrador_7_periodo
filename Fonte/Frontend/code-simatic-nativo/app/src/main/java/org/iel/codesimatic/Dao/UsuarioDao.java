package org.iel.codesimatic.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.iel.codesimatic.model.Usuario;

public class UsuarioDao {
    private SQLiteDatabase database;

    private UsuarioDatabaseHelper banco;

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
        banco = new UsuarioDatabaseHelper(context);
    }

    public void open(){
        try {
            if(banco.getDatabaseName() != null) {
                database = banco.getWritableDatabase();
            }else{
                System.out.println("Database null");
            }
        }catch (SQLException exception){
            System.out.println("Execeao de sql - "+exception.toString());
        }
    }

    public void close(){
        banco.close();
    }

    public String salva (Usuario entity){
        ContentValues valores;
        long insertId;

        //abrindo conexao
        open();

//        database = banco.getWritableDatabase();

        //inserindo tabela
        valores = new ContentValues();
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_NOME, entity.getNomeCompleto());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_CPF, entity.getCpf());

        //tenho que fazer a conversao de LocalDateTime para o tipo real do banco
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_DATA_CRIACAO, entity.getDataCriacaoToString());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_EMAIL, entity.getEmail());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_SENHA, entity.getSenha());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_RAMAL, entity.getRamal());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_SETOR, entity.getSetor());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_SEXO, entity.getSexoInteger());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_STATUS, entity.getStatusInteger());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_VERSION, entity.getVersion());

        //fazendo o envio dos dados e recebendo o id
        insertId = database.insert(UsuarioDatabaseHelper.USUARIO_NOME_TABELA,null,valores);

        //fechando conexao
        close();
//        database.close();

        if(insertId == -1){
            return "Erro ao inserir registro";
        }else{
            return "Usuário salvo com sucesso!";
        }
    }

    public Cursor listaUsuarios(){
        Cursor cursor;
        database = banco.getReadableDatabase();
        cursor = database.query(UsuarioDatabaseHelper.USUARIO_NOME_TABELA,columns,null,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        close();
        return cursor;
    }

    public Cursor carregaUsuarioById(int id){
        Cursor cursor;
        database = banco.getReadableDatabase();
        String where = UsuarioDatabaseHelper.USUARIO_CAMPO_ID + "=" + id;
        cursor = database.query(UsuarioDatabaseHelper.USUARIO_NOME_TABELA,columns,where,null,null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        close();
        return cursor;
    }

    public void atualiza(int id, Usuario entity){
        ContentValues valores;
        String where;

        open();
        where = UsuarioDatabaseHelper.USUARIO_CAMPO_ID + "=" + id;
        valores = new ContentValues();

        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_NOME, entity.getNomeCompleto());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_CPF, entity.getCpf());

        //tenho que fazer a conversao de LocalDateTime para o tipo real do banco
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_DATA_CRIACAO, entity.getDataCriacaoToString());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_EMAIL, entity.getEmail());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_SENHA, entity.getSenha());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_RAMAL, entity.getRamal());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_SETOR, entity.getSetor());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_SEXO, entity.getSexoInteger());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_STATUS, entity.getStatusInteger());
        valores.put(UsuarioDatabaseHelper.USUARIO_CAMPO_VERSION, entity.getVersion());

        database.update(UsuarioDatabaseHelper.USUARIO_NOME_TABELA,valores,where,null);
        close();
    }

    public void deleta(int id){
        String where = UsuarioDatabaseHelper.USUARIO_CAMPO_ID + "=" + id;
        database = banco.getReadableDatabase();
        database.delete(UsuarioDatabaseHelper.USUARIO_NOME_TABELA,where,null);
        close();
    }
}
