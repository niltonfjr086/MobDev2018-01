package devmob2018.com.comandaapp.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import devmob2018.com.comandaapp.R;
import devmob2018.com.comandaapp.model.entity.Produto;

public class ProdutoAdapter extends BaseAdapter {

    private List<Produto> items;
    private LayoutInflater vi;
    private Integer textViewResourceId;

    public ProdutoAdapter(Context context, int textViewResourceId, List<Produto> items) {
//        super(context, textViewResourceId, items);
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        this.vi = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void remove(Produto p) {
        this.items.remove(p);
        notifyDataSetChanged();
    }

    public void add(Produto p) {
        this.items.add(p);
        notifyDataSetChanged();
    }

    public void addAll(List l) {
        this.items.addAll(l);
        notifyDataSetChanged();
    }

    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = vi.inflate(textViewResourceId, null);

        Produto p = items.get(position);
        if (p != null) {
            TextView t = (TextView) convertView.findViewById(R.id.categoriaProdutoNome);
            TextView tt = (TextView) convertView.findViewById(R.id.produtoNome);
            TextView ttt = (TextView) convertView.findViewById(R.id.produtoValor);

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
        return convertView;
    }

}
