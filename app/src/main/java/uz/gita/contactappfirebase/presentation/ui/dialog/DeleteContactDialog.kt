package uz.gita.contactappfirebase.presentation.ui.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.contactappfirebase.R
import uz.gita.contactappfirebase.databinding.DialogDeleteContactBinding

class DeleteContactDialog : DialogFragment(R.layout.dialog_delete_contact) {
    private val binding by viewBinding(DialogDeleteContactBinding::bind)
    private var onClickOkListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        binding.buttonOk.setOnClickListener {
            onClickOkListener?.invoke()
            dismiss()
        }
    }

    fun setOnClickOkListener(block: () -> Unit) {
        onClickOkListener = block
    }
}