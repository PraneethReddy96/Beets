package com.tejeet.beets.di

import com.app.tiktok.mock.Mock
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