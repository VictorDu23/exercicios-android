package layout.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private final int[] placas = {
            R.drawable.placa1,
            R.drawable.placa2,
            R.drawable.placa3,
            R.drawable.placa4,
            R.drawable.placa5
    };

    private final int[] alternativas = {
            R.array.opcoes_q1,
            R.array.opcoes_q2,
            R.array.opcoes_q3,
            R.array.opcoes_q4,
            R.array.opcoes_q5
    };

    private final int[] gabarito = {1, 1, 0, 1, 1};

    private int questaoAtual;
    private int acertos;
    private boolean respondida;

    private TextView txtContador;
    private TextView txtPergunta;
    private ImageView imgPlaca;
    private Button[] botoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtContador = findViewById(R.id.txtContador);
        txtPergunta = findViewById(R.id.txtPergunta);
        imgPlaca = findViewById(R.id.imgPlaca);

        botoes = new Button[4];
        botoes[0] = findViewById(R.id.btnOpcao1);
        botoes[1] = findViewById(R.id.btnOpcao2);
        botoes[2] = findViewById(R.id.btnOpcao3);
        botoes[3] = findViewById(R.id.btnOpcao4);

        for (int i = 0; i < botoes.length; i++) {
            final int posicao = i;
            botoes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    responder(posicao);
                }
            });
        }

        Button btnProxima = findViewById(R.id.btnProxima);
        btnProxima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avancar();
            }
        });

        mostrarQuestao();
    }

    private void mostrarQuestao() {
        respondida = false;
        txtContador.setText(getString(R.string.contador, questaoAtual + 1, placas.length));
        txtPergunta.setText(getString(R.string.pergunta, questaoAtual + 1));
        imgPlaca.setImageResource(placas[questaoAtual]);

        String[] textos = getResources().getStringArray(alternativas[questaoAtual]);
        int verde = ContextCompat.getColor(this, R.color.primaria);

        for (int i = 0; i < botoes.length; i++) {
            botoes[i].setText(textos[i]);
            botoes[i].setBackgroundColor(verde);
            botoes[i].setEnabled(true);
        }
    }

    private void responder(int escolha) {
        if (respondida) {
            return;
        }
        respondida = true;

        int certa = gabarito[questaoAtual];
        botoes[certa].setBackgroundColor(ContextCompat.getColor(this, R.color.acerto));

        if (escolha == certa) {
            acertos++;
            Toast.makeText(this, R.string.msg_acerto, Toast.LENGTH_SHORT).show();
        } else {
            botoes[escolha].setBackgroundColor(ContextCompat.getColor(this, R.color.erro));
            Toast.makeText(this, R.string.msg_erro, Toast.LENGTH_SHORT).show();
        }

        for (int i = 0; i < botoes.length; i++) {
            botoes[i].setEnabled(false);
        }
    }

    private void avancar() {
        if (!respondida) {
            Toast.makeText(this, R.string.msg_escolha, Toast.LENGTH_SHORT).show();
            return;
        }

        if (questaoAtual < placas.length - 1) {
            questaoAtual++;
        } else {
            Toast.makeText(this, getString(R.string.msg_fim, acertos, placas.length),
                    Toast.LENGTH_LONG).show();
            questaoAtual = 0;
            acertos = 0;
        }

        mostrarQuestao();
    }
}
