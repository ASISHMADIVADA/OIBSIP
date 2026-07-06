# 🖥️ Online Examination System (Java Swing)

A simple desktop-based Online Examination System built using Java Swing.  
It allows users to log in, attempt MCQ-based exams with a timer, and view results instantly.

---

## 📌 Features

- 🔐 User Login Authentication  
- 📝 Multiple Choice Questions (MCQ)  
- ⏱️ Countdown Timer with Auto Submit  
- ⏭️ Next Question Navigation  
- 📊 Automatic Score Calculation  
- 🚪 Logout Option from Result Screen  
- ❌ Exit Confirmation Dialog (Session Safety)  

---

## 🛠️ Tech Stack

- Java  
- Swing (GUI)  
- AWT Event Handling  
- Object-Oriented Programming (OOP)

---

## 📂 Project Structure

src/
│
├── Main.java
├── LoginFrame.java
├── QuizFrame.java
├── Question.java
├── QuestionBank.java
├── TimerManager.java
└── User.java

---

## 🔑 Default Login Credentials

Username: admin  
Password: 1234  

---

## ▶️ How to Run

1. Open terminal in project folder  
   cd src  

2. Compile all files  
   javac *.java  

3. Run the program  
   java Main  

---

## 🧠 Working Flow

1. User logs in  
2. Quiz window opens  
3. Timer starts automatically  
4. User selects answers  
5. Clicks Next for navigation  
6. Score is calculated  
7. Result screen is shown  
8. Logout returns to login screen  

---

## ⏱️ Timer System

- Countdown timer runs during exam  
- If time becomes 0:
  - Exam auto-submits  
  - Result screen is shown  

---

## ❌ Exit Handling

If user clicks close button:

Are you sure you want to quit the exam?  
(Yes / No)

---

## 🚀 Future Improvements

- MySQL database integration  
- Admin dashboard  
- Random question generator  
- Result history storage  
- Improved UI design  

---

## 👨‍💻 Author

Java Swing Project – Online Examination System