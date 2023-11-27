package hr.ferit.filipcuric.lv6.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.filipcuric.lv6.R

@Composable
fun IngredientCard(
    @DrawableRes iconResource: Int,
    title: String,
    subtitle: String,
) {
        Column {
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = title,
                modifier = Modifier
                    .padding(10.dp)
                    .height(75.dp)
                    .width(75.dp),
            )
            Box(
                modifier = Modifier
                    .padding(start=10.dp, bottom=10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = title,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        )
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun IngredientCardPreview() {
    Row {
        IngredientCard(
            iconResource = R.drawable.flour,
            title = "Flour",
            subtitle = "450 g",
        )
        IngredientCard(
            iconResource = R.drawable.vanilla,
            title = "Vanilla",
            subtitle = "1/2 teaspoon"
        )
        IngredientCard(
            iconResource = R.drawable.strawberry,
            title = "Strawberry",
            subtitle = "200 g"
        )
    }
}
