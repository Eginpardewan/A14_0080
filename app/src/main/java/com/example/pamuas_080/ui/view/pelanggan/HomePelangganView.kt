package com.example.pamuas_080.ui.view.pelanggan

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pamuas_080.R
import com.example.pamuas_080.model.Pelanggan
import com.example.pamuas_080.ui.customwidget.CstTopAppBar
import com.example.pamuas_080.ui.navigation.DestinasiHomePelanggan
import com.example.pamuas_080.ui.viewmodel.PenyediaViewModel
import com.example.pamuas_080.ui.viewmodel.pelanggan.HomePelangganViewModel
import com.example.pamuas_080.ui.viewmodel.pelanggan.HomeUiState




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPelanggan(
    navigateToltemEntry: ()-> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomePelangganViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = DestinasiHomePelanggan.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPelanggan()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToltemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pelanggan")
            }
        },
    ) {innerPadding ->
        HomeStatus(
            homeUiState = viewModel.pelangganUiState,
            retryAction = { viewModel.getPelanggan() }, modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick, onDeleteClick = {
                viewModel.deletePelanggan(it.id_pelanggan)
                viewModel.getPelanggan()
            }
        )

    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pelanggan) -> Unit = {},
    onDetailClick: (Int) -> Unit
){
    when (homeUiState){
        is HomeUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())


        is HomeUiState.Success ->
            if (homeUiState.pelanggan.isEmpty()){
                return Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "Tidak ada data Kontak")
                }
            }else{
                PelangganLayout(
                    pelanggan = homeUiState.pelanggan,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_pelanggan)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.villa1),
        contentDescription = stringResource(R.string.app_name)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier){
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
fun PelangganLayout(
    pelanggan: List<Pelanggan>,
    modifier: Modifier = Modifier,
    onDetailClick: (Pelanggan) -> Unit,
    onDeleteClick: (Pelanggan) -> Unit = {}
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pelanggan){ pelanggan ->
            PelangganCard(
                pelanggan = pelanggan,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pelanggan) },
                onDeleteClick = {
                    onDeleteClick(pelanggan)
                }
            )
        }
    }
}

@Composable
fun PelangganCard(
    pelanggan: Pelanggan,
    modifier: Modifier = Modifier,
    onDeleteClick:(Pelanggan) -> Unit = {}
){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = pelanggan.nama_pelanggan,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {onDeleteClick(pelanggan)}) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = null)
                }
                Text(
                    text = pelanggan.id_pelanggan.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = pelanggan.no_hp,
                    style = MaterialTheme.typography.titleMedium
                )
            }

        }
    }
}
