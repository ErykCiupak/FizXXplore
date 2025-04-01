package eu.example.physicsapp.Menu.Calculators.Basics.DecodeResistor

import androidx.compose.ui.graphics.Color
data class BottomSheetTemplate(val colorName: String, val colorCode: Color, val textColor: Color, val whichPasek: Int)
val colorLists = listOf(
    listOf(
        BottomSheetTemplate("czarny", Color.Black, Color.White,1),
        BottomSheetTemplate("brązowy", Color(0xFFA52A2A), Color.White, 1),
        BottomSheetTemplate("czerwony", Color.Red, Color.White, 1),
        BottomSheetTemplate("pomarańczowy", Color(0xFFFFA500), Color.White, 1),
        BottomSheetTemplate("żółty", Color.Yellow, Color.Black, 1),
        BottomSheetTemplate("zielony", Color.Green, Color.Black, 1),
        BottomSheetTemplate("niebieski", Color.Blue, Color.White, 1),
        BottomSheetTemplate("fioletowy", Color(0xFF800080), Color.White, 1),
        BottomSheetTemplate("szary", Color(0xFF808080), Color.White, 1),
        BottomSheetTemplate("biały", Color.White, Color.Black, 1)
    ),
    listOf(
        BottomSheetTemplate("czarny", Color.Black, Color.White,2),
        BottomSheetTemplate("brązowy", Color(0xFFA52A2A), Color.White,2),
        BottomSheetTemplate("czerwony", Color.Red, Color.White,2),
        BottomSheetTemplate("pomarańczowy", Color(0xFFFFA500), Color.White,2),
        BottomSheetTemplate("żółty", Color.Yellow, Color.Black,2),
        BottomSheetTemplate("zielony",  Color.Green, Color.Black,2),
        BottomSheetTemplate("niebieski",  Color.Blue, Color.White ,2),
        BottomSheetTemplate("fioletowy", Color(0xFF800080), Color.White,2),
        BottomSheetTemplate("szary", Color(0xFF808080), Color.White,2),
        BottomSheetTemplate("biały", Color.White, Color.Black ,2)
    ),
    listOf(
        BottomSheetTemplate("czarny", Color.Black, Color.White,3),
        BottomSheetTemplate("brązowy", Color(0xFFA52A2A), Color.White,3),
        BottomSheetTemplate("czerwony", Color.Red, Color.White,3),
        BottomSheetTemplate("pomarańczowy", Color(0xFFFFA500), Color.White,3),
        BottomSheetTemplate("żółty", Color.Yellow, Color.Black,3),
        BottomSheetTemplate("zielony",  Color.Green, Color.Black,3),
        BottomSheetTemplate("niebieski",  Color.Blue, Color.White ,3),
        BottomSheetTemplate("fioletowy", Color(0xFF800080), Color.White,3),
        BottomSheetTemplate("szary", Color(0xFF808080), Color.White,3),
        BottomSheetTemplate("biały", Color.White, Color.Black ,3)
    ),
    listOf(
        BottomSheetTemplate("czarny", Color.Black, Color.White,4),
        BottomSheetTemplate("brązowy", Color(0xFFA52A2A), Color.White,4),
        BottomSheetTemplate("czerwony", Color.Red, Color.White,4),
        BottomSheetTemplate("pomarańczowy", Color(0xFFFFA500), Color.White,4),
        BottomSheetTemplate("żółty", Color.Yellow, Color.Black,4),
        BottomSheetTemplate("zielony",  Color.Green, Color.Black,4),
        BottomSheetTemplate("niebieski",  Color.Blue, Color.White ,4),
        BottomSheetTemplate("fioletowy", Color(0xFF800080), Color.White,4),
        BottomSheetTemplate("szary", Color(0xFF808080), Color.White,4),
        BottomSheetTemplate("biały", Color.White, Color.Black ,4),
        BottomSheetTemplate("złoty", Color(0xFFFFD700), Color.Black,4),
        BottomSheetTemplate("srebrny", Color(0xFFC0C0C0), Color.White ,4)
    ),
    listOf(
        BottomSheetTemplate("brązowy", Color(0xFFA52A2A), Color.White,5),
        BottomSheetTemplate("czerwony", Color.Red, Color.White,5),
        BottomSheetTemplate("zielony",  Color.Green, Color.Black,5),
        BottomSheetTemplate("niebieski",  Color.Blue, Color.White ,5),
        BottomSheetTemplate("fioletowy", Color(0xFF800080), Color.White,5),
        BottomSheetTemplate("szary", Color(0xFF808080), Color.White,5),
        BottomSheetTemplate("złoty", Color(0xFFFFD700), Color.Black,5),
        BottomSheetTemplate("srebrny", Color(0xFFC0C0C0), Color.White ,5)
    ),
    listOf(
        BottomSheetTemplate("brązowy", Color(0xFFA52A2A), Color.White,6),
        BottomSheetTemplate("czerwony", Color.Red, Color.White,6),
        BottomSheetTemplate("pomarańczowy", Color(0xFFFFA500), Color.White,6),
        BottomSheetTemplate("żółty", Color.Yellow, Color.Black,6),
        BottomSheetTemplate("niebieski",  Color.Blue, Color.White ,6),
        BottomSheetTemplate("fioletowy", Color(0xFF800080), Color.White,6),
    )
)