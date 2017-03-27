package pandp.jo_ken_po;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import android.support.v7.app.AppCompatActivity;
import pandp.jo_ken_po.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;
/*public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}*/
public class MainActivity extends AppCompatActivity {

    ImageButton pedra;
    ImageButton papel;
    ImageButton tesoura;
    Animation animPunhoEsquerdo;
    Animation animPunhoDireito;
    ImageView ivPunhoEsquerdo;
    ImageView ivPunhoDireito;
    ImageView ivResultado;
    TextView tvVitorias;
    TextView tvDerrotas;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private static Context ctx;
    BD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        pedra = (ImageButton) findViewById(R.id.btnPedra);
        papel = (ImageButton) findViewById(R.id.btnPapel);
        tesoura = (ImageButton) findViewById(R.id.btnTesoura);
        ivPunhoEsquerdo = (ImageView) findViewById(R.id.punhoEsquerdo);
        ivPunhoDireito = (ImageView) findViewById(R.id.punhoDireito);
        ivResultado = (ImageView) findViewById(R.id.resultado);
        tvVitorias = (TextView) findViewById(R.id.qtVitorias);
        tvDerrotas = (TextView) findViewById(R.id.qtDerrotas);

        ctx = getApplicationContext();
        db = new BD(ctx);
        Ranking ranking = db.getRanking();
        tvVitorias.setText(String.valueOf(ranking.getVitorias()));
        tvDerrotas.setText(String.valueOf(ranking.getDerrotas()));

        pedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovePunhos(EscolhaEnum.PEDRA);
            }
        });

        papel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovePunhos(EscolhaEnum.PAPEL);
            }
        });

        tesoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovePunhos(EscolhaEnum.TESOURA);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void MovePunhos(final EscolhaEnum escolhaJogador) {

        final EscolhaEnum escolhaAdversario = EscolhaAdversario.GetEscolhaAdversario();
        final JogadoresEnum vencedor = Vencedor.GetVencedor(escolhaJogador, escolhaAdversario);

        ivPunhoEsquerdo.setImageResource(R.mipmap.punho_esquerdo);
        ivPunhoDireito.setImageResource(R.mipmap.punho_direito);
        ivResultado.setImageResource(0);

        animPunhoEsquerdo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacao_jokenpo);
        ivPunhoEsquerdo.startAnimation(animPunhoEsquerdo);
        animPunhoDireito = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animacao_jokenpo);
        ivPunhoDireito.startAnimation(animPunhoDireito);

        animPunhoEsquerdo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                switch (escolhaJogador) {
                    case PEDRA:
                        ivPunhoEsquerdo.setImageResource(R.mipmap.pedra_image);
                        break;
                    case PAPEL:
                        ivPunhoEsquerdo.setImageResource(R.mipmap.papel_image);
                        break;
                    case TESOURA:
                        ivPunhoEsquerdo.setImageResource(R.mipmap.tesoura_image);
                        break;
                    default:
                        ivPunhoEsquerdo.setImageResource(R.mipmap.pedra_image);
                }
            }
        });
        animPunhoDireito.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                switch (escolhaAdversario) {
                    case PEDRA:
                        ivPunhoDireito.setImageResource(R.mipmap.pedra_image);
                        break;
                    case PAPEL:
                        ivPunhoDireito.setImageResource(R.mipmap.papel_image);
                        break;
                    case TESOURA:
                        ivPunhoDireito.setImageResource(R.mipmap.tesoura_image);
                        break;
                    default:
                        ivPunhoDireito.setImageResource(R.mipmap.pedra_image);
                }
                SetVencedor(vencedor);
            }
        });
    }

    public void SetVencedor(JogadoresEnum vencedor){
        MediaPlayer m;

        switch (vencedor) {
            case JOGADOR:
                ivResultado.setImageResource(R.mipmap.ganhou_image);
                m = MediaPlayer.create(this,R.raw.win);
                tvVitorias.setText(String.valueOf(db.upVitoria()));
                break;
            case ADVERSARIO:
                ivResultado.setImageResource(R.mipmap.perdeu_text);
                m = MediaPlayer.create(this,R.raw.lose);
                tvDerrotas.setText(String.valueOf(db.upDerrota()));
                break;
            case NENHUM:
                ivResultado.setImageResource(R.mipmap.empate_text);
                m = MediaPlayer.create(this,R.raw.lose);
                break;
            default:
                ivResultado.setImageResource(R.mipmap.empate_text);
                m = MediaPlayer.create(this,R.raw.lose);
        }
        m.start();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://pandp.jo_ken_po/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://pandp.jo_ken_po/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
