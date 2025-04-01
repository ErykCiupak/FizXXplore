package eu.example.physicsapp.Menu.Academy


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.PublicTemplates.CustomButtonAcademy
import eu.example.physicsapp.ui.theme.boldText
data class Chapter(
    val title: String,
    val description: String,
    val lessonCount: String,
    val location: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Academy(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Akademia",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = boldText, fontSize = 20.sp
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
    ) {paddingValues->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val chapters = listOf(
                Chapter("Rozdział 1", "Podstawy elektrotechniki", "Liczba lekcji: 8","electroTechBasics"),
                Chapter("Rozdział 2", "Elementy elektryczne", "Liczba lekcji: 2","electricElementsBasics"),
                Chapter("Rozdział 3", "Baterie", "Liczba lekcji: 2","academyBatterySelect"),
                Chapter("Rozdział 4", "Przewody elektryczne", "Liczba lekcji: 2","academyEleWiresSelect"),
                Chapter("Rozdział 5", "Urządzenia elektryczne", "Liczba lekcji: 2","academyEleDevicesSelect"),
                Chapter("Rozdział 6", "Sieci i instalacje niskiego napięcia", "Liczba lekcji: 2","lowVoltageSelect"),
                Chapter("Rozdział 7", "Wyłączniki różnicowoprądowe", "Liczba lekcji: 2","circutBreakerSelect"),
                Chapter("Rozdział 8", "Ochrona przeciwporażeniowa", "Liczba lekcji: 2","shockProtectSelect")
            )

            items(chapters) { chapter ->
                CustomButtonAcademy(chapter.title, chapter.description, chapter.lessonCount, chapter.location, navController)
            }
        }

    }

}