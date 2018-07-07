package com.guto.pousadaaapplication.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.guto.pousadaapplication.R;
import com.guto.pousadaapplication.model.entity.Estadia;

import java.util.List;

public class EstadiaAdapter extends BaseAdapter {

    private List<Estadia> items;
    private LayoutInflater vi;
    private Integer textViewResourceId;

    public EstadiaAdapter(Context context, int textViewResourceId, List<Estadia> items) {
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

    public void remove(Estadia p) {
        this.items.remove(p);
        notifyDataSetChanged();
    }

    public void add(Estadia p) {
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

        Estadia p = items.get(position);
        if (p != null) {

            TextView t = (TextView) convertView.findViewById(R.id.totalDiarias);

            if (t != null) {
                t.setText(String.valueOf(p.getTotal_diarias_consumidas()));
            }

  /*          TextView t = (TextView) convertView.findViewById(R.id.categoriaProdutoNome);
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
            }*/
        }
        return convertView;
    }

}
