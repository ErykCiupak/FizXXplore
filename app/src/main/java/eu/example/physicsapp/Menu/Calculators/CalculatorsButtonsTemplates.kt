package eu.example.physicsapp.Menu.Calculators

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.AreaCalculator.areaConvert
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.EnergyCalculator.energyConvert
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.LenghtCalculator.lengthConvert
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.MassCalculator.massConvert
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.PowerCalculator.powerConvert
import eu.example.physicsapp.Menu.Calculators.UnitCovertersFolder.VolumeCalculator.volumeConverter


@SuppressLint("SuspiciousIndentation")
@Composable
fun OutputTemplate(name:String,outPutNumber:String,extand: String,viewModel: SharedViewModel){
    if (extand!= viewModel.selectedUnitExts)
    Box(
        modifier = Modifier.fillMaxWidth(0.90f).height(80.dp).
            padding(top = 1.dp)
//            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
//            shape = RoundedCornerShape(16.dp)),
        ,contentAlignment = Alignment.Center
    ){
        Card(
            colors = CardDefaults.cardColors(
                containerColor =MaterialTheme.colorScheme.background
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .height(80.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name, fontSize = 14.sp
                )
                Text(
                    text = "$outPutNumber $extand", fontSize = 14.sp
                )
            }
        }
    }
}
@Composable
fun UnitTemplate(name:String, extand: String, viewModel: SharedViewModel, convertVersion:String){
    if(extand!=viewModel.selectedUnitExts){
        Box(
            modifier = Modifier.fillMaxWidth(0.90f).height(70.dp).padding(top = 10.dp),
            contentAlignment = Alignment.Center
        ){
            Card(
            colors = CardDefaults.cardColors(
                containerColor =MaterialTheme.colorScheme.background
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 0.dp)
                .height(80.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
                Button(
                    onClick = {
                        viewModel.updateIsSheetOpen(false)
                        viewModel.updateSelectedUnitName(name)
                        viewModel.upadateSelectedUnitExts(extand)
                        when (convertVersion) {
                            "lenght" -> lengthConvert(viewModel)
                            "area" -> areaConvert(viewModel)
                            "volume" -> volumeConverter(viewModel)
                            "power" -> powerConvert(viewModel)
                            "energy" -> energyConvert(viewModel)
                            "mass" -> massConvert(viewModel)
                            "density" -> massConvert(viewModel)
                        }

                    },
                    colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.background),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = name,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = extand,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
    }