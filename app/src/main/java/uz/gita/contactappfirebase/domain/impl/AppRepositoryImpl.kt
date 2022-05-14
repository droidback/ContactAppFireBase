package uz.gita.contactappfirebase.domain.impl

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.gita.contactappfirebase.data.model.ContactData
import uz.gita.contactappfirebase.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val contactReference: DatabaseReference) : AppRepository {

    override fun getAllContacts() = callbackFlow<Result<List<ContactData>>> {
        val callback = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.children.map {
                    it.getValue(ContactData::class.java) ?: return
                }
                trySendBlocking(Result.success(data))
                    .onFailure { throwable -> trySendBlocking(Result.failure(Exception(throwable.toString()))) }
            }

            override fun onCancelled(error: DatabaseError) {
                trySendBlocking(Result.failure(error.toException()))
            }
        }
        contactReference.addValueEventListener(callback)
        awaitClose { contactReference.removeEventListener(callback) }
    }.flowOn(Dispatchers.IO)

    override fun updateContact(data: ContactData) {
        contactReference.child(data.id.toString()).setValue(data)
    }

    override fun addContact(data: ContactData) {
        Log.d("TTT", "addContact")
        contactReference.child(data.id.toString()).setValue(data)
    }

    override fun deleteContact(id: Long) {
        contactReference.child(id.toString()).removeValue()
    }
}