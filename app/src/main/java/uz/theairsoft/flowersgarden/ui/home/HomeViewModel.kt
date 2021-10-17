package uz.theairsoft.flowersgarden.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.theairsoft.flowersgarden.R

class HomeViewModel : ViewModel() {
    var signatureList = MutableLiveData<ArrayList<SignatureModel>>()
    var categoryList = MutableLiveData<ArrayList<CategoryModel>>()
    var newProductList = MutableLiveData<ArrayList<ProductModel>>()
    var bestsellerProductList = MutableLiveData<ArrayList<ProductModel>>()
    var discountProductList = MutableLiveData<ArrayList<ProductModel>>()
    private var list =
        listOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4)
    private var categoryImageList = listOf(R.drawable.image_42, R.drawable.image_43)
    private var productImageList = listOf(
        R.drawable.img_placeholder__4_,
        R.drawable.img_placeholder__5_,
        R.drawable.img_placeholder__6_,
        R.drawable.img_placeholder__7_,
        R.drawable.img_placeholder__8_,
        R.drawable.img_placeholder__9_,
        R.drawable.img_placeholder__10_,
        R.drawable.img_placeholder__11_,
        R.drawable.img_placeholder__12_,
    )


    fun addData() {
        val list = ArrayList<SignatureModel>()
        repeat(8) { id ->
            list.add(SignatureModel(id, this.list[id % 4]))
        }
        signatureList.postValue(list)
    }

    fun addCategoryData() {
        val list = ArrayList<CategoryModel>()
        repeat(5) { id ->
            list.add(
                CategoryModel(
                    id,
                    "Сезонные \nцветы $id",
                    categoryImageList[id % 2]
                )
            )
        }
        categoryList.postValue(list)
    }

    fun addNewProductData(productType: ProductType) {
        val list = ArrayList<ProductModel>()
        repeat(9) { id ->
            list.add(ProductModel(id, productType, productImageList.random(), false))
        }
        when (productType) {
            ProductType.NEW -> newProductList.postValue(list)
            ProductType.DISCOUNT -> discountProductList.postValue(list)
            ProductType.BESTSELLER -> bestsellerProductList.postValue(list)
        }
    }


}