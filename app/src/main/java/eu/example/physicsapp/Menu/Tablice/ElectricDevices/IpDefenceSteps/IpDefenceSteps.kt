package eu.example.physicsapp.Menu.Tablice.ElectricDevices.IpDefenceSteps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.R
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IpDefenceStepsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Stopnie ochrony IP", fontFamily = boldText, fontSize = 20.sp) },
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
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ip_scheme),
                contentDescription = "IP Scheme",
                modifier = Modifier.size(width = 400.dp, height = 280.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )

            Spacer(modifier = Modifier.height(16.dp))

            listOf(
                "Cyfra 1" to listOf(
                    listOf("Znak", "Ochrona przed \ndostępem do części \nniebezpiecznych", "Ochrona przed dostaniem się do wnętrza ciał stałych"),
                    listOf("0", "brak ochrony", "brak ochrony"),
                    listOf("1", "przed dotknięciem wierzchem dłoni", "o średnicy ≥ 50 mm"),
                    listOf("2", "przed dotknięciem palcem", "o średnicy ≥ 12,5 mm"),
                    listOf("3", "przed dotknięciem narzędziem", "o średnicy ≥ 2,5 mm"),
                    listOf("4", "przed dotknięciem drutem", "o średnicy ≥ 1 mm"),
                    listOf("5", "jak wyżej", "skuteczna ochronna przed pyłem"),
                    listOf("6", "jak wyżej", "pyłoszczelność")
                ),
                "Cyfra 2" to listOf(
                    listOf("Znak", "Ochrona przed wnikaniem do wnętrza wody"),
                    listOf("0", "brak ochrony"),
                    listOf("1", "krople spadające pionowe"),
                    listOf("2", "krople spadające pionowo na urządzenie odchylone od pionu o 15°"),
                    listOf("3", "woda natryskiwana pod kątem 60° od pionu"),
                    listOf("4", "woda rozbryzgiwana na obudowę z dowolnego kierunku"),
                    listOf("5", "strumień wody z dowolnego kierunku"),
                    listOf("6", "silna struga wody"),
                    listOf("7", "krótkotrwałe zanurzenie w wodzie o określonym ciśnieniu"),
                    listOf("8", "długotrwałe zanurzenie w wodzie"),
                    listOf("9", "silna struga wody o wysokiej temperaturze")
                ),
                "Litera dodatkowa" to listOf(
                    listOf("Symbol", "Ochrona przed dostępem do części niebezpiecznych"),
                    listOf("A", "przy wyciskaniu w każdy otwór osłony próbnika w postali kuli o średnicy 50 mm"),
                    listOf("B", "jw., ale dla próbnika w postaci palca probierczego (średnica 12,5 mm, długość 80 mm)"),
                    listOf("C", "jw., ale dla próbnika w postaci pręta (średnica 2,5 mm, długość 100 mm)"),
                    listOf("D", "jw., ale dla próbnika w postaci drutu (średnica 1 mm, długość 100 mm)")
                ),
                "Litera uzupełniająca" to listOf(
                    listOf("Symbol", "Cecha urządzenia"),
                    listOf("H", "aparat wysokiego napięcia"),
                    listOf("M", "ochrona przed wnikaniem wody, gdy części ruchome urządzenia są w ruchu"),
                    listOf("S", "ochrona przed wnikaniem wody, gdy części ruchome urządzenia są nieruchome"),
                    listOf("W", "możliwość stosowania w określonych warunkach pogodowych po zapewnieniu dodatkowych środków ochrony lub zabiegów")
                )
            ).forEach { (sectionTitle, rows) ->
                Section(title = sectionTitle)
                rows.forEach { row ->

                    HorizontalDivider()
                    TableRow(row)
                }
            }
        }
    }
}

@Composable
fun Section(title: String) {
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = title, fontSize = 16.sp, fontFamily = boldText)
    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(0.08f),
        thickness = 2.dp,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun TableRow(cells: List<String>, fontSize: TextUnit = 11.sp) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .heightIn(min = 50.dp),  // Minimum height of 50.dp, but can expand
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        cells.forEachIndexed { index, cell ->
            val weight = if (index == 0) 0.4f else 1.5f
            Box(
                modifier = Modifier
                    .weight(weight)
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = cell, fontSize = fontSize)
            }
        }
    }
}

