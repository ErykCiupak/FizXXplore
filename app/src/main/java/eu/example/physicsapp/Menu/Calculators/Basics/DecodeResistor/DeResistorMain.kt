package eu.example.physicsapp.Menu.Calculators.Basics.DecodeResistor

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.ui.theme.boldText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import eu.example.physicsapp.Menu.Calculators.Basics.WireConduction.resiAndConduCalcu
import eu.example.physicsapp.Menu.Calculators.Basics.WireConduction.formatResistantOutput
import eu.example.physicsapp.R
import eu.example.physicsapp.ui.theme.regularText
import kotlinx.coroutines.launch

data class PasekData(
    var name: String,
    var color: Color,
    var textColor: Color
)

var paskiData = mutableStateListOf(
    PasekData("pomarańczowy", Color(0xFFFFA500),Color.White),
    PasekData("czerwony", Color.Red,  Color.White),
    PasekData("brązowy", Color(0xFFA52A2A), Color.White),
    PasekData("żółty", Color.Yellow,  Color.Black),
    PasekData("brązowy", Color(0xFFA52A2A),  Color.White),
    PasekData("brązowy", Color(0xFFA52A2A), Color.White)
)

var showBottomSheet1 by  mutableStateOf(false)
var showBottomSheet2 by  mutableStateOf(false)
var showBottomSheet3 by  mutableStateOf(false)
var showBottomSheet4 by  mutableStateOf(false)
var showBottomSheet5 by  mutableStateOf(false)
var showBottomSheet6 by  mutableStateOf(false)
var ilePaskow by mutableStateOf("6")

var rezystancjaRes by mutableStateOf("0.0")
var wynik by mutableDoubleStateOf(0.0)
var tolerancja by mutableStateOf("1.0 %")
var ppm by mutableStateOf("100.00 ppm/℃")
var selectedOption by  mutableStateOf("6")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeReristorCalculator(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Dekodowanie rezystora",
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
            DeResistorForm()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeResistorForm() {
    fun tylkoRaz(){
        paskiData =mutableStateListOf(
            PasekData("pomarańczowy", Color(0xFFFFA500),Color.White),
            PasekData("czerwony", Color.Red,  Color.White),
            PasekData("brązowy", Color(0xFFA52A2A), Color.White),
            PasekData("żółty", Color.Yellow,  Color.Black),
            PasekData("brązowy", Color(0xFFA52A2A),  Color.White),
            PasekData("brązowy", Color(0xFFA52A2A), Color.White)
        )
        rezystancjaRes = "0.0"
        tolerancja = "1.00 %"
        ppm = "100.00 ppm/℃"
        showBottomSheet1 = false
        showBottomSheet2 = false
        showBottomSheet3 = false
        showBottomSheet4 = false
        showBottomSheet5 = false
        ilePaskow = "6"
        selectedOption ="6"
        wynik()
    }
    var hasExecuted by remember { mutableStateOf(false) }
    if (!hasExecuted) {
        tylkoRaz()
        hasExecuted= true
    }
    val sheetState = rememberModalBottomSheetState()

    val tabTitles = listOf("Kod", "Opcje")
    val pagerState = rememberPagerState(pageCount = {2}
    )
    val coroutineScope = rememberCoroutineScope()

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
    HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
        when(page){
            0->{
                Column(modifier = Modifier.fillMaxWidth(.95f),horizontalAlignment = Alignment.CenterHorizontally){
                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor =MaterialTheme.colorScheme.background
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .padding(vertical = 0.dp)
                            .height(100.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ){
                        Column (modifier = Modifier.fillMaxSize()){
                            InfoRow("Rezystancja R", rezystancjaRes)
                            InfoRow("Tolerancja", tolerancja)
                            if(ilePaskow=="6"){
                                InfoRow("Współ. temperaturowa a", ppm)
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.height(150.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        paskiData.forEachIndexed { index, paski ->
                            val skipBox = when (ilePaskow) {
                                "4" -> index == 2 || index == 5
                                "5" -> index == 5
                                else -> false
                            }
                            if (!skipBox) {
                                Box(
                                    modifier = Modifier
                                        .background(paski.color)
                                        .size(50.dp)
                                        .border(1.dp, MaterialTheme.colorScheme.onBackground)
                                )
                            }else{
                                Box(modifier = Modifier.background(Color.Transparent).size(50.dp).border(1.dp, MaterialTheme.colorScheme.onBackground)
                                ){
                                    Image(painter = painterResource(id = R.drawable.cross), contentDescription = "krzyz",colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground))
                                }
                            }
                        }
                    }
                    //Paski
                    data class PaskiTemplate(val bottomSheet:Int,val pasekButtonbColor:Color,val pasekText:String,val pasekTextColor:Color)
                    val paski = when(ilePaskow){
                        "6"->listOf(
                            PaskiTemplate(1,paskiData[0].color, paskiData[0].name, paskiData[0].textColor),
                            PaskiTemplate(2,paskiData[1].color, paskiData[1].name, paskiData[1].textColor),
                            PaskiTemplate(3,paskiData[2].color, paskiData[2].name, paskiData[2].textColor),
                            PaskiTemplate(4,paskiData[3].color, paskiData[3].name, paskiData[3].textColor),
                            PaskiTemplate(5,paskiData[4].color, paskiData[4].name, paskiData[4].textColor),
                            PaskiTemplate(6,paskiData[5].color, paskiData[5].name, paskiData[5].textColor)
                        )
                        "5"->listOf(
                            PaskiTemplate(1,paskiData[0].color, paskiData[0].name, paskiData[0].textColor),
                            PaskiTemplate(2,paskiData[1].color, paskiData[1].name, paskiData[1].textColor),
                            PaskiTemplate(3,paskiData[2].color, paskiData[2].name, paskiData[2].textColor),
                            PaskiTemplate(4,paskiData[3].color, paskiData[3].name, paskiData[3].textColor),
                            PaskiTemplate(5,paskiData[4].color, paskiData[4].name, paskiData[4].textColor)
                        )
                        "4"->listOf(
                            PaskiTemplate(1,paskiData[0].color, paskiData[0].name, paskiData[0].textColor),
                            PaskiTemplate(2,paskiData[1].color, paskiData[1].name, paskiData[1].textColor),
                            PaskiTemplate(4,paskiData[3].color, paskiData[3].name, paskiData[3].textColor),
                            PaskiTemplate(5,paskiData[4].color, paskiData[4].name, paskiData[4].textColor)
                        )
                        else -> listOf(
                            PaskiTemplate(1,paskiData[0].color, paskiData[0].name, paskiData[0].textColor),
                        )
                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        items(paski) { item ->
                            RowPaska(item.bottomSheet,item.pasekButtonbColor,item.pasekText,item.pasekTextColor)
                        }
                    }
                }
                val showBottomSheets = mutableListOf(
                    showBottomSheet1, showBottomSheet2, showBottomSheet3, showBottomSheet4, showBottomSheet5, showBottomSheet6
                )
                showBottomSheets.forEachIndexed { index, showBottomSheet ->
                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = {
                                when(index){
                                    0 -> showBottomSheet1 = false
                                    1 -> showBottomSheet2 = false
                                    2 -> showBottomSheet3 = false
                                    3 -> showBottomSheet4 = false
                                    4 -> showBottomSheet5 = false
                                    5 -> showBottomSheet6 = false
                                }
                            },
                            sheetState = sheetState,
                            dragHandle = {
                                Spacer(Modifier.height(15.dp))
                                Text(
                                    text = "Wybierz kolor",
                                    fontFamily = regularText,
                                    modifier = Modifier.padding(0.dp,15.dp)
                                )
                                Spacer(Modifier.height(15.dp))
                            },
                            shape = BottomSheetDefaults.HiddenShape,
                            modifier = Modifier.fillMaxHeight(.5f),
                        ) {
                            val listOfOutput = colorLists.getOrNull(index) ?: emptyList()
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            ) {
                                items(listOfOutput) { item ->
                                    ReOutputTemplate(item.colorName, item.colorCode, item.textColor, item.whichPasek)
                                }
                            }
                        }
                    }
                }
            }
            1->{
                Column(modifier = Modifier.fillMaxWidth(.95f)){
                    Text("Liczba pasków")
                    RadioButtonResistor()
                }
            }
        }
    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun ReOutputTemplate(colorName:String,colorCode: Color,textColor: Color,whichPasek:Int){
        Box(
            modifier = Modifier.fillMaxWidth(0.90f).height(60.dp).
            padding(top = 1.dp)
            ,contentAlignment = Alignment.Center
        ){
            Card(
                colors = CardDefaults.cardColors(
                    colorCode
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .height(80.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Button(onClick = {
                    when(whichPasek){
                        1 -> {paskiData[0].name = colorName
                            paskiData[0].color = colorCode
                            showBottomSheet1 = false
                            paskiData[0].textColor = textColor
                        }
                        2 -> {paskiData[1].name = colorName
                            paskiData[1].color = colorCode
                            showBottomSheet2 = false
                            paskiData[1].textColor = textColor
                        }
                        3 -> {paskiData[2].name = colorName
                            paskiData[2].color = colorCode
                            showBottomSheet3 = false
                            paskiData[2].textColor = textColor
                        }
                        4 -> {paskiData[3].name = colorName
                            paskiData[3].color = colorCode
                            showBottomSheet4 = false
                            paskiData[3].textColor = textColor
                        }
                        5 -> {paskiData[4].name = colorName
                            paskiData[4].color = colorCode
                            showBottomSheet5 = false
                            paskiData[4].textColor = textColor
                        }
                        6 -> {paskiData[5].name = colorName
                            paskiData[5].color = colorCode
                            showBottomSheet6 = false
                            paskiData[5].textColor = textColor
                        }
                    }
                    wynik()
                },
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                    shape = RectangleShape
                ) {
                    Text(text = colorName,color = textColor)
                }
            }
        }
}

@Composable
fun RowPaska(whichBottomSheet:Int, pasekButtonbColor:Color, pasekText:String, pasekTextColor:Color){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
        Text("Pasek $whichBottomSheet")
        Button(onClick = {
            when(whichBottomSheet){
                1 -> {showBottomSheet1 = true}
                2 -> {showBottomSheet2 = true}
                3 -> {showBottomSheet3 = true}
                4 -> {showBottomSheet4 = true}
                5 -> {showBottomSheet5 = true}
                6 -> {showBottomSheet6 = true}
            }
        },
            colors = ButtonDefaults.outlinedButtonColors(pasekButtonbColor ),
            modifier = Modifier.width(150.dp),
            shape = RectangleShape) {
            Text(text = pasekText, color = pasekTextColor)
        }
    }
}
@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label)
        Text(value)
    }
}

fun wynik(){
    val pasek1 = when(paskiData[0].name){
            "czarny" ->0.0
            "brązowy" ->100.0
            "czerwony" ->200.0
            "pomarańczowy" ->300.0
            "żółty" ->400.0
            "zielony" ->500.0
            "niebieski" ->600.0
            "fioletowy" ->700.0
            "szary" ->800.0
            "biały" ->900.0
        else -> {0.0}
    }
    val pasek2 = when(paskiData[1].name){
        "czarny" ->0.0
        "brązowy" ->10.0
        "czerwony" ->20.0
        "pomarańczowy" ->30.0
        "żółty" ->40.0
        "zielony" ->50.0
        "niebieski" ->60.0
        "fioletowy" ->70.0
        "szary" ->80.0
        "biały" ->90.0
        else -> {0.0}
    }
    val pasek3 = when(paskiData[2].name){
        "czarny" ->0.0
        "brązowy" ->1.0
        "czerwony" ->2.0
        "pomarańczowy" ->3.0
        "żółty" ->4.0
        "zielony" ->5.0
        "niebieski" ->6.0
        "fioletowy" ->7.0
        "szary" ->8.0
        "biały" ->9.0
        else -> {0.0}
    }
    val mnoznik = when(paskiData[3].name){
        "czarny" ->1.0
        "brązowy" ->10.0
        "czerwony" ->100.0
        "pomarańczowy" ->1000.0
        "żółty" ->10000.0
        "zielony" ->100000.0
        "niebieski" ->1000000.0
        "fioletowy" ->10000000.0
        "szary" ->100000000.0
        "biały" ->1000000000.0
        "złoty" ->0.1
        "srebrny" ->0.01
        else -> {0.0}
    }
    tolerancja = when(paskiData[4].name){
        "brązowy" -> "1.00 %"
        "czerwony" ->"2.00 %"
        "zielony" ->"0.50 %"
        "niebieski" ->"0.25 %"
        "fioletowy" ->"0.10 %"
        "szary" ->"0.05 %"
        "złoty" ->"5.00 %"
        "srebrny" ->"10.00 %"
        else -> {"0.0 %"}
    }
    ppm = when(paskiData[5].name){
        "brązowy" ->"100.00 ppm/℃"
        "czerwony" ->"50.00 ppm/℃"
        "pomarańczowy" ->"15.00 ppm/℃"
        "żółty" ->"25.00 ppm/℃"
        "niebieski" ->"10.00 ppm/℃"
        "fioletowy" ->"5.00 ppm/℃"
        else -> {"0.00 ppm/℃"}
    }
    wynik = if(ilePaskow=="4"){
        (pasek1 + pasek2)*mnoznik
    }else{
        (pasek1 + pasek2 + pasek3)*mnoznik
    }
    rezystancjaRes = formatResistantOutput(wynik)
}

@Composable
fun RadioButtonResistor() {
    val options = listOf("4", "5","6")
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        options.forEach { option ->
            RowWithRadioButton(option, selectedOption) {
                selectedOption = option
                ilePaskow = option
                resiAndConduCalcu()
                wynik()
            }
        }
    }
}
@Composable
fun RowWithRadioButton(text: String, selectedOption: String, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(.5f)
            .clickable(onClick =  onSelect )
            .padding(8.dp)
            .height(60.dp)
    ) {
        RadioButton(
            selected = text == selectedOption,
            onClick = onSelect
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}