package co.proexe

import co.proexe.model.repository.LocalEpgRepository
import co.proexe.model.repository.TimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EpgModule {

    @Provides
    @Singleton
    fun provideLocalEpgRepository() = LocalEpgRepository()

    @Provides
    @Singleton
    fun provideTimeRepository() = TimeRepository()
}