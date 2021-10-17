package uz.theairsoft.flowersgarden.ui.enter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.theairsoft.flowersgarden.databinding.FragmentSmsAuthBinding

class SmsAuthFragment : Fragment() {

    lateinit var binding: FragmentSmsAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSmsAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
            btnNext.isEnabled = false
            etSmsCode.smsCodeMask(btnNext)
        }

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnNext.setOnClickListener {
            val action = SmsAuthFragmentDirections.navigateToWelcomeFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}