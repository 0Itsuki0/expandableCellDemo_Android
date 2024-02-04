package com.example.tabledemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tabledemo.ui.theme.TableDemoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TableDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val sectionData1 = SectionData("title1", listOf("1", "2", "3"))
                    val sectionData2 = SectionData("title2", listOf("4", "5", "6"))
                    val sectionList = listOf(sectionData1, sectionData2)
                    TableDemoTheme {
                        Column(
                            modifier = Modifier
                        ) {
                            ExpandableTableView(sectionList)
                        };
                    }

                }
            }
        }
    }
}


@Composable
fun HeaderCell(headerTitle: String, isExpanded: Boolean, onHeaderClicked: () -> Unit) {
    Surface(
        color = androidx.compose.ui.graphics.Color.Blue
    ) {
        Row(modifier = Modifier
            .clickable { onHeaderClicked() }
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Hello ${headerTitle}!",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp, 8.dp))
            if (isExpanded) {
                Text(
                    text = "▼",
                    style = MaterialTheme.typography.headlineMedium,)
            } else {
                Text(
                    text = "▲",
                    style = MaterialTheme.typography.headlineMedium,)
            }
        }
    }
}


@Composable
fun RegularCell(title: String, modifier: Modifier = Modifier) {
    Surface(
        color = androidx.compose.ui.graphics.Color.Red
    ) {
        Text(
            text = "Hello $title!",
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier
                .padding(16.dp, 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun Section(sectionData: SectionData) {
    var isExpanded by rememberSaveable { mutableStateOf(true) }

    Column() {
        HeaderCell(
            headerTitle = sectionData.sectionTitle,
            isExpanded = isExpanded,
            onHeaderClicked = { isExpanded = !isExpanded }
        )
        if (isExpanded) {
            sectionData.items.onEachIndexed { _, item ->
                RegularCell(title = item)
            }
        }
    }
}

@Composable
fun ExpandableTableView(sectionData: List<SectionData>) {
    Column() {
        sectionData.onEachIndexed { _, section ->
            Section(sectionData = section)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    var sectionData1 = SectionData("title1", listOf("1", "2", "3"))
    var sectionData2 = SectionData("title2", listOf("4", "5", "6"))
    var sectionList = listOf(sectionData1, sectionData2)
    TableDemoTheme {
        Column(
            modifier = Modifier
        ) {
            ExpandableTableView(sectionList)
        };
    }
}