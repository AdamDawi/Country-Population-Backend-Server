package example.com.di

import CountryRepository
import example.com.repository.CountryRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<CountryRepository>{
        CountryRepositoryImpl()
    }
}