package com.example.mvvmroomdb.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmroomdb.R
import com.example.mvvmroomdb.retrofit.model.Data
import de.hdodenhof.circleimageview.CircleImageView

class ShowRemoteUsersRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<ShowRemoteUsersRecyclerViewAdapter.RemoteUserViewHolder>() {

    private val allUserdata : ArrayList<Data> = ArrayList()

    inner class RemoteUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userImageView: CircleImageView = itemView.findViewById(R.id.remoteUserAvatarImageView)
        val userNameTextView : TextView = itemView.findViewById(R.id.remoteUsernameTextView)
        val userEmailTextView : TextView = itemView.findViewById(R.id.remoteUserEmailTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteUserViewHolder {
        return RemoteUserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.remote_user_item, parent, false))
    }

    override fun onBindViewHolder(holder: RemoteUserViewHolder, position: Int) {
        val currentUser = allUserdata[position]
        Glide.with(context).load(currentUser.avatar).into(holder.userImageView)
        holder.userNameTextView.text = "${currentUser.first_name} ${currentUser.last_name}"
        holder.userEmailTextView.text = currentUser.email
    }

    override fun getItemCount(): Int {
        return allUserdata.size
    }

    fun updateAllUsers(list: List<Data>){
        allUserdata.clear()
        allUserdata.addAll(list)
        this.notifyDataSetChanged()
    }
}