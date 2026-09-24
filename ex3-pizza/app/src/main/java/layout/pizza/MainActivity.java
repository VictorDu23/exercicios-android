package layout.pizza;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final double PRECO_LOMBINHO = 45.00;
    private static final double PRECO_MARGUERITA = 40.00;
    private static final double PRECO_CALABRESA = 42.00;
    private static final double PRECO_PEPPERONI = 48.00;
    private static final double TAXA_ENTREGA = 5.00;

    private EditText editQtdLombinho;
    private EditText editQtdMarguerita;
    private EditText editQtdCalabresa;
    private EditText editQtdPepperoni;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editQtdLombinho = findViewById(R.id.editQtdLombinho);
        editQtdMarguerita = findViewById(R.id.editQtdMarguerita);
        editQtdCalabresa = findViewById(R.id.editQtdCalabresa);
        editQtdPepperoni = findViewById(R.id.editQtdPepperoni);
        txtTotal = findViewById(R.id.txtTotal);

        Button btnFazerPedido = findViewById(R.id.btnFazerPedido);
        btnFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerPedido();
            }
        });
    }

    private void fazerPedido() {
        txtTotal.setText("");

        int lombinho = quantidade(editQtdLombinho);
        int marguerita = quantidade(editQtdMarguerita);
        int calabresa = quantidade(editQtdCalabresa);
        int pepperoni = quantidade(editQtdPepperoni);

        int pizzas = lombinho + marguerita + calabresa + pepperoni;
        if (pizzas == 0) {
            Toast.makeText(this, R.string.aviso_vazio, Toast.LENGTH_SHORT).show();
            return;
        }

        double subtotal = lombinho * PRECO_LOMBINHO
                + marguerita * PRECO_MARGUERITA
                + calabresa * PRECO_CALABRESA
                + pepperoni * PRECO_PEPPERONI;
        double total = subtotal + TAXA_ENTREGA;

        txtTotal.setText(getString(R.string.resumo, pizzas, subtotal, TAXA_ENTREGA, total));
        Toast.makeText(this, R.string.msg_pedido, Toast.LENGTH_SHORT).show();
    }

    private int quantidade(EditText campo) {
        String texto = campo.getText().toString().trim();
        if (texto.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(texto);
    }
}
