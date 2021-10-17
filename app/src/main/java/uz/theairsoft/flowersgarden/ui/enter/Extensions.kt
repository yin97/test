package uz.theairsoft.flowersgarden.ui.enter

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy
import uz.theairsoft.flowersgarden.R
import java.util.ArrayList

fun EditText.formatPhoneMask(icon: Boolean = false, button: Button) {
    val phoneFormat: MutableList<String> = ArrayList()
    phoneFormat.add("+998 [00] [000] [00] [00]")

    val listener =
        MaskedTextChangedListener.installOn(
            this,
            "+998 [00] [000] [00] [00]",
            phoneFormat, AffinityCalculationStrategy.WHOLE_STRING,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    if (icon) {
                        if (maskFilled)
                            this@formatPhoneMask.setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.ic_check_success,
                                0
                            )
                        else
                            this@formatPhoneMask.setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.ic_check_error,
                                0
                            )
                        button.isEnabled = maskFilled
                    }

                }
            }
        )
}

fun EditText.getMaskedPhoneWithoutSpace(): String {
    var phone = this.text.toString()
    if (phone.startsWith("+"))
        phone = phone.substring(1, phone.length)
    return phone.replace(" ", "")
}

fun EditText.smsCodeMask(button: Button) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            button.isEnabled = s != null && s.length == 4
        }

    })
}