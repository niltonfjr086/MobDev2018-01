package devmob2018.com.comandaapp.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import devmob2018.com.comandaapp.R;
import devmob2018.com.comandaapp.model.entity.Categoria;
import devmob2018.com.comandaapp.model.entity.ItemComanda;

public class ItemComandaAdapter extends ArrayAdapter<ItemComanda> {

    private List<ItemComanda> items;

    public ItemComandaAdapter(Context context, int textViewResourceId, List<ItemComanda> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item_comanda_list_view_item, null);
        }
        ItemComanda ic = items.get(position);
        if (ic != null) {
            TextView t = (TextView) v.findViewById(R.id.itemProdutoNome);
            TextView tt = (TextView) v.findViewById(R.id.qtdItensProduto);
            TextView ttt = (TextView) v.findViewById(R.id.subtotal);

            if (t != null) {
                t.setText(ic.getProduto().getNome());
            }
            if (tt != null) {
                tt.setText("(" + ic.getQuantidade() + ")");
            }
            if (ttt != null) {
                ttt.setText("R$ " + ic.getSubtotal());
            }
        }
        return v;
    }
}
