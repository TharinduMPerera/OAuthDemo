package com.app.tharindu.oauthdemo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.tharindu.oauthdemo.R;
import com.app.tharindu.oauthdemo.models.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private List<Repository> repositoryList;

    public RepoAdapter(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.widget_repo, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repository repository = repositoryList.get(position);

        if (repository == null) {
            return;
        }

        holder.nameTextView.setText(repository.getName() != null ? repository.getName() : "Name: N/A");
        holder.languageTextView.setText(repository.getLanguage() != null ? repository.getLanguage() : "Language: N/A");
        holder.typeTextView.setText(repository.getPrivate() != null ? (repository.getPrivate() ? "Private" : "Public") : "Type: N/A");
    }

    @Override
    public int getItemCount() {
        return repositoryList != null ? repositoryList.size() : 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nameTextView)
        TextView nameTextView;

        @BindView(R.id.languageTextView)
        TextView languageTextView;

        @BindView(R.id.typeTextView)
        TextView typeTextView;

        protected ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
