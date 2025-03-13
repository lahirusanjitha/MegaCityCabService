<%-- 
    Document   : login
    Created on : Mar 6, 2025, 9:39:45 PM
    Author     : Lahiru Sanjitha
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - MegaCityCabService</title>
    
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 350px;
        }
        .login-container img {
            width: 100px; 
            margin-bottom: 15px;
        }
        .login-container h2 {
            margin-bottom: 15px;
        }
        .input-field {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-btn, .register-btn {
            width: 100%;
            padding: 10px;
            background: #ffcc00;
            border: none;
            color: black;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
        }
        .login-btn:hover, .register-btn:hover {
            background: #ffdb4d;
        }
        .error-msg {
            color: red;
            margin-top: 10px;
        }
        .admin-link {
            display: block;
            margin-top: 10px;
            color: #007BFF;
            text-decoration: none;
        }
        .admin-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <img src="images/logo.png" alt="Taxi Logo">
        <h2>Megacitycab-Login</h2>
        <form id="loginForm">
            <input type="text" id="userName" class="input-field" placeholder="Username" required>
            <input type="password" id="password" class="input-field" placeholder="Password" required>
            <button type="submit" class="login-btn">Login</button>
        </form>
        <button class="register-btn" onclick="window.location.href='register.jsp'">Register</button>
        <a href="adminLogin.jsp" class="admin-link">Admin Login</a>
        <p id="errorMessage" class="error-msg"></p>
    </div>

    <script>
        document.getElementById("loginForm").addEventListener("submit", function(event) {
            event.preventDefault(); 

            let userName = document.getElementById("userName").value;
            let password = document.getElementById("password").value;

            fetch("http://localhost:8080/MegaCityCab/api/customers/login", { 
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ userName: userName, password: password })
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === "success") {
                    sessionStorage.setItem("customerId", data.customerId); 
                    console.log("Customer ID stored:", sessionStorage.getItem("customerId"));
                    window.location.href = "customerDashboard.jsp"; 
                } else {
                    document.getElementById("errorMessage").innerText = "Invalid username or password!";
                }
            })
            .catch(error => {
                document.getElementById("errorMessage").innerText = "Error connecting to server!";
                console.error("Error:", error);
            });
        });
    </script>
</body>
</html>

