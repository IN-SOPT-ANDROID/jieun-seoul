package org.sopt.sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.adapter.FollowerAdapter
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.remote.FollowerServicePool
import org.sopt.sample.remote.ResponseFollowerDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private val FollowerService = FollowerServicePool.FollowerService
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
        with(FollowerService) {
            getData().enqueue(object : Callback<ResponseFollowerDTO> {
                override fun onResponse(
                    call: Call<ResponseFollowerDTO>,
                    response: Response<ResponseFollowerDTO>
                ) {
                    if (response.isSuccessful) {
                        Log.e("success", "success")
                        val adapter = response.body()?.let {
                            context?.let { it1 ->
                                FollowerAdapter(it.data, it1).apply {
                                    setRepoList(it.data)
                                }
                            }
                        }
                        binding.rvRepos.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<ResponseFollowerDTO>, t: Throwable) {
                    Log.e("server fail", "${t.message.toString()}")
                }
            })
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