package com.example.pamuas_080.ui.view.review

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
import com.example.pamuas_080.ui.navigation.DestinasiNavigasi
import com.example.pamuas_080.ui.viewmodel.PenyediaViewModel
import com.example.pamuas_080.ui.viewmodel.review.UpdateReviewViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateReview: DestinasiNavigasi {
    override val route = "update review"
    override val titleRes = "Update Review"
    const val ID_REVIEW = "id_review"
    val routesWithArg = "$route/{$ID_REVIEW}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreenReview(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate:()-> Unit,
    viewModel: UpdateReviewViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = DestinasiUpdateReview.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ){padding ->
        com.example.pamuas_080.ui.view.review.EntryBody(
            modifier = Modifier.padding(padding),
            insertReviewUiState = viewModel.updateReviewUiState,
            onReviewValueChange = viewModel::updateInsertReviewState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateReview()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}
