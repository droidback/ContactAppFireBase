package uz.gita.contactappfirebase.presentation.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.contactappfirebase.R
import uz.gita.contactappfirebase.databinding.EventDialogBinding

class EventContactDialog : BottomSheetDialogFragment() {
    private val binding by viewBinding(EventDialogBinding::bind)
    private var onClickDeleteListener: (() -> Unit)? = null
    private var onClickEditListener: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.event_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lineDelete.setOnClickListener {
            onClickDeleteListener?.invoke()
            dismiss()
        }

        binding.lineEdit.setOnClickListener {
            onClickEditListener?.invoke()
            dismiss()
        }
    }

    fun setOnClickEditListener(block: () -> Unit) {
        onClickEditListener = block
    }

    fun setOnClickDeleteListener(block: () -> Unit) {
        onClickDeleteListener = block
    }
}