package org.garius.mytodo.app.di




import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.garius.mytodo.todo.data.database.DatabaseFactory
import org.garius.mytodo.todo.data.database.TaskDatabase
import org.garius.mytodo.todo.data.repository.ImplTaskRepository
import org.garius.mytodo.todo.domain.TaskRepository
import org.garius.mytodo.todo.presentation.add.AddTaskScreenRoot
import org.garius.mytodo.todo.presentation.add.AddTaskScreenViewModel
import org.garius.mytodo.todo.presentation.dashboard.DashboardViewModel
import org.garius.mytodo.todo.presentation.home.HomeScreenViewModel
import org.garius.mytodo.todo.presentation.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind


import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<TaskDatabase>().taskDao }

    singleOf(::ImplTaskRepository).bind<TaskRepository>()



//    singleOf(::UserDataValidator)
//
    viewModelOf(::SplashViewModel)
//    viewModelOf(::WelcomeViewModel)
//    viewModelOf(::LoginViewModel)
//    viewModelOf(::RegisterViewModel)
//    viewModelOf(::ForgotPasswordViewModel)
//    viewModelOf(::ForgotPasswordEmailViewModel)
//    viewModelOf(::OTPVerificationViewModel)
//    viewModelOf(::ResetPasswordViewModel)
//
//
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::DashboardViewModel)
    viewModelOf(::AddTaskScreenViewModel)









}