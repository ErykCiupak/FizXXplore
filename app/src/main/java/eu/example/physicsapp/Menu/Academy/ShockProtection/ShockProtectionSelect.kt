package eu.example.physicsapp.Menu.Academy.ShockProtection


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
import eu.example.physicsapp.PublicTemplates.CustomButtonAcademy
import eu.example.physicsapp.ui.theme.boldText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShockProtectionSelect(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Ochrona przeciwporażeniowa",
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
                Chapter("Lekcja 1", "Części czynne i przewodzące", "O podstawowych pojęciach ochrony przeciwporażeniowej.","startActivity"),
                Chapter("Lekcja 2", "Ochrona podstawowa", "O ochronie podstawowej i jej środkach.","startActivity"),

                )

            items(chapters) { chapter ->
                CustomButtonAcademy(chapter.title, chapter.description, chapter.lessonCount, chapter.location, navController)
            }
        }

    }

}