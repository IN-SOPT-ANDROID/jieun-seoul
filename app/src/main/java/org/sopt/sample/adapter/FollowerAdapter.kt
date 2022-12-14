package org.sopt.sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.databinding.LayoutGithubRepoBinding
import org.sopt.sample.remote.Person

class FollowerAdapter(Item: List<Person>, context: Context) :
    RecyclerView.Adapter<FollowerAdapter.RepoViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var repoList: List<Person> = emptyList()
    lateinit var gitBinding: LayoutGithubRepoBinding

    class RepoViewHolder(
        private val binding: LayoutGithubRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Person) {
            Glide.with(binding.root)
                .load(data.avatar)
                .circleCrop()
                .into(binding.imgFollower)
            binding.txtName.text = data.first_name
            binding.txtEmail.text = data.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        gitBinding = LayoutGithubRepoBinding.inflate(inflater, parent, false)
        return RepoViewHolder(gitBinding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount() = repoList.size

    fun setRepoList(repolist: List<Person>) {
        this.repoList = repolist.toList()
        notifyItemRangeChanged(0, repoList.size)
    }
}