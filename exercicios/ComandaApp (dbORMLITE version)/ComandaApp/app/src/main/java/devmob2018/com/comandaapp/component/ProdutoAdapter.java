package devmob2018.com.comandaapp.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import devmob2018.com.comandaapp.R;
import devmob2018.com.comandaapp.model.entity.Produto;

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    private List<Produto> items;

    public ProdutoAdapter(Context context, int textViewResourceId, List<Produto> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.produto_list_view_item, null);
        }
        Produto p = items.get(position);
        if (p != null) {
            TextView t = (TextView) v.findViewById(R.id.categoriaProdutoNome);
            TextView tt = (TextView) v.findViewById(R.id.produtoNome);
            TextView ttt = (TextView) v.findViewById(R.id.produtoValor);

            if (t != null) {
                t.setText(p.getCategoria().getNome());
            }
            if (tt != null) {
                tt.setText(p.getNome());
            }
            if (ttt != null) {
                ttt.setText(" - R$ " + String.valueOf(p.getValor()));
            }
        }
        return v;
    }

}
