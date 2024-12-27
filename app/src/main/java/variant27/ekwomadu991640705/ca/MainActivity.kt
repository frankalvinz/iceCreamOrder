package variant27.ekwomadu991640705.ca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import variant27.ekwomadu991640705.ca.ui.theme.FinProjEkwomadu991640705Theme

    /**
     * Main entry point of the application. This activity sets up the UI and manages navigation.
     *
     * The [MainActivity] class is the entry point of the Ice Cream Land application. It is responsible for setting up
     * the user interface and managing the navigation between different pages (Home, Order, Confirmation, and Result).
     * The activity uses Jetpack Compose for building the UI and implements navigation with the `NavHost` composable.
     * It also enables edge-to-edge display for a more immersive user experience.
     *
     * @Author Frank Ekwomadu. C
     */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinProjEkwomadu991640705Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel() // Use ViewModel here

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomePage(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }
        composable("order") {
            OrderPage(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }
        composable("confirmation") {
            ConfirmationPage(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }
        composable("result") {
            ResultPage(
                viewModel = viewModel,
                navController = navController,
                modifier = modifier
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppNavigationPreview() {
    FinProjEkwomadu991640705Theme {
        AppNavigation()
    }
}