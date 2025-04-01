package eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.AreaCalculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.Menu.Calculators.OutputTemplate
import eu.example.physicsapp.Menu.Calculators.SharedViewModel
import eu.example.physicsapp.Menu.Calculators.UnitTemplate
import eu.example.physicsapp.ui.theme.boldText
import eu.example.physicsapp.ui.theme.regularText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcArea(navController: NavController,viewModel: SharedViewModel){

    //Resetowanie zmiennych po wejsciu ponownie
    fun tylkoRaz(){
        viewModel.updateSelectedUnitName("metr kwadratowy")
        viewModel.upadateSelectedUnitExts("m²")
        viewModel.inputNumber = ""
        areaConvert(viewModel)
    }
    var hasExecuted by remember { mutableStateOf(false) }
    if (!hasExecuted) {
        tylkoRaz()
        hasExecuted= true
    }

    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Powierzchnia",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = boldText, fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("unitConverter")
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back arrow"
                        )
                    }
                }
            )

        }
    ) {innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .clickable { // Kliknięcie w inne miejsce
                    isFocused = false
                    focusManager.clearFocus() // Zwalnia fokus
                }
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { viewModel.updateIsSheetOpen(true) },
                    colors = ButtonColors(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.onBackground,
                        Color.White,
                        Color.White), modifier = Modifier
                        .width(150.dp)
                        .padding(0.dp)
                        .height(120.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {

                    Icon(Icons.Default.Create, contentDescription = "pen", tint = MaterialTheme.colorScheme.onBackground, modifier = Modifier.size(30.dp))
                        Text(
                            text = viewModel.selectedUnitName,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontFamily = regularText,
                            modifier = Modifier.width(110.dp)

                        )


                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(120.dp)
                        .width(250.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedTextField(
                        label = { Text(text = "Wpisz liczbe", fontFamily = regularText,fontSize = 13.sp, color = MaterialTheme.colorScheme.onBackground) },
                        value = viewModel.inputNumber,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            if (it.length <= 6) viewModel.inputNumber = it
                            areaConvert(viewModel)
                        },
                        modifier = Modifier
                            .height(60.dp)
                            .onFocusChanged { focusState ->
                                isFocused = focusState.isFocused // Aktualizuje stan fokusu
                            }
                            .width(150.dp), colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
                        ),
                        singleLine = true

                    )
                    Text(
                        text = viewModel.selectedUnitExts,
                        fontFamily = regularText,
                        modifier = Modifier.width(70.dp)
                    )
                }
            }
            data class OutputData(val name:String, val outPutNumber:String, val extand:String, val viewModel: SharedViewModel)
            val listOfOutput = listOf(
                OutputData("metr kwadratowy", viewModel.outPutNumberM2, "m²", viewModel),
                OutputData("centymetr kwadratowy", viewModel.outPutNumberCm2, "cm²", viewModel),
                OutputData("milimetr kwadratowy", viewModel.outPutNumberMm2, "mm²", viewModel),
                OutputData("kilometr kwadratowy", viewModel.outPutNumberKm2, "km²", viewModel),
                OutputData("ar", viewModel.outPutNumberAr, "ar", viewModel),
                OutputData("hektar", viewModel.outPutNumberHa, "ha", viewModel),
                OutputData("cal kwadratowy", viewModel.outPutNumberIn2, "in²", viewModel),
                OutputData("stopa kwadratowa", viewModel.outPutNumberFt2, "ft²", viewModel),
                OutputData("jard kwadratowy", viewModel.outPutNumberYd2, "yd²", viewModel),
                OutputData("mila kwadratowa", viewModel.outPutNumberMi2, "mi²", viewModel),
                OutputData("akr", viewModel.outPutNumberAc, "ac", viewModel)
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(listOfOutput) { item ->
                    OutputTemplate(item.name, item.outPutNumber, item.extand, item.viewModel)
                }
            }
        }
    }
    //Lista wysuwana
    if (viewModel.isSheetOpen){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                viewModel.updateIsSheetOpen(false)
            },
            dragHandle = {
                Text(
                    text = "Wybierz jednostke",
                    fontFamily = regularText,
                    modifier = Modifier.padding(0.dp,15.dp)
                )
            }, modifier = Modifier.fillMaxHeight(0.5f)
        ) {
            data class UnitData(val name:String, val extand:String, val viewModel: SharedViewModel, val convertVersion:String)
            val listOfOutput = listOf(
                UnitData("metr kwadratowy","m²",viewModel,"area"),
                UnitData("milimetr kwadratowy","mm²",viewModel,"area"),
                UnitData("centymetr kwadratowy","cm²",viewModel,"area"),
                UnitData("kilometr kwadratowy","km²",viewModel,"area"),
                UnitData("ar","ar",viewModel,"area"),
                UnitData("hektar","ha",viewModel,"area"),
                UnitData("cal kwadratowy","in²",viewModel,"area"),
                UnitData("stopa kwadratowa","ft²",viewModel,"area"),
                UnitData("jard kwadratowy","yd²",viewModel,"area"),
                UnitData("mila kwadratowa","mi²",viewModel,"area"),
                UnitData("akr", "ac", viewModel,"area")
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(listOfOutput) { item ->
                    UnitTemplate(item.name,item.extand, item.viewModel,item.convertVersion)
                }
            }
        }
    }

}
