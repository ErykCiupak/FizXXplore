package eu.example.physicsapp.Menu.Calculators.CircuitsDC



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.PublicTemplates.CustomButton
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuDCSelect(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Obwody DC",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = boldText, fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("calcOption")
//                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back arrow"
                        )
                    }
                }
            )

        }
    ) {paddingValues->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton("Prawo Ohma","calcOhmLaw","ohm",navController)
            CustomButton("Dzielnik napięcia","calcVoltDiv","rezystory_razem",navController)
            CustomButton("Łączenie rezystorów","calcConnResis","rezystory_odzielnie",navController)
//            CustomButton("Ładowanie kondensatorów","calcWireCondu","wip",navController)
//            CustomButton("Ładowanie cewki","calcColumb","wip",navController)

        }
    }

}