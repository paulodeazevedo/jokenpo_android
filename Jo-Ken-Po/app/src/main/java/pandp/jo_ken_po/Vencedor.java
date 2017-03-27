package pandp.jo_ken_po;

import android.widget.Toast;

/**
 * Created by Paulo on 05/07/2016.
 */
public class Vencedor {

    public static JogadoresEnum GetVencedor(EscolhaEnum jogador, EscolhaEnum adversario) {

        if (jogador == adversario) {
            return JogadoresEnum.NENHUM;
        } else if (jogador == EscolhaEnum.PEDRA && adversario == EscolhaEnum.PAPEL) {
            return JogadoresEnum.ADVERSARIO;
        } else if (jogador == EscolhaEnum.PEDRA && adversario == EscolhaEnum.TESOURA) {
            return JogadoresEnum.JOGADOR;
        } else if (jogador == EscolhaEnum.PAPEL && adversario == EscolhaEnum.PEDRA) {
            return JogadoresEnum.JOGADOR;
        } else if (jogador == EscolhaEnum.PAPEL && adversario == EscolhaEnum.TESOURA) {
            return JogadoresEnum.ADVERSARIO;
        } else if (jogador == EscolhaEnum.TESOURA && adversario == EscolhaEnum.PEDRA) {
            return JogadoresEnum.ADVERSARIO;
        } else if (jogador == EscolhaEnum.TESOURA && adversario == EscolhaEnum.PAPEL) {
            return JogadoresEnum.JOGADOR;
        } else {
            return JogadoresEnum.NENHUM;
        }
    }
}
