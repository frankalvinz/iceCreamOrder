package variant27.ekwomadu991640705.ca

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

import variant27.ekwomadu991640705.ca.ui.theme.FinProjEkwomadu991640705Theme

@Composable
    /**
     * Displays the Order Page of the Ice Cream Land app.
     *
     * This page allows users to customize their order by adding extras, entering their name,
     * and navigating to the next screen or going back to the previous screen. The total price
     * is calculated based on the selected extras and the base price of the ice cream.
     *
     * @param viewModel The [MainViewModel] that holds the app's state and business logic.
     * @param navController The [NavController] used for navigating between screens.
     * @param modifier A [Modifier] for customizing the layout or appearance of this composable.
     *
     * @Author Frank Ekwomadu. C
     */
fun OrderPage (
    viewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier
){
    // State for selections
    val context = LocalContext.current
    var localName by rememberSaveable { mutableStateOf("") }
    val extrasStates = rememberSaveable {
        mutableStateOf(
            mapOf(
                "Sprinkles $0.25" to false,
                "Cheese $0.50" to false,
                "Veggies $0.75" to false
            )
        )
    }

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
        Spacer(modifier = Modifier.height(20.dp))

        // Subheader
        Text(
            text = "Add to your order",
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 30.sp),
            color = Color.Red,
            modifier = Modifier.padding(bottom = 35.dp)
        )

        // Extras Section
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            extrasStates.value.forEach { (label, isSelected) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = isSelected,
                        onCheckedChange = { checked ->
                            extrasStates.value = extrasStates.value.toMutableMap().apply {
                                this[label] = checked
                            }
                        }
                    )
                    Text(
                        text = label,
                        style = defaultTextStyle
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))

        TextField(
            value = localName,
            onValueChange = { localName = it },
            label = { Text("Your Name") },
            placeholder = { Text("John Doe") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.popBackStack() }) { Text("Back") }
            Button(onClick = {
                if (localName.isNotEmpty()) {
                    val totalExtrasPrice = extrasStates.value
                        .filter { it.value }  // Only include extras that are checked (true)
                        .map { it.key.substringAfter(" $").toFloat() }  // Extract the price from the label
                        .sum()

                    val totalOrderPrice = viewModel.iceCreamPrice + totalExtrasPrice
                    viewModel.setTotalPrice(totalOrderPrice)
                    viewModel.setName(localName)
                    navController.navigate("confirmation")
                } else {
                    Toast.makeText(
                        context,
                        "Invalid input! Please enter a valid name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) { Text("Next") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderPagePreview() {
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
        OrderPage(viewModel, navController, modifier)
    }
}