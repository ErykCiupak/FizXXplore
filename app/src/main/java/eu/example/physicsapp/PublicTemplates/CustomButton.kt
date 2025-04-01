package eu.example.physicsapp.PublicTemplates

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.example.physicsapp.ui.theme.regularText

@Composable
fun CustomButton(name:String,localisation:String,icon:String,navController: NavController){
    Box(
        modifier = Modifier.fillMaxWidth().height(90.dp),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {
                navController.navigate(localisation)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .padding(0.dp,8.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                val context = LocalContext.current
                val drawableId = getDrawableId(context, icon)
                if (icon!=""){
                    Image(
                        modifier = Modifier.size(50.dp),
                        painter =painterResource(id=drawableId),
                        contentDescription = "asd",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background))
//                    Spacer(modifier = Modifier.width(5.dp))
                }else{
                    Spacer(modifier = Modifier.width(24.dp))
                }

                Text(text = name, fontFamily = regularText, color = MaterialTheme.colorScheme.background, fontSize = 16.sp, modifier = Modifier.widthIn(max = 200.dp))
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Arrow right",
                    tint = MaterialTheme.colorScheme.background)
            }

        }
    }

}
@SuppressLint("DiscouragedApi")
@Composable
fun getDrawableId(context: Context, drawableName: String): Int {
    return context.resources.getIdentifier(drawableName, "drawable", context.packageName)
}
@Composable
fun CustomButtonAcademy(rozdzial:String,text:String,opis:String,localisation:String,navController: NavController){
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Button(
            onClick = {
                navController.navigate(localisation)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(0.dp,8.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Column(modifier = Modifier.width(300.dp)) {
                    Text(text = rozdzial, fontFamily = regularText, color = MaterialTheme.colorScheme.background, fontSize = 12.sp, modifier = Modifier.widthIn(max = 250.dp))
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(text = text, fontFamily = regularText, color = MaterialTheme.colorScheme.background, fontSize = 16.sp, modifier = Modifier.widthIn(max = 250.dp))
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(text = opis, fontFamily = regularText, color = MaterialTheme.colorScheme.background, fontSize = 12.sp, modifier = Modifier.widthIn(max = 250.dp), lineHeight = 15.sp)
                }
                Box(modifier = Modifier.width(100.dp)){
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Arrow right",
                        tint = MaterialTheme.colorScheme.background)
                }

            }

        }
    }

}