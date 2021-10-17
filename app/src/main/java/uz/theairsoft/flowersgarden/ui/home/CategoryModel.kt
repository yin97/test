package uz.theairsoft.flowersgarden.ui.home

import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import uz.theairsoft.flowersgarden.R

class CategoryModel(
    val id: Int,
    val text: String,
    val image: Int
) : AbstractItem<CategoryModel.CategoryViewHolder>() {

    override var identifier: Long
        get() = id.toLong()
        set(value) {}

    class CategoryViewHolder(itemView: View) : FastAdapter.ViewHolder<CategoryModel>(itemView) {
        override fun bindView(item: CategoryModel, payloads: List<Any>) {
            itemView.findViewById<TextView>(R.id.category_name).text = item.text

            Glide.with(itemView.context)
                .load(item.image)
                .into(itemView.findViewById(R.id.category_image))
        }

        override fun unbindView(item: CategoryModel) {

        }

    }

    override val layoutRes: Int
        get() = R.layout.item_category
    override val type: Int
        get() = 0

    override fun getViewHolder(v: View): CategoryViewHolder = CategoryViewHolder(v)
}