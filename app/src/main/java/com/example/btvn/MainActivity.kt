package com.example.btvn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

private val PrimaryBlue = Color(0xFF1E88E5)
private val LightBlueCard = Color(0xFFD0E1FD)
private val LightRedCard = Color(0xFFFFCDD2)
private val DarkRedText = Color(0xFFD32F2F)

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf("ready_screen") }

    when (currentScreen) {
        "ready_screen" -> ReadyScreen(
            onNavigateToList = { currentScreen = "list_screen" }
        )
        "list_screen" -> ListScreen(
            onNavigate = { destination -> currentScreen = destination },
            onBack = { currentScreen = "ready_screen" }
        )
        "text_detail" -> TextDetailScreen(
            onBack = { currentScreen = "list_screen" }
        )
        "image_detail" -> ImageDetailScreen(
            onBack = { currentScreen = "list_screen" }
        )
        "textfield_detail" -> TextFieldDetailScreen(
            onBack = { currentScreen = "list_screen" }
        )
        "row_detail" -> RowDetailScreen(
            onBack = { currentScreen = "list_screen" }
        )
    }
}

@Composable
fun ReadyScreen(onNavigateToList: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Compose3DLogo(modifier = Modifier.size(120.dp))

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Jetpack Compose",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Jetpack Compose is a modern toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateToList,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(26.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
        ) {
            Text(
                text = "I'm ready",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun Compose3DLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f
        val r = w / 2f

        val topPath = Path().apply {
            moveTo(cx, cy - r)
            lineTo(cx + r * 0.866f, cy - r * 0.5f)
            lineTo(cx, cy)
            lineTo(cx - r * 0.866f, cy - r * 0.5f)
            close()
        }
        drawPath(topPath, Color(0xFF4CAF50))

        val leftPath = Path().apply {
            moveTo(cx - r * 0.866f, cy - r * 0.5f)
            lineTo(cx, cy)
            lineTo(cx, cy + r)
            lineTo(cx - r * 0.866f, cy + r * 0.5f)
            close()
        }
        drawPath(leftPath, Color(0xFF1E88E5))

        val rightPath = Path().apply {
            moveTo(cx, cy)
            lineTo(cx + r * 0.866f, cy - r * 0.5f)
            lineTo(cx + r * 0.866f, cy + r * 0.5f)
            lineTo(cx, cy + r)
            close()
        }
        drawPath(rightPath, Color(0xFF0D47A1))

        val innerR = r * 0.45f
        val innerTop = Path().apply {
            moveTo(cx, cy - innerR)
            lineTo(cx + innerR * 0.866f, cy - innerR * 0.5f)
            lineTo(cx, cy)
            lineTo(cx - innerR * 0.866f, cy - innerR * 0.5f)
            close()
        }
        drawPath(innerTop, Color(0xFFE8F5E9))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(onNavigate: (String) -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "UI Components List",
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryBlue
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item { CategoryHeader("Display") }
            item {
                ComponentCard(
                    title = "Text",
                    subtitle = "Displays text"
                ) { onNavigate("text_detail") }
            }
            item {
                ComponentCard(
                    title = "Image",
                    subtitle = "Displays an image"
                ) { onNavigate("image_detail") }
            }

            item { CategoryHeader("Input") }
            item {
                ComponentCard(
                    title = "TextField",
                    subtitle = "Input field for text"
                ) { onNavigate("textfield_detail") }
            }
            item {
                ComponentCard(
                    title = "PasswordField",
                    subtitle = "Input field for passwords"
                ) { onNavigate("textfield_detail") }
            }

            item { CategoryHeader("Layout") }
            item {
                ComponentCard(
                    title = "Column",
                    subtitle = "Arranges elements vertically"
                ) { onNavigate("row_detail") }
            }
            item {
                ComponentCard(
                    title = "Row",
                    subtitle = "Arranges elements horizontally"
                ) { onNavigate("row_detail") }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .background(LightRedCard, RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Tự tìm hiểu",
                            fontWeight = FontWeight.Bold,
                            color = DarkRedText,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Tìm ra tất cả các thành phần UI Cơ bản",
                            fontSize = 13.sp,
                            color = DarkRedText.copy(alpha = 0.8f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryHeader(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.Black,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun ComponentCard(title: String, subtitle: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .background(LightBlueCard, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.DarkGray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Text Detail",
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryBlue
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            val formattedText = buildAnnotatedString {
                append("The ")
                withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                    append("quick ")
                }
                withStyle(
                    SpanStyle(
                        color = Color(0xFF8D5B4C),
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                ) {
                    append("Brown\n")
                }
                append("fox ")
                withStyle(SpanStyle(letterSpacing = 4.sp)) {
                    append("j u m p s ")
                }
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                ) {
                    append("over\n")
                }
                withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("the ")
                }
                withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("lazy ")
                }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("dog.")
                }
            }

            Text(
                text = formattedText,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Images",
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryBlue
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Link đường dẫn Ảnh 1 (Ảnh trên)
            val imageUrl1 = "https://upload.wikimedia.org/wikipedia/commons/c/cf/%E1%BA%A2nh_m%E1%BA%B7t_tr%C6%B0%E1%BB%9Bc_%C4%90H_GTVT_TP_HCM.jpg"
            val captionLink1 = "https://giaothongvantaitphcm.edu.vn/wp-content/uploads/2025/01/Logo-GTVT.png"

            // Link đường dẫn Ảnh 2 (Ảnh dưới)
            val imageUrl2 = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTDUAPf5NKdp0sf_IKy_lDvjIabZx6VYFDmk0Ry_fzW7fdo2FY0"

            // Khối Ảnh 1
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFEFEFEF)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.uth_building1),
                    contentDescription = "UTH Building 1",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                AsyncImage(
                    model = imageUrl1,
                    contentDescription = "UTH Network Image 1",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = captionLink1,
                fontSize = 11.sp,
                color = PrimaryBlue,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Khối Ảnh 2
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFEFEFEF)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.uth_building2),
                    contentDescription = "UTH Building 2",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                AsyncImage(
                    model = imageUrl2,
                    contentDescription = "UTH Network Image 2",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "In app",
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDetailScreen(onBack: () -> Unit) {
    var textValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "TextField",
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryBlue
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Thông tin nhập", color = Color.Gray) },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = textValue.ifEmpty { "Tự động cập nhật dữ liệu theo textfield" },
                color = DarkRedText,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Row Layout",
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryBlue
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(4) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp)
                            .background(Color(0xFF90CAF9), RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp)
                            .background(Color(0xFF1E88E5), RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(55.dp)
                            .background(Color(0xFF90CAF9), RoundedCornerShape(8.dp))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "App Full Interactive Flow", showSystemUi = true)
@Composable
fun AppNavigationPreview() {
    MaterialTheme {
        AppNavigation()
    }
}