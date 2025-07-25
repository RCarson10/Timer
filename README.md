# Timer App

A modern Android application built with Jetpack Compose that provides both a digital clock and timer functionality. The app features a clean, Material Design 3 interface and supports exact alarm scheduling for reliable timer notifications.

## Features

- **Digital Clock**: Real-time clock display with current time
- **Timer Functionality**: Set custom timer durations with hours, minutes, and seconds
- **Modern UI**: Built with Jetpack Compose and Material Design 3
- **Alarm Notifications**: Exact alarm scheduling for reliable timer alerts
- **Edge-to-Edge Design**: Modern Android UI with edge-to-edge display

## Screenshots

*Screenshots will be added here once the app is running*

## Prerequisites

Before you begin, ensure you have the following installed:

- **Android Studio** (latest version recommended)
- **Java Development Kit (JDK)** 11 or higher
- **Android SDK** with API level 28+ (Android 9.0+)
- **Gradle** (included with Android Studio)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd Timer
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select "Open an existing Android Studio project"
3. Navigate to the Timer directory and select it
4. Wait for the project to sync and build

### 3. Configure Build Settings

The project is already configured with the following settings:
- **Minimum SDK**: API 28 (Android 9.0)
- **Target SDK**: API 35 (Android 15)
- **Compile SDK**: API 35
- **Kotlin Version**: Latest stable
- **Jetpack Compose**: Enabled

### 4. Build and Run

1. Connect an Android device or start an emulator
2. Click the "Run" button (green play icon) in Android Studio
3. Select your target device
4. Wait for the app to build and install

## Project Structure

```
app/
├── src/main/
│   ├── java/com/personal/timer/
│   │   ├── MainActivity.kt              # Main activity entry point
│   │   ├── TimerAlarmReceiver.kt       # Alarm receiver for notifications
│   │   └── ui/theme/
│   │       ├── Screens/
│   │       │   ├── ClockScreen.kt      # Clock display screen
│   │       │   └── TimerScreen.kt      # Timer functionality screen
│   │       ├── viewModel/
│   │       │   ├── ClockViewModel.kt   # Clock logic
│   │       │   └── TimerViewModel.java # Timer logic
│   │       └── views/
│   │           ├── Clock.kt            # Clock UI component
│   │           └── Timer.kt            # Timer UI component
│   ├── res/                            # Resources (strings, colors, themes)
│   └── AndroidManifest.xml             # App manifest and permissions
└── build.gradle.kts                    # App-level build configuration
```

## Permissions

The app requires the following permissions:

- `SCHEDULE_EXACT_ALARM`: For precise timer notifications (Android 12+)
- `POST_NOTIFICATIONS`: For displaying timer completion notifications
- `WAKE_LOCK`: Optional - to wake device when alarm fires

## Dependencies

The project uses the following main dependencies:

- **Jetpack Compose**: Modern UI toolkit
- **Material Design 3**: Design system
- **AndroidX Core KTX**: Kotlin extensions
- **Lifecycle Runtime**: Lifecycle management
- **Activity Compose**: Compose integration

## Development

### Adding New Features

1. Create new Composable functions in the appropriate `views/` directory
2. Add ViewModels for business logic in `viewModel/` directory
3. Update screens in `Screens/` directory
4. Test on different Android versions and screen sizes

### Building for Release

1. Update version information in `app/build.gradle.kts`
2. Configure signing in Android Studio
3. Build → Generate Signed Bundle/APK
4. Choose APK or Android App Bundle format

## Troubleshooting

### Common Issues

1. **Build Errors**: Ensure you have the latest Android Studio and SDK tools
2. **Permission Issues**: Grant notification permissions when prompted
3. **Alarm Not Working**: Check if battery optimization is disabled for the app

### Getting Help

If you encounter issues:
1. Check the Android Studio logcat for error messages
2. Ensure all dependencies are properly synced
3. Clean and rebuild the project (Build → Clean Project)

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Version History

- **v1.0**: Initial release with clock and timer functionality

---

**Note**: This is a personal project for learning and development purposes. Feel free to modify and extend the functionality as needed.
