package com.example.pamuas_080.ui.view.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.pamuas_080.model.Review
import com.example.pamuas_080.ui.customwidget.CstTopAppBar
import com.example.pamuas_080.ui.navigation.DestinasiHomeReview
import com.example.pamuas_080.ui.viewmodel.PenyediaViewModel
import com.example.pamuas_080.ui.viewmodel.review.HomeReviewUiState
import com.example.pamuas_080.ui.viewmodel.review.HomeReviewViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenReview(
    navigateToltemEntry: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeReviewViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = DestinasiHomeReview.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getReview()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToltemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Review")
            }
        },
    ) { innerPadding ->
        HomeStatus(
            homeUiState = viewModel.reviewUiState,
            retryAction = { viewModel.getReview() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeReviewUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit
) {
    when (homeUiState) {
        is HomeReviewUiState.Loading -> {
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is HomeReviewUiState.Success -> {
            if (homeUiState.review.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Review")
                }
            } else {
                ReviewLayout(
                    review = homeUiState.review,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.id_review) }
                )
            }
        }
        is HomeReviewUiState.Error -> {
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
fun ReviewLayout(
    review: List<Review>,
    modifier: Modifier = Modifier,
    onDetailClick: (Review) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(review) { review ->
            ReviewCard(
                review = review,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(review) } // pastikan dipanggil dengan benar
            )
        }
    }
}
@Composable
fun ReviewCard(
    review: Review,
    modifier: Modifier = Modifier
) {
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
                // Menampilkan review
                Text(
                    text = review.id_reservasi.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = review.id_review.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = review.nilai,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = review.komentar,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
