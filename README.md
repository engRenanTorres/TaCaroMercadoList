# Shopping List App

An Android shopping list application built with Kotlin and Jetpack Compose.

## Project Structure

```
ShoppingListApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/mercadolist/shoppinglist/app/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── ui/theme/
│   │   │   │       ├── Color.kt
│   │   │   │       ├── Theme.kt
│   │   │   │       └── Type.kt
│   │   │   ├── res/
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   └── mipmap-*/
│   │   │   │       ├── ic_launcher.xml
│   │   │   │       └── ic_launcher_round.xml
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── settings.gradle.kts
└── README.md
```

## Technology Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Clean Architecture principles
- **Navigation**: Jetpack Navigation Compose
- **Data Storage**: Room Database
- **State Management**: ViewModel and StateFlow
- **Networking**: Nearby Connections API (for peer-to-peer sync)
- **Design**: Material Design 3

## Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK API 34
- Gradle 8.4

## Building the Project

1. Open the project in Android Studio
2. Wait for Gradle to sync
3. Build the project using the Build menu or by running:
   ```bash
   ./gradlew build
   ```

## Running the Project

1. Connect an Android device or start an emulator
2. Click the Run button in Android Studio or run:
   ```bash
   ./gradlew installDebug
   ```

## Features

This is the initial project setup. The following features will be implemented in subsequent tasks:

- Shopping list management
- Item operations (add, edit, delete, reorder, check off)
- Price and quantity management
- Filtering and sorting
- User management
- Offline-first operation
- Peer-to-peer sync via Nearby Connections API

## License

This project is for personal use.
