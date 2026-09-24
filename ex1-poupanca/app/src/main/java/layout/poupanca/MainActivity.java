package layout.poupanca;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editValorInicial;
    private EditText editAplicacaoMensal;
    private EditText editTempo;
    private EditText editTaxa;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValorInicial = findViewById(R.id.editValorInicial);
        editAplicacaoMensal = findViewById(R.id.editAplicacaoMensal);
        editTempo = findViewById(R.id.editTempo);
        editTaxa = findViewById(R.id.editTaxa);
        txtResultado = findViewById(R.id.txtResultado);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });
    }

    private void calcular() {
        txtResultado.setText("");

        double valorInicial = valorDe(editValorInicial);
        double aplicacaoMensal = valorDe(editAplicacaoMensal);
        int meses = (int) valorDe(editTempo);
        double taxa = valorDe(editTaxa);

        if (valorInicial == 0 && aplicacaoMensal == 0) {
            Toast.makeText(this, R.string.aviso_valor, Toast.LENGTH_SHORT).show();
            return;
        }
        if (meses <= 0) {
            Toast.makeText(this, R.string.aviso_tempo, Toast.LENGTH_SHORT).show();
            return;
        }
        if (taxa >= 1) {
            editTaxa.setError(getString(R.string.aviso_taxa));
            editTaxa.requestFocus();
            return;
        }

        double montante;
        if (taxa == 0) {
            montante = valorInicial + aplicacaoMensal * meses;
        } else {
            double fator = Math.pow(1 + taxa, meses);
            montante = valorInicial * fator + aplicacaoMensal * ((fator - 1) / taxa);
        }

        double investido = valorInicial + aplicacaoMensal * meses;
        double juros = montante - investido;

        txtResultado.setText(getString(R.string.resultado, investido, juros, montante));
    }

    private double valorDe(EditText campo) {
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(texto.replace(",", "."));
    }
}
