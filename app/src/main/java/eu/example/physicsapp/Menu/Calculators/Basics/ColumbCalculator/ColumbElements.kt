package eu.example.physicsapp.Menu.Calculators.Basics.ColumbCalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputFieldWithDropdown(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    selectedUnit: String,
    units: List<String>,
    onUnitSelected: (String) -> Unit,
    enabled: Boolean = true,
    allowNegative: Boolean = false, // Dodano parametr do kontroli wartości ujemnych
    errorMessage: String? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = { input ->
                // Pozwól na puste pole, jeśli wartość jest pusta
                if (input.isBlank()) {
                    onValueChange(input)
                } else if (input.matches(Regex("^-?\\d*\\.?\\d*$"))) {
                    // Pozwól na liczbę, z uwzględnieniem warunków dla liczb ujemnych
                    if (allowNegative || input.toDoubleOrNull()?.let { it >= 0 } == true) {
                        onValueChange(input)
                    }
                }
            },
            label = { Text(label) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(.7f),
            enabled = enabled
        )
        Spacer(modifier = Modifier.width(8.dp))
        DropdownMenuBox(selectedUnit = selectedUnit, units = units, onUnitSelected = onUnitSelected, funckja = {})
    }
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}

@Composable
fun DropdownMenuBox(
    selectedUnit: String,
    units: List<String>,
    onUnitSelected: (String) -> Unit,
    enable: Boolean = true,
    funckja : () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(0.dp), enabled = enable,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Text(selectedUnit, fontSize = 20.sp)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            units.forEach { unit ->
                DropdownMenuItem(
                    text = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center // Wyśrodkowanie
                        ) {
                            Text(unit, fontSize = 20.sp)
                        }
                    },
                    onClick = {
                        onUnitSelected(unit)
                        expanded = false
                        funckja()
                    }
                )
            }
        }
    }
}
fun areChargesOpposite(q1: String, q2: String): Boolean {
    val q1Value = q1.toDoubleOrNull()
    val q2Value = q2.toDoubleOrNull()

    return q1Value != null && q2Value != null && q1Value * q2Value < 0
}
