package eu.example.physicsapp.Menu.Tablice.ElectricDevices.ResistorsLinesCode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResistorsLinesCodeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Kod paskowy", fontFamily = boldText, fontSize = 20.sp) },
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
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ColorColumn()
                PropertyRow()
            }
            Text("*[TWR]-temperaturowy współczynnik rezystancji", fontSize = 10.sp)
        }
    }
}

@Composable
fun ColorColumn() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ColorBox("Kolor", Color.Transparent)
        ColorBox("czarny", Color.Black)
        ColorBox("brązowy", Color(0xFF795548))
        ColorBox("czerwony", Color.Red)
        ColorBox("pomarań...", Color(0xFFFFA500))
        ColorBox("żółty", Color.Yellow)
        ColorBox("zielony", Color.Green)
        ColorBox("niebieski", Color.Blue)
        ColorBox("fioletowy", Color(0xFF800080))
        ColorBox("szary", Color.Gray)
        ColorBox("biały", Color.White)
        ColorBox("złoty", Color(0xFFFFD700))
        ColorBox("srebrny", Color(0xFFC0C0C0))
    }
}

@Composable
fun ColorBox(text: String, color: Color) {
    // Wyznacz przeciwny kolor tekstu na podstawie jasności tła
    val textColor = if (color.luminance() > 0.5f) Color.Black else Color.White

    Box(
        modifier = Modifier
            .width(90.dp)
            .height(50.dp)
            .background(color)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = 12.sp, color = textColor)
    }
}


@Composable
fun PropertyRow() {
    val properties = listOf(
        "Cyfra [C]" to listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9","-","-"),
        "Mnożnik [M]" to listOf("x1 Ω", "x10 Ω", "x100 Ω", "x1 kΩ", "x10 kΩ","x100 kΩ","x1 MΩ","x10 MΩ","x100 MΩ","x1 GΩ","x0,1 GΩ","x0,01 GΩ"),
        "Tolerancja [T]" to listOf("-", "± 1 %", "± 2 %", "± 3 %", "± 4 %", "± 0,5 %", "± 0,25 %", "± 0,1 %" , "± 0,05 %","-", "± 5 %", "± 10 %"),
        "[TWR]*" to listOf("250 ppm/℃", "100 ppm/℃", "50 ppm/℃", "15 ppm/℃", "25 ppm/℃", "20 ppm/℃", "10 ppm/℃", "5 ppm/℃", "1 ppm/℃","-","-","-")
    )

    LazyRow {
        properties.forEach { (title, values) ->
            item { PropertyColumn(title, values) }
        }
    }
}

@Composable
fun PropertyColumn(title: String, values: List<String>) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PropertyBox(title, fontSize = 15.sp)
        values.forEach { value ->
            PropertyBox(value, fontSize = 12.sp)
        }
    }
}

@Composable
fun PropertyBox(text: String, fontSize: TextUnit) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .height(50.dp)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontSize = fontSize)
    }
}
