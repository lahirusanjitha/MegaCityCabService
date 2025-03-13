<%-- 
    Document   : register
    Created on : Mar 7, 2025, 10:37:13 PM
    Author     : Lahiru Sanjitha
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register - MegaCityCabService</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 350px;
        }
        .register-container img {
            width: 100px; 
            margin-bottom: 15px;
        }
        .register-container h2 {
            margin-bottom: 15px;
        }
        .input-field {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .register-btn {
            width: 100%;
            padding: 10px;
            background: #ffcc00;
            border: none;
            color: black;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }
        .register-btn:hover {
            background: #ffdb4d;
        }
        .error-msg {
            color: red;
            margin-top: 10px;
        }
        .login-link {
            display: block;
            margin-top: 10px;
            color: #007BFF;
            text-decoration: none;
        }
        .login-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="register-container">
<!--        <img src="images/logo.png" alt="Taxi Logo">-->
        <h2>Megacitycab - Register</h2>
        <form id="registerForm">
            <input type="text" id="fullName" class="input-field" placeholder="Full Name" required>
            <input type="text" id="userName" class="input-field" placeholder="Username" required>
            <input type="text" id="address" class="input-field" placeholder="Address" required>
            <input type="text" id="nic" class="input-field" placeholder="NIC" required>
            <input type="text" id="tel" class="input-field" placeholder="tel" required>
            <input type="password" id="password" class="input-field" placeholder="Password" required>
            <button type="submit" class="register-btn">Register</button>
        </form>
        <a href="login.jsp" class="login-link">Already have an account? Login here</a>
        <p id="errorMessage" class="error-msg"></p>
    </div>

    <script>
        document.getElementById("registerForm").addEventListener("submit", function(event) {
            event.preventDefault();

            let userName = document.getElementById("userName").value;
            let password = document.getElementById("password").value;
            let address = document.getElementById("address").value;
            let fullName = document.getElementById("fullName").value;
            let tel = document.getElementById("tel").value;
            let nic = document.getElementById("nic").value;
 

            fetch("http://localhost:8080/MegaCityCab/api/customers/register", { 
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({  name:fullName, userName: userName, address: address, nic: nic, tel:tel, password: password})
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === "success") {
                    window.location.href = "login.jsp"; 
                } else {
                    document.getElementById("errorMessage").innerText = "Error during registration!";
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
