package uz.gita.contactappfirebase.presentation.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactappfirebase.R
import uz.gita.contactappfirebase.data.model.ContactData
import uz.gita.contactappfirebase.databinding.DialogEditContactBinding

class EditContactDialog constructor(private val data: ContactData) : DialogFragment(R.layout.dialog_edit_contact) {
    private val binding by viewBinding(DialogEditContactBinding::bind)
    private var onClickSaveListener: ((ContactData) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }

        binding.textInputName.setText(data.name)
        binding.textInputNumber.setText(data.phone)

        binding.buttonSave.setOnClickListener {
            onClickSaveListener?.invoke(
                ContactData(
                    id = data.id,
                    name = binding.textInputName.text.toString(),
                    phone = binding.textInputNumber.text.toString()
                )
            )
            dismiss()
        }
    }

    fun setOnClickSaveListener(block: (ContactData) -> Unit) {
        onClickSaveListener = block
    }
}