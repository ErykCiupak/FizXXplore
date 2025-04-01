package eu.example.physicsapp.Menu.Tablice.Battery.ElectroChemEquiv

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.Menu.Tablice.PhycisBasic.SiUnits.HorizontalDivider
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectroChemEquivScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Równoważnik elektrochemiczny", fontFamily = boldText, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("batterySelect") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back arrow")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderRow()
            ElectroChemEquivList()
        }
    }
}

@Composable
fun HeaderRow() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("RE - równoważnik elektrochemiczny", fontSize = 12.sp, textAlign = TextAlign.Center)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Materiał", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text("Gęstość\n[g/cm³]", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text("RE\n[Ah/g]", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text("Re\n[Ah/cm³]", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }
    }
}

data class ElectroChemEquivalent(
    val material: String,
    val density: String,
    val electrochemEquivalent: String,
    val volumetricEquivalent: String
)

@Composable
fun ElectroChemEquivList() {
    val equivalents = remember {
        listOf(
            ElectroChemEquivalent("Li", "0,54", "3,86", "2,06"),
            ElectroChemEquivalent("Na", "0,97", "1,16", "1,14"),
            ElectroChemEquivalent("Mg", "1,74", "2,20", "3,80"),
            ElectroChemEquivalent("Al", "2,69", "2,98", "8,10"),
            ElectroChemEquivalent("Fe", "7,85", "0,96", "7,50"),
            ElectroChemEquivalent("Zn", "7,14", "0,82", "5,80"),
            ElectroChemEquivalent("Pb", "11,34", "0,26", "2,90"),
            ElectroChemEquivalent("MnO₂", "5,00", "0,31", "1,54"),
            ElectroChemEquivalent("NiOOH", "7,40", "0,43", "3,20"),
            ElectroChemEquivalent("CuCl", "3,50", "0,27", "0,95"),
            ElectroChemEquivalent("PbO₂", "9,40", "0,22", "2,11"),
            ElectroChemEquivalent("LiFePO₄", "3,44", "0,16", "0,55"),
            ElectroChemEquivalent("LiₓCoO₂", "5,05", "0,16", "0,78")
        )
    }

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(equivalents) { equivalent ->
            ElectroChemEquivalentRow(equivalent)
        }
    }
}

@Composable
fun ElectroChemEquivalentRow(equivalent: ElectroChemEquivalent) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(equivalent.material, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(equivalent.density, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(equivalent.electrochemEquivalent, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(equivalent.volumetricEquivalent, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }
    }
}
