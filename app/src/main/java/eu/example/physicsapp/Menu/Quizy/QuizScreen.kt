package eu.example.physicsapp.Menu.Quizy

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import eu.example.physicsapp.PublicTemplates.CustomButton
import eu.example.physicsapp.ui.theme.boldText

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(viewModel: QuizViewModel, navController: NavController,value:Int) {

    viewModel.numberOfQuestions = value

    val showDialog = remember { mutableStateOf(false) }
    BackHandler(enabled = showDialog.value) {
        showDialog.value = false
    }
    if (viewModel.isQuizFinished()) {
        ResultScreen(score = viewModel.score.value, totalQuestions = viewModel.numberOfQuestions-1,navController)
    } else {
        val question = viewModel.questions[viewModel.currentQuestionIndex.value]
        val progress = (viewModel.currentQuestionIndex.value + 1) / viewModel.numberOfQuestions.toFloat() // Calculate progress
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(38.dp) // Set height for the progress bar
                                .padding(16.dp), // Add some padding
                            color = MaterialTheme.colorScheme.onBackground, // Customize the color
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            showDialog.value =true
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Back arrow"
                            )
                        }
                        if (showDialog.value) {
                            ExitConfirmationDialog(
                                onConfirmExit = {
                                    showDialog.value = false
                                    navController.navigate("startActivity")
                                },
                                onDismiss = {
                                    showDialog.value = false
                                }
                            )
                        }
                    }
                )

            }
        ){
            innerPadding->
            Column(
                modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background).padding(innerPadding),
                verticalArrangement = Arrangement.Top, // Align items at the top
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center){
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "Wskaż symbol przedstawiający:",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = question.symbolName,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                }


                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    maxItemsInEachRow = 2,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val showCorrectAnswer = viewModel.selectedAnswerIndex.value != -1 // Czy użytkownik wybrał odpowiedź

                    question.answers.forEachIndexed { index, answer ->
                        val isSelected = index == viewModel.selectedAnswerIndex.value
                        val isCorrect = answer.isCorrect
                        val correctAnswerIndex = question.answers.indexOfFirst { it.isCorrect }

                        Box(
                            modifier = Modifier
                                .padding(20.dp)
                                .size(130.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(2.dp, MaterialTheme.colorScheme.onBackground, shape = RoundedCornerShape(8.dp))
                                .background(
                                    when {
                                        showCorrectAnswer && isSelected && !isCorrect -> Color.Red // Zła odpowiedź
                                        showCorrectAnswer && index == correctAnswerIndex -> Color.Green // Poprawna odpowiedź
                                        else -> Color.Transparent
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = answer.imageResId),
                                contentDescription = "Odpowiedź ${index + 1}",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                                modifier = Modifier
                                    .fillMaxSize(0.8f)
                                    .clickable(enabled = !showCorrectAnswer) { viewModel.selectAnswer(index) } // Blokowanie klikania po wyborze
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(100.dp))

                Button(
                    onClick = { viewModel.nextQuestion() },
                    enabled = viewModel.canProceedToNextQuestion.value,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        contentColor = MaterialTheme.colorScheme.background
                    ), modifier = Modifier.height(80.dp).width(250.dp)
                ) {
                    Text(text = "Następne pytanie",color = MaterialTheme.colorScheme.background, fontFamily = boldText)
                }
            }
        }

    }
}
@Composable
fun AnimatedCircularProgressBar(percentage: Float) {
    val animatedPercentage by animateFloatAsState(
        targetValue = percentage,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000), label = ""
    )

    Box(contentAlignment = androidx.compose.ui.Alignment.Center) {
        Canvas(
            modifier = Modifier.size(150.dp)
        ) {
            // Background circle
            drawArc(
                color = Color.Gray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
            )

            // Animated progress arc
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = (animatedPercentage / 100) * 360,
                useCenter = false,
                style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        // Display the percentage as text in the center
        Text(
            text = "${animatedPercentage.toInt()}%",
            fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ResultScreen(score: Int, totalQuestions: Int,navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var procent = String.format("%.1f", (score.toDouble() / totalQuestions.toDouble()) * 100)
        Text(text = "Twój wynik: $procent%", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
//        Text(text = "Twój wynik:", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(36.dp))
//        Text(text = "Dziękujemy za udział!", fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
//        AnimatedCircularProgressBar(procent.toFloat())
        Spacer(modifier = Modifier.height(76.dp))
        CustomButton("Powrót do menu","startActivity","",navController)
    }
}
@Composable
fun ExitConfirmationDialog(
    onConfirmExit: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,

        text = {
            Text(text = "Czy na pewno chcesz przerwać quiz?", fontSize = 18.sp)
        },
        confirmButton = {
            Button(onClick = onConfirmExit,colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background
            )) {
                Text("Tak, wychodzę")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss,colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background
            )) {
                Text("Nie, zostaję")
            }
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    )
}