package com.nacoda.kamus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nacoda.kamus.model.Kamus;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayburger on 1/11/18.
 */

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.ViewHolder> {

    private ArrayList<Kamus> kamus = new ArrayList<>();

    private Context context;

    public KamusAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Kamus> kamus) {
        this.kamus = kamus;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kamus_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.wordText.setText(kamus.get(position).getWord());
        holder.parent.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(context.getString(R.string.description), kamus.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return kamus.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.word)
        TextView wordText;
        @BindView(R.id.parent)
        LinearLayout parent;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

