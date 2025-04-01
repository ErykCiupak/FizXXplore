package eu.example.physicsapp.Menu.Tablice.ElectricalWires.WiresDiameter

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
fun WireDiamerScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Przekroje i średnica", fontFamily = boldText, fontSize = 20.sp) },
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
        Text("Przekrój [mm²]", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text("Średnica maks. [mm]", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text("Odpowiednik AWG", fontSize = 13.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
    }
}

data class MetalTension(
    val element: String,
    val electrode: String,
    val potential: String,
    val boolean: Boolean
)

@Composable
fun MetalTensionList() {
    val metalTensions = remember {
        listOf(
//            MetalTension("","",""),
            MetalTension("0,05","-","30",false),
            MetalTension("0,08","-","28",false),
            MetalTension("0,14","-","26",false),
            MetalTension("0,25","-","24",false),
            MetalTension("0,34","-","22",false),
            MetalTension("0,38","-","21",false),
            MetalTension("0,50","1,1","20",false),
            MetalTension("0,75","1,2","18",false),
            MetalTension("1,0","1,4","17",false),
            MetalTension("1,5","1,7","16",false),
            MetalTension("2,5","2,2","14",false),
            MetalTension("4","2,7","12",false),
            MetalTension("6","3,3","10",false),
            MetalTension("10","4,2","8",false),
            MetalTension("16","5,3","6",false),
            MetalTension("25","6,6","4",false),
            MetalTension("35","7,9","2",false),
            MetalTension("50","9,1","1",false),
            MetalTension("70","11,0","2/0",false),
            MetalTension("95","12,9","3/0",false),
            MetalTension("120","14,5","4/0",false),
            MetalTension("150","16,2","300 kcmil",false),
            MetalTension("185","18,0","250 kcmil",false),
            MetalTension("240","20,6","500 kcmil",false),
            MetalTension("300","23,1","600 kcmil",false),
            MetalTension("400","26,1","750 kcmil",false),
            MetalTension("500","29,1","1000 kcmil",false),
            MetalTension("630","33,2","1000 kcmil",false),
            MetalTension("800","37,6","1000 kcmil",false),
            MetalTension("1000","42,2","1000 kcmil",false),
            MetalTension("","","",true),

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
            if(!metalTension.boolean) {
                Text(
                    metalTension.element,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    metalTension.electrode,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    metalTension.potential,
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }else{
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
//                    .height(250.dp)
                ){
                    Column {
                        Row {
                            Text("Wartość przekrojów i średnic pochodzą z normy DIN VDE 0295.", fontSize = 11.sp, lineHeight = 15.sp)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Text("Podane średnice są średnicami maksymalnymi dla przewodów miedzianych wielodrutowych (klasa 2)", fontSize = 11.sp,lineHeight = 15.sp)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Text("Podane odpowiedniki normy amerykańskiej AWG są jedynie przybliżonymi ekwiwalentami pod względem obiciążalności długotrwałej." +
                                    " Dane przychodża z dokumentu \"Budowa żyły według DIN VDE 0295, IEC 60228 i HD 383\", dostępnego na stronie producenta Helukabel. ", fontSize = 11.sp,lineHeight = 15.sp)
                        }
                    }

                }
            }

        }
    }
}
