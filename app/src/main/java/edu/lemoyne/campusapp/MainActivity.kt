package edu.lemoyne.campusapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.pm.ShortcutInfoCompat
import edu.lemoyne.campusapp.ui.theme.CampusAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }

    }
}

// --- Class 7: Step 1: a counter that remembers
@Composable
fun CounterDemo() {
    var count by remember { mutableStateOf(0) }

    Button(
        onClick = { count++ }
    ) {
        Text(text = "Tapped $count times")
    }
}

// --- Class 6: Step1: my own screen ---

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // --- Class 7: Step 2: the list lives in state
    var stats = remember {
        mutableStateListOf(
            "Best Player: Cam Schlittler",
            "Current Matchup: Boston Red Sox",
            "Playoffs Clinched",
            "Last 10: 6-4"
        )
    }

    // --- Class 7: Step 3: What typed lives in state
    var newStat by remember { mutableStateOf("") }
    // --- Class 6: Step 3: a column, so things stack ---
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 24.dp)
    ) {
        //CounterDemo()
        // --- Lab 6 · Task 3: a picture of my own ---
        Image(
            painter = painterResource(id = R.drawable.yankees),
            contentDescription = "New York Yankees Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // --- Class 6: Step 4: Real styling ---
        Text(
            text = "New York Yankees",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Record: 93-68",
            fontSize = 16.sp,
            // --- Lab 6 · Task 1: ... ---
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Class 7: Step 3: the text field
        OutlinedTextField(
            value = newStat,
            onValueChange = { newStat = it },
            label = { Text("Stat name") },
            modifier = Modifier.fillMaxWidth()
        )

        // --- Lab 7 · Task 4: a live character counter ---
        Text(
            text = "${newStat.length} / 40",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

                    //Class 7: Step 4: the button changes the state ---
        Button(onClick = {
            stats.add(newStat)
            newStat = ""
        }) {
            Text("Add Stat")
        }
        // --- Lab7 . Task 1 remove the last item
        Button(onClick = {
            if(stats.isNotEmpty()){
                stats.removeAt(stats.lastIndex)
            }
        }) {
            Text("Remove last")
        }

        // --- Lab 7 · Task 3: clear all ---
        Button(onClick = {
            if(stats.isNotEmpty()) {
                stats.clear()
            }
        }) {
            Text("Clear Stats")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Class 7: Step 2: draw whatever is in the list
        // --- Lab7 . Step 2: singular and plural ---
        Text(
            text = if (stats.size == 1) "1 stat" else "${stats.size} stats",
            fontWeight = FontWeight.Bold
        )


        for (stat in stats) {
            Text(text = stat, fontSize = 18.sp)
        }

        // --- Lab 6 · Task 2: footer ---
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Last updated September 2026",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CampusAppTheme() {
        HomeScreen()
    }
}

// --- Lab 6 · Task 4: dark mode preview ---
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    CampusAppTheme {
        Surface {
            HomeScreen()
        }
    }
}