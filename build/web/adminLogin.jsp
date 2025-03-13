<!--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
--><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Login - MegaCityCabService</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .admin-login-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 350px;
        }
        .admin-login-container img {
            width: 100px;
            margin-bottom: 15px;
        }
        .admin-login-container h2 {
            margin-bottom: 15px;
        }
        .input-field {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-btn {
            width: 100%;
            padding: 10px;
            background: #ffcc00;
            border: none;
            color: black;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }
        .login-btn:hover {
            background: #ffdb4d;
        }
        .error-msg {
            color: red;
            margin-top: 10px;
        }
        .user-link {
            display: block;
            margin-top: 10px;
            color: #007BFF;
            text-decoration: none;
        }
        .user-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="admin-login-container">
<!--        <img src="images/admin_logo.png" alt="Admin Logo">-->
        <h2>Admin Login</h2>
        <form id="adminLoginForm">
            <input type="text" id="adminUserName" class="input-field" placeholder="Username" required>
            <input type="password" id="adminPassword" class="input-field" placeholder="Password" required>
            <button type="submit" class="login-btn">Login</button>
        </form>
        <a href="login.jsp" class="user-link">User Login</a>
        <p id="errorMessage" class="error-msg"></p>
    </div>

    <script>
        document.getElementById("adminLoginForm").addEventListener("submit", function(event) {
            event.preventDefault();

            let adminUserName = document.getElementById("adminUserName").value;
            let adminPassword = document.getElementById("adminPassword").value;

            fetch("http://localhost:8080/MegaCityCab/api/admin/login", { 
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ userName: adminUserName, password: adminPassword })
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === "success") {
                    sessionStorage.setItem("adminId", data.adminId); 
                    window.location.href = "adminDashboard.jsp"; 
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
