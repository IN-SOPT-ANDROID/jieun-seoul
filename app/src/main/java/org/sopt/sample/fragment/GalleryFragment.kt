package org.sopt.sample.fragment

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import coil.load
import org.sopt.sample.databinding.FragmentMyPageBinding


class GalleryFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = checkNotNull(_binding)
//    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
//        binding.imageView.load(it)
//    }
    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        binding.imageView.load(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnImage.setOnClickListener {
            //launcher.launch("image/")
            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
}