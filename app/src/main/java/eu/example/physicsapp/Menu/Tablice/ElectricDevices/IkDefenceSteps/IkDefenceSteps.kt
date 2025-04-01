package eu.example.physicsapp.Menu.Tablice.ElectricDevices.IkDefenceSteps


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
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IkDefenceStepsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Stopnie ochrony IK", fontFamily = boldText, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("electriDevicesSelect") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back arrow")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            IkDefenceStepsHeaderRow()
            IkDefenceStepsList()
        }
    }
}
@Composable
fun IkDefenceStepsHeaderRow() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text("Ochrona przed oddziaływaniem mechanicznym \n(np. uderzeniem) wg. PN-EN 50102:2001", fontSize = 12.sp, textAlign = TextAlign.Center)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Poziom IK", fontSize = 13.sp, modifier = Modifier.weight(0.6f), textAlign = TextAlign.Center)
            Text("Energia \nuderzenia", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text("Rownowartość uderzenia", fontSize = 13.sp, modifier = Modifier.weight(1.5f), textAlign = TextAlign.Center)
        }
    }
}

data class IkDefenceStep(
    val material: String,
    val density: String,
    val electrochemEquivalent: String
)

@Composable
fun IkDefenceStepsList() {
    val ikDefenceSteps = remember {
        listOf(
            IkDefenceStep("00", "0 J", "-"),
            IkDefenceStep("01", "0,15 J", "Upadek obiektu o masie 0,2 kg \nz wysokości 7,5 cm"),
            IkDefenceStep("02", "0,20 J", "Upadek obiektu o masie 0,2 kg \nz wysokości 10 cm"),
            IkDefenceStep("03", "0,35 J", "Upadek obiektu o masie 0,2 kg \nz wysokości 17,5 cm"),
            IkDefenceStep("04", "0,50 J", "Upadek obiektu o masie 0,2 kg \nz wysokości 25 cm"),
            IkDefenceStep("05", "0,70 J", "Upadek obiektu o masie 0,2 kg \nz wysokości 35 cm"),
            IkDefenceStep("06", "1,00 J", "Upadek obiektu o masie 0,5 kg \nz wysokości 20 cm"),
            IkDefenceStep("07", "2,00 J", "Upadek obiektu o masie 0,5 kg \nz wysokości 40 cm"),
            IkDefenceStep("08", "5,00 J", "Upadek obiektu o masie 1 kg \nz wysokości 29,5 cm"),
            IkDefenceStep("09", "10,00 J", "Upadek obiektu o masie 5 kg \nz wysokości 20 cm"),
            IkDefenceStep("10", "20,00 J", "Upadek obiektu o masie 5 kg \nz wysokości 40 cm"),
        )
    }

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(ikDefenceSteps) { ikDefenceSteps ->
            IkDefenceStepsRow(ikDefenceSteps)
        }
    }
}

@Composable
fun IkDefenceStepsRow(ikDefenceStep: IkDefenceStep) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        eu.example.physicsapp.Menu.Tablice.PhycisBasic.SiUnits.HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(ikDefenceStep.material, fontSize = 12.sp, modifier = Modifier.weight(0.6f), textAlign = TextAlign.Center, lineHeight = 12.sp)
            Text(ikDefenceStep.density, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, lineHeight = 12.sp)
            Text(ikDefenceStep.electrochemEquivalent, fontSize = 12.sp, modifier = Modifier.weight(1.5f), textAlign = TextAlign.Center, lineHeight = 12.sp)
        }
    }
}
