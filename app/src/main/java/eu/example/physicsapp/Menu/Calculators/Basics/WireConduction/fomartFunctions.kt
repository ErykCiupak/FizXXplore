package eu.example.physicsapp.Menu.Calculators.Basics.WireConduction

fun formatResistantOutput(value: Double): String {
    val absValue = kotlin.math.abs(value)

    val scaledValue = absValue

    return when {
        scaledValue >= 1e30 -> "%.2e Ω".format(scaledValue)
        scaledValue >= 1e24 -> "%.2f YΩ".format(scaledValue / 1e24)
        scaledValue >= 1e21 -> "%.2f ZΩ".format(scaledValue / 1e21)
        scaledValue >= 1e18 -> "%.2f EΩ".format(scaledValue / 1e18)
        scaledValue >= 1e15 -> "%.2f PΩ".format(scaledValue / 1e15)
        scaledValue >= 1e12 -> "%.2f TΩ".format(scaledValue / 1e12)
        scaledValue >= 1e9 -> "%.2f GΩ".format(scaledValue / 1e9)
        scaledValue >= 1e6 -> "%.2f MΩ".format(scaledValue / 1e6)
        scaledValue >= 1e3 -> "%.2f kΩ".format(scaledValue / 1e3)
        scaledValue >= 1 -> "%.2f Ω".format(scaledValue)
        scaledValue >= 1e-3 -> "%.2f mΩ".format(scaledValue * 1e3)
        scaledValue >= 1e-6 -> "%.2f µΩ".format(scaledValue * 1e6)
        scaledValue >= 1e-9 -> "%.2f nΩ".format(scaledValue * 1e9)
        scaledValue >= 1e-12 -> "%.2f pΩ".format(scaledValue * 1e12)
        scaledValue >= 1e-15 -> "%.2f fΩ".format(scaledValue * 1e15)
        scaledValue >= 1e-18 -> "%.2f aΩ".format(scaledValue * 1e18)
        scaledValue >= 1e-21 -> "%.2f zΩ".format(scaledValue * 1e21)
        else -> "%.2e Ω".format(scaledValue)
    }
}
fun formatConductionOutput(value: Double): String {
    val absValue = kotlin.math.abs(value)

    val scaledValue = absValue

    return when {
        scaledValue >= 1e30 -> "%.2e S".format(scaledValue)
        scaledValue >= 1e24 -> "%.2f YS".format(scaledValue / 1e24)
        scaledValue >= 1e21 -> "%.2f ZS".format(scaledValue / 1e21)
        scaledValue >= 1e18 -> "%.2f ES".format(scaledValue / 1e18)
        scaledValue >= 1e15 -> "%.2f PS".format(scaledValue / 1e15)
        scaledValue >= 1e12 -> "%.2f TS".format(scaledValue / 1e12)
        scaledValue >= 1e9 -> "%.2f GS".format(scaledValue / 1e9)
        scaledValue >= 1e6 -> "%.2f MS".format(scaledValue / 1e6)
        scaledValue >= 1e3 -> "%.2f kS".format(scaledValue / 1e3)
        scaledValue >= 1 -> "%.2f S".format(scaledValue)
        scaledValue >= 1e-3 -> "%.2f mS".format(scaledValue * 1e3)
        scaledValue >= 1e-6 -> "%.2f µS".format(scaledValue * 1e6)
        scaledValue >= 1e-9 -> "%.2f nS".format(scaledValue * 1e9)
        scaledValue >= 1e-12 -> "%.2f pS".format(scaledValue * 1e12)
        scaledValue >= 1e-15 -> "%.2f fS".format(scaledValue * 1e15)
        scaledValue >= 1e-18 -> "%.2f aS".format(scaledValue * 1e18)
        scaledValue >= 1e-21 -> "%.2f zS".format(scaledValue * 1e21)
        else -> "%.2e Ω".format(scaledValue)
    }
}