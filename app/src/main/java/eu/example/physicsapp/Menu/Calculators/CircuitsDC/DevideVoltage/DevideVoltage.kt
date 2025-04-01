package eu.example.physicsapp.Menu.Calculators.CircuitsDC.DevideVoltage

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
fun DevVolCalculator(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Dzielnik napięcia",
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
            DevideVoltageForm()
        }
    }
}


@Composable
fun DevideVoltageForm() {
    var selectedOption by remember { mutableStateOf("napięcie U2") }

    var voltageValue1 by remember { mutableStateOf("") }
    var voltageUnit1 by remember { mutableStateOf("V") }

    var voltageValue2 by remember { mutableStateOf("") }
    var voltageUnit2 by remember { mutableStateOf("V") }

    var resistValue1 by remember { mutableStateOf("") }
    var resistUnit1 by remember { mutableStateOf("Ω") }

    var resistValue2 by remember { mutableStateOf("") }
    var resistUnit2 by remember { mutableStateOf("Ω") }

    var result by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    // Jednostki dla siły Coulomba
    LaunchedEffect(
        voltageValue1, voltageValue2, resistValue1,resistValue2, selectedOption
    ) {
        // Walidacja danych wejściowych
        val isDataValid = when (selectedOption) {
            "napięcie U2" -> voltageValue1.isNotEmpty()  && resistValue1.isNotEmpty() && resistValue2.isNotEmpty()
            "rezystancję R2" -> voltageValue1.isNotEmpty()  && resistValue1.isNotEmpty() && voltageValue2.isNotEmpty()
            "rezystancję R1" -> voltageValue1.isNotEmpty()  && resistValue2.isNotEmpty() && voltageValue2.isNotEmpty()
            "napięcie U1" -> resistValue1.isNotEmpty()  && resistValue2.isNotEmpty() && voltageValue2.isNotEmpty()
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
        "µΩ" to 1e-6, "mΩ" to 1e-3, "Ω" to 1.0, "kΩ" to 1e3, "MΩ" to 1e6
    )

    // Funkcja resetująca pola
    fun resetFields() {
        voltageValue1 = ""
        voltageUnit1 = "V"
        voltageValue2 = ""
        voltageUnit2 = "V"
        resistValue2 = ""
        resistUnit2 = "Ω"
        resistValue1 = ""
        resistUnit1 = "Ω"
    }

    // Funkcja, która uruchamia resetowanie po każdej zmianie selectedOption
    LaunchedEffect(selectedOption) {
        resetFields()
    }

    // LaunchedEffect dla obliczeń
    LaunchedEffect(voltageValue1, voltageValue2, resistValue1, resistValue2, selectedOption,voltageUnit2,voltageUnit1,resistUnit1,resistUnit2) {
        val R1 = resistValue1.toDoubleOrNull()?.times(unitConversion[resistUnit1] ?: 1.0) ?: 0.0
        val R2 = resistValue2.toDoubleOrNull()?.times(unitConversion[resistValue2] ?: 1.0) ?: 1.0
        val U1 = voltageValue1.toDoubleOrNull()?.times(unitConversion[voltageValue1] ?: 1.0) ?: 0.0
        val U2 = voltageValue2.toDoubleOrNull()?.times(unitConversion[voltageValue2] ?: 1.0) ?: 0.0

        if(U2<U1){
            result = when (selectedOption) {
                "napięcie U2" -> {
                    if (U1 != 0.0 && R1 != 0.0 && R2 != 0.0) {
                        val culculatedVoltage = U1*(R2/(R1+R2))
                        formatVoltageResult(culculatedVoltage, unitConversion)
                    } else {
                        "Podaj: U1, R1 i R2"
                    }
                }
                "napięcie U1" ->{
                    if (U2 != 0.0 && R1 != 0.0 && R2 != 0.0) {
                        val culculatedVoltage = U2*((R1+R2)/R2)
                        formatVoltageResult(culculatedVoltage, unitConversion)
                    } else {
                        "Podaj: U2, R1 i R2"
                    }
                }
                "rezystancję R1" ->{
                    if (U1 != 0.0 && U2 != 0.0 && R2 != 0.0) {
                        val culculatedVoltage = R2* ((U1/U2)-1)
                        formatResistResult(culculatedVoltage, unitConversion)
                    } else {
                        "Podaj: U1, U2 i R2"
                    }
                }
                "rezystancję R2" ->{
                    if (U1 != 0.0 && R1 != 0.0 && U2 != 0.0) {
                        val culculatedVoltage = R1 * (U2/(U1-U2))
                        formatResistResult(culculatedVoltage, unitConversion)
                    } else {
                        "Podaj: R1, U1 i U2"
                    }
                }

                else -> "Nieznana opcja"
            }
        }else{
            result = "U2 musi być mniejsze od U1"
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
                                        painter = painterResource(id = R.drawable.dzielnik_napiec),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                        modifier = Modifier
                                            .fillMaxWidth() // Wypełnia całą wysokość Row
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
                                        selected = selectedOption == "napięcie U2",
                                        onClick = { selectedOption = "napięcie U2" }
                                    )
                                    Text(text = "napięcie U2", modifier = Modifier.padding(start = 8.dp))
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    RadioButton(
                                        selected = selectedOption == "rezystancję R2",
                                        onClick = { selectedOption = "rezystancję R2" }
                                    )
                                    Text(text = "rezystancję R2", modifier = Modifier.padding(start = 8.dp))
                                }

                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    RadioButton(
                                        selected = selectedOption == "rezystancję R1",
                                        onClick = { selectedOption = "rezystancję R1" }
                                    )
                                    Text(text = "rezystancję R1", modifier = Modifier.padding(start = 8.dp))
                                }

                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    RadioButton(
                                        selected = selectedOption == "napięcie U1",
                                        onClick = { selectedOption = "napięcie U1" }
                                    )
                                    Text(text = "napięcie U1", modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if(selectedOption!="napięcie U1"){
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "napięcie U1",
                value = voltageValue1,
                onValueChange = { if (it.length <= 6) voltageValue1 = it },
                selectedUnit = voltageUnit1,
                units = listOf("µA", "mA", "A", "kA", "MA"),
                onUnitSelected = { voltageUnit1 = it },
                enabled = true,
                allowNegative = false
            )
        }
        if(selectedOption!="napięcie U2"){
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "napięcie U2",
                value = voltageValue2,
                onValueChange = { if (it.length <= 6) voltageValue2 = it },
                selectedUnit = voltageUnit2,
                units = listOf("µA", "mA", "A", "kA", "MA"),
                onUnitSelected = { voltageUnit2 = it },
                enabled = true,
                allowNegative = false
            )
        }
        if(selectedOption!="rezystancję R1"){
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "rezystancję R1",
                value = resistValue1,
                onValueChange = { if (it.length <= 6) resistValue1 = it },
                selectedUnit = resistUnit1,
                units = listOf("µA", "mA", "A", "kA", "MA"),
                onUnitSelected = { resistUnit1 = it },
                enabled = true,
                allowNegative = false
            )
        }
        if(selectedOption!="rezystancję R2"){
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "rezystancję R2",
                value = resistValue2,
                onValueChange = { if (it.length <= 6) resistValue2 = it },
                selectedUnit = resistUnit2,
                units = listOf("µA", "mA", "A", "kA", "MA"),
                onUnitSelected = { resistUnit2 = it },
                enabled = true,
                allowNegative = false
            )
        }
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
