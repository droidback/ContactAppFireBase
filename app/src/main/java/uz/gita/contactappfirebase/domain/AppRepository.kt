package uz.gita.contactappfirebase.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.contactappfirebase.data.model.ContactData

interface AppRepository {
    fun getAllContacts(): Flow<Result<List<ContactData>>>

    fun updateContact(data: ContactData)
    fun addContact(data: ContactData)
    fun deleteContact(id: Long)
}