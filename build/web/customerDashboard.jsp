<%-- 
    Document   : customerDashboard
    Created on : Mar 6, 2025, 10:55:59 PM
    Author     : Lahiru Sanjitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <style>
        body {
            background-color: #f8f9fa;
        }

        .navbar {
            padding: 15px;
        }

        .btn-group-custom {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 20px;
        }

        .btn-custom {
            padding: 12px 20px;
            border-radius: 10px;
            font-weight: 600;
            transition: all 0.3s ease-in-out;
        }

        .btn-custom:hover {
            transform: scale(1.05);
            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
        }

        .card {
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        .card-header {
            font-size: 18px;
            font-weight: bold;
        }

        .modal-content {
            border-radius: 10px;
        }

        .table th {
            background-color: #343a40 !important;
            color: white;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand fw-bold" href="#">Mega City Cab</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link btn btn-danger text-white px-3 rounded-3" href="#" id="logoutBtn">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h2 class="text-center fw-bold text-dark">Welcome to Customer Dashboard</h2>

    <div class="btn-group-custom">
        <button class="btn btn-primary btn-custom" id="vehicleModal" data-bs-toggle="modal" data-bs-target="#vehiclesModal">
            <i class="bi bi-car-front"></i> View Available Vehicles
        </button>
        <button class="btn btn-warning btn-custom text-white" id="viewBookingBtn" data-bs-toggle="modal" data-bs-target="#bookingViewModal">
            <i class="bi bi-journal-check"></i> View My Bookings
        </button>
    </div>

    <div class="card mt-4">
        <div class="card-header bg-primary text-white text-center">Book a Trip</div>
        <div class="card-body">
            <form id="bookingForm">
                <div class="mb-3">
                    <label for="pickupLocation" class="form-label">Pickup Location</label>
                    <input type="text" id="pickupLocation" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="dropLocation" class="form-label">Drop Location</label>
                    <input type="text" id="dropLocation" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="bookingDate" class="form-label">Booking Date</label>
                    <input type="datetime-local" id="bookingDate" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="vehicleSelect" class="form-label">Select Vehicle</label>
                    <select id="vehicleSelect" class="form-select" required></select>
                </div>
                <div class="mb-3">
                    <label for="driverSelect" class="form-label">Driver</label>
                    <input type="text" id="driverSelect" class="form-control" readonly>
                </div>
                <button type="submit" class="btn btn-success w-100 btn-custom">Confirm Booking</button>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="vehiclesModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Available Vehicles</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Vehicle ID</th>
                            <th>Model</th>
                            <th>Type</th>
                            <th>Plate Number</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody id="vehiclesTable"></tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="bookingViewModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">My Bookings Status</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Pickup</th>
                            <th>Dropoff</th>
                            <th>Booking Date</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody id="bookingTableBody"></tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="scripts/customerDashboard.js"></script>

</body>
</html>
