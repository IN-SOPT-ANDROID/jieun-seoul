package org.sopt.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.adapter.StreetFoodAdapter
import org.sopt.sample.data.StreetFood
import org.sopt.sample.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = requireNotNull(_binding){"HomeFragment"}

    private val mockstreetfoodList = listOf<StreetFood>(
        StreetFood(
            image = R.drawable.img1,
            name = "내 주머니 속 포장마차 리스트",
            location="서울 송파구 성내천로 77"
        ),
        StreetFood(
            image = R.drawable.img1,
            name = "지은이네 붕어빵",
            location="서울 송파구 성내천로 77"
        ),
        StreetFood(
            image = R.drawable.img2,
            name = "민솔이네 계란빵",
            location="서울 송파구 위례성대로22길 6"
        ),
        StreetFood(
            image = R.drawable.img3,
            name = "준원이네 떡볶이",
            location="건대입구역 2번출구 앞"
        ),
        StreetFood(
            image = R.drawable.img4,
            name = "아라네 군고구마",
            location="서울 종로구 창경궁로 230"
        ),
        StreetFood(
            image = R.drawable.img5,
            name = "태희네 타코야끼",
            location="서울 성북구 성북로 62-1"
        ),
        StreetFood(
            image = R.drawable.img6,
            name = "준서네 국화빵",
            location="서울 종로구 대학로11길 9-5"
        ),
        StreetFood(
            image = R.drawable.img7,
            name = "원일이네 호떡",
            location="혜화역 4번 출구 앞"
        ),
        StreetFood(
            image = R.drawable.img8,
            name = "서정이네 와플",
            location="서울 강남구 삼성로 212"
        ),
        StreetFood(
            image = R.drawable.img9,
            name = "예진이네 호두과자",
            location="서울 강남구 테헤란로98길 15"
        ),
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = StreetFoodAdapter(requireContext())
        binding.rvRepos.adapter=adapter
        adapter.setRepoList(mockstreetfoodList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    companion object{
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}