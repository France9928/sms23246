package it.uniba.dib.sms23246.ui.user;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms23246.R;

public class PatologieAdapter extends RecyclerView.Adapter<PatologieAdapter.PatologiaViewHolder> {
    private List<Patologia> patologie = new ArrayList<>();

    public void setPatologie(List<Patologia> patologie) {
        this.patologie = patologie;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatologiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patologia, parent, false);
        return new PatologiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatologiaViewHolder holder, int position) {
        Patologia patologia = patologie.get(position);
        holder.bind(patologia);
    }

    @Override
    public int getItemCount() {
        return patologie.size();
    }

    static class PatologiaViewHolder extends RecyclerView.ViewHolder {
        private TextView nomePatologiaTextView;
        private SeekBar customSeekBar;

        public PatologiaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomePatologiaTextView = itemView.findViewById(R.id.nomePatologiaTextView);
            customSeekBar = itemView.findViewById(R.id.customSeekBar);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void bind(Patologia patologia) {
            nomePatologiaTextView.setText(patologia.getNome());
            customSeekBar.setProgress(patologia.getLivello());
            int livelloPatologia = patologia.getLivello();
            // Imposta il colore della SeekBar in base al valore di livello
            int progressDrawableId = R.drawable.grey_progress_bar;
            if (livelloPatologia == 1) {
                progressDrawableId = R.drawable.green_progress_bar;
            } else if (livelloPatologia == 2) {
                progressDrawableId = R.drawable.yellow_progress_bar;
            } else if (livelloPatologia == 3) {
                progressDrawableId = R.drawable.red_progress_bar;
            }
            customSeekBar.setProgressDrawable(itemView.getResources().getDrawable(progressDrawableId));
        }
    }
}
