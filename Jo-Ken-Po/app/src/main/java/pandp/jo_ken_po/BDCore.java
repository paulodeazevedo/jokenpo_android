package pandp.jo_ken_po;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by pazevedo on 09/07/2016.
 */
public class BDCore extends SQLiteOpenHelper {

    private static final String NOME_BD = "ranking";
    private static final Integer VERSAO_BD = 1;

    public BDCore(Context ctx) {
        super(ctx, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                dic.getTbPontuacao() + "(" +
                dic.getID() + " INTEGER PRIMARY  KEY, " +
                dic.getQtVitorias() + " INTEGER, " +
                dic.getQtDerrotas() + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + dic.getTbPontuacao() + ";");
        onCreate(db);
    }
}
