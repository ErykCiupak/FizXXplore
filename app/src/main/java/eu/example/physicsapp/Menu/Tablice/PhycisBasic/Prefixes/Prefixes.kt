package eu.example.physicsapp.Menu.Tablice.PhycisBasic.Prefixes

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.Menu.Tablice.PhycisBasic.SiUnits.HorizontalDivider
import eu.example.physicsapp.ui.theme.boldText
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prefixes(navController: NavController) {
    val tabs = listOf("dziesiętne", "binarne")
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Przedrostki", fontFamily = boldText, fontSize = 20.sp) },
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
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                indicator = { tabPositions ->
                    SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(tab) }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.Top
            ) { page ->
                when (page) {
                    0 -> TabContent(prefixList = dziesietnePrefixes())
                    1 -> TabContent(prefixList = binarnePrefixes())
                }
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
fun TabContent(prefixList: List<Przedrostki>) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderRow()

        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            items(prefixList) { obiekty ->
                ObiektRow(symbol = obiekty.symbol, nazwa = obiekty.nazwa, mnoznik = obiekty.mnoznik)
            }
        }
    }
}

@Composable
fun HeaderRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(0.9f)) {
            Text("Symbol", fontSize = 14.sp)
            Text("Nazwa", fontSize = 14.sp)
            Text("Mnożnik", fontSize = 14.sp)
        }
    }
}

@Composable
fun ObiektRow(symbol: String, nazwa: String, mnoznik: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text(symbol, fontSize = 12.sp)
            Text(nazwa, fontSize = 12.sp)
            Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.width(30.dp)) {
                Text(mnoznik, fontSize = 12.sp)
            }
        }
    }
}

// Lists of prefixes
fun dziesietnePrefixes(): List<Przedrostki> {
    return listOf(
        Przedrostki("y","jokto","10⁻²⁴"),
        Przedrostki("z","zepto","10⁻²¹"),
        Przedrostki("a","atto","10⁻¹⁸"),
        Przedrostki("f","femto","10⁻¹⁵"),
        Przedrostki("p","piko","10⁻¹²"),
        Przedrostki("n","nano","10⁻⁹"),
        Przedrostki("μ","mikro","10⁻⁶"),
        Przedrostki("m","mili","10⁻³"),
        Przedrostki("c","centy","10⁻²"),
        Przedrostki("de","decy","10⁻¹"),
        Przedrostki("da","deka","10¹"),
        Przedrostki("h","hekto","10²"),
        Przedrostki("k","kilo","10³"),
        Przedrostki("M","mega","10⁶"),
        Przedrostki("G","giga","10⁹"),
        Przedrostki("T","tera","10¹²"),
        Przedrostki("P","peta","10¹⁵"),
        Przedrostki("E","eksa","10¹⁸"),
        Przedrostki("Z","zetta","10²¹"),
        Przedrostki("J","jotta","10²⁴"),
    )
}

fun binarnePrefixes(): List<Przedrostki> {
    return listOf(
        Przedrostki("Ki","kibi","2¹⁰"),
        Przedrostki("Mi","mebi","2²⁰"),
        Przedrostki("Gi","gibi","2³⁰"),
        Przedrostki("Ti","tebi","2⁴⁰"),
        Przedrostki("Pi","pebi","2⁵⁰"),
        Przedrostki("Ei","eksbi","2⁶⁰"),
        Przedrostki("Zi","zebi","2⁷⁰"),
        Przedrostki("Yi","jobi","2⁸⁰")
    )
}
