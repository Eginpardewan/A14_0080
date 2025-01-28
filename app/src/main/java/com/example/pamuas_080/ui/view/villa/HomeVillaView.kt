package com.example.pamuas_080.ui.view.villa

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pamuas_080.R
import com.example.pamuas_080.model.Villa
import com.example.pamuas_080.ui.customwidget.CstTopAppBar
import com.example.pamuas_080.ui.navigation.DestinasiHomeVilla
import com.example.pamuas_080.ui.viewmodel.PenyediaViewModel
import com.example.pamuas_080.ui.viewmodel.villa.HomeVillaUiState
import com.example.pamuas_080.ui.viewmodel.villa.HomeVillaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenVilla(
    navigateToEntry: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeVillaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = DestinasiHomeVilla.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getVilla()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Villa")
            }
        },
    ) { innerPadding ->
        HomeStatus(
            homeUiState = viewModel.villaUiState,
            retryAction = { viewModel.getVilla() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { villa ->
                viewModel.deleteVilla(villa.id_villa)
                viewModel.getVilla()
            }
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeVillaUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Villa) -> Unit = {},
    onDetailClick: (Int) -> Unit  // Mengubah parameter menjadi Int
) {
    when (homeUiState) {
        is HomeVillaUiState.Loading -> {
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is HomeVillaUiState.Success -> {
            if (homeUiState.villa.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Villa")
                }
            } else {
                VillaLayout(
                    villa = homeUiState.villa,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it) },
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        }
        is HomeVillaUiState.Error -> {
            OnError(retryAction, modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.villa1),
        contentDescription = stringResource(R.string.app_name)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.villa1), contentDescription = "")
        Text(text = stringResource(id = R.string.app_name), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(id = R.string.app_name))
        }
    }
}

@Composable
fun VillaLayout(
    villa: List<Villa>,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit,  // Mengubah parameter menjadi Int
    onDeleteClick: (Villa) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(villa) { villa ->
            VillaCard(
                villa = villa,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(villa.id_villa) },
                onDeleteClick = {
                    onDeleteClick(villa)
                }
            )
        }
    }
}

@Composable
fun VillaCard(
    villa: Villa,
    modifier: Modifier = Modifier,
    onDeleteClick: (Villa) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = villa.nama_villa,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(villa) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Villa")
                }
            }
            Text(
                text = "ID: ${villa.id_villa}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Alamat: ${villa.alamat}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Kamar Tersedia: ${villa.kamar_tersedia}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
