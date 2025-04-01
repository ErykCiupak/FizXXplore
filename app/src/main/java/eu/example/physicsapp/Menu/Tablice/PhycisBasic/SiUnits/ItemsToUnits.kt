package eu.example.physicsapp.Menu.Tablice.PhycisBasic.SiUnits

// Klasa danych reprezentująca element jednostki fizycznej
data class Item(
    val id: Int,
    val headline: String,
    val details: List<InsideInfo> = emptyList()
)

// Klasa danych reprezentująca szczegóły jednostki
data class InsideInfo(
    val unit: String,
    val title: String,
    val unitExtends: String
)

// Lista elementów jednostek fizycznych
//InsideInfo("", "", ""),
//   ²   ³    ∙
val items = listOf(
    Item(id = 0, headline = "Długość, powierzchnia, objętość, kąt", details = listOf(
        InsideInfo("m", "długość", "metr"),
        InsideInfo("m²", "powierzchnia", "metr kwadratowy"),
        InsideInfo("m³", "objętość", "metr sześcienny"),
        InsideInfo("rad", "kąt płaski", "radian"),
        InsideInfo("sr", "kąt bryłowy", "steradian")
    )),
    Item(id = 1, headline = "Czas, częstotliwość, prędkość, przyspieszenie", details = listOf(
        InsideInfo("s", "czas", "sekunda"),
        InsideInfo("Hz", "częstotliwość", "herc"),
        InsideInfo("1/s", "prędkość obrotowa", "na sekundę"),
        InsideInfo("1/s", "pulsacja", "na sekundę"),
        InsideInfo("m/s", "prędkość", "metr na sekundę"),
        InsideInfo("rad/s", "prędkość kątowa", "radian na sekundę"),
        InsideInfo("m/s²", "przyspieszenie", "metr na sekundę do kwadratu")
    )),
    Item(id = 2, headline = "Mechanika", details = listOf(
        InsideInfo("kg", "masa", "kilogram"),
        InsideInfo("kg/m³", "gęstość masy", "kilogram na metr sześcienny"),
        InsideInfo("kg ∙ m", "moment bezwładności", "kilogramy razy metr"),
        InsideInfo("N", "siła", "niuton"),
        InsideInfo("N ∙ m", "moment siły / moment obrotowy", "niutonometr"),
        InsideInfo("N ∙ s", "popęd", "niutonosekunda"),
        InsideInfo("Pa", "ciśnienie", "paskal"),
        InsideInfo("bar", "ciśnienie", "bar (1 bar = 0,1 MPa)"),
        InsideInfo("N/m²", "nacisk", "niuton na metr kwadratowy"),
        InsideInfo("J", "praca / energia", "dżul(1 J = 1 N∙m = Ws)"),
        InsideInfo("W", "moc", "wat (1 W = 1 J/s = 1 N∙m/s)")
    )),
    Item(id = 3, headline = "Elektryczność", details = listOf(
        InsideInfo("C", "ładunek elektryczny", "kulomb"),
        InsideInfo("C/m²", "gęstość powierzchniowa ładunku", "kulomb na metr kwadratowy"),
        InsideInfo("C/m³ ∙ m", "gęstość przestrzenna ładunku", "kulomb na metr sześcienny"),
        InsideInfo("V", "napięcie / potencjał elektryczny", "wolt"),
        InsideInfo("V/m", "nątężenie pola elektrycznego", "wolt na metr (1 V/m = 1 N/C)"),
        InsideInfo("F", "pojemność elektryczna", "farad"),
        InsideInfo("F/m", "przenikalność elektryczna", "farad na metr"),
        InsideInfo("A", "natężenie prądu", "amper"),
        InsideInfo("A/m²", "gęstość prądu", "amper na metr kwadratowy"),
        InsideInfo("Ω", "rezystancja / reaktancja / impedancja", "om"),
        InsideInfo("Ω ∙ m", "rezystywność (opór elektryczny właściwy)", "omometr"),
        InsideInfo("S", "konduktancja (przewodność elektryczna)", "simens"),
        InsideInfo("S/m", "konduktywność (przewodność elektryczna właściwa)", "simens na metr"),
        InsideInfo("W", "moc elektryczna czynna", "wat"),
        InsideInfo("var", "moc elektryczna bierna", "war"),
        InsideInfo("VA", "moc elektryczna pozorna", "woltamper"),
        InsideInfo("H", "indukcyjność", "henr"),
        InsideInfo("Wh", "praca / energia elektryczna", "watogodzina (1 Wh = 1 J/s)")
    )),
    Item(id = 4, headline = "Magnetyzm", details = listOf(
        InsideInfo("H/m", "przenikalność", "henr na metr"),
        InsideInfo("A/m", "natężenie pola magnetycznego", "amper na metr"),
        InsideInfo("Wb", "strumień magnetyczny", "weber"),
        InsideInfo("T", "indukcja magnetyczna", "tesla")
    )),
    Item(id = 5, headline = "Promieniowanie elektromagnetyczne", details = listOf(
        InsideInfo("J", "energia promieniowania", "dżul"),
        InsideInfo("W", "moc promieniowania", "wat"),
        InsideInfo("W/sr", "natężenie promieniowania", "wat na steradian"),
        InsideInfo("W/(sr∙m²)", "gęstość promieniowania", "wat na steradian razy metr kwadratowy"),
        InsideInfo("W/m²", "natężenie napromieniowania (irradiancja)", "wat na metr kwadratowy")
    )),
    Item(id = 6, headline = "Optyka", details = listOf(
        InsideInfo("cd", "światłość", "kandela"),
        InsideInfo("cd/m²", "luminancja (jaskrawość)", "kandela na metr kwadratowy"),
        InsideInfo("lm", "strumień świetlny", "lumen"),
        InsideInfo("lm/W", "skuteczność świetlna (wydajność świetlna)", "lumen na wat"),
        InsideInfo("lx", "natężenie oświetlenia", "lux (1 lx = 1 lm/m²)"),
        InsideInfo("dpt", "wspólczynnik skupienia soczewek (zdolność skupijąca)", "dioptria"),
    )),
    Item(id = 7, headline = "Ciepło", details = listOf(
        InsideInfo("K", "temperatura", "kelwin"),
        InsideInfo("J", "ciepło (energia wewnętrzna)", "dżul"),
        InsideInfo("W", "moc cieplna", "wat"),
        InsideInfo("K/W", "rezystancja cieplna", "kelwin na wat"),
        InsideInfo("W/(K∙m)", "przewodność cieplna", "wat na kelwin razy metr"),
        InsideInfo("W/(K∙m²)", "rpzenikalność cieplna", "wat na kelwin razy metr kwadratowy"),
        InsideInfo("J/K", "pojemność cieplna (entropia)", "dżul na kelwin"),
        InsideInfo("J/(kg∙K)", "ciepło właściwe", "dżul na kilogram razy kelwin"),
    )),
    Item(id = 8, headline = "Chemia", details = listOf(
        InsideInfo("mol", "ilość materii", "mol"),
        InsideInfo("mol/m³", "gęstośc materii", "mol na metr sześcienny"),
        InsideInfo("m³/mol", "objętość materii", "metr sześcienny na mol"),
        InsideInfo("mol/kg", "stężenie materii", "mol na kilogram"),
        InsideInfo("kg/mol", "masa molowa", "kilogram na mol"),
        InsideInfo("J/(mol∙K)", "molowe ciepło właściwe", "dżul na mol razy kelwin"),
        InsideInfo("m²/s", "współczynnik dyfuzji (dyfuzyjność mierzona)", "metr kwadratowy na sekundę"),
    )),
    Item(id = 9, headline = "Reakcje jądrowe", details = listOf(
        InsideInfo("Bq", "aktywność promieniotwórcza", "bekerel"),
        InsideInfo("Gy", "dawka pochłonięta", "grej"),
        InsideInfo("Gy/s", "moc dawki pochłoniętej", "grej na sekundę"),
        InsideInfo("Sv", "dawka równoważna", "siwert"),
        InsideInfo("Sv/s", "moc dawki równoważnej", "siwert na sekundę"),
        InsideInfo("C/kg", "dawka ekspozycyjna", "kulomb na kilogram"),
        InsideInfo("A/kg", "moc dawki ekspozycyjnej", "amper na kilogram"),
    )),
    Item(id = 10, headline = "Akustyka", details = listOf(
        InsideInfo("Pa", "ciśnienie akustyczne", "paskal"),
        InsideInfo("m/s", "prędkość dźwięku", "metr na sekunde"),
        InsideInfo("W/m²", "natężenie dźwięku", "wat na metr kwadratowy"),
        InsideInfo("Pa∙s/m", "impedancja właściwa (falowa)", "paskal razy sekunda na metr"),
        InsideInfo("R", "impedancja akustyczna (oporność falowa)", "rejl"),
        InsideInfo("N∙s/m", "impedancja mechaniczna", "niuton razy sekunda na metr"),
        InsideInfo("m²", "równoważna powierzchnia absorpcji", "metr kwadratowy"),
    )),
    Item(id = 11, headline = "Inne", details = listOf(
        InsideInfo("AU", "odległość (astronomia)", "jednostka astronomiczna (1 AU = 1,49597870x10^11m)"),
        InsideInfo("ly", "odległość (astronomia)", "rok świetlny (1 ly = 63241 AU)"),
        InsideInfo("pc", "odległość (astronomia)", "parsek (1 pc = 3,26 ly = 206265 AU)"),
        InsideInfo("u", "masa atomowa", "atomowa jednostka masy (unit)"),
        InsideInfo("a", "powierzchnia gruntów", "ar (1 a = 100m²)"),
        InsideInfo("ha", "powierzchnia gruntów", "hektar (1 ha = 100 a)"),
    ))
)


