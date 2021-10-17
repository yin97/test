package uz.theairsoft.flowersgarden.ui.enter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.theairsoft.flowersgarden.MainActivity
import uz.theairsoft.flowersgarden.databinding.FragmentDoneBinding

class DoneFragment : Fragment() {

    private lateinit var binding: FragmentDoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        return binding.root
    }

}