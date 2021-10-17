package uz.theairsoft.flowersgarden.ui.enter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.theairsoft.flowersgarden.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {


    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnNext.setOnClickListener {
            val action = RegistrationFragmentDirections.navigateToDoneFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}