package pandp.jo_ken_po;

/**
 * Created by pazevedo on 09/07/2016.
 */
public class dic {

    //tabelas
    private static final String TB_PONTUACAO = "tb_pontuacao";
    //campos TB_PONTUACAO
    private static final String ID = "_id";
    private static final String QT_VITORIAS = "qt_vitorias";
    private static final String QT_DERROTAS = "qt_derrotas";

    public static String getTbPontuacao() {
        return TB_PONTUACAO;
    }

    public static String getID() {
        return ID;
    }

    public static String getQtVitorias() {
        return QT_VITORIAS;
    }

    public static String getQtDerrotas() {
        return QT_DERROTAS;
    }
}
