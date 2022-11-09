package co.proexe

import android.content.Context
import co.proexe.model.repository.LocalEpgRepository
import co.proexe.model.repository.TimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EpgModule {

    @Provides
    @Singleton
    fun provideLocalEpgRepository(@ApplicationContext context: Context) = LocalEpgRepository(context)

    @Provides
    @Singleton
    fun provideTimeRepository(@ApplicationContext context: Context) = TimeRepository(context)
}