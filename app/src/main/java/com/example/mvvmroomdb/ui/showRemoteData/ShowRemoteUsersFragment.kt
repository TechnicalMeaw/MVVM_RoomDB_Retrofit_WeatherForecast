package com.example.mvvmroomdb.ui.showRemoteData

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.mvvmroomdb.databinding.FragmentShowRemoteUsersBinding
import com.example.mvvmroomdb.rv.ShowRemoteUsersRecyclerViewAdapter
import com.example.mvvmroomdb.worker.MyWorker

class ShowRemoteUsersFragment : Fragment() {

    companion object {
        fun newInstance() = ShowRemoteUsersFragment()
    }

    private lateinit var binding: FragmentShowRemoteUsersBinding
    private lateinit var viewModel: ShowRemoteUsersViewModel
    private lateinit var adapter: ShowRemoteUsersRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowRemoteUsersBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this)[ShowRemoteUsersViewModel::class.java]


        binding.showAllRemoteUserRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = ShowRemoteUsersRecyclerViewAdapter(this.requireContext())
        binding.showAllRemoteUserRecyclerView.adapter = this.adapter


        viewModel.getRemoteData(this.requireContext())
        viewModel.remoteUsersData.observe(viewLifecycleOwner, Observer {

            adapter.updateAllUsers(it.data)
        })

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.METERED)
            .build()

        val workRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this.requireContext()).enqueue(workRequest)

        return binding.root
    }

}