package com.example.pamuas_080.ui.view.reservasi

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pamuas_080.ui.customwidget.CstTopAppBar
import com.example.pamuas_080.ui.navigation.DestinasiUpdateReservasi
import com.example.pamuas_080.ui.viewmodel.PenyediaViewModel
import com.example.pamuas_080.ui.viewmodel.reservasi.UpdateReservasiViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreenReservasi(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateReservasiViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = DestinasiUpdateReservasi.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        com.example.pamuas_080.ui.view.reservasi.EntryBody(
            modifier = Modifier.padding(padding),
            insertUiState = viewModel.updateReservasiUiState,
            onReservasiValueChange = viewModel::updateInsertReservasiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateReservasi()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}
