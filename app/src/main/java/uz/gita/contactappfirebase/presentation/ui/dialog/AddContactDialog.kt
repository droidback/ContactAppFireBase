package uz.gita.contactappfirebase.presentation.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactappfirebase.R
import uz.gita.contactappfirebase.data.model.ContactData
import uz.gita.contactappfirebase.databinding.DialogAddContactBinding

class AddContactDialog : DialogFragment(R.layout.dialog_add_contact) {
    private val binding by viewBinding(DialogAddContactBinding::bind)
    private var onClickSaveListener: ((ContactData) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }

        binding.buttonSave.setOnClickListener {
            onClickSaveListener?.invoke(
                ContactData(
                    id = System.currentTimeMillis(),
                    name = binding.textInputName.text.toString() + " ",
                    phone = binding.textInputNumber.text.toString() + " "
                )
            )
            dismiss()
        }
    }

    fun setOnClickSaveListener(block: (ContactData) -> Unit) {
        onClickSaveListener = block
    }
}