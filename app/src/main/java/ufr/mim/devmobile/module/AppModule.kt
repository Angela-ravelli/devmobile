package ufr.mim.devmobile.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ufr.mim.devmobile.viewmodel.DataStoreManager

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}