package br.edu.ifsp.agendasqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    static final String TABLE_NAME ="contatos";

    static final String KEY_ID = "id";
    static final String KEY_NOME = "nome";
    static final String KEY_FONE = "fone";
    static final String KEY_EMAIL = "email";
    static final String KEY_FAVORITO = "favorito";
    static final String KEY_CELULAR = "celular";
    static final String KEY_DIAMESANIV = "dia_mes_aniv";

    private static final int DATABASE_VERSION = 4;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                                               + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                               + KEY_NOME + " TEXT, "
                                               + KEY_FONE + " TEXT, "
                                               + KEY_EMAIL + " TEXT, "
                                               + KEY_FAVORITO+ " TEXT, "
                                               + KEY_CELULAR + " TEXT, "
                                               + KEY_DIAMESANIV+ " TEXT ) ;" ;


    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String instDdl;

        switch (oldVersion){
            case 1:{
                instDdl = "Alter table "+TABLE_NAME+" add column "+KEY_FAVORITO+" TEXT ";
                db.execSQL(instDdl);
                instDdl = "update "+TABLE_NAME+" set "+KEY_FAVORITO+" = 0";
                db.execSQL(instDdl);
            }

            case 2:{
                instDdl = "Alter table "+TABLE_NAME+" add column "+KEY_CELULAR+" TEXT ";
                db.execSQL(instDdl);
            }

            case 3:{
                instDdl = "Alter table "+TABLE_NAME+" add column "+KEY_DIAMESANIV+" TEXT ";
                db.execSQL(instDdl);
            }
        }
    }
}
