package com.studio.ananas.pcardexam.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.studio.ananas.pcardexam.R
import com.studio.ananas.pcardexam.ui.components.ScreenDestinations.CONVERT
import com.studio.ananas.pcardexam.ui.components.ScreenDestinations.MAIN
import com.studio.ananas.pcardexam.ui.components.ScreenDestinations.RECEIPT
import com.studio.ananas.pcardexam.ui.components.ScreenDestinations.SETTINGS
import com.studio.ananas.pcardexam.ui.components.ScreenDestinations.SIGNATURE
import com.studio.ananas.pcardexam.ui.components.ScreenDestinations.SPLASH
import com.studio.ananas.pcardexam.vms.CurrencyViewModel
import com.studio.ananas.pcardexam.vms.MainViewModel
import com.studio.ananas.pcardexam.vms.SettingsViewModel
import com.studio.ananas.pcardexam.vms.SignatureViewModel
import kotlinx.coroutines.delay

//if ever we want to show screen names
sealed class Destination(val destination: String, @StringRes titleRes: Int) {
    data object Main : Destination(ScreenDestinations.MAIN, R.string.title_main)
    data object Settings : Destination(ScreenDestinations.SETTINGS, R.string.title_settings)
    data object Signature : Destination(ScreenDestinations.SIGNATURE, R.string.title_signature)
    data object Receipt : Destination(ScreenDestinations.RECEIPT, R.string.title_receipt)
}

object ScreenDestinations {
    const val SPLASH = "splash"
    const val MAIN = "main"
    const val SIGNATURE = "signature"
    const val RECEIPT = "receipt"
    const val SETTINGS = "settings"
    const val CONVERT = "convert"
}

@Composable
fun NavigationHost(padding: PaddingValues) {
    val navController = rememberNavController()
    val settingsViewModel: SettingsViewModel = viewModel()
    val mainViewModel: MainViewModel = viewModel()
    val signatureViewModel: SignatureViewModel = viewModel()
    val currencyViewModel: CurrencyViewModel = viewModel()

    LaunchedEffect(Unit) {
        // Your one-time logic here, e.g., loading data
        delay(3000)
        navController.navigate(MAIN) {
            popUpTo(SPLASH) { inclusive = true }
        }
    }

    NavHost(navController, startDestination = SPLASH) {
        composable(SPLASH) {
            Splash()
        }
        composable(MAIN) {

            MainScreen(
                padding,
                settingsViewModel = settingsViewModel,
                mainViewModel = mainViewModel,
                onNavigateToSignature = { navController.navigate(SIGNATURE) },
                onNavigateToReceipt = { navController.navigate(RECEIPT) },
                onNavigateToSettings = { navController.navigate(SETTINGS) },
            )
        }
        composable(SETTINGS) {
            SettingsScreen(
                padding,
                settingsViewModel = settingsViewModel,
                onNavigateBack = { navController.popBackStack() })
        }
        composable(SIGNATURE) {
            SignatureScreen(
                padding = padding,
                signatureViewModel = signatureViewModel,
                onNavigateToReceipt = { navController.navigate(RECEIPT) },
                onNavigateBack = { navController.popBackStack() })
        }
        composable(CONVERT) {
            ConvertScreen(
                padding = padding, currencyViewModel = currencyViewModel, onNavigateBack = { navController.popBackStack() })
        }
        composable(RECEIPT) {
            ReceiptScreen(
                padding = padding,
                mainViewModel = mainViewModel,
                signatureViewModel = signatureViewModel,
                settingsViewModel = settingsViewModel,
                currencyViewModel = currencyViewModel,
                onNavigateToConvert = {navController.navigate(CONVERT)},
                onNavigateBack = {
                    navController.navigate(MAIN) {
                        popUpTo(MAIN) { inclusive = true }
                        mainViewModel.reset()
                        signatureViewModel.clear()
                    }
                }
            )
        }
    }
}
