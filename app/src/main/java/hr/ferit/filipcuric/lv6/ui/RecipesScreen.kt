package hr.ferit.filipcuric.lv6.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import hr.ferit.filipcuric.lv6.ui.theme.DarkGray
import hr.ferit.filipcuric.lv6.R
import hr.ferit.filipcuric.lv6.Routes
import hr.ferit.filipcuric.lv6.data.RecipeViewModel
import hr.ferit.filipcuric.lv6.ui.theme.LightGray
import hr.ferit.filipcuric.lv6.ui.theme.Pink
import hr.ferit.filipcuric.lv6.ui.theme.White

@Composable
fun RecipesScreen(
    viewModel: RecipeViewModel,
    navigation: NavController
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title = "What would you like to cook today?",
            subtitle = "Good morning, Filip",
        )
        SearchBar(
            iconResource = R.drawable.ic_search,
            labelText = "Search..."
        )
        RecipeCategories()
        RecipeList(viewModel = viewModel, navigation = navigation)
        IconButton(
            iconResource = R.drawable.ic_plus,
            text = "Add new recipe"
        )
    }
}

@Composable
fun ScreenTitle(
    title: String,
    subtitle: String,
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = subtitle,
            style = TextStyle(
                color = Color.Magenta,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
                ),
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )
        Text(
            text = title,
            style = TextStyle(color = Color.Black, fontSize = 26.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    @DrawableRes iconResource: Int,
    labelText: String,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        placeholderColor = DarkGray,
        textColor = DarkGray,
        unfocusedLabelColor = DarkGray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
) {
    var searchInput by remember {
        mutableStateOf("")
    }
    TextField(
        value = searchInput,
        onValueChange = { searchInput = it },
        label = {
            Text(labelText)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = labelText,
                tint = DarkGray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            )
        },
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun TabButton(
    text: String,
    isActive: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(24.dp),
        elevation = null,
        colors = if (isActive) ButtonDefaults.buttonColors(
            contentColor = White,
            containerColor = Pink
        ) else
        ButtonDefaults.buttonColors(
            contentColor = DarkGray,
            containerColor = LightGray
        ),
        modifier = Modifier
            .fillMaxHeight(),
        onClick = { onClick() }
    ) {
        Text(text)
    }
}

@Composable
fun RecipeCategories() {
    var currentActiveButton by remember { mutableIntStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton(
            text = "All",
            isActive = currentActiveButton == 0,
            modifier = Modifier.weight(1f)
        ) {
            currentActiveButton = 0
        }
        TabButton(
            text = "Breakfast",
            isActive = currentActiveButton == 1,
            modifier = Modifier.weight(1f)
        ) {
            currentActiveButton = 1
        }
        TabButton(
            text = "Lunch",
            isActive = currentActiveButton == 2,
            modifier = Modifier.weight(1f)
        ) {
            currentActiveButton = 2
        }
    }
}

@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    text: String
) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(containerColor = Pink),
    ) {
        Row {
            Icon (
                painterResource(id = iconResource),
                contentDescription = text,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}

@Composable
fun Chip(
    text: String,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Magenta
) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = 12.sp
            )
        )
    }
}

@Composable
fun RecipeCard(
    imageResource: String,
    title: String,
    onClick: () -> Unit
) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .height(326.dp)
                .width(215.dp)
                .clickable { onClick() }
        ) {
            Box (
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageResource),
                    contentDescription = title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = White
                        )
                    )
                    Row {
                        Chip(text = "30 min")
                        Chip(text = "4 ingredients")
                    }
                }
            }
        }
}

@Composable
fun RecipeList(
    viewModel: RecipeViewModel,
    navigation: NavController
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "7 recipes",
                style = TextStyle(color = Color.DarkGray, fontSize =
                14.sp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = "Flame",
                tint = Color.DarkGray,
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(viewModel.recipesData.size) {
                RecipeCard(
                    imageResource = viewModel.recipesData[it].image,
                    title = viewModel.recipesData[it].title
                ) {
                    navigation.navigate(
                        Routes.getRecipeDetailsPath(it)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
