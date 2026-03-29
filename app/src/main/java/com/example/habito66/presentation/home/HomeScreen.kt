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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habito66.presentation.home.components.CitasBottomHomeSection
import com.example.habito66.presentation.home.components.HeaderHomeSection
import com.example.habito66.presentation.home.components.ItemsListHomeSection
import com.example.habito66.presentation.home.components.StatsHomeSection
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    onNavigateToNewHabit: (String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val quoteState by viewModel.quoteState.collectAsStateWithLifecycle()
    val habitsList by viewModel.habitsList.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                onItemClick = { habitId ->
                    onNavigateToNewHabit(habitId)
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}





