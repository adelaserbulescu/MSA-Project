# 📜 History Education App
## MSA Project

### Team Gorjenii

<img width="320" height="180" alt="logoteam2" src="https://github.com/user-attachments/assets/6f8e2d49-6d32-4385-b9b9-ae1a2628aa2c" />

### Team Members

Lintes Claudiu Constantin <br>
Șerbulescu Adela-Elena

(<WIP 🏗️ WIP>)

## Quick Links / References

Database Layout: [https://miro.com/app/board/uXjVJunkexA=/?focusWidget=3458764647250338739](https://miro.com/app/board/uXjVJunkexA=/?focusWidget=3458764647250338739)

---

## 🧭 Project Overview

(<WIP 🏗️ WIP>)

---

## ⚙️ Project Requirements

(<WIP 🏗️ WIP>)

---

## 💾 Database Uses

(<WIP 🏗️ WIP>)

---

## 🧩 Database Structure

#### >> 📑 Main-App

### **Countries**
| Column        | Type         | Description                        | Attributes                                |
| ------------- | ------------ | ---------------------------------- | ----------------------------------------- |
| countryID     | INTEGER      | Unique identifier for each country | `.autoIncrement()`, `PrimaryKey`          |
| groupID       | INTEGER      | Links to CountryGroup table        | `.references(CountryGroupEntity.groupID)` |
| countryName   | VARCHAR(256) | Country name                       | `.uniqueIndex()`                          |
| dateStarted   | DATE         | When the country was founded       | `.nullable()`                             |
| dateEnded     | DATE         | When the country was dissolved     | `.nullable()`                             |
| stillExists   | BOOLEAN      | True if the country still exists   | —                                         |
| flagImagePath | VARCHAR(512) | Path to country flag image         | `.nullable()`                             |
| hexColor      | VARCHAR(16)  | Associated color (for UI themes)   | `.nullable()`                             |

---

### **CountryGroups**
| Column           | Type            | Description                              | Attributes                       |
| ---------------- | --------------- | ---------------------------------------- | -------------------------------- |
| groupID          | INTEGER         | Unique identifier for each country group | `.autoIncrement()`, `PrimaryKey` |
| groupName        | VARCHAR(256)    | Name of the group                        | —                                |
| groupDescription | VARCHAR(1024)   | Optional description                     | `.nullable()`                    |
| groupType        | ENUM(GroupType) | Type of group                            | `.default(GroupType.OTHER)`      |
| dateStarted      | DATE            | Start date of group                      | `.nullable()`                    |
| dateEnded        | DATE            | End date of group                        | `.nullable()`                    |
| stillExists      | BOOLEAN         | True if group still exists               | —                                |

---

### **HtmlContents**
| Column               | Type                  | Description                                       | Attributes                                   |
| -------------------- | --------------------- | ------------------------------------------------- | -------------------------------------------- |
| htmlContentID        | INTEGER               | Unique ID for HTML content                        | `.autoIncrement()`, `PrimaryKey`             |
| countryID            | INTEGER               | Country reference                                 | `.references(CountryEntity.countryID)`       |
| contentHtml          | TEXT                  | HTML content                                      | —                                            |
| contentType          | ENUM(HtmlContentType) | Type of content                                   | `.default(HtmlContentType.OTHER)`            |
| version              | INTEGER               | Version number                                    | `.default(1)`                                |
| pageIndex            | INTEGER               | Page index                                        | `.default(0)`                                |
| pageSource           | VARCHAR(256)          | Optional page source                              | `.nullable()`                                |
| dateAdded            | DATE                  | When the content was added                        | —                                            |
| uniqueVersionPerPage | —                     | Enforces uniqueness of version per country & page | `uniqueIndex(countryID, pageIndex, version)` |

---

### **Medias**
| Column    | Type            | Description           | Attributes                             |
| --------- | --------------- | --------------------- | -------------------------------------- |
| mediaID   | INTEGER         | Unique media entry ID | `.autoIncrement()`, `PrimaryKey`       |
| countryID | INTEGER         | Country reference     | `.references(CountryEntity.countryID)` |
| mediaType | ENUM(MediaType) | Type of media         | `.default(MediaType.OTHER)`            |
| mediaPath | VARCHAR(512)    | Path or URL of media  | —                                      |

---

### **Enum Used**
| Enum            | Values                                                 | Description                           |
| --------------- | ------------------------------------------------------ | ------------------------------------- |
| CountryType     | —                                                      | Currently unused, reserved for future |
| GroupType       | ALLIANCE, AREA, ROADMAP, OTHER, CUSTOM                 | Type of country group                 |
| HtmlContentType | MAIN, GENERAL, HISTORY, CULTURE, SCIENCE, EVENT, OTHER | Type of HTML content for country      |
| MediaType       | IMAGE, PAGE, VIDEO, ROUTE, OTHER                       | Type of media (image, video, etc.)    |

---

#### >> 🔒 Accounts & Authentification

## Account

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

#### >> 📄 Quizess

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

## 🧰 Technologies Used

### **Database**
- 💾 MySQL (Locally Hosted)

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
