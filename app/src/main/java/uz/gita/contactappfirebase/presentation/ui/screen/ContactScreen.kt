package uz.gita.contactappfirebase.presentation.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.contactappfirebase.R
import uz.gita.contactappfirebase.data.model.ContactData
import uz.gita.contactappfirebase.databinding.ScreenContactBinding
import uz.gita.contactappfirebase.presentation.ui.adapter.ContactAdapter
import uz.gita.contactappfirebase.presentation.ui.dialog.AddContactDialog
import uz.gita.contactappfirebase.presentation.ui.dialog.DeleteContactDialog
import uz.gita.contactappfirebase.presentation.ui.dialog.EditContactDialog
import uz.gita.contactappfirebase.presentation.ui.dialog.EventContactDialog
import uz.gita.contactappfirebase.presentation.viewmodel.ContactViewModel
import uz.gita.contactappfirebase.presentation.viewmodel.impl.ContactViewModelImpl

@AndroidEntryPoint
class ContactScreen : Fragment(R.layout.screen_contact) {
    private val binding by viewBinding(ScreenContactBinding::bind)
    private val viewModel: ContactViewModel by viewModels<ContactViewModelImpl>()
    private val adapter = ContactAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.contactList.adapter = adapter
        binding.contactList.layoutManager = LinearLayoutManager(requireContext())

        binding.addButton.setOnClickListener {
            viewModel.onClickAddDialog()
        }

        adapter.setOnClickMoreButtonListener {
            viewModel.onClickEventDialog(it)
        }

        viewModel.openAddContactDialogLiveData.observe(viewLifecycleOwner, openContactDialogObserver)
        viewModel.eventContactDialogLiveData.observe(viewLifecycleOwner, eventContactDialogObserver)
        viewModel.deleteContactDialogLiveData.observe(viewLifecycleOwner, deleteContactObserver)
        viewModel.editContactDialogLiveData.observe(viewLifecycleOwner, editContactDialogObserver)
        viewModel.loadContactLiveData.observe(viewLifecycleOwner, loadContactObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
    }

    private val loadContactObserver = Observer<List<ContactData>> {
        adapter.submitList(it)
    }

    private val openContactDialogObserver = Observer<Unit> {
        val addDialog = AddContactDialog()

        addDialog.setOnClickSaveListener {
            Log.d("TTT", "ContactScreen")
            viewModel.onClickAddContact(it)
        }

        addDialog.isCancelable = false
        addDialog.show(childFragmentManager, "AddDialog")
    }

    private val eventContactDialogObserver = Observer<ContactData> {
        val eventDialog = EventContactDialog()

        eventDialog.setOnClickDeleteListener {
            viewModel.onClickDeleteDialog(it.id)
        }

        eventDialog.setOnClickEditListener {
            viewModel.onClickEditDialog(it)
        }

        eventDialog.show(childFragmentManager, "EventDialog")
    }

    private val deleteContactObserver = Observer<Long> {
        val deleteDialog = DeleteContactDialog()

        deleteDialog.setOnClickOkListener {
            viewModel.onClickDeleteContact(it)
        }

        deleteDialog.isCancelable = false
        deleteDialog.show(childFragmentManager, "DeleteDialog")
    }

    private val editContactDialogObserver = Observer<ContactData> {
        val editDialog = EditContactDialog(it)

        editDialog.setOnClickSaveListener { newData ->
            viewModel.onClickEditContact(newData)
        }
        editDialog.isCancelable = false
        editDialog.show(childFragmentManager, "EditDialog")
    }

    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
    }
}