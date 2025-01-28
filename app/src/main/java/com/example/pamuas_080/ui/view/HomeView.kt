package com.example.pamuas_080.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    onNavigatePelanggan: () -> Unit,
    onNavigateVilla: () -> Unit,
    onNavigateReservasi: () -> Unit,
    onNavigateReview: () -> Unit,
    onBack: () -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {

        }
    ) { innerPadding ->
        BodyHome(
            modifier = Modifier.padding(innerPadding),
            onPelangganClick = {
                onNavigateReview()
            },
            onVillaClick = {
                onNavigateVilla()
            },
            onReservasiClick = {
                onNavigateReservasi()
            },
            onReviewClick = {
                onNavigateReview()
            }
        )
    }
}

@Composable
fun BodyHome(
    modifier: Modifier = Modifier,
    onPelangganClick: () -> Unit = {},
    onVillaClick: () -> Unit = {},
    onReservasiClick: () -> Unit = {},
    onReviewClick: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            CardMenu(
                namaMenu = "Villa",
                onClick = onVillaClick,
                namaIcon = Icons.Default.Home
            )
            CardMenu(
                namaMenu = "Pelanggan",
                onClick = onPelangganClick,
                namaIcon = Icons.Default.Person
            )
        }
        Row {
            CardMenu(
                namaMenu = "Reservasi",
                onClick = onReservasiClick,
                namaIcon = Icons.Default.Home
            )
            CardMenu(
                namaMenu = "Review",
                onClick = onReviewClick,
                namaIcon = Icons.Default.Star
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMenu(
    namaMenu: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    namaIcon: ImageVector
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .width(188.dp)
            .height(230.dp)
            .padding(8.dp),
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFF42A5F5))
                .fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Icon(
                    imageVector = namaIcon,
                    contentDescription = "",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = namaMenu,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}