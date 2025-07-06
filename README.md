# 📚 Ignite Book App - Gutenberg Book Browser

An Android app built using **Jetpack Compose** that allows users to browse and open books from the [Project Gutenberg](https://www.gutenberg.org/) public domain library. (Screenshot Attached Below)

## 🔧 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **MVVM Architecture**
- **Retrofit** – API consumption
- **Gson** – JSON deserialization
- **Coil** – Image loading
- **hilt** – Dependency Injection
- **Navigation Compose** – App navigation
  

## 📦 API

- Data is fetched from [Gutenberg.org API](https://gutendex.com/)
- Http - url whitelisted to browse in andorid app
- Only books with **cover images** are shown to user in list book list
- Book details include title, author(s), subjects, formats

## 🚀 Features

### ✅ Book Listing
- Genre list on start of app to choose from
- List fof book fetched according to slected genre
- Lists books with cover images
- Displays title, author
- Supports infinite scroll for book list
- Search feature implemented by title or author - as soon as user starts tryping
- Custom Logo
- Custom Font

### 🔍 Book Details
- Tapping a book tries to open the most **viewable format** link in the browser.
- Preffered Web Browser opens up to read the clicked book

### 🔗 Format Preference (with Fallback)
When a book is tapped, the app will:
1. Prefer `text/html` (e.g., web-viewable book)
2. If unavailable, fallback to `application/pdf`
3. If unavailable, fallback to `text/plain`
4. If none of the above available → show alert:  
   **"No viewable version available"**


### 📷 Cover Images
- Loaded using Coil from the `formats["image/jpeg"]` URL

---

### Custom Fonts Added 
--- Montserrat, Regular
--- Montserrat, bold
--- Montserrat, Semi-Bold 


## ⚠️ Notes
- The app respects public domain usage of content.
- API calls are limited to those books which provide usable and viewable content.

---

## ✨ Screenshots (Ignite Book App)
Include UI screenshots here once ready.
<div style="display: flex; gap: 10px;">
  <img src="https://github.com/user-attachments/assets/95bc50a0-5a2a-42bb-845b-43ab2674fa7b" alt="Glassified UI 1" width="250"/>
  <img src="https://github.com/user-attachments/assets/9b0359ab-1209-413e-a267-969ea51e33b9" alt="Glassified UI 2" width="250"/>
     
</div>


---

## 👨‍💻 Developed by  
Ashish – Android Developer  
[LinkedIn – ashishkr2534](https://www.linkedin.com/in/ashishkr2534)  
Tech Focus: Jetpack Compose, Kotlin, SwiftUI, System Architecture
