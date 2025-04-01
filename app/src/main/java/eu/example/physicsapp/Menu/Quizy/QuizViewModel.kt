package eu.example.physicsapp.Menu.Quizy


import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import eu.example.physicsapp.R

data class Question(
    val symbolName: String,
    var answers: List<Answer> // Lista odpowiedzi, która może być przetasowywana
)

data class Answer(
    val imageResId: Int,
    val isCorrect: Boolean
)

class QuizViewModel : ViewModel() {

    var numberOfQuestions =0
    private val correctAnswers = listOf(
        R.drawable.qz_zacisk,
        R.drawable.qz_uziemienie,
        R.drawable.qi_prze_napowie,
        R.drawable.qi_linia_dol,
        R.drawable.qi_linia_pion,
        R.drawable.qi_lacznik_pods,
        R.drawable.qi_lacznik_zmienny,
        R.drawable.qi_sciemniacz,
        R.drawable.qi_gniazdo_wty,
        R.drawable.qi_silnik_liniowy,
        R.drawable.qi_amperomierz,
        R.drawable.qi_liczni_energi_czynnej,
        R.drawable.qi_elektromangnes,
        R.drawable.qi_silnik,
        R.drawable.qi_diodaled,
        R.drawable.qi_tranzystorpnp,
        R.drawable.qi_warystor,
        R.drawable.qi_kondensator,
        R.drawable.qi_kuch_elek,
        R.drawable.qi_prad_staly,
        R.drawable.qi_prad_zmienny

    )
    private val allImages = correctAnswers


    private fun getRandomIncorrectAnswers(correctAnswer: Int): List<Answer> {
        val incorrectAnswers = allImages.filter { it != correctAnswer }.shuffled().take(3)
        return incorrectAnswers.map { Answer(it, false) }
    }

    private fun createQuestion(symbolName: String, correctAnswer: Int): Question {
        // Create a mutable list with the correct answer
        val answers = mutableListOf<Answer>(
            Answer(correctAnswer, true)
        )

        // Add random incorrect answers to the list
        answers.addAll(getRandomIncorrectAnswers(correctAnswer))

        // Shuffle the answers to randomize their order
        answers.shuffle()

        return Question(symbolName, answers)
    }

    val questions = listOf(
        createQuestion("zacisk, połączenie rozłączane", correctAnswers[0]),
        createQuestion("uziemienie", correctAnswers[1]),
        createQuestion("linie napowietrzną", correctAnswers[2]),
        createQuestion("linie odchodzącą w dół", correctAnswers[3]),
        createQuestion("linie przechodzącą pionowo", correctAnswers[4]),
        createQuestion("łącznik podświetlony", correctAnswers[5]),
        createQuestion("łącznik zmienny (schodowy)", correctAnswers[6]),
        createQuestion("ściemniacz", correctAnswers[7]),
        createQuestion("gniazdo wtykowe instalacyjne", correctAnswers[8]),
        createQuestion("silnik liniowy", correctAnswers[9]),
        createQuestion("amperomierz", correctAnswers[10]),
        createQuestion("licznik energii czynnej", correctAnswers[11]),
        createQuestion("elektromagnes", correctAnswers[12]),
        createQuestion("silnik", correctAnswers[13]),
        createQuestion("diode LED", correctAnswers[14]),
        createQuestion("tranzystor PNP", correctAnswers[15]),
        createQuestion("warystor", correctAnswers[16]),
        createQuestion("kondensator, baterie kondensatorów", correctAnswers[17]),
        createQuestion("kuchenke elektryczną", correctAnswers[18]),
        createQuestion("prąd stały", correctAnswers[19]),
        createQuestion("prąd zmienny", correctAnswers[20])

    ).shuffled()

    var currentQuestionIndex = mutableIntStateOf(0)
    var selectedAnswerIndex = mutableIntStateOf(-1)
    var score = mutableIntStateOf(0)

    var canProceedToNextQuestion = mutableStateOf(false) // Flag to check if the next question can be proceeded to

    fun selectAnswer(index: Int) {
        if (selectedAnswerIndex.value == -1) {
            selectedAnswerIndex.value = index
            if (questions[currentQuestionIndex.value].answers[index].isCorrect) {
                score.value++
            }
            canProceedToNextQuestion.value = true // Allow proceeding to the next question
        }
    }

    fun nextQuestion() {
        if (canProceedToNextQuestion.value) {
            selectedAnswerIndex.value = -1
            canProceedToNextQuestion.value = false // Resetting the flag
            if (currentQuestionIndex.value < numberOfQuestions - 1) {
                currentQuestionIndex.value++
            }
        }
    }

    fun isQuizFinished() = currentQuestionIndex.value >= numberOfQuestions- 1
}