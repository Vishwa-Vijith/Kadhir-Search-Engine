# 🚀 Kadhir Search Engine

🔍 **Real-time Search Engine for Technology Updates**

Kadhir Search Engine is a real-time search engine that dynamically fetches the latest technology news, research papers, and trends from various sources such as GDELT, arXiv, Semantic Scholar, Hacker News, GitHub, and Product Hunt.


🌟 Why the Name "Kadhir"?
The name "Kadhir" is inspired by the Tamil word "கதிர்" (Kadhir), which means "ray of light" or "brilliance." Just like how a ray of light illuminates the dark, Kadhir Search Engine aims to bring clarity by delivering precise, real-time knowledge from the vast world of technology. It symbolizes enlightenment, innovation, and the pursuit of knowledge.

---

## 📌 Features

- ✅ **Real-time Crawling** – Fetches fresh content on demand.
- ✅ **Query Categorization** – Classifies queries into tech news, research papers, or trending repositories.
- ✅ **Multi-source Integration** – Uses APIs like GDELT, NewsData.io, arXiv, Semantic Scholar, GitHub, and more.
- ✅ **Search Ranking & Filtering** – Provides relevant, recent, and popular results.
- ✅ **Fast & Scalable Backend** – Built with Java & Spring Boot.
- ✅ **Intuitive Frontend** – Developed using React.js.

---

## 🏗️ Tech Stack

### 🔹 Backend (Spring Boot)
- Java 17+
- Spring Boot (Web, JPA, Cache, Lombok, Swagger)
- OkHttp/WebClient for API calls
- Redis for caching
- JUnit & Mockito for testing

### 🔹 Frontend (React.js)
- React 18 with Hooks
- React Router for navigation
- Fetch API for backend communication

### 🔹 APIs & Data Sources
- **GDELT & NewsData.io** – Fetch latest tech news
- **arXiv & Semantic Scholar** – Get latest research papers
- **GitHub API & Product Hunt** – Find trending repositories and projects
- **Hacker News API (YCombinator)** – Tech discussions and insights

---

## 🛠️ Setup & Installation

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/Vishwa-Vijith/Kadhir-Search-Engine.git
cd Kadhir-Search-Engine
```

### 2️⃣ Backend Setup
```sh
cd back-end
./mvnw spring-boot:run  # Runs the backend on port 8080
```

### 3️⃣ Frontend Setup
```sh
cd ../front-end
npm install  # Install dependencies
npm start    # Runs the frontend on port 3000
```

---

## 📡 API Endpoints
| Endpoint               | Method | Description                        |
|------------------------|--------|------------------------------------|
| `/api/search?q=QUERY`  | GET    | Search for latest tech updates     |

---

## 👨‍💻 Contribution Guide

1. **Fork the repository**
2. **Create a new branch**: `git checkout -b feature-name`
3. **Commit changes**: `git commit -m "Added new feature"`
4. **Push to GitHub**: `git push origin feature-name`
5. **Open a Pull Request**

---

## ⚡ Future Enhancements

🚀 **Machine Learning Integration** – Improve search ranking with AI.  
🌎 **Multi-language Support** – Search results in multiple languages.  
📊 **Analytics Dashboard** – Track popular searches and trends.  

---

## 📜 License

This project is open-source under the MIT License.

---

## ✨ Author

👤 **Vishwa Vijith**  
📧 Contact: VishwaVijith@outlook.com  
🔗 GitHub: [Vishwa-Vijith](https://github.com/Vishwa-Vijith)

