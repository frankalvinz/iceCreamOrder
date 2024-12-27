package variant27.ekwomadu991640705.ca

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import variant27.ekwomadu991640705.ca.ui.theme.FinProjEkwomadu991640705Theme

@Composable
    /**
     * Displays the Result Page of the Ice Cream Land app.
     *
     * This page thanks the user for their order and provides a summary of the total price.
     * It also includes an option to exit the app after the transaction is complete.
     *
     * @param viewModel The [MainViewModel] that holds the app's state and business logic.
     * @param navController The [NavController] used for navigating between screens.
     * @param modifier A [Modifier] for customizing the layout or appearance of this composable.
     *
     * @Author Frank Ekwomadu. C
     */
fun ResultPage (
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier
){
    // Global text style for the content
    val defaultTextStyle = TextStyle(
        fontSize = 14.sp
    )

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
        Spacer(modifier = Modifier.height(150.dp))

        // Subheader
        Text(
            text = "Thank you, " + viewModel.name + "!",
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 30.sp),
            color = Color.Green,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = "Your Order will be brought to your table, shortly",
            style = defaultTextStyle,
            modifier = Modifier.padding(bottom = 35.dp)
        )

        // Final Details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Total
            Text(
                text = "$" + viewModel.totalPrice + "0",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 60.sp),
                color = Color.Red,
                modifier = Modifier.padding(bottom = 15.dp)
            )

            // Paid text
            Text(
                text = "paid",
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 30.sp),
                color = Color.Black,
                modifier = Modifier.padding(bottom = 35.dp)
            )
        }

        Spacer(modifier = Modifier.height(60.dp))
        Button(onClick = { /* Closes the app */ }) { Text("Exit") }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPagePreview() {
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
        ResultPage(viewModel, navController, modifier)
    }
}