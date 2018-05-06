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

public class CategoriaAdapter extends ArrayAdapter<Categoria> {

    private List<Categoria> items;

    public CategoriaAdapter(Context context, int textViewResourceId, List<Categoria> items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.categoria_list_view_item, null);
        }
        Categoria c = items.get(position);
        if (c!= null) {
            TextView t = (TextView) v.findViewById(R.id.tumbnailCategoria);
            TextView tt = (TextView) v.findViewById(R.id.categoriaNome);
            TextView ttt = (TextView) v.findViewById(R.id.qtdProdutos);

            if (t != null) {
                t.setText(String.valueOf(c.getId()));                            }
            if(tt != null){
                tt.setText(c.getNome());
            }
            if(ttt != null){
                ttt.setText("("+ c.getListProdutos().size()+") Produto(s)");
            }
        }
        return v;
    }
}
