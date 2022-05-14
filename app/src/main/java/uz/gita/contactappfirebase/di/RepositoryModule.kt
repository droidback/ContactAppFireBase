package uz.gita.contactappfirebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.contactappfirebase.domain.AppRepository
import uz.gita.contactappfirebase.domain.impl.AppRepositoryImpl
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
interface RepositoryModule {

    @[Binds Singleton]
    fun getRepository(impl: AppRepositoryImpl): AppRepository
}