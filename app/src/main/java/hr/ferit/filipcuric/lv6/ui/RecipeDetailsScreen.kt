package hr.ferit.filipcuric.lv6.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import hr.ferit.filipcuric.lv6.R
import hr.ferit.filipcuric.lv6.data.Recipe
import hr.ferit.filipcuric.lv6.data.RecipeViewModel
import hr.ferit.filipcuric.lv6.ui.theme.DarkGray
import hr.ferit.filipcuric.lv6.ui.theme.Gray
import hr.ferit.filipcuric.lv6.ui.theme.LightGray
import hr.ferit.filipcuric.lv6.ui.theme.Pink
import hr.ferit.filipcuric.lv6.ui.theme.White

@Composable
fun RecipeDetailsScreen(
    viewModel: RecipeViewModel,
    navigation: NavController,
    recipeId: Int,
) {
    val recipe = viewModel.recipesData[recipeId]

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            TopImageAndBar(coverImage = recipe.image, viewModel = viewModel, recipe = recipe, navigation = navigation)
        }
        item {
            ScreenInfo(
                recipe.title,
                recipe.category,
            )
        }
        item {
            BasicInfo(recipe = recipe)
        }
        item {
            Description(recipe = recipe)
        }
        item {
            Servings()
        }
        item {
            IngredientsHeader()
        }
        item {
            IngredientList(recipe = recipe)
        }
        item {
            ShoppingListButton()
        }
        item {
            Reviews(recipe = recipe)
        }
        item {
            OtherRecipes()
        }
    }
}

@Composable
fun IngredientsHeader() {
    var currentActiveButton by remember { mutableStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton(
            text = "Ingredients",
            isActive = currentActiveButton == 0,
            modifier = Modifier.weight(1f)
        ) {
            currentActiveButton = 0
        }
        TabButton(
            text = "Tools",
            isActive = currentActiveButton == 1,
            modifier = Modifier.weight(1f)
        ) {
            currentActiveButton = 1
        }
        TabButton(
            text = "Steps",
            isActive = currentActiveButton == 2,
            modifier = Modifier.weight(1f)
        ) {
            currentActiveButton = 2
        }
    }
}

@Composable
fun IngredientCard(
    iconResource: String,
    title: String,
    subtitle: String,
) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = iconResource),
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

@Composable
fun Servings() {
    var servings by remember {
        mutableStateOf(0)
    }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .background(color = LightGray)
        ) {
            Text(text = "Serving", modifier = Modifier.padding(start=8.dp), fontWeight = FontWeight.Medium)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                CircularButton(
                    iconResource = R.drawable.ic_minus,
                    color = Pink,
                    elevation = null,
                    onClick = {
                        if(servings > 0)
                            servings--
                    }
                )
                Text(text = "$servings", modifier = Modifier.padding(horizontal = 16.dp))
                CircularButton(
                    iconResource = R.drawable.ic_plus,
                    color = Pink,
                    elevation = null,
                    onClick = {
                        servings++
                    }
                )
            }
        }
}

@Composable
fun CircularButton(
    @DrawableRes iconResource: Int,
    color: Color = Gray,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(defaultElevation = 12.dp),
    onClick: () -> Unit = {},
) {
    Button(
        contentPadding = PaddingValues(),
        elevation = elevation,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = color),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ) {
        Icon(
            painterResource(id = iconResource),
            contentDescription = null,
        )
    }
}

@Composable
fun TopImageAndBar(
    coverImage: String,
    viewModel: RecipeViewModel,
    recipe: Recipe,
    navigation: NavController,
) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = coverImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .height(56.dp)
                    .padding(horizontal = 16.dp)
            ) {
                CircularButton(iconResource = R.drawable.ic_arrow_back, color = Pink, onClick = {
                    navigation.popBackStack()
                })
                CircularButton(
                    iconResource = R.drawable.ic_favorite,
                    color = if(recipe.isFavorited) Pink else DarkGray,
                    onClick = {
                    recipe.isFavorited = !recipe.isFavorited
                    viewModel.updateRecipe(recipe)
                })
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White
                            ),
                            startY = 100f
                        )
                    )
            )
        }
    }
}

    @Composable
    fun ScreenInfo(
        title: String,
        category: String
    ) {
        Column {
            Text(
                text = category,
                style = TextStyle(color = Pink, fontSize = 15.sp, fontWeight = FontWeight.Light),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = title,
                style = TextStyle(color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
    }

@Composable
fun InfoColumn(
    @DrawableRes iconResource: Int,
    text: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
            tint = Pink,
            modifier = Modifier.height(24.dp)
        )
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BasicInfo(
    recipe: Recipe,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(iconResource = R.drawable.ic_clock, text = recipe.cookingTime)
        InfoColumn(iconResource = R.drawable.ic_flame, text = recipe.energy)
        InfoColumn(iconResource = R.drawable.ic_star, text = recipe.rating)
    }
}

@Composable
fun Description(
    recipe: Recipe,
) {
    Text(
        text = recipe.description,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
    )
}

@Composable
fun <T> EasyGrid(nColumns: Int, items: List<T>, content: @Composable (T) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        for (i in items.indices step nColumns) {
            Row {
                for (j in 0 until nColumns) {
                    if (i + j < items.size) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.weight(1f)
                        ) {
                            content(items[i + j])
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}

@Composable
fun IngredientList(
    recipe: Recipe
) {
    EasyGrid(nColumns = 3, items = recipe.ingredients) {
        IngredientCard(iconResource = it.image, title = it.title, subtitle = it.subtitle)
    }
}

@Composable
fun ShoppingListButton() {
    Button(
        onClick = { /*TODO*/ },
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
        Text(
            text = "Add to shopping list",
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun Reviews(
    recipe: Recipe
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Text(text = "Reviews", style = TextStyle(fontSize = 16.sp,
                fontWeight = FontWeight.Bold))
            Text(text = recipe.reviews, color = DarkGray)
        }

        IconButton(
            iconResource = R.drawable.ic_arrow_right,
            text = "See all",
        )
    }
}

@Composable
fun OtherRecipes() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_2),
            contentDescription = "Strawberry Pie",
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_3),
            contentDescription = "Strawberry Pie",
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        )
    }

}
