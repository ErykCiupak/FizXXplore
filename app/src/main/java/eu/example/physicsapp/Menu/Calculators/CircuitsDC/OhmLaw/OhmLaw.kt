package eu.example.physicsapp.Menu.Calculators.CircuitsDC.OhmLaw


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.ui.theme.boldText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import eu.example.physicsapp.Menu.Calculators.Basics.ColumbCalculator.InputFieldWithDropdown
import eu.example.physicsapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OhmCalculator(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Prawo Ohma",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = boldText,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("obwodDCSelect") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back arrow"
                        )
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
            OhmLawForm()
        }
    }
}


@Composable
fun OhmLawForm() {
    var selectedOption by remember { mutableStateOf("prąd") }

    var currentValue by remember { mutableStateOf("") }
    var currentUnit by remember { mutableStateOf("A") }


    var voltageValue by remember { mutableStateOf("") }
    var voltageUnit by remember { mutableStateOf("V") }

    var resistValue by remember { mutableStateOf("") }
    var resistUnit by remember { mutableStateOf("Ω") }

    var result by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    // Jednostki dla siły Coulomba
    LaunchedEffect(
        currentValue, voltageValue, resistValue, selectedOption
    ) {
        // Walidacja danych wejściowych
        val isDataValid = when (selectedOption) {
            "prąd" -> resistValue.isNotEmpty()  && voltageValue.isNotEmpty()
            "napięcie" -> currentValue.isNotEmpty() &&  resistValue.isNotEmpty()
            "rezystancję" -> currentValue.isNotEmpty() && voltageValue.isNotEmpty()
            else -> false
        }

        if (!isDataValid) {
            result = "Wprowadź wszystkie wymagane dane"
            isError = true
        } else {
            isError = false
            // Obliczenia wyników (zachowano istniejący kod)
        }
    }

    val unitConversion = mapOf(
        "µV" to 1e-6, "mV" to 1e-3, "V" to 1.0, "kV" to 1e3, "MV" to 1e6,
        "µΩ" to 1e-6, "mΩ" to 1e-3, "Ω" to 1.0, "kΩ" to 1e3, "MΩ" to 1e6,
        "µA" to 1e-6, "mA" to 1e-3, "A" to 1.0, "kA" to 1e3, "MA" to 1e6
    )

    // Funkcja resetująca pola
    fun resetFields() {
        currentValue = ""
        currentUnit = "A"
        voltageValue = ""
        voltageUnit = "V"
        resistValue = ""
        resistUnit = "Ω"
    }

    // Funkcja, która uruchamia resetowanie po każdej zmianie selectedOption
    LaunchedEffect(selectedOption) {
        resetFields()
    }

    // LaunchedEffect dla obliczeń
    LaunchedEffect(currentValue, currentUnit, voltageValue, voltageUnit, resistValue, resistUnit) {
        val I = currentValue.toDoubleOrNull()?.times(unitConversion[currentUnit] ?: 1.0) ?: 0.0
        val U = voltageValue.toDoubleOrNull()?.times(unitConversion[voltageUnit] ?: 1.0) ?: 1.0
        val R = resistValue.toDoubleOrNull()?.times(unitConversion[resistUnit] ?: 1.0) ?: 0.0

        result = when (selectedOption) {
            "prąd" -> {
                if (U == 0.0 || R == 0.0) {
                    "Proszę wprowadzić poprawne dane"
                } else {
                    val calculatedCurrent = U/R
                    formatCurrentResult(calculatedCurrent, unitConversion)
                }
            }
            "napięcie" -> {
                if (I != 0.0 && R != 0.0) {
                    val culculatedVoltage = I*R
                    formatVoltageResult(culculatedVoltage, unitConversion)
                } else {
                    "Podaj ładunki i siłę"
                }
            }
            "rezystancję" -> {
                if (I != 0.0 && U != 0.0) {
                    val calculatedResist = U/I
                    formatResistResult(calculatedResist, unitConversion)  // Użycie funkcji formatChargeResult
                } else {
                    "Podaj Q1, r i siłę"
                }
            }
            else -> "Nieznana opcja"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val tabTitles = listOf("Obliczenia", "Opcje")
        val pagerState = rememberPagerState(pageCount = {2}
        )
        val coroutineScope = rememberCoroutineScope()

        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(.4f)) {
            // TabRow for the top tabs
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth(),
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        color = MaterialTheme.colorScheme.onBackground // Kolor wskaźnika (podkreślenie aktywnej zakładki)
                    )
                }
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(text = title,color = MaterialTheme.colorScheme.onBackground) }
                    )
                }
            }

            val wzorObraz = when(selectedOption) {
                "prąd" -> R.drawable.prad_wzor
                "napięcie" -> R.drawable.napiecie_wzor
                "rezystancję" -> R.drawable.rezystancja_wzor
                else -> R.drawable.pusty
            }
            // HorizontalPager for swipeable content
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                when (page) {
                    0 -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize().padding(16.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(modifier = Modifier.height(50.dp),contentAlignment = Alignment.Center){
                                    if (isError) {
                                        Text(
                                            text = "Wprowadz dane",
                                            color = MaterialTheme.colorScheme.error,
                                            modifier = Modifier.padding(top = 8.dp)
                                        )
                                    }else if(!result.contains("NaN")){
                                        Text(text = result)
                                    }else{
                                        Text(text = "Wprowadz poprawne dane")
                                    }

                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                        Image(
                                            painter = painterResource(id = wzorObraz),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                            modifier = Modifier
                                                .fillMaxHeight() // Wypełnia całą wysokość Row
                                                .aspectRatio(1f), // Opcjonalnie, ustawia proporcje
                                            )

                                }
                            }
                        }
                    }
                    1 -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize().padding(16.dp)
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    RadioButton(
                                        selected = selectedOption == "prąd",
                                        onClick = { selectedOption = "prąd" }
                                    )
                                    Text(text = "prąd", modifier = Modifier.padding(start = 8.dp))
                                }

                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    RadioButton(
                                        selected = selectedOption == "napięcie",
                                        onClick = { selectedOption = "napięcie" }
                                    )
                                    Text(text = "napięcie", modifier = Modifier.padding(start = 8.dp))
                                }

                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    RadioButton(
                                        selected = selectedOption == "rezystancję",
                                        onClick = { selectedOption = "rezystancję" }
                                    )
                                    Text(text = "rezystancję", modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        if(selectedOption=="napięcie"|| selectedOption=="rezystancję"){
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Prąd I",
                value = currentValue,
                onValueChange = { if (it.length <= 6) currentValue = it },
                selectedUnit = currentUnit,
                units = listOf("µA", "mA", "A", "kA", "MA"),
                onUnitSelected = { currentUnit = it },
                enabled = true,
                allowNegative = false
            )
        }
        if(selectedOption=="prąd"|| selectedOption=="rezystancję"){
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Napiecie U",
                value = voltageValue,
                onValueChange = { if (it.length <= 6) voltageValue = it },
                selectedUnit = voltageUnit,
                units = listOf("µV", "mV", "V", "kV", "MV"),
                onUnitSelected = { voltageUnit = it },
                enabled = true,
                allowNegative = false
            )
        }
        if(selectedOption=="prąd"|| selectedOption=="napięcie"){
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Rezystancja R",
                value = resistValue,
                onValueChange = { if (it.length <= 6) resistValue = it },
                selectedUnit = resistUnit,
                units = listOf("µΩ", "mΩ", "Ω", "kΩ", "MΩ"),
                onUnitSelected = { resistUnit = it },
                enabled = true,
                allowNegative = false
            )
        }
    }
}


//Funckja zaokraglajaco wyniki w formie m
fun formatCurrentResult(value: Double, conversions: Map<String, Double>): String {
    val absValue = kotlin.math.abs(value)
    val scaledValue = absValue * (conversions["A"] ?: 1.0)

    return when {
        scaledValue >= 1e30 -> "%.2e A".format(scaledValue)
        scaledValue >= 1e24 -> "%.2f YA".format(scaledValue / 1e24)
        scaledValue >= 1e21 -> "%.2f ZA".format(scaledValue / 1e21)
        scaledValue >= 1e18 -> "%.2f EA".format(scaledValue / 1e18)
        scaledValue >= 1e15 -> "%.2f PA".format(scaledValue / 1e15)
        scaledValue >= 1e12 -> "%.2f TA".format(scaledValue / 1e12)
        scaledValue >= 1e9 -> "%.2f GA".format(scaledValue / 1e9)
        scaledValue >= 1e6 -> "%.2f MA".format(scaledValue / 1e6)
        scaledValue >= 1e3 -> "%.2f kA".format(scaledValue / 1e3)
        scaledValue >= 1 -> "%.2f A".format(scaledValue)
        scaledValue >= 1e-3 -> "%.2f mA".format(scaledValue * 1e3)
        scaledValue >= 1e-6 -> "%.2f µA".format(scaledValue * 1e6)
        scaledValue >= 1e-9 -> "%.2f nA".format(scaledValue * 1e9)
        scaledValue >= 1e-12 -> "%.2f pA".format(scaledValue * 1e12)
        scaledValue >= 1e-15 -> "%.2f fA".format(scaledValue * 1e15)
        scaledValue >= 1e-18 -> "%.2f aA".format(scaledValue * 1e18)
        scaledValue >= 1e-21 -> "%.2f zA".format(scaledValue * 1e21)
        else -> "%.2e A".format(scaledValue)                    // Format naukowy
    }
}
//Funckja zaokraglajaco wyniki w formie C
fun formatVoltageResult(charge: Double, conversions: Map<String, Double>): String {
    val absCharge = kotlin.math.abs(charge)
    val scaledCharge = absCharge * (conversions["V"] ?: 1.0)

    return when {
        scaledCharge >= 1e30 -> "%.2e V".format(scaledCharge)
        scaledCharge >= 1e24 -> "%.2f YV".format(scaledCharge / 1e24)
        scaledCharge >= 1e21 -> "%.2f ZV".format(scaledCharge / 1e21)
        scaledCharge >= 1e18 -> "%.2f EV".format(scaledCharge / 1e18)
        scaledCharge >= 1e15 -> "%.2f PV".format(scaledCharge / 1e15)
        scaledCharge >= 1e12 -> "%.2f TV".format(scaledCharge / 1e12)
        scaledCharge >= 1e9 -> "%.2f GV".format(scaledCharge / 1e9)
        scaledCharge >= 1e6 -> "%.2f MV".format(scaledCharge / 1e6)
        scaledCharge >= 1e3 -> "%.2f kV".format(scaledCharge / 1e3)
        scaledCharge >= 1 -> "%.2f V".format(scaledCharge)
        scaledCharge >= 1e-3 -> "%.2f mV".format(scaledCharge * 1e3)
        scaledCharge >= 1e-6 -> "%.2f µV".format(scaledCharge * 1e6)
        scaledCharge >= 1e-9 -> "%.2f nV".format(scaledCharge * 1e9)
        scaledCharge >= 1e-12 -> "%.2f pV".format(scaledCharge * 1e12)
        scaledCharge >= 1e-15 -> "%.2f fV".format(scaledCharge * 1e15)
        scaledCharge >= 1e-18 -> "%.2f aV".format(scaledCharge * 1e18)
        scaledCharge >= 1e-21 -> "%.2f zV".format(scaledCharge * 1e21)
        else -> "%.2e V".format(scaledCharge)                      // Format naukowy
    }
}
//Funckja zaokraglajaco wyniki w formie N
fun formatResistResult(value: Double, conversions: Map<String, Double>): String {
    val absValue = kotlin.math.abs(value)
    val conversionFactor = conversions["Ω"] ?: 1.0
    val scaledValue = absValue * conversionFactor

    return when {
        scaledValue >= 1e30 -> "%.2e Ω".format(scaledValue)
        scaledValue >= 1e24 -> "%.2f YΩ".format(scaledValue / 1e24)
        scaledValue >= 1e21 -> "%.2f ZΩ".format(scaledValue / 1e21)
        scaledValue >= 1e18 -> "%.2f EΩ".format(scaledValue / 1e18)
        scaledValue >= 1e15 -> "%.2f PΩ".format(scaledValue / 1e15)
        scaledValue >= 1e12 -> "%.2f TΩ".format(scaledValue / 1e12)
        scaledValue >= 1e9 -> "%.2f GΩ".format(scaledValue / 1e9)
        scaledValue >= 1e6 -> "%.2f MΩ".format(scaledValue / 1e6)
        scaledValue >= 1e3 -> "%.2f kΩ".format(scaledValue / 1e3)
        scaledValue >= 1 -> "%.2f Ω".format(scaledValue)
        scaledValue >= 1e-3 -> "%.2f mΩ".format(scaledValue * 1e3)
        scaledValue >= 1e-6 -> "%.2f µΩ".format(scaledValue * 1e6)
        scaledValue >= 1e-9 -> "%.2f nΩ".format(scaledValue * 1e9)
        scaledValue >= 1e-12 -> "%.2f pΩ".format(scaledValue * 1e12)
        scaledValue >= 1e-15 -> "%.2f fΩ".format(scaledValue * 1e15)
        scaledValue >= 1e-18 -> "%.2f aΩ".format(scaledValue * 1e18)
        scaledValue >= 1e-21 -> "%.2f zΩ".format(scaledValue * 1e21)
        else -> "%.2e Ω".format(scaledValue)
    }
}
