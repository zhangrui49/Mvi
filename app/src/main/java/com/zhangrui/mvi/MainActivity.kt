package com.zhangrui.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle

class MainActivity : AppCompatActivity() {

    private val viewMode by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeStateProp(
            viewMode.uiData.state,
            MainViewModel.MainUiState::loadMore,
            Lifecycle.State.CREATED
        ) {

        }
        observeStateProp(
            viewMode.uiData.state,
            MainViewModel.MainUiState::data
        ) {

        }

        viewMode.uiData.run {
            state.partialCollect(this@MainActivity) {
                collect(MainViewModel.MainUiState::isLoading) {

                }
                collect(MainViewModel.MainUiState::isEmpty) {

                }
                collect(MainViewModel.MainUiState::data) {

                }
            }
        }

    }
}