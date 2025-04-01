package eu.example.physicsapp.Menu.Tablice.Battery.MetalTensions

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
fun MetalTensionScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Szereg napięciowy metali", fontFamily = boldText, fontSize = 20.sp) },
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
            MetalTensionList()
        }
    }
}

@Composable
fun HeaderRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Pierwiastek", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text("Elektroda", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text("Potencjał", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
    }
}

data class MetalTension(
    val element: String,
    val electrode: String,
    val potential: String
)

@Composable
fun MetalTensionList() {
    val metalTensions = remember {
        listOf(
            MetalTension("Lit","Li/Li⁺","-3,04"),
            MetalTension("Potas","K/K⁺","-2,94"),
            MetalTension("Wapń","Ca/Ca²⁺","-2,87"),
            MetalTension("Sód","Na/Na⁺","-2,71"),
            MetalTension("Magnez","Mg/Mg²⁺","-2,37"),
            MetalTension("Beryl","Be/Be²⁺","-1,85"),
            MetalTension("Glin","Al/Al³⁺","-1,66"),
            MetalTension("Tytan","Ti/Ti²⁺","-1,63"),
            MetalTension("Cyrkon","Zr/Zr³⁺","-1,53"),
            MetalTension("Tytan","Ti/Ti³⁺","-1,21"),
            MetalTension("Wanad","V/V²⁺","-1,18"),
            MetalTension("Mangan","Mn/Mn²⁺","-1,18"),
            MetalTension("Niob","Nb/Nb³⁺","-1,10"),
            MetalTension("Chrom","Cr/Cr²⁺","-0,91"),
            MetalTension("Wanad","V/V³⁺","-0,88"),
            MetalTension("Cynk","Zn/Zn²⁺","-0,76"),
            MetalTension("Chrom","Cr/Cr³⁺","-0,74"),
            MetalTension("Żelazo","Fe/Fe²⁺","-0,44"),
            MetalTension("Kadm","Cd/Cd²⁺","-0,40"),
            MetalTension("Mangan","Mn/Mn³⁺","-0,28"),
            MetalTension("Kobalt","Co/Co²⁺","-0,28"),
            MetalTension("Nikiel","Ni/Ni²⁺","-0,25"),
            MetalTension("Molibden","Mo/Mo³⁺","-0,20"),
            MetalTension("Cyna","Sn/Sn²⁺","-0,14"),
            MetalTension("Ołów","Pb/Pb²⁺","-0,13"),
            MetalTension("Żelazo","Fe/Fe³⁺","-0,03"),
            MetalTension("Wodór","1/2 H₂/H⁺","0,00"),
            MetalTension("Cyna","Sn/Sn‘⁺","+0,01"),
            MetalTension("Miedź","Cu/Cu²⁺","+0,34"),
            MetalTension("Kobalt","Co/Co³⁺","+0,42"),
            MetalTension("Miedź","Cu/Cu⁺","+0,52"),
            MetalTension("Ołów","Pb/Pb‘⁺","+0,78"),
            MetalTension("Rtęć","2Hg/Hg₂²⁺","+0,79"),
            MetalTension("Srebro","Ag/Ag⁺","+0,80"),
            MetalTension("Rtęć","Hg/Hg²⁺","+0,85"),
            MetalTension("Pallad","Pd/Pd²⁺","+0,99"),
            MetalTension("Iryd","Ir/Ir³⁺","+1,00"),
            MetalTension("Platyna","Pt/Pt²⁺","+1,19"),
            MetalTension("Złoto","Au/Au³⁺","+1,50"),
            MetalTension("Złoto","Au/Au⁺","+1,68"),
        )
    }

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(metalTensions) { item ->
            MetalTensionRow(item)
        }
    }
}

@Composable
fun MetalTensionRow(metalTension: MetalTension) {
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
            Text(metalTension.element, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(metalTension.electrode, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(metalTension.potential, fontSize = 12.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }
    }
}
