package eu.example.physicsapp.Menu.Tablice.PhycisBasic.ResistansOfMetals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.Menu.Tablice.PhycisBasic.SiUnits.HorizontalDivider
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetalsResistans(navController: NavController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Rezystywność i konduktywność", fontFamily = boldText, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("phycisBasicSelect") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back arrow")
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
            HeaderRow()
            TabContent1()
        }
    }
}
@Composable
fun HeaderRow() {
    Box(modifier = Modifier.fillMaxWidth().height(70.dp), contentAlignment = Alignment.Center){
        Text("Rezystywność i konduktywność wybranych materiałów w temperaturze 20°C", fontSize = 12.sp, textAlign = TextAlign.Center)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center,

    ) {
        Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(0.9f)) {
            Box(modifier = Modifier.width(100.dp), contentAlignment = Alignment.Center){
                Text("Materiał", fontSize = 13.sp)
            }
            Box(modifier = Modifier.width(100.dp), contentAlignment = Alignment.Center){
                Text("Rezystywność \nρ [10⁻⁹ Ω∙m]", fontSize = 13.sp)
            }
            Box(modifier = Modifier.width(110.dp), contentAlignment = Alignment.Center){
                Text("Konduktywność \nσ [10⁶ S/m]", fontSize = 13.sp)
            }

        }
    }
}
data class Przedrostki(
    val symbol: String,
    val nazwa: String,
    val mnoznik: String
)
@Composable
fun TabContent1() {
//    Text("This is content of Tab 1", modifier = Modifier.fillMaxSize())
    Column(modifier = Modifier.fillMaxSize()) {

        val obiekty = listOf(
            Przedrostki("tin","109.0","9.2"),
            Przedrostki("zinc","59.0","16.9"),
            Przedrostki("aluminum","28.2","35.5"),
            Przedrostki("cadmium","72.7","13.8"),
            Przedrostki("cobalt","62.4","16.0"),
            Przedrostki("constantan","490.0","2.0"),
            Przedrostki("manganin","482.0","2.1"),
            Przedrostki("copper","16.8","59.5"),
            Przedrostki("brass","63.0","15.9"),
            Przedrostki("nickelSilver","310.0","3.2"),
            Przedrostki("nickel","69.9","14.3"),
            Przedrostki("nickeline","410.0","2.4"),
            Przedrostki("lead","220.0","4.5"),
            Przedrostki("platinum","106.0","9.4"),
            Przedrostki("silver","15.9","62.9"),
            Przedrostki("steel","143.0","7.0"),
            Przedrostki("tungsten","56.0","17.9"),
            Przedrostki("gold","24.4","41.0"),
            Przedrostki("iron","100.0","10.0")
            )

        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ){
            items(obiekty){obiekty->
                ObiektResistans(obiekty.symbol,obiekty.nazwa,obiekty.mnoznik)
            }
        }
    }

}
@Composable
fun ObiektResistans(symbol: String, nazwa: String, mnoznik: String){
    Column(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(.9f)){
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.width(100.dp)){
                Text(symbol,fontSize = 12.sp)
            }
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.width(100.dp)){
                Text(nazwa,fontSize = 12.sp)
            }

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.width(100.dp)){
                Text(mnoznik,fontSize = 12.sp)
            }

        }

    }
}


