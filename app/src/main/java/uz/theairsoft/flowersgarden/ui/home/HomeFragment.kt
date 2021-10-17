package uz.theairsoft.flowersgarden.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import uz.theairsoft.flowersgarden.R
import uz.theairsoft.flowersgarden.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val itemAdapter = ItemAdapter<SignatureModel>()
    private val categoryItemAdapter = ItemAdapter<CategoryModel>()
    private val newProductItemAdapter = ItemAdapter<ProductModel>()
    private val bestsellerProductItemAdapter = ItemAdapter<ProductModel>()
    private val discountProductItemAdapter = ItemAdapter<ProductModel>()
    private var fastAdapter = FastAdapter.with(itemAdapter)
    private var categoryFastAdapter = FastAdapter.with(categoryItemAdapter)
    private var newProductFastAdapter = FastAdapter.with(newProductItemAdapter)
    private var bestsellerProductFastAdapter = FastAdapter.with(bestsellerProductItemAdapter)
    private var discountProductFastAdapter = FastAdapter.with(discountProductItemAdapter)
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        initBindingParam(binding)


        homeViewModel.apply {
            addData()
            addCategoryData()
            addNewProductData(ProductType.NEW)
            addNewProductData(ProductType.DISCOUNT)
            addNewProductData(ProductType.BESTSELLER)
        }

        return binding.root
    }

    private fun initBindingParam(binding: FragmentHomeBinding) {
        binding.apply {
            signatureList.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = fastAdapter
            }
            categoryList.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = categoryFastAdapter
            }
            newList.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = newProductFastAdapter
            }
            bestsellerList.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = bestsellerProductFastAdapter
            }
            discountList.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = discountProductFastAdapter
            }


            viewPagerAdapter = ViewPagerAdapter(this@HomeFragment)
            pager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, pager) { _, _ -> }.attach()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHandler()

        newProductFastAdapter.addEventHook(ProductModel.AddFavouriteClickEvent())
        discountProductFastAdapter.addEventHook(ProductModel.AddFavouriteClickEvent())
        bestsellerProductFastAdapter.addEventHook(ProductModel.AddFavouriteClickEvent())

        homeViewModel.apply {
            signatureList.observe(viewLifecycleOwner, { list ->
                itemAdapter.add(list)
            })
            categoryList.observe(viewLifecycleOwner, { categoryList ->
                categoryItemAdapter.add(categoryList)
            })
            newProductList.observe(viewLifecycleOwner, { list ->
                newProductItemAdapter.add(list)
            })
            discountProductList.observe(viewLifecycleOwner, { list ->
                discountProductItemAdapter.add(list)
            })
            bestsellerProductList.observe(viewLifecycleOwner, { list ->
                bestsellerProductItemAdapter.add(list)
            })
        }

    }

    private fun initHandler() {
        object : Runnable {
            override fun run() {
                if (binding.pager.currentItem + 1 != viewPagerAdapter.itemCount) {
                    binding.pager.setCurrentItem(binding.pager.currentItem + 1, true)
                } else {
                    binding.pager.setCurrentItem(0, true)
                }
                Handler(Looper.getMainLooper()).postDelayed(this, 5000)
            }
        }.also { runnable = it }
        Handler(Looper.getMainLooper()).postDelayed(runnable, 5000)
    }

    override fun onPause() {
        super.onPause()
        Handler(Looper.getMainLooper()).removeCallbacks(runnable)
    }
}