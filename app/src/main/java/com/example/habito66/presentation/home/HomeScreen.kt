package com.example.habito66.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habito66.presentation.habits.HomeUiEvent
import com.example.habito66.presentation.home.components.CitasBottomHomeSection
import com.example.habito66.presentation.home.components.HeaderHomeSection
import com.example.habito66.presentation.home.components.ItemsListHomeSection
import com.example.habito66.presentation.home.components.StatsHomeSection
import com.google.protobuf.LazyStringArrayList.emptyList
import org.koin.androidx.compose.koinViewModel
import kotlin.collections.emptyList

@Composable
fun HomeScreen(
    onNavigateToNewHabit: (String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val quoteState by viewModel.quoteState.collectAsStateWithLifecycle()
    val habitsList by viewModel.habitsList.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUiEvent.ShowUndoSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = "Hábito '${event.habit.name}' eliminado",
                        actionLabel = "Deshacer",
                        duration = SnackbarDuration.Short
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.undoDeleteHome()
                    }
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateToNewHabit("new_habit")
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Create new habit"
                )
            }
        },
        bottomBar = {CitasBottomHomeSection(quoteState)}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            HeaderHomeSection()

            StatsHomeSection()

            ItemsListHomeSection(
                habits = habitsList,
                onItemClick = { habitId -> onNavigateToNewHabit(habitId) },
                onDeleteHabit = { habitToDelete -> viewModel.deleteHabitHome(habitToDelete) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}





