package uz.gita.contactappfirebase.presentation.viewmodel.impl


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.gita.contactappfirebase.data.model.ContactData
import uz.gita.contactappfirebase.domain.AppRepository
import uz.gita.contactappfirebase.presentation.viewmodel.ContactViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModelImpl @Inject constructor(private val repository: AppRepository) : ViewModel(), ContactViewModel {

    override val errorLiveData = MutableLiveData<String>()
    override val notConnectionLiveData = MutableLiveData<Unit>()
    override val loadContactLiveData = MutableLiveData<List<ContactData>>()
    override val openAddContactDialogLiveData = MutableLiveData<Unit>()
    override val eventContactDialogLiveData = MutableLiveData<ContactData>()
    override val editContactDialogLiveData = MutableLiveData<ContactData>()
    override val deleteContactDialogLiveData = MutableLiveData<Long>()

    init {
        repository.getAllContacts().onEach {
            it.onSuccess { list ->
                loadContactLiveData.value = list
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.toString()
            }
        }.launchIn(viewModelScope)
    }

    override fun onClickAddDialog() {
        openAddContactDialogLiveData.value = Unit
    }

    override fun onClickEventDialog(data: ContactData) {
        eventContactDialogLiveData.value = data
    }

    override fun onClickDeleteDialog(id: Long) {
        deleteContactDialogLiveData.value = id
    }

    override fun onClickEditDialog(data: ContactData) {
        editContactDialogLiveData.value = data
    }

    override fun onClickAddContact(data: ContactData) {
        Timber.d("TTT", "ViewModelAddContact")
        repository.addContact(data)
    }

    override fun onClickDeleteContact(id: Long) {
        repository.deleteContact(id)
    }

    override fun onClickEditContact(data: ContactData) {
        repository.updateContact(data)
    }

}