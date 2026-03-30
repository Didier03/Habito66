package com.example.habito66.di

import androidx.room.Room
import com.example.habito66.core.network.createKtorClient
import com.example.habito66.data.local.HabitDatabase
import com.example.habito66.data.local.dao.HabitDao
import com.example.habito66.data.remote.api.KtorQuoteRemoteDataSource
import com.example.habito66.data.remote.api.QuoteRemoteDataSource
import com.example.habito66.data.repository.HabitRepositoryImpl
import com.example.habito66.data.repository.QuoteRepositoryImpl
import com.example.habito66.domain.repository.QuoteRepository
import com.example.habito66.domain.usecase.GetDailyQuoteUseCase
import com.example.habito66.presentation.habits.CreateHabitViewModel
import com.example.habito66.presentation.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single<HabitDatabase> {
        Room.databaseBuilder(
            androidContext(),
            HabitDatabase::class.java,
            "habito66_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single<HabitDao> {
        val database = get<HabitDatabase>()
        database.habitDao()
    }
}
val networkModule = module {
    // Cliente específico para ZenQuotes
    single(named("ZenQuotesClient")) {
        createKtorClient(baseUrl = "https://zenquotes.io/api/")
    }
    /** El día de mañana, tu propio backend:
        single(named("MyBackendClient")) {
        createKtorClient(baseUrl = "https://api.mi-habit-tracker.com/v1/")
    }*/
}

val remoteDataSourceModule = module {
    // Le decimos a Koin: "Inyecta el cliente llamado 'ZenQuotesClient' aquí"
    single<QuoteRemoteDataSource> {
        KtorQuoteRemoteDataSource(httpClient = get(named("ZenQuotesClient")))
    }
}
val repositoryModule = module {
    singleOf(::QuoteRepositoryImpl) bind QuoteRepository::class
    singleOf(::HabitRepositoryImpl)
}

val useCaseModule = module {
    factoryOf(::GetDailyQuoteUseCase)
}

val presentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::CreateHabitViewModel)
}

val appModules = listOf(
    databaseModule,
    networkModule,
    remoteDataSourceModule,
    repositoryModule,
    useCaseModule,
    presentationModule
)