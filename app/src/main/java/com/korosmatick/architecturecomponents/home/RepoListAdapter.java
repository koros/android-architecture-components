package com.korosmatick.architecturecomponents.home;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.korosmatick.architecturecomponents.R;
import com.korosmatick.architecturecomponents.model.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>{

    private final List<Repo> data =  new ArrayList<>();
    private final RepoSelectedListener repoSelectedListener;

    RepoListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner, RepoSelectedListener repoSelectedListener) {
        this.repoSelectedListener = repoSelectedListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            if (repos == null) {
                data.clear();
                notifyDataSetChanged();
                return;
            }
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RepoDiffCallback(data, repos));
            data.clear();
            data.addAll(repos);
            diffResult.dispatchUpdatesTo(this);
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_repo_list_item, viewGroup, false);
        return new RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder repoViewHolder, int position) {
        repoViewHolder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id();
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_repo_name) TextView repoNameTextView;
        @BindView(R.id.tv_repo_description) TextView repoDescriptionTextView;
        @BindView(R.id.tv_forks) TextView forksTextView;
        @BindView(R.id.tv_stars) TextView starsTextView;

        private Repo repo;

        RepoViewHolder(@NonNull View itemView, RepoSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if (repo != null) {
                    repoSelectedListener.onRepoSelected(repo);
                }
            });
        }

        void bind(Repo repo) {
            this.repo = repo;
            repoNameTextView.setText(repo.name());
            repoDescriptionTextView.setText(repo.description());
            forksTextView.setText(String.valueOf(repo.forks()));
            starsTextView.setText(String.valueOf(repo.stars()));
        }
    }
}
