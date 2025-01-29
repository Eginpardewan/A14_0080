package com.example.pamuas_080.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

object DestinasiHome : DestinasiNavigasi{
    override val route = "Home"
    override val titleRes = "Aplikasi Reservasi Villa"
}

object DestinasiHomePelanggan: DestinasiNavigasi{
    override val route = "Home Pelanggan"
    override val titleRes = "Home Pelanggan"
}

object DestinasiInsertPelanggan: DestinasiNavigasi{
    override val route= "item_entry_Pelanggan"
    override val titleRes = "Entry Pelanggan"

}

object DestinasiDetailPelanggan : DestinasiNavigasi {
    override val route = "detail/{id_pelanggan}"
    override val titleRes = "Detail Pelanggan"
    const val ID_PELANGGAN = "id_pelanggan"
    val routeWithArgs = "$route/{$ID_PELANGGAN}"
}

object DestinasiUpdatePelanggan: DestinasiNavigasi {
    override val route = "update pelanggan"
    override val titleRes = "Update Pelanggan"
    const val ID_PELANGGAN = "id_pelanggan"
    val routesWithArg = "$route/{$ID_PELANGGAN}"
}

object DestinasiHomeVilla: DestinasiNavigasi{
    override val route = "Home Villa"
    override val titleRes = "Home Villa"
}

object DestinasiInsertVilla: DestinasiNavigasi{
    override val route= "item_entry_Villa"
    override val titleRes = "Entry Villa"

}

object DestinasiDetailVilla : DestinasiNavigasi {
    override val route = "detail/{id_villa}"
    override val titleRes = "Detail Villa"
    const val ID_VILLA = "id_villa"
    val routeWithArgs = "$route/{$ID_VILLA}"
}

object DestinasiUpdateVilla: DestinasiNavigasi {
    override val route = "update villa"
    override val titleRes = "Update Villa"
    const val ID_VILLA = "id_villa"
    val routesWithArg = "$route/{$ID_VILLA}"
}

object DestinasiHomeReservasi: DestinasiNavigasi{
    override val route = "Home Reservasi"
    override val titleRes = "Home Reservasi"
}

object DestinasiInsertReservasi: DestinasiNavigasi{
    override val route= "item_entry_Reservasi"
    override val titleRes = "Entry Reservasi"
}

object DestinasiDetailReservasi : DestinasiNavigasi {
    override val route = "detail/{id_reservasi}"
    override val titleRes = "Detail Reservasi"
    const val ID_RESERVASI = "id_reservasi"
    val routeWithArgs = "$route/{$ID_RESERVASI}"
}

object DestinasiUpdateReservasi: DestinasiNavigasi {
    override val route = "update reservasi"
    override val titleRes = "Update Reservasi"
    const val ID_RESERVASI = "id_reservasi"
    val routesWithArg = "$route/{$ID_RESERVASI}"
}

object DestinasiHomeReview: DestinasiNavigasi{
    override val route = "Home Review"
    override val titleRes = "Home Review"
}

object DestinasiInsertReview: DestinasiNavigasi{
    override val route= "item_entry_Review"
    override val titleRes = "Entry Review"
}

object DestinasiDetailReview : DestinasiNavigasi {
    override val route = "detail/{id_review}"
    override val titleRes = "Detail Review"
    const val ID_REVIEW = "id_review"
    val routeWithArgs = "$route/{$ID_REVIEW}"
}

object DestinasiUpdateReview: DestinasiNavigasi {
    override val route = "update review"
    override val titleRes = "Update Review"
    const val ID_REVIEW = "id_review"
    val routesWithArg = "$route/{$ID_REVIEW}"
}