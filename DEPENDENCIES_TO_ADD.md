# Dependencies and Imports to Add

## 1. Add to app/build.gradle.kts

```kotlin
dependencies {
    // TODO: Add these dependencies
    
    // For LocalBroadcastManager
    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.1.0")
    
    // For ViewModels (if not already included)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    
    // For Coroutines (if not already included)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // For StateFlow (if not already included)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
}
```

## 2. Imports to Add

### TimerViewModel.kt
```kotlin
// TODO: Add these imports if not already present
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
```

### MainActivity.kt
```kotlin
// TODO: Add these imports if not already present
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.activity.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.personal.timer.ui.theme.viewModel.TimerViewModel
```

### TimerReceiver.kt
```kotlin
// TODO: Add these imports if not already present
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.personal.timer.ui.theme.viewModel.TimerViewModel
```

## 3. Files to Create/Modify

### Files Created:
1. `TimerReceiver.kt` - Handles alarm completion
2. `DEPENDENCIES_TO_ADD.md` - This file

### Files Modified:
1. `TimerViewModel.kt` - Added AlarmManager implementation
2. `MainActivity.kt` - Added ViewModel integration
3. `TimerScreen.kt` - Added ViewModel parameter
4. `AndroidManifest.xml` - Added TimerReceiver registration

## 4. Implementation Steps

1. **Add Dependencies**: Update your `build.gradle.kts` with the required dependencies
2. **Sync Project**: Sync your project after adding dependencies
3. **Fill in TODOs**: Go through each file and implement the TODO comments
4. **Test**: Build and test the timer functionality

## 5. Key Implementation Points

### TimerViewModel TODOs to Implement:
- [ ] Initialize AlarmManager in `initializeAlarmManager()`
- [ ] Create PendingIntent with proper flags
- [ ] Implement `scheduleAlarm()` with appropriate AlarmManager method
- [ ] Implement `cancelAlarm()` to stop timers
- [ ] Add countdown updates for UI (optional)
- [ ] Handle timer completion state

### TimerReceiver TODOs to Implement:
- [ ] Uncomment LocalBroadcastManager code
- [ ] Add proper notification handling
- [ ] Customize notification appearance
- [ ] Add sound/vibration patterns

### MainActivity TODOs to Implement:
- [ ] Uncomment LocalBroadcastManager registration
- [ ] Add proper error handling
- [ ] Add lifecycle management

### TimerScreen TODOs to Implement:
- [ ] Test timer state display
- [ ] Add proper time formatting
- [ ] Test all button interactions
- [ ] Add visual feedback for timer states 