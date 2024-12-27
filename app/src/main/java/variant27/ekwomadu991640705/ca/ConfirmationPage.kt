package variant27.ekwomadu991640705.ca

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import variant27.ekwomadu991640705.ca.ui.theme.FinProjEkwomadu991640705Theme

@Composable
    /**
     * Displays the Confirmation Page of the Ice Cream Land app.
     *
     * This page summarizes the user's order, including subtotal, tax, and total price.
     * Users can choose to round up their total price for charity and navigate to the
     * result screen or return to the previous page.
     *
     * @param viewModel The [MainViewModel] that holds the app's state and business logic.
     * @param navController The [NavController] used for navigating between screens.
     * @param modifier A [Modifier] for customizing the layout or appearance of this composable.
     *
     * @Author Frank Ekwomadu. C
     */
fun  ConfirmationPage (
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier
){
    val taxPrice = viewModel.totalPrice*0.05
    val subtotal = viewModel.totalPrice
    val total = taxPrice + subtotal

    // Global text style for the content
    val defaultTextStyle = TextStyle(
        fontSize = 14.sp
    )

    // Function to round up to the nearest $0.10
    fun roundUpToNearestTenth(value: Float): Float {
        return kotlin.math.ceil(value * 10) / 10
    }

    // Main Column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Header Logo
            Image(
                painter = painterResource(id = R.drawable.icecream),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Header Text
            Text(
                text = "Ice Cream Land",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.2f),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                ),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Subheader
        Text(
            text = "Order Confirmation",
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 30.sp),
            color = Color.Red,
            modifier = Modifier.padding(bottom = 35.dp)
        )

        // Note to User
        Text(
            text = buildAnnotatedString {
                append("Dear ")
                withStyle(style = SpanStyle(color = Color.Green, fontStyle = FontStyle.Italic)) {
                    append(viewModel.name)  // similar to <span>
                }
                append(", please confirm the details below.")
            },
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        //Order Details
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            // Subtotal Price Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Subtotal :",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${String.format("%.2f", subtotal)}",
                    color = Color.Red
                )
            }

            // Tax Price Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tax (5%) :",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${String.format("%.2f", taxPrice)}",
                    color = Color.Red
                )
            }

            // Total Price Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total :",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$${String.format("%.2f", total)}",
                    color = Color.Red
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        // Round up toggle
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = viewModel.roundUpPrice,
                onCheckedChange = { viewModel.setRoundUpPrice(it) }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Round Up For Kids Foundation Camp",
                style = defaultTextStyle
            )
        }
        Spacer(modifier = Modifier.height(25.dp))

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.popBackStack() }) { Text("Back") }
            Button(onClick = {
                if (viewModel.roundUpPrice) {
                    viewModel.setTotalPrice(roundUpToNearestTenth(total.toFloat()))
                } else {
                    viewModel.setTotalPrice(total.toFloat())
                }
                navController.navigate("result")
            }) { Text("Next") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationPagePreview() {
    val viewModel = MainViewModel().apply {
        // Set some default data for the preview
        setName("John Doe")
        setIceCreamPrice(3.00f)
        setTotalPrice(5.00f)
        setRoundUpPrice(true)
    }
    val navController = rememberNavController()
    val modifier = Modifier
    FinProjEkwomadu991640705Theme {
        ConfirmationPage(viewModel, navController, modifier)
    }
}