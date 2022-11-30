package org.sopt.sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.sopt.sample.adapter.FollowerAdapter
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.remote.FollowerServicePool
import org.sopt.sample.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private val homeViewmodel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { "HomeFragment" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewmodel.getData()
        homeViewmodel.successGet.observe(viewLifecycleOwner) { success ->
            if (success) {
                Log.d(homeViewmodel.homeResult.value?.data.toString(), "data")
                val adapter = context?.let { it1 ->
                    homeViewmodel.homeResult.value?.let {
                        FollowerAdapter(homeViewmodel.homeResult.value!!.data, it1).apply {
                            Log.d(homeViewmodel.homeResult.value!!.toString(), "data")
                            setRepoList(homeViewmodel.homeResult.value!!.data)
                        }
                    }
                }
                binding.rvRepos.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}