package com.example.pamuas_080.ui.view.villa

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
import com.example.pamuas_080.ui.navigation.DestinasiInsertVilla
import com.example.pamuas_080.ui.viewmodel.PenyediaViewModel
import com.example.pamuas_080.ui.viewmodel.villa.InsertVillaUiEvent
import com.example.pamuas_080.ui.viewmodel.villa.InsertVillaUiState
import com.example.pamuas_080.ui.viewmodel.villa.InsertVillaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryVillaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertVillaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = DestinasiInsertVilla.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertVillaUiState = viewModel.uiState,
            onVillaValueChange = viewModel::updateInsertVillaState,
            onSaveClick = {
                coroutineScope.launch {
                    // Pastikan ID villa valid
                    val id_villa = viewModel.uiState.insertVillaUiEvent.id_villa
                    if (id_villa != 0) {
                        viewModel.insertVilla()
                        navigateBack()
                    } else {
                        // Menampilkan pesan error jika ID tidak valid
                        // Bisa tambahkan dialog atau snackbar di sini
                    }
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
    insertVillaUiState: InsertVillaUiState,
    onVillaValueChange: (InsertVillaUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertVillaUiEvent = insertVillaUiState.insertVillaUiEvent,
            onValueChange = onVillaValueChange,
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
    insertVillaUiEvent: InsertVillaUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertVillaUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertVillaUiEvent.nama_villa,
            onValueChange = { onValueChange(insertVillaUiEvent.copy(nama_villa = it)) },
            label = { Text("Nama Villa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertVillaUiEvent.id_villa.toString(),
            onValueChange = { newValue ->
                // Pastikan input ID dikonversi menjadi Int jika valid
                val newIdVilla = newValue.toIntOrNull() ?: 0
                onValueChange(insertVillaUiEvent.copy(id_villa = newIdVilla))
            },
            label = { Text("ID Villa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertVillaUiEvent.alamat,
            onValueChange = { onValueChange(insertVillaUiEvent.copy(alamat = it)) },
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertVillaUiEvent.kamar_tersedia.toString(),
            onValueChange = { newValue ->
                val newKamarTersedia = newValue.toIntOrNull() ?: 0
                onValueChange(insertVillaUiEvent.copy(kamar_tersedia = newKamarTersedia))
            },
            label = { Text("Kamar Tersedia") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
