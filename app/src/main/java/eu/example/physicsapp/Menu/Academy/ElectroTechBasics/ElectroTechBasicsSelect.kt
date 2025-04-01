package eu.example.physicsapp.Menu.Academy.ElectroTechBasics

import androidx.compose.foundation.layout.Column
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
import eu.example.physicsapp.Menu.Academy.Chapter
import eu.example.physicsapp.PublicTemplates.CustomButton
import eu.example.physicsapp.PublicTemplates.CustomButtonAcademy
import eu.example.physicsapp.ui.theme.boldText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectroTechBasicsSelect(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Podstawy elektrotechniki",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = boldText, fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("academy")
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
                Chapter("Lekcja 1", "Potencjał i Napięcie", "O tym czym jest napięcie i jaką odgrywa rolę.","startActivity"),
                Chapter("Lekcja 2", "Prąd elektryczny", "O prądzie i idei obwodu elektrycznego","startActivity"),
                Chapter("Lekcja 3", "Rezystancja", "O oporze elektrycznym, rezystywność i konduktancji.","startActivity"),
                Chapter("Lekcja 4", "Prawo Ohma", "O prawie łączącym prąd, napięcie i rezystancję.","startActivity"),
                Chapter("Lekcja 5", "Spadek napięcie", "O sposobie wyznaczania i obliczania spadku napięcia.","startActivity"),
                Chapter("Lekcja 6", "Prawo Kirchhoffa", "O obwodach szeregowych i równoległych.","startActivity"),
                Chapter("Lekcja 7", "Łączenie rezystorów", "O obliczaniu rezystancji zastępczej w układzie równoległym i szeregowym.","startActivity"),
                Chapter("Lekcja 8", "Transfiguracja gwiazdka-trójką", "O obliczaniu rezystancji zastępczej mieszanego układu rezystorów.","startActivity"),
            )

            items(chapters) { chapter ->
                CustomButtonAcademy(chapter.title, chapter.description, chapter.lessonCount, chapter.location, navController)
            }
        }

    }

}