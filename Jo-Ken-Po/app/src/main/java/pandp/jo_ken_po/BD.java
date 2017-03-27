package pandp.jo_ken_po;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by pazevedo on 09/07/2016.
 */
public class BD {
    private SQLiteDatabase db;

    public BD(Context context) {
        BDCore auxBd = new BDCore(context);
        db = auxBd.getWritableDatabase();
    }

    public void insertInicial() {
        ContentValues values = new ContentValues();
        values.put(dic.getQtVitorias(), 0);
        values.put(dic.getQtDerrotas(), 0);
        db.insert(dic.getTbPontuacao(), null, values);
    }

    public int upVitoria(){
        ContentValues values = new ContentValues();
        Ranking ranking = getRanking();
        try{
            values.put(dic.getQtVitorias(), ranking.getVitorias() + 1);
            db.update(dic.getTbPontuacao(), values, dic.getID() + "=" + ranking.getId(), null);
            ranking = getRanking();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ranking.getVitorias();
    }

    public int upDerrota(){
        ContentValues values = new ContentValues();
        Ranking ranking = getRanking();
        try{
            values.put(dic.getQtDerrotas(), ranking.getDerrotas() + 1);
            db.update(dic.getTbPontuacao(), values, dic.getID() + "=" + ranking.getId(), null);
            ranking = getRanking();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ranking.getDerrotas();
    }

    public Ranking getRanking() {
        Ranking ranking = new Ranking();
        String[] colunas = new String[]{dic.getID(), dic.getQtVitorias(), dic.getQtDerrotas()};
        Cursor cursor = null;
        try {
            cursor = db.query(dic.getTbPontuacao(), colunas, null, null, null, null, dic.getID() + " DESC");
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                ranking.setId(cursor.getInt(0));
                ranking.setVitorias(cursor.getInt(1));
                ranking.setDerrotas(cursor.getInt(2));
                cursor.close();
            } else {
                insertInicial();
                ranking = getRanking();
            }
        } catch (Exception ex){
          ex.printStackTrace();
        }
        return (ranking);
    }
}
