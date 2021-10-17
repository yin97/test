package uz.theairsoft.flowersgarden.ui.home

import android.view.View
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import uz.theairsoft.flowersgarden.R

class SignatureModel(
    val id: Int,
    val image: Int
) : AbstractItem<SignatureModel.VH>() {

    override var identifier: Long
        get() = id.toLong()
        set(value) {}

    class VH(itemView: View) : FastAdapter.ViewHolder<SignatureModel>(itemView) {
        override fun bindView(item: SignatureModel, payloads: List<Any>) {
            Glide.with(itemView.context)
                .load(item.image)
                .into(itemView.findViewById(R.id.iv_signature_item))
        }

        override fun unbindView(item: SignatureModel) {

        }

    }

    override val layoutRes: Int
        get() = R.layout.item_signature
    override val type: Int
        get() = 0

    override fun getViewHolder(v: View): VH = VH(v)
}