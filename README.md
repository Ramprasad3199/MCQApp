# 📱 Android MCQ Application


This is a full-featured Android application built using Java in Android Studio. The app supports user login/signup, presents a multiple-choice quiz (MCQ) interface with 10 questions (one per screen), allows answer review, and handles secure submission. It also includes persistent login (auto-login) and logout functionality.

---

## 📸 Screenshots

![1](https://github.com/user-attachments/assets/1f637476-5892-4f50-a87c-00b99a1bca79)
![2](https://github.com/user-attachments/assets/1a5ec3a3-6123-4990-b23f-31c23b0aff49)

---

## 🚀 Features

- 🔐 **Login & Signup**
  - Secure login with local user data storage using `SharedPreferences`
  - Signup flow supports multiple users

- 🧠 **MCQ Quiz Flow**
  - 10 multiple choice questions
  - One question per screen with navigation (`Next` and `Back`)
  - Progress bar to indicate completion
  - Prevents users from retaking the quiz once submitted

- ✅ **Answer Review**
  - Summary screen showing all selected answers
  - Submit button to finalize answers

- 📲 **Auto Login**
  - Remembers logged-in users and skips login screen if already logged in
  - Logs out user completely on pressing the logout button

- ✨ **Smooth Transitions**
  - Fade-in/fade-out animations between quiz screens
  - Stylish custom UI for review answers

---


## 🛠 Tech Stack

- **Language:** Java
- **IDE:** Android Studio
- **Local Storage:** `SharedPreferences`
- **UI Components:** `Activity`, `RadioGroup`, `ListView`, `ProgressBar`, `CustomAdapter`
- **Animations:** XML-based `fade_in` / `fade_out`

---

## 📝 License
This project is open source and free to use. No license applied yet. You can modify it for educational or non-commercial use.

## 👨‍💻 Author
Ram Prasad G

https://github.com/Ramprasad3199



