package com.santiagocoronel.mozpertest

import android.content.Context
import androidx.room.Room
import com.santiagocoronel.mozpertest.features.home.data.repository.EmployeeRepositoryImpl
import com.santiagocoronel.mozpertest.features.home.data.repository.local.db.MozperDataBase
import com.santiagocoronel.mozpertest.features.home.data.repository.network.MozperTestApi
import com.santiagocoronel.mozpertest.features.home.domain.EmployeeRepository
import com.santiagocoronel.mozpertest.features.home.domain.GetEmployeesUseCase
import com.santiagocoronel.mozpertest.features.home.presenter.view.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val mozperTestModule: Module = module {

    //database
    single { provideMozperTestDataBase(get()) }

    //viewmodel
    viewModel { HomeViewModel(get()) }

    //usecase
    single { provideGetEmployeesUseCase(get(), get()) }

    //repository
    single<EmployeeRepository> { provideEmployeeRepository(get()) }

    //api
    single { provideMozperTestApi(get()) }

}

fun provideMozperTestDataBase(applicationContext: Context): MozperDataBase {
    return Room.databaseBuilder(applicationContext, MozperDataBase::class.java, "MozperDataBase")
        .build()
}

fun provideGetEmployeesUseCase(
    repository: EmployeeRepository,
    dataBase: MozperDataBase
): GetEmployeesUseCase {
    return GetEmployeesUseCase(repository, dataBase)
}

fun provideEmployeeRepository(api: MozperTestApi): EmployeeRepositoryImpl {
    return EmployeeRepositoryImpl(api)
}

fun provideMozperTestApi(retrofit: Retrofit): MozperTestApi {
    return retrofit.create(MozperTestApi::class.java)
}