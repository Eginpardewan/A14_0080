package com.example.pamuas_080.ui.view.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pamuas_080.ui.customwidget.CstTopAppBar
import com.example.pamuas_080.ui.navigation.DestinasiInsertReview
import com.example.pamuas_080.ui.viewmodel.PenyediaViewModel
import com.example.pamuas_080.ui.viewmodel.review.InsertReviewUiEvent
import com.example.pamuas_080.ui.viewmodel.review.InsertReviewUiState
import com.example.pamuas_080.ui.viewmodel.review.InsertReviewViewModel
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryReviewScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertReviewViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = DestinasiInsertReview.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) {innerPadding ->
        EntryBody(
            insertReviewUiState = viewModel.uiState,
            onReviewValueChange = viewModel::updateInsertReviewState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertReview()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )

    }
}

@Composable
fun EntryBody(
    insertReviewUiState: InsertReviewUiState,
    onReviewValueChange: (InsertReviewUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        com.example.pamuas_080.ui.view.review.FormInput(
            insertReviewUiEvent = insertReviewUiState.insertReviewUiEvent,
            onValueChange = onReviewValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertReviewUiEvent: InsertReviewUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertReviewUiEvent) -> Unit = {},
    enabled: Boolean = true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {OutlinedTextField(
        value = insertReviewUiEvent.nilai,
        onValueChange = { onValueChange(insertReviewUiEvent.copy(nilai = it)) },
        label = { Text("Nilai") },
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true
    )
        OutlinedTextField(
            value = insertReviewUiEvent.komentar,
            onValueChange = { onValueChange(insertReviewUiEvent.copy(komentar = it)) },
            label = { Text("Komentar") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled){
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
