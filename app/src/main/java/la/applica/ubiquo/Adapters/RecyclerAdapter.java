package la.applica.ubiquo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import la.applica.ubiquo.Model.Notificacion;
import la.applica.ubiquo.R;

/**
 * Created by adrianayala on 22/05/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener listener;

    private ArrayList<Notificacion> items;
    private int itemLayout;

    public RecyclerAdapter(ArrayList<Notificacion> items, int itemLayout){
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo;
        public TextView cuerpo;
        public TextView remite;
        public TextView fecha;

        public ViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.tv_titulo);
            cuerpo = (TextView) itemView.findViewById(R.id.tv_cuerpo);
            remite = (TextView) itemView.findViewById(R.id.tv_remite);
            fecha = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Notificacion item = items.get(position);
        holder.titulo.setText(item.getTitulo());
        holder.cuerpo.setText(item.getCuerpo());
        holder.remite.setText(item.getRemite());
        holder.fecha.setText(item.getFecha());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

}
