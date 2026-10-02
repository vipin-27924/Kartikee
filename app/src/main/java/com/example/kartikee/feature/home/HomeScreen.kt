package com.example.kartikee.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    var selectedEnergy by remember { mutableStateOf("Calm") }
    val energyLevels = listOf("Low", "Calm", "Well", "Energetic")
    val weekDays = listOf(
        DayItem("26", "Mon", false),
        DayItem("27", "Tue", true),
        DayItem("28", "Wed", false),
        DayItem("29", "Thu", false),
        DayItem("30", "Fri", false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7F6))
            .padding(20.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Your plan feels",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "manageable.",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B4D3E)
                )
            }
            IconButton(
                onClick = { /* Audio Guidance TTS trigger */ },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0EAE5))
            ) {
                Icon(
                    imageVector = Icons.Default.VolumeUp,
                    contentDescription = "Read screen aloud",
                    tint = Color(0xFF1B4D3E)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Today's Schedule",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1B4D3E)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(weekDays) { item ->
                CalendarDayCard(item)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Mood & Energy Selector
        Text(
            text = "How is your energy right now?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1B4D3E)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            energyLevels.forEach { level ->
                FilterChip(
                    selected = selectedEnergy == level,
                    onClick = { selectedEnergy = level },
                    label = { Text(level) },
                    modifier = Modifier.weight(1f),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF1B4D3E),
                        selectedLabelColor = Color.White,
                        containerColor = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Upcoming Session Hero Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "UPCOMING SESSION",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE67E22)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Speech Therapy - Articulation Practice",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B4D3E)
                )
                Text(
                    text = "2:00 PM • 15 mins • Voice & Audio",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Launch Live Therapy */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B4D3E)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Start Session")
                }
            }
        }
    }
}

data class DayItem(val date: String, val day: String, val isSelected: Boolean)

@Composable
fun CalendarDayCard(item: DayItem) {
    Box(
        modifier = Modifier
            .width(60.dp)
            .height(75.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (item.isSelected) Color(0xFF1B4D3E) else Color.White)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = item.day,
                fontSize = 12.sp,
                color = if (item.isSelected) Color.White.copy(alpha = 0.8f) else Color.Gray
            )
            Text(
                text = item.date,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (item.isSelected) Color.White else Color(0xFF1B4D3E)
            )
        }
    }
}