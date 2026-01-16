 NewsApp – MVVM Architecture (Android)
A demo Android News application built using MVVM architecture, consuming data from the NewsAPI.
The app allows users to explore the latest news through multiple filters like top headlines, countries, languages, and search queries.
🔗 GitHub Repository:
https://github.com/jalagama/NewsApp-MVVM-Architecture

📱 Features
Top Headlines
View trending and breaking news headlines

News by Country

Browse news articles based on selected countries


News by Language

Filter news articles by language

Search News

Search news articles using custom search queries


🧭 App Navigation Flow
Home Screen
 ├── Top Headlines
 ├── Countries → News based on selected country
 ├── Languages → News based on selected language
 └── Search → News based on search query


🏗️ Architecture
This app follows MVVM (Model–View–ViewModel) architecture for better separation of concerns and testability.
Layers Overview
Model

Data models and API response objects

View
Activities / Fragments responsible only for UI rendering
ViewModel
Handles UI logic and exposes observable data
Repository
Acts as a single source of truth for data
Data Source
Remote API calls using NewsAPI

🔌 API Used


NewsAPI


Official Website: https://newsapi.org/


Provides news articles from multiple sources worldwide


⚠️ You must create your own API key to run this app.

🛠️ Tech Stack


Language: Kotlin


Architecture: MVVM


Networking: Retrofit


Asynchronous Handling: Coroutines / LiveData


UI: XML Layouts


Dependency Management: Gradle



🚀 Getting Started
Prerequisites


Android Studio (latest stable version)


Minimum SDK as defined in the project


NewsAPI account & API key


Setup Instructions


Clone the repository:
git clone https://github.com/jalagama/NewsApp-MVVM-Architecture.git



Open the project in Android Studio


Add your NewsAPI key:


Create a local.properties entry or constants file


NEWS_API_KEY=your_api_key_here



Build and run the app on an emulator or physical device



📂 Project Structure (High Level)
com.example.newsapp
 ├── data
 │   ├── api
 │   ├── model
 │   └── repository
 ├── ui
 │   ├── home
 │   ├── headlines
 │   ├── countries
 │   ├── languages
 │   └── search
 └── viewmodel


✅ Highlights


Clean separation of concerns


Scalable architecture


Easy to extend with new filters or data sources


Beginner-friendly yet production-inspired structure



🔮 Future Improvements


Pagination support


Offline caching (Room)


Error & empty state handling


Unit tests for ViewModel & Repository


Jetpack Compose migration


Dark mode support



📜 License
This project is created for learning and demonstration purposes.
