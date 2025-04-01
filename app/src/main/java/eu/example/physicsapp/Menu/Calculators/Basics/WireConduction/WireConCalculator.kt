package eu.example.physicsapp.Menu.Calculators.Basics.WireConduction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.ui.theme.boldText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import eu.example.physicsapp.Menu.Calculators.SharedViewModel
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.AreaCalculator.areaConvert
import eu.example.physicsapp.ui.theme.regularText

var dlugosc by mutableStateOf("")
var test by mutableStateOf("m")
var przekrojOrSrednica by mutableStateOf("przekroju")
var materialWybor by mutableStateOf("miedź")
var przekrojWybor by mutableStateOf("1,5 mm²")
var rezystancja by mutableStateOf("0.0")
var konduktacja by mutableStateOf("0.0")
var temperatura by mutableStateOf("20")
var srednica by mutableStateOf("")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WireCondCalculator(navController: NavController,viewModel: SharedViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Rezystancja / konduktancja",
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
            WireConduCalculatorForm(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WireConduCalculatorForm(viewModel: SharedViewModel){
    fun tylkoRaz(){
        materialWybor = "miedź"
        przekrojWybor = "1,5 mm²"
        przekrojOrSrednica = "przekroju"
        dlugosc = ""
        temperatura = "20"
        srednica = ""
        rezystancja = "0.0"
        test = "m"
        areaConvert(viewModel)
    }
    var hasExecuted by remember { mutableStateOf(false) }
    if (!hasExecuted) {
        tylkoRaz()
        hasExecuted= true
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Card(
            colors = CardDefaults.cardColors(
                containerColor =MaterialTheme.colorScheme.background
            ),
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(vertical = 0.dp)
                .height(80.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ){
            Column (modifier = Modifier.fillMaxWidth().fillMaxHeight()){
                Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(.5f).weight(1f),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Rezystancja R")
                    Text(rezystancja)

                }
                Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(.5f).weight(1f),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                    Text("Konduktancja G")
                    Text(konduktacja)
                }
            }
        }
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(.9f),
            verticalAlignment = Alignment.Bottom
        ) {
            Text("Grubość pzewodu na podstawie:")
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            RadioButtonGroupExample()
        }

        WireDropdown(
            label = "Materiał",
            value = "",
            onValueChange = {},
            selectedUnit = "",
            units = listOf(),
            onUnitSelected = {},
            input = false,
            dropdown = false,
            viewModel = viewModel
        )
        if(przekrojOrSrednica=="przekroju"){
            WireDropdown(
                label = "Przekrój przewodu S",
                value = "",
                onValueChange = {},
                selectedUnit = "",
                units = listOf(),
                onUnitSelected = {},
                input = false,
                dropdown = false,
                viewModel = viewModel
            )
        }else if(przekrojOrSrednica=="średnicy"){
            WireDropdown(
                label = "Średnica d",
                value = srednica,
                onValueChange = { if (it.length <= 6) srednica = it },
                selectedUnit = "",
                units = listOf(),
                onUnitSelected = {},
                input = true,
                dropdown = false,
                viewModel = viewModel
            )
        }

        WireDropdown(
            label = "Długość l",
            value = dlugosc,
            onValueChange = { if (it.length <= 6) dlugosc = it },
            selectedUnit = test,
            units = listOf("µm", "mm", "m", "km", "Mm"),
            onUnitSelected = { test = it },
            input = true,
            dropdown = true,
            viewModel = viewModel
        )
        WireDropdown(
            label = "Temperatura",
            value = temperatura,
            onValueChange = { if (it.length <= 6) temperatura = it },
            selectedUnit = "",
            units = listOf(),
            onUnitSelected = {},
            input = true,
            dropdown = false,
            viewModel = viewModel
        )
    }
    Text("Temperatura nie może przekraczać 100℃ i być ujemna", fontSize = 10.sp)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (viewModel.isSheetOpen){
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.updateIsSheetOpen(false)
            },
            sheetState = sheetState,
            modifier = Modifier.fillMaxHeight(),
            dragHandle = {
                Text(
                    text = "Wybierz materiał",
                    fontFamily = regularText,
                    modifier = Modifier.padding(0.dp,15.dp)
                )
            },
            shape = BottomSheetDefaults.HiddenShape,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            data class UnitData(val name:String, val extand:String, val viewModel: SharedViewModel, val convertVersion:String)
            val listOfOutput = listOf(
                UnitData("cyna","in²",viewModel,"area"),
                UnitData("cynk","in²",viewModel,"area"),
                UnitData("aluminium","in²",viewModel,"area"),
                UnitData("kadm","in²",viewModel,"area"),
                UnitData("kobal","in²",viewModel,"area"),
                UnitData("konstantan","in²",viewModel,"area"),
                UnitData("manganin","in²",viewModel,"area"),
                UnitData("miedź","in²",viewModel,"area"),
                UnitData("mosiądz","in²",viewModel,"area"),
                UnitData("nikiel","in²",viewModel,"area"),
                UnitData("nikielin","in²",viewModel,"area"),
                UnitData("nowe srebro","in²",viewModel,"area"),
                UnitData("ołów","in²",viewModel,"area"),
                UnitData("platyna","in²",viewModel,"area"),
                UnitData("srebro","in²",viewModel,"area"),
                UnitData("stal czarna","in²",viewModel,"area"),
                UnitData("wolfram","in²",viewModel,"area"),
                UnitData("złoto","in²",viewModel,"area"),
                UnitData("żelazo","in²",viewModel,"area")

            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(listOfOutput) { item ->
                    ResistTemplate(item.name,item.viewModel,"material")
                }
            }
        }
    }
    if (viewModel.isSheetOpen2){
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.updateIsSheetOpen2(false)
            },
            sheetState = sheetState,
            modifier = Modifier.fillMaxHeight(),
            dragHandle = {
                Text(
                    text = "Wybierz przekrój",
                    fontFamily = regularText,
                    modifier = Modifier.padding(0.dp,15.dp)
                )
            },
            shape = BottomSheetDefaults.HiddenShape,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            data class UnitData(val name:String, val extand:String, val viewModel: SharedViewModel, val convertVersion:String)
            val listOfOutput = listOf(
                UnitData("0,25 mm²","0.00025",viewModel,"area"),
                UnitData("0,34 mm²","0.00034",viewModel,"area"),
                UnitData("0,50 mm²","0.0005",viewModel,"area"),
                UnitData("0,75 mm²","0.00075",viewModel,"area"),
                UnitData("1,0 mm²","0.001",viewModel,"area"),
                UnitData("1,5 mm²","0.0015",viewModel,"area"),
                UnitData("2,5 mm²","0.0025",viewModel,"area"),
                UnitData("4 mm²","0.004",viewModel,"area"),
                UnitData("6 mm²","0.006",viewModel,"area"),
                UnitData("10 mm²","0.01",viewModel,"area"),
                UnitData("16 mm²","0.016",viewModel,"area"),
                UnitData("25 mm²","0.025",viewModel,"area"),
                UnitData("35 mm²","0.035",viewModel,"area"),
                UnitData("50 mm²","0.05",viewModel,"area"),
                UnitData("70 mm²","0.07",viewModel,"area"),
                UnitData("95 mm²","0.095",viewModel,"area"),
                UnitData("120 mm²","0.12",viewModel,"area"),
                UnitData("150 mm²","0.15",viewModel,"area"),
                UnitData("185 mm²","0.185",viewModel,"area"),
                UnitData("240 mm²","0.24",viewModel,"area"),
                UnitData("300 mm²","0.3",viewModel,"area"),
                UnitData("400 mm²","0.4",viewModel,"area"),
                UnitData("500 mm²","0.5",viewModel,"area"),
                UnitData("630 mm²","0.63",viewModel,"area"),
                UnitData("800 mm²","0.8",viewModel,"area"),
                UnitData("1000 mm²","1.0",viewModel,"area"),
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(listOfOutput) { item ->
                    ResistTemplate(item.name,item.viewModel,"przekroj")
                }
            }
        }
    }
}
fun resiAndConduCalcu(){

    //Standardowe liczby
    var restywnosc = 0.0000168
    var wspolczynnik = 0.00386
    var przekrojMetry = 0.0015

    when(materialWybor){
        "cyna" -> {
            restywnosc = 0.000109
            wspolczynnik = 0.0045
        }
        "cynk" -> {
            restywnosc = 0.000059
            wspolczynnik = 0.004
        }
        "aluminium" -> {
            restywnosc = 0.0000282
            wspolczynnik = 0.0039
        }
        "kadm" -> {
            restywnosc = 0.000072
            wspolczynnik = 0.004
        }
        "kobalt" -> {
            restywnosc = 0.000062
            wspolczynnik = 0.0043
        }
        "konstantan" -> {
            restywnosc = 0.000049
            wspolczynnik = 0.0001
        }
        "manganin" -> {
            restywnosc = 0.000043
            wspolczynnik = 0.00002
        }
        "miedź" -> {
            restywnosc = 0.0000168
            wspolczynnik = 0.00386
        }
        "mosiądz" -> {
            restywnosc = 0.00007
            wspolczynnik = 0.001
        }
        "nikiel" -> {
            restywnosc = 0.0000693
            wspolczynnik = 0.006
        }
        "nikielin" -> {
            restywnosc = 0.00005
            wspolczynnik = 0.0002
        }
        "nowe srebro" -> {
            restywnosc = 0.00005
            wspolczynnik = 0.0001
        }
        "ołów" -> {
            restywnosc = 0.000208
            wspolczynnik = 0.004
        }
        "platyna" -> {
            restywnosc = 0.000098
            wspolczynnik = 0.00392
        }
        "srebro" -> {
            restywnosc = 0.0000159
            wspolczynnik = 0.0038
        }
        "stal czarna" -> {
            restywnosc = 0.0001
            wspolczynnik = 0.0045
        }
        "wolfram" -> {
            restywnosc = 0.000056
            wspolczynnik = 0.0045
        }
        "złoto" -> {
            restywnosc = 0.000022
            wspolczynnik = 0.0034
        }
        "żelazo" -> {
            restywnosc = 0.0001
            wspolczynnik = 0.006
        }
    }
    when(przekrojWybor){
        "0,25 mm²" -> { przekrojMetry = 0.00025 }
        "0,34 mm²" -> { przekrojMetry = 0.00034 }
        "0,50 mm²" -> { przekrojMetry = 0.0005 }
        "0,75 mm²" -> { przekrojMetry = 0.00075 }
        "1,0 mm²" -> { przekrojMetry = 0.001 }
        "1,5 mm²" -> { przekrojMetry = 0.0015 }
        "2,5 mm²" -> { przekrojMetry = 0.0025 }
        "4 mm²" -> { przekrojMetry = 0.004 }
        "6 mm²" -> { przekrojMetry = 0.006 }
        "10 mm²" -> { przekrojMetry = 0.01 }
        "16 mm²" -> { przekrojMetry = 0.016 }
        "25 mm²" -> { przekrojMetry = 0.025 }
        "35 mm²" -> { przekrojMetry = 0.035 }
        "50 mm²" -> { przekrojMetry = 0.05 }
        "70 mm²" -> { przekrojMetry = 0.07 }
        "95 mm²" -> { przekrojMetry = 0.095 }
        "120 mm²" -> { przekrojMetry = 0.12 }
        "150 mm²" -> { przekrojMetry = 0.15 }
        "185 mm²" -> { przekrojMetry = 0.185 }
        "240 mm²" -> { przekrojMetry = 0.24 }
        "300 mm²" -> { przekrojMetry = 0.3 }
        "400 mm²" -> { przekrojMetry = 0.4 }
        "500 mm²" -> { przekrojMetry = 0.5 }
        "630 mm²" -> { przekrojMetry = 0.63 }
        "800 mm²" -> { przekrojMetry = 0.8 }
        "1000 mm²" -> { przekrojMetry = 1.0 }
    }


    if(temperatura.isNotEmpty()){
        val temperaturaDouble = temperatura.toDouble()
        if(dlugosc>"0.0"&& temperatura!=""&&temperaturaDouble<=100){
            val r = when(test){
                "µm" -> {
                    dlugosc.toDouble()*0.000001
                }
                "mm" -> {
                    dlugosc.toDouble()*0.001
                }
                "m" -> {
                    dlugosc.toDouble()*1
                }
                "km" -> {
                    dlugosc.toDouble() * 1000
                }
                "Mm" -> {
                    dlugosc.toDouble() * 1000000
                }

                else -> {
                    dlugosc.toDouble()*1
                }
            }
            val p = restywnosc*(1+wspolczynnik*(temperaturaDouble-20))

            if(temperaturaDouble!=20.0){
                restywnosc = p
            }
            if(przekrojOrSrednica=="przekroju"){

                val calculatedForce = restywnosc * (r/przekrojMetry)
                val calculatedCondu = 1/(restywnosc * (r/przekrojMetry))
                konduktacja = formatConductionOutput(calculatedCondu)
                rezystancja =  formatResistantOutput(calculatedForce)

            }else if(przekrojOrSrednica=="średnicy" && srednica>"0.0"){
                val srednicaMetry = srednica.toDouble()
                val variableS = Math.PI * (srednicaMetry / 2) * (srednicaMetry / 2)

                val calculatedForce = restywnosc * (r/variableS)
                val calculatedCondu = 1/(restywnosc * (r/variableS))
                konduktacja = formatConductionOutput(calculatedCondu)
                rezystancja =  formatResistantOutput(calculatedForce)
            }else{

                rezystancja = "0.0"
                konduktacja = "0.0"
            }
        }else{
            rezystancja = "0.0"
            konduktacja = "0.0"
        }
    }else{
        rezystancja = "0.0"
        konduktacja = "0.0"
    }

}