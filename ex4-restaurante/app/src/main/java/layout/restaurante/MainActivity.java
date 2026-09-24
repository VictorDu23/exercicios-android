package layout.restaurante;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final double PERCENTUAL_SERVICO = 0.10;

    private EditText editConsumo;
    private EditText editCouvert;
    private EditText editPessoas;
    private TextView txtTaxa;
    private TextView txtContaTotal;
    private TextView txtPorPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editConsumo = findViewById(R.id.editConsumo);
        editCouvert = findViewById(R.id.editCouvert);
        editPessoas = findViewById(R.id.editPessoas);

        txtTaxa = findViewById(R.id.txtTaxa);
        txtContaTotal = findViewById(R.id.txtContaTotal);
        txtPorPessoa = findViewById(R.id.txtPorPessoa);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularConta();
            }
        });
    }

    private void calcularConta() {
        txtTaxa.setText("");
        txtContaTotal.setText("");
        txtPorPessoa.setText("");

        double consumo = valorDe(editConsumo);
        double couvert = valorDe(editCouvert);
        int pessoas = (int) valorDe(editPessoas);

        if (consumo <= 0) {
            Toast.makeText(this, R.string.aviso_consumo, Toast.LENGTH_SHORT).show();
            return;
        }
        if (pessoas <= 0) {
            Toast.makeText(this, R.string.aviso_pessoas, Toast.LENGTH_SHORT).show();
            return;
        }

        double taxa = consumo * PERCENTUAL_SERVICO;
        double contaTotal = consumo + couvert + taxa;
        double porPessoa = contaTotal / pessoas;

        txtTaxa.setText(getString(R.string.valor, taxa));
        txtContaTotal.setText(getString(R.string.valor, contaTotal));
        txtPorPessoa.setText(getString(R.string.valor, porPessoa));
    }

    private double valorDe(EditText campo) {
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(texto.replace(",", "."));
    }
}
