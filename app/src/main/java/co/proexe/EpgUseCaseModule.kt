package co.proexe

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class EpgUseCaseModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun provideEpgUseCase(epgUseCaseImpl: EpgUseCaseImpl): EpgUseCase
}