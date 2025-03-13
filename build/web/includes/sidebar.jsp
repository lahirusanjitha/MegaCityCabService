<%-- 
    Document   : sidebar
    Created on : Mar 7, 2025, 9:09:54 AM
    Author     : Lahiru Sanjitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="col-md-2 d-none d-md-block bg-dark sidebar vh-100">
    <div class="position-sticky">
        <ul class="nav flex-column p-3">
            <li class="nav-item">
                <a class="nav-link text-white active" href="adminDashboard.jsp">
                    <i class="fas fa-tachometer-alt me-2"></i> Dashboard
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="manageBookings.jsp">
                    <i class="fas fa-calendar-check me-2"></i> Manage Bookings
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="manageVehicles.jsp">
                    <i class="fas fa-car me-2"></i> Manage Vehicles
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="manageDrivers.jsp">
                    <i class="fas fa-user-tie me-2"></i> Manage Drivers
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="manageCustomers.jsp">
                    <i class="fas fa-users me-2"></i> Manage Customers
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-white" href="manageBill.jsp">
                    <i class="fas fa-file-invoice-dollar me-2"></i> Manage Bill
                </a>
            </li>
            <li class="nav-item mt-3">
                <a class="nav-link text-danger fw-bold" id="logoutBtn">
                    <i class="fas fa-sign-out-alt me-2"></i> Logout
                </a>
            </li>
        </ul>
    </div>
</nav>

<style>
    .sidebar {
        background: #212529; 
        color: white;
        height: 100vh; 
    }
    .nav-link {
        padding: 12px;
        border-radius: 5px;
        transition: all 0.3s ease-in-out;
    }
    .nav-link:hover {
        background: rgba(255, 255, 255, 0.1);
        transform: translateX(5px);
    }
    .nav-link.active {
        background: rgba(255, 255, 255, 0.2);
        font-weight: bold;
    }
</style>
<script>
    $("#logoutBtn").click(function () {
        sessionStorage.clear();
        window.location.href = "login.jsp";
    });    
</script>