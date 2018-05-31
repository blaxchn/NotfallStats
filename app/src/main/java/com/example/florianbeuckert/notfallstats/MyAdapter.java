package com.example.florianbeuckert.notfallstats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView id, datum, codeGemeldet, codeKorrekt, bemerkung, kommentar;
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.row_id);
            datum = (TextView) itemView.findViewById(R.id.row_datum);
            codeGemeldet = (TextView) itemView.findViewById(R.id.row_code_gemeldet);
            codeKorrekt = (TextView) itemView.findViewById(R.id.row_code_korrekt);
            bemerkung = (TextView) itemView.findViewById(R.id.row_bemerkung);
            kommentar = (TextView) itemView.findViewById(R.id.row_kommentar);
            img = (ImageView) itemView.findViewById(R.id.row_image);
        }
    }

    private List<Datensatz> daten;

    public MyAdapter(List<Datensatz> daten) {
        this.daten = daten;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Datensatz d = daten.get(position);

        holder.id.setText("" + d.getId());
        holder.datum.setText(d.getDatum());

        switch (d.evaluateStat()) {
            case Datensatz.STAT_KORREKT:
                holder.img.setImageResource(R.drawable.check);
                break;
            case Datensatz.STAT_PRIO_KORREKT:
                holder.img.setImageResource(R.drawable.arrow_right);
                break;
            case Datensatz.STAT_PRIO_ZU_HOCH:
                holder.img.setImageResource(R.drawable.arrow_bottom_right);
                break;
            case Datensatz.STAT_PRIO_ZU_NIEDRIG:
                holder.img.setImageResource(R.drawable.arrow_top_right);
                break;
            case Datensatz.STAT_NICHT_BESTIMMBAR:
                holder.img.setImageResource(R.drawable.alert);
                break;
        }

        holder.codeGemeldet.setText(d.getCodeGemeldet().toString());
        holder.codeKorrekt.setText(d.getCodeKorrekt().toString());

        holder.bemerkung.setText(d.getBemerkung());
        holder.kommentar.setText(d.getKommentar());
    }

    @Override
    public int getItemCount() {
        return daten.size();
    }
}
