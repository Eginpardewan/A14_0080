package com.example.pamuas_080.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pamuas_080.ui.detail.DetailScreen
import com.example.pamuas_080.ui.view.HomeView
import com.example.pamuas_080.ui.view.pelanggan.EntryPelangganScreen
import com.example.pamuas_080.ui.view.pelanggan.HomeScreenPelanggan
import com.example.pamuas_080.ui.view.pelanggan.UpdateScreen
import com.example.pamuas_080.ui.view.reservasi.DetailScreenReservasi
import com.example.pamuas_080.ui.view.reservasi.EntryReservasiScreen
import com.example.pamuas_080.ui.view.reservasi.HomeScreenReservasi
import com.example.pamuas_080.ui.view.reservasi.UpdateScreenReservasi
import com.example.pamuas_080.ui.view.review.DetailScreenReview
import com.example.pamuas_080.ui.view.review.EntryReviewScreen
import com.example.pamuas_080.ui.view.review.HomeScreenReview
import com.example.pamuas_080.ui.view.review.UpdateScreenReview
import com.example.pamuas_080.ui.view.villa.DetailScreenVilla
import com.example.pamuas_080.ui.view.villa.EntryVillaScreen
import com.example.pamuas_080.ui.view.villa.HomeScreenVilla
import com.example.pamuas_080.ui.view.villa.UpdateScreenVilla

@Composable
fun PengelolaHalaman(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(route = DestinasiHome.route) {
            HomeView(
                modifier = Modifier,
                onNavigatePelanggan = { navController.navigate(DestinasiHomePelanggan.route) },
                onNavigateVilla = { navController.navigate(DestinasiHomeVilla.route) },
                onNavigateReservasi = { navController.navigate(DestinasiHomeReservasi.route) },
                onNavigateReview = { navController.navigate(DestinasiHomeReview.route)},
                onBack = {

                }
            )
        }

        composable(DestinasiHomePelanggan.route) {
            HomeScreenPelanggan(
                navigateToltemEntry = { navController.navigate(DestinasiInsertPelanggan.route) },
                onDetailClick = { id_pelanggan ->
                    navController.navigate("${DestinasiDetailPelanggan.route}/$id_pelanggan")
                },
                navigateBack = { navController.navigate(DestinasiHome.route) }
            )
        }

        composable(DestinasiInsertPelanggan.route) {
            EntryPelangganScreen(navigateBack = {
                navController.navigate(DestinasiHomePelanggan.route) {
                    popUpTo(DestinasiHomePelanggan.route) { inclusive = true }
                }
            })
        }

        composable(
            DestinasiDetailPelanggan.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPelanggan.ID_PELANGGAN) { type = NavType.IntType }) // Change to NavType.IntType
        ) {
            val id_pelanggan = it.arguments?.getInt(DestinasiDetailPelanggan.ID_PELANGGAN)
            id_pelanggan?.let { id ->
                DetailScreen(
                    onBackClick = { navController.popBackStack() },
                    onEditClick = { navController.navigate("${DestinasiUpdatePelanggan.route}/$id") },
                    id_pelanggan = id,
                    onDeleteClick = { navController.popBackStack() }
                )
            }
        }

        composable(
            route = DestinasiUpdatePelanggan.routesWithArg,
            arguments = listOf(navArgument(DestinasiUpdatePelanggan.ID_PELANGGAN) { type = NavType.IntType }) // Change to NavType.IntType
        ) { backStackEntry ->
            val id_pelanggan = backStackEntry.arguments?.getInt(DestinasiUpdatePelanggan.ID_PELANGGAN)
            id_pelanggan?.let {
                UpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomePelanggan.route) {
                            popUpTo(DestinasiHomePelanggan.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                )
            }
        }

        composable(DestinasiHomeVilla.route) {
            HomeScreenVilla(
                navigateToEntry = { navController.navigate(DestinasiInsertVilla.route) },
                onDetailClick = { id_villa ->
                    navController.navigate("${DestinasiDetailVilla.route}/$id_villa")
                },
                navigateBack = { navController.navigate(DestinasiHomeVilla.route) }
            )
        }

        composable(DestinasiInsertVilla.route) {
            EntryVillaScreen(navigateBack = {
                navController.navigate(DestinasiHomeVilla.route) {
                    popUpTo(DestinasiHomeVilla.route) { inclusive = true }
                }
            })
        }

        composable(
            DestinasiDetailVilla.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailVilla.ID_VILLA) { type = NavType.IntType })
        ) {
            val id_villa = it.arguments?.getInt(DestinasiDetailVilla.ID_VILLA)
            id_villa?.let { id ->
                DetailScreenVilla(
                    onBackClick = { navController.popBackStack() },
                    onEditClick = { navController.navigate("${DestinasiUpdateVilla.route}/$id") },
                    id_villa = id,
                    onDeleteClick = { navController.popBackStack() }
                )
            }
        }

        composable(
            route = DestinasiUpdateVilla.routesWithArg,
            arguments = listOf(navArgument(DestinasiUpdateVilla.ID_VILLA) { type = NavType.IntType })
        ) { backStackEntry ->
            val id_villa = backStackEntry.arguments?.getInt(DestinasiUpdateVilla.ID_VILLA)
            id_villa?.let {
                UpdateScreenVilla(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeVilla.route) {
                            popUpTo(DestinasiHomeVilla.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                )
            }
        }

        composable(DestinasiHomeReservasi.route) {
            HomeScreenReservasi(
                navigateToltemEntry = { navController.navigate(DestinasiInsertReservasi.route) },
                onDetailClick = { id_reservasi ->
                    navController.navigate("${DestinasiDetailReservasi.route}/$id_reservasi")
                },
                navigateBack = { navController.navigate(DestinasiHome.route) }
            )
        }

        composable(DestinasiInsertReservasi.route) {
            EntryReservasiScreen(navigateBack = {
                navController.navigate(DestinasiHomeReservasi.route) {
                    popUpTo(DestinasiHomeReservasi.route) { inclusive = true }
                }
            })
        }

        composable(
            DestinasiDetailReservasi.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailReservasi.ID_RESERVASI) { type = NavType.IntType })
        ) {
            val id_reservasi = it.arguments?.getInt(DestinasiDetailReservasi.ID_RESERVASI)
            id_reservasi?.let { id ->
                DetailScreenReservasi(
                    onBackClick = { navController.popBackStack() },
                    onEditClick = { navController.navigate("${DestinasiUpdateReservasi.route}/$id") },
                    id_reservasi = id,
                    onDeleteClick = { navController.popBackStack() }
                )
            }
        }

        composable(
            route = DestinasiUpdateReservasi.routesWithArg,
            arguments = listOf(navArgument(DestinasiUpdateReservasi.ID_RESERVASI) { type = NavType.IntType })
        ) { backStackEntry ->
            val id_reservasi = backStackEntry.arguments?.getInt(DestinasiUpdateReservasi.ID_RESERVASI)
            id_reservasi?.let {
                UpdateScreenReservasi(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeReservasi.route) {
                            popUpTo(DestinasiHomeReservasi.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                )
            }
        }

        composable(DestinasiHomeReview.route) {
            HomeScreenReview(
                navigateToltemEntry = { navController.navigate(DestinasiInsertReview.route) },
                onDetailClick = { id_review ->
                    navController.navigate("${DestinasiDetailReview.route}/$id_review")
                },
                navigateBack = { navController.navigate(DestinasiHome.route) }
            )
        }

        composable(DestinasiInsertReview.route) {
            EntryReviewScreen(navigateBack = {
                navController.navigate(DestinasiHomeReview.route) {
                    popUpTo(DestinasiHomeReview.route) { inclusive = true }
                }
            })
        }

        composable(
            DestinasiDetailReview.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailReview.ID_REVIEW) { type = NavType.IntType })
        ) {
            val id_review = it.arguments?.getInt(DestinasiDetailReview.ID_REVIEW)
            id_review?.let { id ->
                DetailScreenReview(
                    onBackClick = { navController.popBackStack() },
                    onEditClick = { navController.navigate("${DestinasiUpdateReview.route}/$id") },
                    id_review = id,
                    onDeleteClick = { navController.popBackStack() }
                )
            }
        }

        composable(
            route = DestinasiUpdateReview.routesWithArg,
            arguments = listOf(navArgument(DestinasiUpdateReview.ID_REVIEW) { type = NavType.IntType })
        ) { backStackEntry ->
            val id_review = backStackEntry.arguments?.getInt(DestinasiUpdateReview.ID_REVIEW)
            id_review?.let {
                UpdateScreenReview(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHomeReview.route) {
                            popUpTo(DestinasiHomeReview.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                )
            }
        }
    }
}
