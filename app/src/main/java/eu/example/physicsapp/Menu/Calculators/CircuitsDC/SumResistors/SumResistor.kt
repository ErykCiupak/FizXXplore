package eu.example.physicsapp.Menu.Calculators.CircuitsDC.SumResistors

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.ui.theme.boldText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import eu.example.physicsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SumResistorCalculator(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Łączenie rezystorów",
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
            SumResiForm()
        }
    }
}

data class Resistor(
    val value: String,
    val unit: String = "Ω" // domyślnie ustawiamy Ω
)

@Composable
fun SumResiForm(){
    val unitConversion = mapOf(
        "µV" to 1e-6, "mV" to 1e-3, "V" to 1.0, "kV" to 1e3, "MV" to 1e6,
        "µΩ" to 1e-6, "mΩ" to 1e-3, "Ω" to 1.0, "kΩ" to 1e3, "MΩ" to 1e6
    )
    // Lista rezystorów przechowywana w stanie; inicjalnie jeden rezystor z pustą wartością
    val resistorList = remember { mutableStateListOf(Resistor("")) }
    // Lista dostępnych jednostek
    val unitOptions = listOf("μΩ", "mΩ", "Ω", "kΩ", "MΩ")
    var selectedSumResistorOption by remember { mutableStateOf("lacznie") }
    // Obliczanie sumy rezystancji – każdą wartość konwertujemy do Ω
    var totalResistanceSeries  = resistorList.sumOf { resistor ->
        val value = resistor.value.toDoubleOrNull() ?: 0.0
        val factor = when (resistor.unit) {
            "μΩ" -> 1e-6
            "mΩ" -> 1e-3
            "Ω"  -> 1.0
            "kΩ" -> 1e3
            "MΩ" -> 1e6
            else -> 1.0
        }
        value * factor
    }
    val totalResistanceParallel = resistorList.mapNotNull { resistor ->
        val value = resistor.value.toDoubleOrNull()
        val factor = when (resistor.unit) {
            "μΩ" -> 1e-6
            "mΩ" -> 1e-3
            "Ω"  -> 1.0
            "kΩ" -> 1e3
            "MΩ" -> 1e6
            else -> 1.0
        }
        value?.times(factor)?.takeIf { it > 0 } // Unikamy dzielenia przez zero
    }.sumOf { 1.0 / it }.let { if (it > 0) 1.0 / it else 0.0 }

// Wybór wyświetlanej wartości
    val wynikLaczenia = if(totalResistanceSeries==0.0||totalResistanceParallel==0.0){
        "0.0 Ω"
    }else{
        if (selectedSumResistorOption == "lacznie") {
            formatResistResult(totalResistanceSeries, unitConversion)
        } else {
            formatResistResult(totalResistanceParallel, unitConversion)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Card {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(wynikLaczenia, fontSize = 25.sp)
                }
            }
        }

        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedSumResistorOption == "lacznie",
                        onClick = { selectedSumResistorOption = "lacznie" }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.rezystory_razem),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedSumResistorOption == "odzielnie",
                        onClick = { selectedSumResistorOption = "odzielnie" }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.rezystory_odzielnie),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                }
            }
        }
        item {
            Button(
                onClick = { if (resistorList.size < 10) resistorList.add(Resistor("")) },
                enabled = resistorList.size < 10,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Dodaj rezystor")
            }
        }
        items(resistorList.size) { index ->
            var expanded by remember { mutableStateOf(false) }
            val resistor = resistorList[index]

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = resistor.value,
                    onValueChange = {
                        if (it.isBlank()) {
                            if (it.length <= 6) {
                                resistorList[index] = resistor.copy(value = it)
                            }
                        } else if (it.matches(Regex("^-?\\d*\\.?\\d*$"))) {
                            if (it.toDoubleOrNull()?.let { it >= 0 } == true) {
                                if (it.length <= 6) {
                                    resistorList[index] = resistor.copy(value = it)
                                }
                            }
                        }
                    },
                    label = { Text("R${index + 1}") },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .width(100.dp)
                )

                Box {
                    Button(
                        onClick = { expanded = true },
                        modifier = Modifier.fillMaxWidth(0.2f),
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onBackground
                        )
                    ) {
                        Text(resistor.unit, fontSize = 20.sp)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        unitOptions.forEach { unit ->
                            DropdownMenuItem(
                                text = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(unit, fontSize = 20.sp)
                                    }
                                },
                                onClick = {
                                    resistorList[index] = resistor.copy(unit = unit)
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                if (index != 0) {
                    IconButton(
                        onClick = { resistorList.removeAt(index) },
                        modifier = Modifier.width(50.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Usuń rezystor"
                        )
                    }
                } else {
                    Box(modifier = Modifier.width(50.dp))
                }
            }
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