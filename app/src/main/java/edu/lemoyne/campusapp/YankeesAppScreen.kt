package edu.lemoyne.campusapp

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.lemoyne.campusapp.ui.theme.CampusAppTheme


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

// --- Class 9: Step 2: one owner for the data ---
@Composable
fun YankeeAppScreen(modifier: Modifier = Modifier) {
    // --- Class 7: Step 2: the list lives in state
    val stats = remember {
        mutableStateListOf(
            "Best Player: Cam Schlittler",
            "Current Matchup: Boston Red Sox",
            "Playoffs Clinched",
            "Last 10: 6-4"
        )
    }

    // --- Class 9: Step 4: which screen is showing is just state
    var currentScreen by rememberSaveable { mutableStateOf("home")}


    when (currentScreen) {
        "home" -> HomeScreen(
            stats = stats,
            onAddStat = { stats.add(it) },
            onSeeAll = { currentScreen = "list" },
            // --- Lab 9: Task 2:
            onAbout = { currentScreen = "about"}
        )

        "list" -> ListScreen(
            stats = stats,
            onBack = {currentScreen = "home"},
            modifier = modifier
        )

        // --- Lab 9: Task 2:
        "about" -> AboutScreen(
            onBack = {currentScreen = "home"},
            modifier = modifier
        )

    }
}

// --- Class 6: Step1: my own screen ---

@Composable
fun HomeScreen(
    stats: MutableList<String>,
    onAddStat: (String) -> Unit,
    onSeeAll: () -> Unit,
    // --- Lab 9: Task 2:
    onAbout: () -> Unit,
    modifier: Modifier = Modifier
) {

    // --- Class 7: Step 3: What typed lives in state
    var newStat by remember { mutableStateOf("") }
    // Class 8: Step 2: the error message lives in a state too
    var error by remember { mutableStateOf<String?>(null) }
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
            // --- Class 8: Step 3: The field itself pushes back ---

            onValueChange = {
                newStat = it.take(MAX_NAME_LENGTH)
                error = null
            },
            label = { Text("Stat name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = error != null

        )

        error?.let { message ->
            Text(
                text = message,
                color = Color.Red
            )
        }

        // --- Lab 7 · Task 4: a live character counter ---
        Text(
            text = "${newStat.length} / $MAX_NAME_LENGTH",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        //Class 7: Step 4: the button changes the state ---
        Button(
            onClick = {
                // --- Class 8: Step 3: check before you add
                val problem = validateStatName(input = newStat, existingStats = stats)
                if (problem == null) {
                    // --- Class 9: Step 2: Ask the owner to add it
                    onAddStat(newStat)
                    newStat = ""
                } else {
                    error = problem
                }

            },
            // --- Class 8: Step 4: the sign on the door, not the lock ---
            enabled = newStat.isNotBlank()
        ) {
            Text("Add Stat")
        }
        // --- Lab7 . Task 1 remove the last item



        Spacer(modifier = Modifier.height(8.dp))

        // Class 7: Step 2: draw whatever is in the list
        // --- Lab7 . Step 2: singular and plural ---
        Text(
            text = if (stats.size == 1) "1 stat" else "${stats.size} stats",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Class 9: Step 5: a way to the second screen ---
        Button(
            onClick = onSeeAll
        ){
            Text(text = "See all stats")
        }
        // --- Lab 9: Task 2:
        Button(
            onClick = onAbout
        ){
            Text(text = "About")
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

// --- Class 9: Step3: the second screen
@Composable
fun ListScreen(
    stats: List<String>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Class 9: Step 6: the phones back button goes back too
    BackHandler { onBack() }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ){
        TextButton(onClick = onBack) {
          Text ("Back")
        }
        Text(
            text = "All Stats",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        // --- Lab 9 · Task 1: count on the list screen ---.
        Text(
            text = if (stats.size == 1) "1 stat" else "${stats.size} stats",
            fontWeight = FontWeight.Medium,
        )

        Spacer(modifier = Modifier.height(16.dp))

        for(stat in stats) {
            Text(text = stat, fontSize = 18.sp)
        }
    }

}

// --- Lab 9 · Task 2: a third screen ---
@Composable
fun AboutScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("Back")
        }
        Text(
            text = "About",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "The stats list keeps track of stats for the New York Yankees.")
        Text(text = "Built for CSC 441 by Justin James.")
    }
}

//
const val MAX_NAME_LENGTH = 30

// --- Class 8: Step 1: one rule book for stat names ---
fun validateStatName(input: String, existingStats: List<String>): String? {
    val name = input.trim()
    return when {
        name.isEmpty() -> "Enter a stat name"
        // --- Lab 8 · Task 1: minimum length ---
        name.length < 3 -> "Too short — at least 3 characters"
        name.length > MAX_NAME_LENGTH -> "Keep it to $MAX_NAME_LENGTH characters or fewer"
        existingStats.any { it.equals(name, ignoreCase = true) } -> "$name is already on the list"
        // --- Lab 8 · Task 2: my own rule ---
        name.all { it.isDigit() } -> "A name can't be only numbers"
        else -> null
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    CampusAppTheme() {
        HomeScreen(
            stats = remember {
                mutableStateListOf(
                    "Best Player: Cam Schlittler",
                    "Current Matchup: Boston Red Sox",
                    "Playoffs Clinched",
                    "Last 10: 6-4"
                )
            },
            onAddStat = {},
            onSeeAll = {},
            // --- Lab 9: Task 2:
            onAbout = {}
        )
    }
}

// --- Lab 6 · Task 4: dark mode preview ---
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    CampusAppTheme {
        Surface {
            HomeScreen(
                stats = remember {
                    mutableStateListOf(
                        "Best Player: Cam Schlittler",
                        "Current Matchup: Boston Red Sox",
                        "Playoffs Clinched",
                        "Last 10: 6-4"
                    )
                },
                onAddStat = {},
                onSeeAll = {},
                // --- Lab 9: Task 2:
                onAbout = {}
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    CampusAppTheme {
        ListScreen(
            stats = remember {
                mutableStateListOf(
                    "Best Player: Cam Schlittler",
                    "Current Matchup: Boston Red Sox",
                    "Playoffs Clinched",
                    "Last 10: 6-4"
                )
            },
            onBack = {}
        )
    }
}
