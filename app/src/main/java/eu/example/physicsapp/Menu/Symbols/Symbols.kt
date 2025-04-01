package eu.example.physicsapp.Menu.Symbols

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.R
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Symbols(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Symbole",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = boldText,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("startActivity")
                    }) {
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
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val symbols = listOf(
                SymbolItem("Przewód", R.drawable.prze),
                SymbolItem("Przewód jednożyłowy", R.drawable.prze_poj),
                SymbolItem("Przewód dwużyłowy", R.drawable.prze_pod),
                SymbolItem("Przewód trójżyłowy", R.drawable.prze_pot),
                SymbolItem("Przewód neutralny", R.drawable.prze_neut),
                SymbolItem("Przewód ochronny", R.drawable.prze_ochr),
                SymbolItem("Przewód ochronno-neutralny", R.drawable.prze_ochr_neut),
                SymbolItem("Przewód ruchomy", R.drawable.prze_ruch),
                SymbolItem("anoda ochronna", R.drawable.anoda_ochronna),
                SymbolItem("Masa, korpus, podstawa montażowa", R.drawable.masa),
                SymbolItem("Uziemienie bezszumowe", R.drawable.uziemienie_bezszumowe),
                SymbolItem("Uzemienie ochronne", R.drawable.uziemienie_ochronne),
                SymbolItem("Uziemienie (symbol ogólny)", R.drawable.uziemienie),
                SymbolItem("Rozdzielnica", R.drawable.rodzielnica),
                SymbolItem("Listwa zaciskowa", R.drawable.listwa_zaciskowa),
                SymbolItem("Odgałęzienie od przewodu", R.drawable.odgalezenie_od_przewodu),
                SymbolItem("Linia podziemna", R.drawable.linia_podziemna),
                SymbolItem("Linia podwodna", R.drawable.linia_podwodna),
                SymbolItem("Zasilanie linii telekomunikacyjnej prądem stałym", R.drawable.zasil_tele_stalym),
                SymbolItem("Zasilanie linii telekomunikacyjnej prądem przemiennym", R.drawable.zasil_tele_zmienny),
                SymbolItem("Wiecej wkrótce", R.drawable.wip),
            )
            SymbolScreen(symbols)
        }
    }
}

data class SymbolItem(
    val name: String,
    val iconRes: Int // Resource ID ikony
)

@Composable
fun SymbolScreen(symbols: List<SymbolItem>) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        // Wyszukiwarka
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    Icon(Icons.Default.Close, contentDescription = "Clear", modifier = Modifier.clickable { searchQuery = "" }) // Przyciski czyszczenia
                }
            },
            placeholder = { Text("Szukaj", color = Color.Gray) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Lista symboli
        LazyColumn {
            items(symbols.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }) { symbol ->
                SymbolItemRow(symbol)
            }

        }

    }
}

@Composable
fun SymbolItemRow(symbol: SymbolItem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor =MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(80.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = symbol.name,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = symbol.iconRes),
                contentDescription = null,
                modifier = Modifier.size(150.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            )
        }
    }
}
