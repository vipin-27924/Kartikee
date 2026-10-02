package com.example.kartikee.feature.library

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TherapyCategory(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val durationMinutes: Int,
    val icon: ImageVector,
    val badge: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen() {
    var selectedCategory by remember { mutableStateOf<TherapyCategory?>(null) }

    val categories = listOf(
        TherapyCategory(
            id = "ot",
            title = "Occupational Therapy (OT)",
            subtitle = "Fine motor & hand-eye coordination",
            description = "Interactive touch-and-drag exercises designed to develop hand coordination, spatial precision, and focus.",
            durationMinutes = 15,
            icon = Icons.Default.TouchApp,
            badge = "Recommended"
        ),
        TherapyCategory(
            id = "st",
            title = "Speech Therapy",
            subtitle = "Articulation & expression practice",
            description = "Guided vocalization modules, phoneme repetition, and tone matching exercises with immediate audio feedback.",
            durationMinutes = 10,
            icon = Icons.Default.RecordVoiceOver,
            badge = "Daily Goal"
        ),
        TherapyCategory(
            id = "sensory",
            title = "Sensory Regulation",
            subtitle = "Guided breathing & calming visualizers",
            description = "Soothing audio loops, rhythm breathing timers, and low-stimulation animations to assist with sensory overwhelm.",
            durationMinutes = 5,
            icon = Icons.Default.SelfImprovement,
            badge = "Calming"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7F6))
            .padding(20.dp)
    ) {
        Text(
            text = "Therapy Library",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B4D3E)
        )
        Text(
            text = "Choose a session tailored to your current goal",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(categories) { category ->
                TherapyCategoryCard(category = category, onClick = { selectedCategory = category })
            }
        }
    }

    // Modal Bottom Sheet displaying details when a card is selected
    selectedCategory?.let { category ->
        ModalBottomSheet(
            onDismissRequest = { selectedCategory = null },
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = category.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B4D3E)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${category.durationMinutes} minutes • Guided Voice AI Support",
                    fontSize = 12.sp,
                    color = Color(0xFFE67E22),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = category.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        selectedCategory = null
                        /* Trigger active session view */
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B4D3E)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Start ${category.title} Session")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun TherapyCategoryCard(category: TherapyCategory, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFE0EAE5)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = category.icon,
                    contentDescription = null,
                    tint = Color(0xFF1B4D3E)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = category.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B4D3E)
                )
                Text(
                    text = category.subtitle,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}