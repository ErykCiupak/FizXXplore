package eu.example.physicsapp.Menu.Tablice.PhycisBasic.SiUnits

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhycisUnits(navController: NavController) {
    // Zarządzanie stanem UI
    val isFocused = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    var expandedItemId by remember { mutableStateOf<Int?>(null) } // ID aktualnie rozwiniętego elementu
    val searchQuery = remember { mutableStateOf("") } // Stan tekstu wyszukiwania

    // Przefiltrowane elementy w zależności od zapytania wyszukiwania
    val filteredItems by remember(searchQuery.value) {
        derivedStateOf {
            items.mapNotNull { item ->
                val filteredDetails = item.details.filter { insideInfo ->
                    insideInfo.title.contains(searchQuery.value, ignoreCase = true) ||
                            insideInfo.unit.contains(searchQuery.value, ignoreCase = true) ||
                            insideInfo.unitExtends.contains(searchQuery.value, ignoreCase = true)
                }
                if (filteredDetails.isNotEmpty()) {
                    item.copy(details = filteredDetails) // Zwróć przefiltrowany element
                } else {
                    null
                }
            }
        }
    }

    // Oczyszczanie stanu rozwinięcia, jeśli pole wyszukiwania jest puste
    LaunchedEffect(searchQuery.value) {
        if (searchQuery.value.isEmpty()) {
            expandedItemId = null // Zwija wszystkie elementy, jeśli pole jest puste
        }
    }

    // Komponent Scaffold z paskiem narzędzi
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Jednostki i wielkości", fontFamily = boldText, fontSize = 20.sp) },
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
                .fillMaxSize()
                .clickable {
                    isFocused.value = false
                    focusManager.clearFocus() // Oczyść fokus
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Komponent pola wyszukiwania
            SearchBar(searchQuery = searchQuery, isFocused = isFocused)

            Spacer(modifier = Modifier.height(18.dp))

            // Lista przefiltrowanych elementów
            LazyColumn(
                modifier = Modifier.fillMaxHeight().weight(1f)
            ) {
                items(filteredItems, key = { it.id }) { item ->
                    // Ustawienie rozwinięcia w zależności od wyszukiwania
                    val isExpanded = searchQuery.value.isNotEmpty() || expandedItemId == item.id // Sprawdzenie, czy element jest rozwinięty

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        // Nagłówek dla elementu
                        HeaderRow(item, isExpanded) {
                            // Tylko jeśli wyszukiwanie jest puste, zmień aktualnie rozwinięty element
                            if (searchQuery.value.isEmpty()) {
                                expandedItemId = if (isExpanded) {
                                    null // Jeśli ten element jest już rozwinięty, zamknij go
                                } else {
                                    item.id // Rozwiń nowy element
                                }
                            }
                        }

                        // Animowane rozwinięcie szczegółów
                        AnimatedVisibility(visible = isExpanded) {
                            Column {
                                item.details.forEach { insideInfo ->
                                    InsideContent(
                                        unit = insideInfo.unit,
                                        title = insideInfo.title,
                                        unitExtends = insideInfo.unitExtends
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}


// Komponent pola wyszukiwania
@Composable
fun SearchBar(
    searchQuery: MutableState<String>,
    isFocused: MutableState<Boolean>
) {
    OutlinedTextField(
        value = searchQuery.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
        onValueChange = { searchQuery.value = it }, // Aktualizuj stan zapytania
        label = { Text("Szukaj") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        trailingIcon = {
            if (searchQuery.value.isNotEmpty()) {
                Icon(Icons.Default.Close, contentDescription = "Clear", modifier = Modifier.clickable { searchQuery.value = "" }) // Przyciski czyszczenia
            }
        },
        modifier = Modifier
            .height(60.dp)
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused // Aktualizuj stan fokusowania
            }
            .fillMaxWidth(0.9f)
            .focusable(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.onBackground,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary
        )
    )
}

// Komponent nagłówka dla każdego elementu
@Composable
fun HeaderRow(item: Item, isExpanded: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.96f)
            .height(60.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(item.headline, fontSize = 16.sp, modifier = Modifier.fillMaxWidth(0.9f))

        // Ikona zmiany stanu rozwinięcia
        val icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
        Icon(icon, contentDescription = "Toggle", modifier = Modifier.alpha(0.5f))
    }
}

// Komponent do wyświetlania szczegółów jednostki
@Composable
fun InsideContent(unit: String, title: String, unitExtends: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // Wyświetlanie jednostki
        Box(modifier = Modifier.width(100.dp).height(60.dp), contentAlignment = Alignment.Center) {
            Text(unit, fontFamily = boldText, fontSize = 15.sp)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp), // Ustaw wysokość, aby każdy element miał odpowiednią przestrzeń
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, fontSize = 13.sp, lineHeight = 22.sp) // Tytuł jednostki
            Text(text = unitExtends, fontSize = 11.sp, lineHeight = 14.sp) // Rozszerzenie jednostki
        }
    }
}

// Komponent do rysowania poziomego separatora
@Composable
fun HorizontalDivider() {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
    )
}