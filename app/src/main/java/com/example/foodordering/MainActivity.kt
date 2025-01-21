package com.example.foodordering

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodordering.ui.theme.FoodOrderingTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(

            )
        }
    }
}

@Composable
@Preview
fun MainScreen(

) {
    val scaffoldState = rememberScaffoldState()

    Scaffold (
        bottomBar = {
            MyBottomBar()
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                contentColor = Color.White,
                backgroundColor = colorResource(id=R.color.orange)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.shopping_cart),
                    contentDescription = "add",
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                )
            }
        }, scaffoldState = scaffoldState,
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        content = { paddingValues: PaddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues = paddingValues)
            ) {
                NameAndProfile()
                Search()
            }
        }
    )
}

@Composable
fun Search() {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text, onValueChange = { text = it },
        label = {
            Text(
                text = "Find Your Food",
                fontStyle = FontStyle.Italic
            )
        },
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                modifier = Modifier.size(23.dp)
            )
        }, shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = colorResource(id = R.color.grey),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            textColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
            unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(50.dp)
            .background(colorResource(id = R.color.grey), CircleShape)
    )
}

@Composable
fun NameAndProfile() {
    ConstraintLayout(
        modifier = Modifier
            .padding(top = 48.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        val (name, order, img) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(img) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .clickable {  }
        )
        Text(
            text = "Hi Rechard",
            color = colorResource(id = R.color.orange),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = "Order & Eat",
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .constrainAs(order) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
    }
}

@Composable
fun MyBottomBar() {
    val bottomMenuItemsList = prepareBottomMenu()
    val contextForToast = LocalContext.current.applicationContext
    var selectedItem by remember {
        mutableStateOf("Home")
    }
    BottomAppBar (
        cutoutShape = CircleShape,
        backgroundColor = Color(android.graphics.Color.parseColor("#f8f8f8")),
        elevation = 3.dp
    ) {
        bottomMenuItemsList.forEachIndexed { index, item ->
            if (index == 2) {
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    enabled = false
                )

            }

            BottomNavigationItem(
                selected = (selectedItem == item.label),
                onClick = {
                    selectedItem = item.label
                    Toast.makeText(contextForToast, item.label, Toast.LENGTH_SHORT).show()
                },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        modifier = Modifier.padding(top = 14.dp)
                    )
                }, alwaysShowLabel = true,
                enabled = true

            )
        }
    }
}

data class BottomMenuItem(
    val label: String, val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {

    val bottomMenuItemList = arrayListOf<BottomMenuItem>()

    bottomMenuItemList.add(
        BottomMenuItem(label = "Home", icon = painterResource(id = R.drawable.bottom_btn1))
    )
    bottomMenuItemList.add(
        BottomMenuItem(label = "Profile", icon = painterResource(id = R.drawable.bottom_btn2))
    )
    bottomMenuItemList.add(
        BottomMenuItem(label = "Support", icon = painterResource(id = R.drawable.bottom_btn3))
    )
    bottomMenuItemList.add(
        BottomMenuItem(label = "Settings", icon = painterResource(id = R.drawable.bottom_btn4))
    )

    return bottomMenuItemList
}
