package uz.theairsoft.flowersgarden.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.mikepenz.fastadapter.listeners.ClickEventHook
import uz.theairsoft.flowersgarden.R

enum class ProductType {
    NEW,
    DISCOUNT,
    BESTSELLER
}

class ProductModel(
    val id: Int,
    val productType: ProductType,
    val image: Int,
    var isFavourite: Boolean
) : AbstractItem<ProductModel.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : FastAdapter.ViewHolder<ProductModel>(itemView) {
        override fun bindView(item: ProductModel, payloads: List<Any>) {
            Glide.with(itemView.context)
                .load(item.image)
                .into(itemView.findViewById(R.id.productImage))

            if (item.isFavourite) {
                itemView.findViewById<ImageView>(R.id.ic_favourite)
                    .setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                itemView.findViewById<ImageView>(R.id.ic_favourite)
                    .setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            when (item.productType) {
                ProductType.NEW -> {
                    itemView.findViewById<TextView>(R.id.product_type).apply {
                        setBackgroundResource(R.drawable.background_product_new_type)
                        text = "Новинка"
                    }
                }
                ProductType.DISCOUNT -> {
                    itemView.findViewById<TextView>(R.id.product_type).apply {
                        setBackgroundResource(R.drawable.background_product_discount_type)
                        text = "Скидка 20 %"
                    }
                }
                ProductType.BESTSELLER -> {
                    itemView.findViewById<TextView>(R.id.product_type).apply {
                        setBackgroundResource(R.drawable.background_product_bestseller_type)
                        text = "Топ"
                    }
                }
            }

        }

        override fun unbindView(item: ProductModel) {

        }

    }

    override val layoutRes: Int
        get() = R.layout.item_product
    override val type: Int
        get() = 0

    override fun getViewHolder(v: View): ProductViewHolder = ProductViewHolder(v)

    class AddFavouriteClickEvent() :
        ClickEventHook<ProductModel>() {
        override fun onClick(
            v: View,
            position: Int,
            fastAdapter: FastAdapter<ProductModel>,
            item: ProductModel
        ) {
            item.isFavourite = !item.isFavourite
            fastAdapter.notifyAdapterItemChanged(position)
        }

        override fun onBindMany(viewHolder: RecyclerView.ViewHolder): List<View>? {
            if (viewHolder is ProductViewHolder) {
                return listOf(viewHolder.itemView.findViewById(R.id.ic_favourite))
            }
            return super.onBindMany(viewHolder)
        }
    }

}