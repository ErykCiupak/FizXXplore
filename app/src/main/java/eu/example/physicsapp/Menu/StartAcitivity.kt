package eu.example.physicsapp.Menu

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.example.physicsapp.R
import eu.example.physicsapp.PublicTemplates.CustomButton

@Composable
fun FirstScreen(navController: NavController){
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    var isVisible by remember { mutableStateOf(true)}
    Scaffold {paddingValues->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(30.dp))
            LaunchedEffect(isPortrait) {
                isVisible = isPortrait
            }
            if (isVisible){
                Image(
                    painter = painterResource(id = R.drawable.logomain),
                    contentDescription = "App logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(150.dp)
                        .clip(CircleShape)



                )
                Spacer(modifier = Modifier.height(35.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth(0.8f), thickness = 4.dp, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(35.dp))
            }
            CustomButton("Kalkulatory","calcOption","",navController)

            CustomButton("Quiz","quizSelect","",navController)

            CustomButton("Tablice","tableSelect","",navController)

            CustomButton("Symbole","symbols","",navController)

//            CustomButton("Akademia","academy","wip",navController)
        }
    }

}


