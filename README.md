# 💬 Chatter Box – Real-Time Chat Application

**Chatter Box** is a full-stack real-time chat application built using Spring Boot and ReactJS. It allows users to register, authenticate, add friends, and exchange messages instantly in a secure and interactive UI.

---

## 🚀 Tech Stack

### 🔧 Backend
- Java 17
- Spring Boot
- Spring Data JPA
- REST APIs
- Oracle Database

---

## 🔑 Key Features

- 🔐 Secure user registration and login
- 🧑‍🤝‍🧑 Friend request and approval system
- 💬 Real-time messaging between users
- 📃 RESTful API design for user, friend, and message management
- 🗄️ Oracle DB integration for persistent storage
- ⚙️ Modular Spring Boot architecture

---

## 📁 Folder Structure

Chatter-box/
├── backend/ # Spring Boot app

│ ├── controller/

│ ├── service/

│ ├── model/

│ ├── repository/

│ └── ChatterBoxApplication.java

├── frontend/ # React app

│ ├── src/

│ ├── components/

│ ├── pages/

│ └── App.js



---

## ⚙️ Getting Started

### Prerequisites
- Java 17
- Node.js & npm
- Oracle DB
- Maven

---

### 🧪 Backend Setup

```bash
cd backend
# Update DB credentials in application.properties
./mvnw spring-boot:run
```

🌐 API Endpoints (Sample)
Method	Endpoint	Description
POST	/auth/register	Register new user
POST	/auth/login	User login
GET	/users/{id}	Fetch user by ID
POST	/friends/request	Send friend request
GET	/messages/{friend}	Get chat history

🔮 Future Enhancements
✅ JWT-based authentication

🌍 WebSocket-based real-time chat

📱 Mobile responsive UI

🗑️ Chat history deletion

🔔 Notifications


👨‍💻 Author
Built with ❤️ by Bunny Bathula

📜 License
This project is licensed under the MIT License.
