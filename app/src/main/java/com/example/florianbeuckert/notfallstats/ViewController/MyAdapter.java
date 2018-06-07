package com.example.florianbeuckert.notfallstats.ViewController;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.florianbeuckert.notfallstats.Data.Dataset;
import com.example.florianbeuckert.notfallstats.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView idEintrag, datum, codeGemeldet, codeKorrekt, bemerkung, kommentar;
        private ImageView img;

        public MyViewHolder(final View itemView) {
            super(itemView);

            idEintrag = (TextView) itemView.findViewById(R.id.row_id);
            datum = (TextView) itemView.findViewById(R.id.row_datum);
            codeGemeldet = (TextView) itemView.findViewById(R.id.row_code_gemeldet);
            codeKorrekt = (TextView) itemView.findViewById(R.id.row_code_korrekt);
            bemerkung = (TextView) itemView.findViewById(R.id.row_bemerkung);
            kommentar = (TextView) itemView.findViewById(R.id.row_kommentar);
            img = (ImageView) itemView.findViewById(R.id.row_image);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setMessage("Eintrag " + idEintrag.getText() + " wirklich löschen?");

                    builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mainActivity.loescheEintrag(Integer.parseInt(idEintrag.getText().toString()));
                        }
                    });

                    builder.setNegativeButton("Nein", null);

                    AlertDialog dialog = builder.create();
                    builder.show();

                    return true;
                }
            });
        }
    }

    private List<Dataset> daten;
    private MainActivity mainActivity;

    public MyAdapter(List<Dataset> daten, MainActivity mainActivity) {
        this.daten = daten;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Dataset d = daten.get(position);

        holder.idEintrag.setText("" + d.getId());
        holder.datum.setText(d.getDate());

        switch (d.evaluateStat()) {
            case Dataset.STAT_CORRECT:
                holder.img.setImageResource(R.drawable.check);
                break;
            case Dataset.STAT_PRIORITY_ACCURATE:
                holder.img.setImageResource(R.drawable.arrow_right);
                break;
            case Dataset.STAT_PRIORITY_TOO_HIGH:
                holder.img.setImageResource(R.drawable.arrow_bottom_right);
                break;
            case Dataset.STAT_PRIORITY_TOO_LOW:
                holder.img.setImageResource(R.drawable.arrow_top_right);
                break;
            case Dataset.STAT_N_A:
                holder.img.setImageResource(R.drawable.alert);
                break;
        }

        holder.codeGemeldet.setText(d.getCodeReported().toString());
        holder.codeKorrekt.setText(d.getCodeActual().toString());

        holder.bemerkung.setText(d.getAnnotation());
        holder.kommentar.setText(d.getComment());
    }

    @Override
    public int getItemCount() {
        return daten.size();
    }
}
