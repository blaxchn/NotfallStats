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

        private TextView idDataset, date, codeReported, codeActual, annotation, comment;
        private ImageView img;

        public MyViewHolder(final View itemView) {
            super(itemView);

            idDataset = (TextView) itemView.findViewById(R.id.row_id);
            date = (TextView) itemView.findViewById(R.id.row_date);
            codeReported = (TextView) itemView.findViewById(R.id.row_code_reported);
            codeActual = (TextView) itemView.findViewById(R.id.row_code_correkt);
            annotation = (TextView) itemView.findViewById(R.id.row_annotation);
            comment = (TextView) itemView.findViewById(R.id.row_comment);
            img = (ImageView) itemView.findViewById(R.id.row_image);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setMessage(itemView.getContext().getString(R.string.dialog_delete_dataset_1) + idDataset.getText() + itemView.getContext().getString(R.string.dialog_delete_dataset_2));

                    builder.setPositiveButton(itemView.getContext().getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mainActivity.deleteDataset(Integer.parseInt(idDataset.getText().toString()));
                        }
                    });

                    builder.setNegativeButton(itemView.getContext().getString(R.string.no), null);

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

        holder.idDataset.setText("" + d.getId());
        holder.date.setText(d.getDate());

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

        holder.codeReported.setText(d.getCodeReported().toString());
        holder.codeActual.setText(d.getCodeActual().toString());

        holder.annotation.setText(d.getAnnotation());
        holder.comment.setText(d.getComment());
    }

    @Override
    public int getItemCount() {
        return daten.size();
    }
}
