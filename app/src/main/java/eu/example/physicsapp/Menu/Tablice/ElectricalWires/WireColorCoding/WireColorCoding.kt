package eu.example.physicsapp.Menu.Tablice.ElectricalWires.WireColorCoding


import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.Menu.Tablice.PhycisBasic.SiUnits.HorizontalDivider
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WireColorCodingScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Barwy przewodów", fontFamily = boldText, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("electroWiresSelect") }) {
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
        Text("Nazwa (PL)", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text("Nazwa (ENG)", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text("Kod", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text("Kolor", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
    }
}

data class MetalTension(
    val element: String,
    val electrode: String,
    val potential: String,
    val color: Long
)

@Composable
fun MetalTensionList() {
    val metalTensions = remember {
        listOf(
            MetalTension("czarna","black","BK",0xFF000000),
            MetalTension("brązowa","brown","BN",0xFF8B4513),
            MetalTension("czerwona","red","RD",0xFFFF0000),
            MetalTension("pomarańczowa","orange","OG",0xFFFFA500),
            MetalTension("żółta","yellow","YE",0xFFFFFF00),
            MetalTension("zielona","green","GN",0xFF008000),
            MetalTension("niebieska","blue","BU",0xFF0000FF),
            MetalTension("fioletowa","violet","VT",0xFF800080),
            MetalTension("szara","grey","GY",0xFF808080),
            MetalTension("biała","white","WH",0xFFFFFFFF),
            MetalTension("różowa","pink","PK",0xFFFFC0CB),
            MetalTension("żółta","gold","GD",0xFFFFD700),
            MetalTension("turkusowa","torquoise","TQ",0xFF40E0D0),
            MetalTension("srebrna","silver","SR",0xFFC0C0C0),
//            MetalTension("żółto-zielona","","",0xFF40E0D0),
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
            Box(modifier = Modifier.weight(1f),contentAlignment = Alignment.Center){
                Box(modifier = Modifier.size(20.dp).background(Color(metalTension.color)))
            }

        }
    }
}
