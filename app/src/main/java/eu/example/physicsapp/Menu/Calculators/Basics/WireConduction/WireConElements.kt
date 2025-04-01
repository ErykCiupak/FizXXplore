package eu.example.physicsapp.Menu.Calculators.Basics.WireConduction

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.example.physicsapp.Menu.Calculators.Basics.ColumbCalculator.DropdownMenuBox
import eu.example.physicsapp.Menu.Calculators.SharedViewModel

@Composable
fun RadioButtonGroupExample() {
    val options = listOf("przekroju", "średnicy")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("przekroju") }

    Row(modifier = Modifier.fillMaxWidth()) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .clickable { onOptionSelected(option)
                        przekrojOrSrednica = option}
                    .padding(8.dp)
                    .weight(1f)
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option)
                        przekrojOrSrednica = option
                        resiAndConduCalcu()}
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WireDropdown(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    selectedUnit: String,
    units: List<String>,
    onUnitSelected: (String) -> Unit,
    enabled: Boolean = true,
    input: Boolean = true,
    dropdown: Boolean = true,
    viewModel: SharedViewModel

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier.width(170.dp)
        ){
            Text(label,fontSize = 13.sp)
        }
        if (input){
            OutlinedTextField(
                value = value,
                onValueChange = { input ->
                    // Pozwól na puste pole, jeśli wartość jest pusta
                    if (input.isBlank()) {
                        onValueChange(input)
                    } else if (input.matches(Regex("^-?\\d*\\.?\\d*$"))) {
                        // Pozwól na liczbę, z uwzględnieniem warunków dla liczb ujemnych
                        if (input.toDoubleOrNull()?.let { it >= 0 } == true) {
                            onValueChange(input)
                        }
                    }
                    resiAndConduCalcu()
                },
                placeholder = { Text(label,fontSize = 12.sp) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(.55f),
                enabled = enabled
            )
        }else{
            if(label=="Materiał"){
                Card (modifier = Modifier.fillMaxWidth(.5f)){
                    Button(onClick = { viewModel.updateIsSheetOpen(true) },
                        shape = BottomSheetDefaults.HiddenShape,
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(materialWybor,color = MaterialTheme.colorScheme.onBackground, fontSize = 10.sp) }
                }
            }else{
                Card (modifier = Modifier.fillMaxWidth(.5f)){
                    Button(onClick = { viewModel.updateIsSheetOpen2(true) },
                        shape = BottomSheetDefaults.HiddenShape,
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(przekrojWybor,color = MaterialTheme.colorScheme.onBackground, fontSize = 12.sp) }
                }
            }
        }
        if (dropdown){
            Box(modifier = Modifier.width(90.dp)){
                DropdownMenuBox(selectedUnit = selectedUnit, units = units, onUnitSelected = onUnitSelected, funckja = {resiAndConduCalcu()})
            }
        }else if(input){
            Spacer(modifier = Modifier.width(30.dp))
            Box(modifier = Modifier
                .width(60.dp)
                .fillMaxHeight(), contentAlignment = Alignment.CenterStart){
                if(label=="Temperatura"){
                    Text("℃", fontSize = 20.sp)
                }else{
                    Text("mm", fontSize = 20.sp)
                }

            }

        }else{
            Box(modifier = Modifier.width(90.dp).fillMaxHeight())
        }
    }

}
@Composable
fun ResistTemplate(name:String, viewModel: SharedViewModel, ktoryZmienic:String){
    Box(
        modifier = Modifier.fillMaxWidth(0.90f).height(70.dp).padding(top = 10.dp),
        contentAlignment = Alignment.Center
    ){
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 0.dp)
                .height(80.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Button(
                onClick = {
                    viewModel.updateIsSheetOpen(false)
                    viewModel.updateIsSheetOpen2(false)
                    if(ktoryZmienic=="material"){
                        materialWybor = name
                    }else if(ktoryZmienic=="przekroj"){
                        przekrojWybor = name
                    }
                    resiAndConduCalcu()
                },
                colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.background),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                }
            }
        }
    }

}
