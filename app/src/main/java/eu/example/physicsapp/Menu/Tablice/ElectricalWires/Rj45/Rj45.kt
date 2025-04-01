package eu.example.physicsapp.Menu.Tablice.ElectricalWires.Rj45

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.R
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RjScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Podłączenie RJ45", fontFamily = boldText, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("electroWiresSelect") }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back arrow")
                    }
                }
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text("Standart T568B", fontSize = 30.sp,color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
            Image(painter = painterResource(id = R.drawable.t568b), contentDescription = "t568b",modifier = Modifier.size(300.dp))

            Text("Standart T568A",fontSize = 30.sp,color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
            Image(painter = painterResource(id = R.drawable.t568a), contentDescription = "t568a",modifier = Modifier.size(300.dp))
        }
    }
}

