@file:OptIn(ExperimentalFoundationApi::class)

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerPickerView(
    initialHours: Int = 0,
    initialMinutes: Int = 0,
    initialSeconds: Int = 0,
    onTimeChanged: (hours: Int, minutes: Int, seconds: Int) -> Unit = { _, _, _ -> }
) {
    val scope = rememberCoroutineScope()

    // State for each wheel
    val hoursState = rememberLazyListState(initialFirstVisibleItemIndex = initialHours)
    val minutesState = rememberLazyListState(initialFirstVisibleItemIndex = initialMinutes)
    val secondsState = rememberLazyListState(initialFirstVisibleItemIndex = initialSeconds)

    // Current selected values
    var selectedHours by remember { mutableStateOf(initialHours) }
    var selectedMinutes by remember { mutableStateOf(initialMinutes) }
    var selectedSeconds by remember { mutableStateOf(initialSeconds) }

    // Update selected values when scrolling stops
    LaunchedEffect(hoursState.isScrollInProgress) {
        if (!hoursState.isScrollInProgress) {
            selectedHours = hoursState.firstVisibleItemIndex
            onTimeChanged(selectedHours, selectedMinutes, selectedSeconds)
        }
    }

    LaunchedEffect(minutesState.isScrollInProgress) {
        if (!minutesState.isScrollInProgress) {
            selectedMinutes = minutesState.firstVisibleItemIndex
            onTimeChanged(selectedHours, selectedMinutes, selectedSeconds)
        }
    }

    LaunchedEffect(secondsState.isScrollInProgress) {
        if (!secondsState.isScrollInProgress) {
            selectedSeconds = secondsState.firstVisibleItemIndex
            onTimeChanged(selectedHours, selectedMinutes, selectedSeconds)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                Color.Black.copy(alpha = 0.05f),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        // Selection highlight overlay
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .align(Alignment.Center)
                .background(
                    Color.Blue.copy(alpha = 0.1f),
                    RoundedCornerShape(8.dp)
                )
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hours wheel
            TimerWheel(
                modifier = Modifier.weight(1f),
                items = (0..23).map { "${it}h" },
                listState = hoursState,
                selectedIndex = selectedHours
            )

            // Minutes wheel
            TimerWheel(
                modifier = Modifier.weight(1f),
                items = (0..59).map { "${it}m" },
                listState = minutesState,
                selectedIndex = selectedMinutes
            )

            // Seconds wheel
            TimerWheel(
                modifier = Modifier.weight(1f),
                items = (0..59).map { "${it}s" },
                listState = secondsState,
                selectedIndex = selectedSeconds
            )
        }
    }
}

@Composable
private fun TimerWheel(
    modifier: Modifier = Modifier,
    items: List<String>,
    listState: LazyListState,
    selectedIndex: Int
) {
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    LazyColumn(
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp)),
        state = listState,
        flingBehavior = snapBehavior,
        contentPadding = PaddingValues(vertical = 80.dp), // Add padding to center the selection
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(items) { index, item ->
            val isSelected = index == selectedIndex
            val alpha = if (isSelected) 1f else 0.3f

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item,
                    fontSize = if (isSelected) 20.sp else 16.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color.Black else Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.alpha(alpha)
                )
            }
        }
    }
}

