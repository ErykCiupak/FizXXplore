package eu.example.physicsapp.Menu.Calculators.CircuitsAC.StarTrangle



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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import eu.example.physicsapp.Menu.Calculators.Basics.ColumbCalculator.InputFieldWithDropdown
import eu.example.physicsapp.Menu.Calculators.CircuitsDC.DevideVoltage.formatVoltageResult
import eu.example.physicsapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarTrangleCalculator(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Gwiazdka-trójkąt",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = boldText,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("obwodACSelect") }) {
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
            StarTrangleForm()
        }
    }
}


@Composable
fun StarTrangleForm() {
    var selectedOption by remember { mutableStateOf("trójkąt") }

    var resistValueR1 by remember { mutableStateOf("") }
    var resistUnitR1 by remember { mutableStateOf("Ω") }

    var resistValueR2 by remember { mutableStateOf("") }
    var resistUnitR2 by remember { mutableStateOf("Ω") }

    var resistValueR3 by remember { mutableStateOf("") }
    var resistUnitR3 by remember { mutableStateOf("Ω") }

    var resistValueR12 by remember { mutableStateOf("") }
    var resistUnitR12 by remember { mutableStateOf("Ω") }

    var resistValueR23 by remember { mutableStateOf("") }
    var resistUnitR23 by remember { mutableStateOf("Ω") }

    var resistValueR13 by remember { mutableStateOf("") }
    var resistUnitR13 by remember { mutableStateOf("Ω") }

    var result1 by remember { mutableStateOf("") }
    var result2 by remember { mutableStateOf("") }
    var result3 by remember { mutableStateOf("") }


    val unitConversion = mapOf(
        "µΩ" to 1e-6, "mΩ" to 1e-3, "Ω" to 1.0, "kΩ" to 1e3, "MΩ" to 1e6
    )

    // Funkcja resetująca pola
    fun resetFields() {

        resistValueR1 = ""
        resistUnitR1 = "Ω"
        resistValueR2 = ""
        resistUnitR2 = "Ω"
        resistValueR3 = ""
        resistUnitR3 = "Ω"

        resistValueR12 = ""
        resistUnitR12 = "Ω"
        resistValueR23 = ""
        resistUnitR23 = "Ω"
        resistValueR13 = ""
        resistUnitR13 = "Ω"

    }

    // Funkcja, która uruchamia resetowanie po każdej zmianie selectedOption
    LaunchedEffect(selectedOption) {
        resetFields()
    }

    // LaunchedEffect dla obliczeń
    LaunchedEffect(resistValueR1, resistValueR2,resistValueR3,resistValueR12,resistValueR23,resistValueR13,
        selectedOption,resistUnitR1,resistUnitR2,resistUnitR3,resistUnitR12,resistUnitR23,resistUnitR13) {
        val R1 = resistValueR1.toDoubleOrNull()?.times(unitConversion[resistUnitR1] ?: 1.0) ?: 0.0
        val R2 = resistValueR2.toDoubleOrNull()?.times(unitConversion[resistUnitR2] ?: 1.0) ?: 0.0
        val R3 = resistValueR3.toDoubleOrNull()?.times(unitConversion[resistUnitR3] ?: 1.0) ?: 0.0
        val R12 = resistValueR12.toDoubleOrNull()?.times(unitConversion[resistUnitR12] ?: 1.0) ?: 0.0
        val R23 = resistValueR23.toDoubleOrNull()?.times(unitConversion[resistUnitR23] ?: 1.0) ?: 0.0
        val R13 = resistValueR13.toDoubleOrNull()?.times(unitConversion[resistUnitR13] ?: 1.0) ?: 0.0

            if (selectedOption=="trójkąt"){
                if (R1 != 0.0 && R2 != 0.0 && R3 != 0.0) {
                    var culculatedVoltage = R1 + R2 + (R1 * R2 / R3)
                    result1=formatResistResult(culculatedVoltage, unitConversion)
                     culculatedVoltage = R2 + R3 + (R2 * R3 / R1)
                    result2=formatResistResult(culculatedVoltage, unitConversion)
                     culculatedVoltage = R1 + R3 + (R1 * R3 / R2)
                    result3=formatResistResult(culculatedVoltage, unitConversion)
                } else {
                    result1="0.0 Ω"
                    result2="0.0 Ω"
                    result3="0.0 Ω"
                }

            }else{
                if (R12 != 0.0 && R23 != 0.0 && R13 != 0.0) {
                    var culculatedVoltage = (R12 * R13) / (R12 + R13 + R23)
                    result1=formatResistResult(culculatedVoltage, unitConversion)
                    culculatedVoltage = (R12 * R23) / (R12 + R13 + R23)
                    result2=formatResistResult(culculatedVoltage, unitConversion)
                    culculatedVoltage = (R23 * R13) / (R12 + R13 + R23)
                    result3=formatResistResult(culculatedVoltage, unitConversion)
                } else {
                    result1="0.0 Ω"
                    result2="0.0 Ω"
                    result3="0.0 Ω"
                }
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
                                Card(modifier = Modifier.fillMaxWidth().height(80.dp)) {
                                    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                                        Row(modifier = Modifier.fillMaxWidth(.95f), horizontalArrangement = Arrangement.SpaceBetween) {
                                            if(selectedOption=="trójkąt"){
                                                Column {
                                                    Text("Rezystancja R₁")
                                                    Text("Rezystancja R₂")
                                                    Text("Rezystancja R₃")
                                                }
                                            }else{
                                                Column{
                                                    Text("Rezystancja R₁₂")
                                                    Text("Rezystancja R₂₃")
                                                    Text("Rezystancja R₁₃")
                                                }
                                            }
                                            Column(horizontalAlignment = Alignment.End) {
                                                Text(result1)
                                                Text(result2)
                                                Text(result3)
                                            }
                                        }
                                    }

                                }
                                Spacer(modifier = Modifier.height(50.dp))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                                    Image(
                                        painter = painterResource(id = R.drawable.gwiazda_trojkat),
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
                                        selected = selectedOption == "trójkąt",
                                        onClick = { selectedOption = "trójkąt" }
                                    )
                                    Text(text = "gwiazda->trójkąt", modifier = Modifier.padding(start = 8.dp))
                                }
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    RadioButton(
                                        selected = selectedOption == "gwiazda",
                                        onClick = { selectedOption = "gwiazda" }
                                    )
                                    Text(text = "trójkąt->gwiazda", modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if(selectedOption=="trójkąt"){
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Rezystancja R₁",
                value = resistValueR1,
                onValueChange = { if (it.length <= 6) resistValueR1 = it },
                selectedUnit = resistUnitR1,
                units = listOf("µΩ", "mΩ", "Ω", "kΩ", "MΩ"),
                onUnitSelected = { resistUnitR1 = it },
                enabled = true,
                allowNegative = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Rezystancja R₂",
                value = resistValueR2,
                onValueChange = { if (it.length <= 6) resistValueR2 = it },
                selectedUnit = resistUnitR2,
                units = listOf("µΩ", "mΩ", "Ω", "kΩ", "MΩ"),
                onUnitSelected = { resistUnitR2 = it },
                enabled = true,
                allowNegative = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Rezystancja R₃",
                value = resistValueR3,
                onValueChange = { if (it.length <= 6) resistValueR3 = it },
                selectedUnit = resistUnitR3,
                units = listOf("µΩ", "mΩ", "Ω", "kΩ", "MΩ"),
                onUnitSelected = { resistUnitR3 = it },
                enabled = true,
                allowNegative = false
            )
        }
        else{
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Rezystancja R₁₂",
                value = resistValueR12,
                onValueChange = { if (it.length <= 6) resistValueR12 = it },
                selectedUnit = resistUnitR12,
                units = listOf("µΩ", "mΩ", "Ω", "kΩ", "MΩ"),
                onUnitSelected = { resistUnitR12 = it },
                enabled = true,
                allowNegative = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Rezystancja R₂₃",
                value = resistValueR23,
                onValueChange = { if (it.length <= 6) resistValueR23 = it },
                selectedUnit = resistUnitR23,
                units = listOf("µΩ", "mΩ", "Ω", "kΩ", "MΩ"),
                onUnitSelected = { resistUnitR23 = it },
                enabled = true,
                allowNegative = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Rezystancja R₁₃",
                value = resistValueR13,
                onValueChange = { if (it.length <= 6) resistValueR13 = it },
                selectedUnit = resistUnitR13,
                units = listOf("µΩ", "mΩ", "Ω", "kΩ", "MΩ"),
                onUnitSelected = { resistUnitR13 = it },
                enabled = true,
                allowNegative = false
            )
        }

    }
}

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

