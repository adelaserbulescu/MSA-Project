# 📜 History Education App
## MSA Project

A Kotlin-based full-stack application designed to manage and visualize information about countries, their media, achievements, and quizzes — all organized within roadmaps and groups.  
Built with **Ktor** on the backend and **Kotlin** for the frontend.

## Quick Links / References

Database Layout: [https://miro.com/app/board/uXjVJunkexA=/?focusWidget=3458764647250338739](https://miro.com/app/board/uXjVJunkexA=/?focusWidget=3458764647250338739)

---

## 🧭 Project Overview 🧭

This project aims to provide an interactive platform to explore countries, their roadmaps, related media, and quizzes.  
Users can view featured media, track achievements, and access country-specific data in a structured and visually appealing way.

---

## ⚙️ Project Requirements ⚙️

- **Kotlin** (Frontend + Backend)
- **Ktor** Framework
- **MySQL** (Locally Hosted)
- **JDK 17+**
- **Gradle** or **Maven** build tool
- **Any IDE** with Kotlin support (IntelliJ IDEA recommended)

---

## 💾 Database Uses 💾

The project uses **MySQL** as its relational database.  
It stores all relevant data about countries, media, user accounts, achievements, and quizzes.

---

## 🧩 Database Structure 🧩

### **Countries**
| Column | Type | Description |
|--------|------|-------------|
| CountryID | VARCHAR (PK) | Unique identifier for each country |
| RoadMapGroupID | VARCHAR (FK) | Links to CountryGroup |
| Name | VARCHAR | Country name |
| DateStarted | DATE | When the country was added |
| DateEnded | DATE | When the country was removed (if applicable) |
| StillExists | BOOLEAN | True if the country still exists |
| FlagImagePath | VARCHAR | Path to country flag image |
| FlagEmoji | VARCHAR | Emoji representation of the flag |
| HexColor | VARCHAR | Associated color (for UI themes) |

---

### **Media**
| Column | Type | Description |
|--------|------|-------------|
| MediaID | VARCHAR (PK) | Unique media identifier |
| CountryID | VARCHAR (FK) | Country related to the media |
| MediaType | ENUM('image','link','route') | Type of media |
| ValuePath | VARCHAR | File path or URL to the media |

---

### **CountryGroup**
| Column | Type | Description |
|--------|------|-------------|
| GroupID | VARCHAR (PK) | Group identifier |
| CountryID | VARCHAR (FK) | Associated country |

---

### **RoadMap**
| Column | Type | Description |
|--------|------|-------------|
| RoadMapID | VARCHAR (PK) | Unique roadmap identifier |
| Name | VARCHAR | Roadmap name |
| DateStarted | DATE | Start date |
| DateEnded | DATE | End date |
| StillExists | BOOLEAN | Indicates if roadmap is active |

---

### **Accounts**
| Column | Type | Description |
|--------|------|-------------|
| AccountID | VARCHAR (PK) | User identifier |
| Username | VARCHAR | User’s name |
| Email | VARCHAR | Contact email |
| Phone | VARCHAR | Contact phone |
| Password | VARCHAR (Hashed) | Securely stored password |
| AchievementID | VARCHAR (FK) | Linked achievement |

---

### **Achievements**
| Column | Type | Description |
|--------|------|-------------|
| AchievementID | VARCHAR (PK) | Unique identifier |
| AchievementName | VARCHAR | Name of the achievement |
| Description | VARCHAR | Details about the achievement |
| ImageLink | VARCHAR | Related image link |

---

### **Quizzes**
| Column | Type | Description |
|--------|------|-------------|
| QuizID | VARCHAR (PK) | Unique quiz ID |
| CountryID | VARCHAR (FK) | Related country |
| CountryGroupID | VARCHAR (FK) | Related group |
| QuizTitle | VARCHAR | Title of the quiz |
| QuizDescription | VARCHAR | Short description |
| ImageLink | VARCHAR | Thumbnail or banner image |
| Score | VARCHAR | Scoring metric |

---

### **QuizQuestions**
| Column | Type | Description |
|--------|------|-------------|
| QuestionID | VARCHAR (PK) | Question identifier |
| QuizID | VARCHAR (FK) | Related quiz |
| QuestionTitle | VARCHAR | Short question heading |
| QuestionBody | VARCHAR | Full question text |

---

## 🧰 Technologies Used 🧰

### **Database**
- 💾 MySQL (Locally Hosted) 💾

### **Back-end**
- 💻 Kotlin  
- ⚡ Ktor Framework

### **Front-end**
- 🖥️ Kotlin (Client-side)

---

## 📄 Activity Pages

| Page | Description |
|------|-------------|
| **Main Page** | Displays the latest updates and highlights |
| **Latest News** | Shows latest additions and announcements |
| **Featured Media** | Highlights notable country media |
| **Roadmap Page** | Shows the timeline and country progress |
| **Specific Country Page** | Detailed view of a country |
| **Media Page** | A page for viewing multiple types of media associated with a country / roadmap |

---
