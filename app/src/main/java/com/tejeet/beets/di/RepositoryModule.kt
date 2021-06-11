package com.tejeet.beets.di

import com.tejeet.beets.mock.Mock
import com.tejeet.beets.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun providesDataRepository(mock: Mock): DataRepository {
        return DataRepository(mock)
    }
}