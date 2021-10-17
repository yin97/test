package uz.theairsoft.flowersgarden.ui.enter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.theairsoft.flowersgarden.R
import uz.theairsoft.flowersgarden.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private var phoneNumber = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
            etPhoneNumber.formatPhoneMask(true, btnNext)
            btnNext.isEnabled = false
        }

        binding.btnNext.setOnClickListener {
            phoneNumber = binding.etPhoneNumber.getMaskedPhoneWithoutSpace()
            val action = LoginFragmentDirections.navigateToSmsFragment()
            findNavController().navigate(action)
        }

        binding.icBack.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }
}