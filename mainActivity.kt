package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.ui.theme.ToDoListTheme
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.unit.TextUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var inputText by remember { mutableStateOf("") }
    var items by remember { mutableStateOf(listOf<String>()) }
    fun chadTXTStyle(
        fontWeight: FontWeight = Normal,
        fontFamily: FontFamily = FontFamily(Font(googleFont = GoogleFont("Raleway"), fontProvider = provider)),
        fontSize: TextUnit = 14.sp
    ): TextStyle {
        return TextStyle(
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            fontSize = fontSize
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "ToDoList App",
            style = chadTXTStyle(fontSize = 28.sp, fontWeight = Bold),
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), verticalAlignment = Alignment.Bottom) {
            OutlinedTextField(value = inputText, onValueChange = { inputText = it }, label = { Text("Entrez un élément", style = chadTXTStyle()) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onDone = {
                    if (inputText.isNotBlank()) {
                        items = items + inputText
                        inputText = ""
                    }
                }), shape = RoundedCornerShape(17), modifier = Modifier.weight(1f).height(64.dp))
            Button(onClick = { if (inputText.isNotBlank()) {items = items + inputText; inputText = "" } }, shape = RoundedCornerShape(17), modifier = Modifier.padding(start = 8.dp).height(56.dp)) {
                Text("Valider", style = chadTXTStyle())
            }
        }

        LazyColumn (verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items) { item ->
                Row {
                    Text(text = item, modifier = Modifier.padding(8.dp).weight(1f), style = chadTXTStyle())
                    Button(onClick = { items = items - item }, modifier = Modifier.padding(start = 8.dp).height(48.dp).background(Color.Red), colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White)) {
                        Text("Valider", color = Color.White, style = chadTXTStyle())
                    }
                }
            }
        }
    }
}
