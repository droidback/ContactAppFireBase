package uz.gita.contactappfirebase.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.contactappfirebase.data.model.ContactData

interface ContactViewModel {
    val errorLiveData: LiveData<String>
    val notConnectionLiveData: LiveData<Unit>

    val loadContactLiveData: LiveData<List<ContactData>>
    val openAddContactDialogLiveData: LiveData<Unit>
    val eventContactDialogLiveData: LiveData<ContactData>
    val editContactDialogLiveData: LiveData<ContactData>
    val deleteContactDialogLiveData: LiveData<Long>

    fun onClickAddDialog()
    fun onClickEventDialog(data: ContactData)
    fun onClickDeleteDialog(id: Long)
    fun onClickEditDialog(data: ContactData)

    fun onClickAddContact(data: ContactData)
    fun onClickDeleteContact(id: Long)
    fun onClickEditContact(data: ContactData)
}