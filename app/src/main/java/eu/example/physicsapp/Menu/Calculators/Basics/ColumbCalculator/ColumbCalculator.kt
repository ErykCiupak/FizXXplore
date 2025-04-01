package eu.example.physicsapp.Menu.Calculators.Basics.ColumbCalculator

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
import eu.example.physicsapp.R
import kotlinx.coroutines.launch
import kotlin.math.sqrt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumbCalculator(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Siła Coulomba",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = boldText,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("basicsSelect") }) {
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
            CoulombCalculatorForm()
        }
    }
}


@Composable
fun CoulombCalculatorForm() {
    var selectedOption by remember { mutableStateOf("siła Coulomba") }
    var chargeQ1Value by remember { mutableStateOf("") }
    var chargeQ1Unit by remember { mutableStateOf("C") }
    var chargeQ2Value by remember { mutableStateOf("") }
    var chargeQ2Unit by remember { mutableStateOf("C") }
    var distanceValue by remember { mutableStateOf("") }
    var distanceUnit by remember { mutableStateOf("m") }
    var forceValue by remember { mutableStateOf("") }
    var forceUnit by remember { mutableStateOf("N") }
    var result by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    // Jednostki dla siły Coulomba
    LaunchedEffect(
        chargeQ1Value, chargeQ2Value, distanceValue, forceValue, selectedOption
    ) {
        // Walidacja danych wejściowych
        val isDataValid = when (selectedOption) {
            "siła Coulomba" -> chargeQ1Value.isNotEmpty() && chargeQ2Value.isNotEmpty() && distanceValue.isNotEmpty()
            "odległość" -> chargeQ1Value.isNotEmpty() && chargeQ2Value.isNotEmpty() && forceValue.isNotEmpty()
            "ładunek Q2" -> chargeQ1Value.isNotEmpty() && distanceValue.isNotEmpty() && forceValue.isNotEmpty()
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
    val forceUnits = listOf("µN", "mN", "N", "kN", "MN")
    val unitConversion = mapOf(
        "µC" to 1e-6, "mC" to 1e-3, "C" to 1.0, "kC" to 1e3, "MC" to 1e6,
        "µm" to 1e-6, "mm" to 1e-3, "cm" to 1e-2, "dm" to 1e-1, "m" to 1.0,
        "dam" to 1e1, "hm" to 1e2, "km" to 1e3
    )
    val forceUnitConversions = mapOf(
        "µN" to 1e-6, "mN" to 1e-3, "N" to 1.0, "kN" to 1e3, "MN" to 1e6
    )

    // Funkcja resetująca pola
    fun resetFields() {
        chargeQ1Value = ""
        chargeQ1Unit = "C"
        chargeQ2Value = ""
        chargeQ2Unit = "C"
        distanceValue = ""
        distanceUnit = "m"
        forceValue = ""
        forceUnit = "N"
    }

    // Funkcja, która uruchamia resetowanie po każdej zmianie selectedOption
    LaunchedEffect(selectedOption) {
        resetFields()
    }

    val calculatedR = remember { mutableDoubleStateOf(0.0) }
    val calculatedQ2 = remember { mutableDoubleStateOf(0.0) }
    // LaunchedEffect dla obliczeń
    LaunchedEffect(chargeQ1Value, chargeQ1Unit, chargeQ2Value, chargeQ2Unit, distanceValue, distanceUnit, forceValue, forceUnit) {
        val q1 = chargeQ1Value.toDoubleOrNull()?.times(unitConversion[chargeQ1Unit] ?: 1.0) ?: 0.0
        val q2 = chargeQ2Value.toDoubleOrNull()?.times(unitConversion[chargeQ2Unit] ?: 1.0) ?: 0.0
        val r = distanceValue.toDoubleOrNull()?.times(unitConversion[distanceUnit] ?: 1.0) ?: 1.0
        val force = forceValue.toDoubleOrNull()?.times(forceUnitConversions[forceUnit] ?: 1.0) ?: 0.0

        result = when (selectedOption) {
            "siła Coulomba" -> {
                if (q1 == 0.0 || q2 == 0.0 || r == 0.0) {
                    "Proszę wprowadzić poprawne dane"
                } else {
                    val calculatedForce = (8.9875e9 * q1 * q2) / (r * r)
                    formatResult(calculatedForce, forceUnitConversions)
                }
            }
            "odległość" -> {
                if (q1 != 0.0 && q2 != 0.0 && force != 0.0) {
                    val calculatedRValue = sqrt((8.9875e9 * q1 * q2) / force)
                    calculatedR.value = calculatedRValue  // Przypisanie wartości do calculatedR
                    formatDistanceResult(calculatedRValue, unitConversion)
                } else {
                    "Podaj ładunki i siłę"
                }
            }
            "ładunek Q2" -> {
                if (q1 != 0.0 && r != 0.0 && force != 0.0) {
                    val calculatedQ2Value = force * r * r / (8.9875e9 * q1)
                    calculatedQ2.value = calculatedQ2Value
                    formatChargeResult(calculatedQ2Value, unitConversion)  // Użycie funkcji formatChargeResult
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
            //Sprawdzanie warunków do określenia jakie obrazki zostanną użyte
            val q1Image = when {
                chargeQ1Value.toDoubleOrNull()?.let { it > 0 } == true -> R.drawable.coulomb_plus
                chargeQ1Value.toDoubleOrNull()?.let { it < 0 } == true -> R.drawable.coulomb_minus
                else -> R.drawable.coulomb_neutralny
            }

            //Strzałki
            var arrow = R.drawable.pusty
            var q2Image = R.drawable.coulomb_neutralny
            when(selectedOption){
                "siła Coulomba" -> {
                    arrow = when {
                        // Jeśli odległość nie istnieje (np. jest pusta lub niepoprawna)
                        distanceValue.isBlank() || distanceValue.toDoubleOrNull() == null -> R.drawable.pusty

                        // Obie ładunki są dodatnie
                        chargeQ1Value.toDoubleOrNull()?.let { it > 0 } == true &&
                                chargeQ2Value.toDoubleOrNull()?.let { it > 0 } == true -> R.drawable.coulomb_od

                        // Obie ładunki są ujemne
                        chargeQ1Value.toDoubleOrNull()?.let { it < 0 } == true &&
                                chargeQ2Value.toDoubleOrNull()?.let { it < 0 } == true -> R.drawable.coulomb_od

                        // Pierwszy ładunek dodatni, drugi ujemny
                        chargeQ1Value.toDoubleOrNull()?.let { it > 0 } == true &&
                                chargeQ2Value.toDoubleOrNull()?.let { it < 0 } == true -> R.drawable.coulomb_do

                        // Pierwszy ładunek ujemny, drugi dodatni
                        chargeQ1Value.toDoubleOrNull()?.let { it < 0 } == true &&
                                chargeQ2Value.toDoubleOrNull()?.let { it > 0 } == true -> R.drawable.coulomb_do

                        // Domyślnie, jeśli inne warunki nie pasują
                        else -> R.drawable.pusty
                    }
                    q2Image = when {
                        chargeQ2Value.toDoubleOrNull()?.let { it > 0 } == true -> R.drawable.coulomb_plus
                        chargeQ2Value.toDoubleOrNull()?.let { it < 0 } == true -> R.drawable.coulomb_minus
                        else -> R.drawable.coulomb_neutralny
                    }
                }
                "odległość" -> {
                    arrow = when {
                        // Jeśli odległość nie istnieje (np. jest pusta lub niepoprawna)
                        calculatedR.value.isNaN() || calculatedR.value == 0.0 -> R.drawable.pusty

                        // Obie ładunki są dodatnie
                        chargeQ1Value.toDoubleOrNull()?.let { it > 0 } == true &&
                                chargeQ2Value.toDoubleOrNull()
                                    ?.let { it > 0 } == true -> R.drawable.coulomb_od

                        // Obie ładunki są ujemne
                        chargeQ1Value.toDoubleOrNull()?.let { it < 0 } == true &&
                                chargeQ2Value.toDoubleOrNull()
                                    ?.let { it < 0 } == true -> R.drawable.coulomb_od

                        // Pierwszy ładunek dodatni, drugi ujemny
                        chargeQ1Value.toDoubleOrNull()?.let { it > 0 } == true &&
                                chargeQ2Value.toDoubleOrNull()
                                    ?.let { it < 0 } == true -> R.drawable.coulomb_do

                        // Pierwszy ładunek ujemny, drugi dodatni
                        chargeQ1Value.toDoubleOrNull()?.let { it < 0 } == true &&
                                chargeQ2Value.toDoubleOrNull()
                                    ?.let { it > 0 } == true -> R.drawable.coulomb_do

                        // Domyślnie, jeśli inne warunki nie pasują
                        else -> R.drawable.pusty
                    }
                    q2Image = when {
                        chargeQ2Value.toDoubleOrNull()?.let { it > 0 } == true -> R.drawable.coulomb_plus
                        chargeQ2Value.toDoubleOrNull()?.let { it < 0 } == true -> R.drawable.coulomb_minus
                        else -> R.drawable.coulomb_neutralny
                    }
                }
                "ładunek Q2" -> {
                    arrow = when {
                        // Jeśli odległość nie istnieje (np. jest pusta lub niepoprawna)
                        distanceValue.isBlank() || distanceValue.toDoubleOrNull() == null -> R.drawable.pusty

                        // Obie ładunki są dodatnie
                        chargeQ1Value.toDoubleOrNull()?.let { it > 0 } == true && calculatedQ2.value > 0 -> R.drawable.coulomb_od

                        // Obie ładunki są ujemne
                        chargeQ1Value.toDoubleOrNull()?.let { it < 0 } == true &&
                                calculatedQ2.value < 0 -> R.drawable.coulomb_od

                        // Pierwszy ładunek dodatni, drugi ujemny
                        chargeQ1Value.toDoubleOrNull()?.let { it > 0 } == true &&
                                calculatedQ2.value < 0 -> R.drawable.coulomb_od

                        // Pierwszy ładunek ujemny, drugi dodatni
                        chargeQ1Value.toDoubleOrNull()?.let { it < 0 } == true &&
                                calculatedQ2.value > 0 -> R.drawable.coulomb_od

                        // Domyślnie, jeśli inne warunki nie pasują
                        else -> R.drawable.pusty
                }
                    q2Image = when {
                        calculatedQ2.value > 0 -> R.drawable.coulomb_plus
                        calculatedQ2.value < 0 -> R.drawable.coulomb_minus
                        else -> R.drawable.coulomb_neutralny
                    }
            }}


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
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    //Kula Q1
                                    Box(modifier = Modifier.fillMaxWidth(.3f).weight(1f)){
                                        Image(
                                            painter = painterResource(id = q1Image),
                                            contentDescription = null,

                                        )
                                    }
                                    //Strzałki
                                    Box(modifier = Modifier.fillMaxWidth(.4f).weight(2f)){
                                        Image(
                                            painter = painterResource(id = arrow),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                                            )
                                    }
                                    //Kula Q2
                                    Box(modifier = Modifier.fillMaxWidth(.3f).weight(1f)){
                                        Image(
                                            painter = painterResource(id = q2Image),
                                            contentDescription = null,

                                        )
                                    }
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
                                        selected = selectedOption == "siła Coulomba",
                                        onClick = { selectedOption = "siła Coulomba" }
                                    )
                                    Text(text = "siłę Coulomba", modifier = Modifier.padding(start = 8.dp))
                                }

                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    RadioButton(
                                        selected = selectedOption == "odległość",
                                        onClick = { selectedOption = "odległość" }
                                    )
                                    Text(text = "odległość", modifier = Modifier.padding(start = 8.dp))
                                }

                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                    RadioButton(
                                        selected = selectedOption == "ładunek Q2",
                                        onClick = { selectedOption = "ładunek Q2" }
                                    )
                                    Text(text = "ładunek Q2", modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Wprowadzenie wartości ładunku Q1
        InputFieldWithDropdown(
            label = "Ładunek Q1",
            value = chargeQ1Value,
            onValueChange = { if (it.length <= 6) chargeQ1Value = it },
            selectedUnit = chargeQ1Unit,
            units = listOf("µC", "mC", "C", "kC", "MC"),
            onUnitSelected = { chargeQ1Unit = it },
            allowNegative = true // Dopuszczone wartości ujemne
        )


        // Wprowadzenie wartości ładunku Q2
        if (selectedOption != "ładunek Q2") {
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Ładunek Q2",
                value = chargeQ2Value,
                onValueChange = { if (it.length <= 6) chargeQ2Value = it },
                selectedUnit = chargeQ2Unit,
                units = listOf("µC", "mC","C","kC", "MC"),
                onUnitSelected = { chargeQ2Unit = it },
                enabled = true,
                allowNegative = true
            )
        }


        // Wprowadzenie wartości odległości
        if (selectedOption != "odległość") {
            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Odległość r",
                value = distanceValue,
                onValueChange = { if (it.length <= 6) distanceValue = it },
                selectedUnit = distanceUnit,
                units = listOf("mm", "cm", "m", "µm", "km"),
                onUnitSelected = { distanceUnit = it },
                enabled = true,
                allowNegative = false
            )
        }
        var forceErrorMessage by remember { mutableStateOf<String?>(null) }
        // Wprowadzenie wartości siły Coulomba
        if (selectedOption != "siła Coulomba") {

            Spacer(modifier = Modifier.height(8.dp))
            InputFieldWithDropdown(
                label = "Siła Coulomba (N)",
                value = forceValue,
                onValueChange = {
                    if (it.length <= 6) {
                        forceValue = it
                        val force = it.toDoubleOrNull()
                        if (force != null) {
                            if (force < 0 && !areChargesOpposite(chargeQ1Value, chargeQ2Value)) {
                                forceErrorMessage = "Liczba musi być większa od zera"
                            } else if (force >= 0 && areChargesOpposite(chargeQ1Value, chargeQ2Value)) {
                                forceErrorMessage = "Liczba musi być mniejsza od zera"
                            } else {
                                forceErrorMessage = null
                            }
                        } else {
                            forceErrorMessage = null
                        }
                    }
                },
                selectedUnit = forceUnit,
                units = forceUnits,
                onUnitSelected = { selectedUnit -> forceUnit = selectedUnit },
                enabled = selectedOption != "siła Coulomba",
                allowNegative = areChargesOpposite(chargeQ1Value, chargeQ2Value),
                errorMessage = forceErrorMessage
            )
        }


        Spacer(modifier = Modifier.height(16.dp))


    }
}


//Funckja zaokraglajaco wyniki w formie m
fun formatDistanceResult(value: Double, conversions: Map<String, Double>): String {
    val absValue = kotlin.math.abs(value)
    val scaledValue = absValue * (conversions["m"] ?: 1.0)

    return when {
        scaledValue >= 1e9 -> "%.2f Gm".format(scaledValue / 1e9) // Gigametry
        scaledValue >= 1e6 -> "%.2f Mm".format(scaledValue / 1e6) // Megametry
        scaledValue >= 1e3 -> "%.2f km".format(scaledValue / 1e3) // Kilometry
        scaledValue >= 1 -> "%.2f m".format(scaledValue)          // Metry
        scaledValue >= 1e-2 -> "%.2f cm".format(scaledValue * 1e2) // Centymetry
        scaledValue >= 1e-3 -> "%.2f mm".format(scaledValue * 1e3) // Milimetry
        scaledValue >= 1e-6 -> "%.2f µm".format(scaledValue * 1e6) // Mikrometry
        scaledValue >= 1e-9 -> "%.2f nm".format(scaledValue * 1e9) // Nanometry
        scaledValue >= 1e-12 -> "%.2f pm".format(scaledValue * 1e12) // Pikometry
        else -> "%.2e m".format(scaledValue)                      // Format naukowy
    }
}
//Funckja zaokraglajaco wyniki w formie C
fun formatChargeResult(charge: Double, conversions: Map<String, Double>): String {
    val absCharge = kotlin.math.abs(charge)
    val scaledCharge = absCharge * (conversions["C"] ?: 1.0)

    return when {
        scaledCharge >= 1e12 -> "%.2f TC".format(scaledCharge / 1e12) // Terakulomby
        scaledCharge >= 1e9 -> "%.2f GC".format(scaledCharge / 1e9)   // Gigakulomby
        scaledCharge >= 1e6 -> "%.2f MC".format(scaledCharge / 1e6)   // Megakulomby
        scaledCharge >= 1e3 -> "%.2f kC".format(scaledCharge / 1e3)   // Kilokulomby
        scaledCharge >= 1 -> "%.2f C".format(scaledCharge)            // Kulomby
        scaledCharge >= 1e-3 -> "%.2f mC".format(scaledCharge * 1e3)  // Milikulomby
        scaledCharge >= 1e-6 -> "%.2f µC".format(scaledCharge * 1e6)  // Mikrokulomby
        scaledCharge >= 1e-9 -> "%.2f nC".format(scaledCharge * 1e9)  // Nanokulomby
        scaledCharge >= 1e-12 -> "%.2f pC".format(scaledCharge * 1e12) // Pikokulomby
        else -> "%.2e C".format(scaledCharge)                        // Format naukowy
    }
}
//Funckja zaokraglajaco wyniki w formie N
fun formatResult(value: Double, conversions: Map<String, Double>): String {
    val absValue = kotlin.math.abs(value)
    val conversionFactor = conversions["N"] ?: 1.0
    val scaledValue = absValue * conversionFactor

    return when {
        scaledValue >= 1e30 -> "%.2e N".format(scaledValue)
        scaledValue >= 1e24 -> "%.2f YN".format(scaledValue / 1e24)
        scaledValue >= 1e21 -> "%.2f ZN".format(scaledValue / 1e21)
        scaledValue >= 1e18 -> "%.2f EN".format(scaledValue / 1e18)
        scaledValue >= 1e15 -> "%.2f PN".format(scaledValue / 1e15)
        scaledValue >= 1e12 -> "%.2f TN".format(scaledValue / 1e12)
        scaledValue >= 1e9 -> "%.2f GN".format(scaledValue / 1e9)
        scaledValue >= 1e6 -> "%.2f MN".format(scaledValue / 1e6)
        scaledValue >= 1e3 -> "%.2f kN".format(scaledValue / 1e3)
        scaledValue >= 1 -> "%.2f N".format(scaledValue)
        scaledValue >= 1e-3 -> "%.2f mN".format(scaledValue * 1e3)
        scaledValue >= 1e-6 -> "%.2f µN".format(scaledValue * 1e6)
        scaledValue >= 1e-9 -> "%.2f nN".format(scaledValue * 1e9)
        scaledValue >= 1e-12 -> "%.2f pN".format(scaledValue * 1e12)
        scaledValue >= 1e-15 -> "%.2f fN".format(scaledValue * 1e15)
        scaledValue >= 1e-18 -> "%.2f aN".format(scaledValue * 1e18)
        scaledValue >= 1e-21 -> "%.2f zN".format(scaledValue * 1e21)
        else -> "%.2e N".format(scaledValue)
    }
}
