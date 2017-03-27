package pandp.jo_ken_po;

import java.util.Random;

/**
 * Created by Paulo on 05/07/2016.
 */
public class EscolhaAdversario {

    public static EscolhaEnum GetEscolhaAdversario(){
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        if((randomNum >= 0) && (randomNum < 33))
            return EscolhaEnum.PEDRA;
        else if((randomNum >= 33) && (randomNum < 66))
            return EscolhaEnum.PAPEL;
        else if((randomNum >= 66) && (randomNum < 100))
            return EscolhaEnum.TESOURA;
        else
            return EscolhaEnum.TESOURA;
    }
}
