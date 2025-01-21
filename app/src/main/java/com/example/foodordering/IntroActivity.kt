package com.example.foodordering

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
@Preview
fun IntroScreenPreview() {
    IntroScreen(onGetStartedClick={})
}

@Composable
fun IntroScreen(onGetStartedClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(Color.White)
        ) {
            val (backgroundImg, logoImg, titleTxt, buttonBox) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.background_intro),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(backgroundImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .height(700.dp),
                contentScale = ContentScale.FillBounds
            )

            Image(
                painter = painterResource(id = R.drawable.pospallog),
                contentDescription = null,
                modifier = Modifier.run {
                    constrainAs(titleTxt) {
                        bottom.linkTo(logoImg.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                        .width(200.dp)
                        .height(200.dp)
                },
                contentScale = ContentScale.Fit
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.run {
                    constrainAs(logoImg) {
                        top.linkTo(backgroundImg.top)
                        bottom.linkTo(backgroundImg.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                        .width(180.dp)
                        .height(180.dp)
                },
                contentScale = ContentScale.Fit
            )
            GetStartedButton(
                onClick = onGetStartedClick,
                modifier = Modifier
                    .padding(top = 58.dp)
                    .constrainAs(buttonBox) {
                        top.linkTo(backgroundImg.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }

            )
        }

    }
}

@Composable
fun GetStartedButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(
        backgroundColor = colorResource(R.color.orange)
    ),
        shape = RoundedCornerShape(50.dp),
        modifier = modifier
            .fillMaxWidth(0.9f)
            .height(50.dp)
        ) {
        Text(
            text = "Get Started",
            fontSize = 22.sp,
            color = Color.White
        )
    }
}