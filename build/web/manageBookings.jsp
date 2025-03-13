<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Bookings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-dark bg-dark p-3">
        <a class="navbar-brand" href="adminDashboard.jsp">Admin Panel</a>
    </nav>
    <div class="container-fluid">
        <div class="row">
            
            <%@ include file="includes/sidebar.jsp" %> <%-- Sidebar included here --%>
            
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <h2 class="mt-4">Manage Bookings</h2>
                <table class="table table-striped mt-3">
                    <thead class="table-dark">
                        <tr>
                            <th>Booking ID</th>
                            <th>Customer</th>
                            <th>Vehicle</th>
                            <th>Pickup</th>
                            <th>Dropoff</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="bookingsTable">
                    </tbody>
                </table>
            </main>
        </div>
    </div>

    <div class="modal fade" id="editBookingModal" tabindex="-1" aria-labelledby="editBookingLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editBookingLabel">Edit Booking</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="editBookingId">
                    <div class="mb-3">
                        <label class="form-label">Status</label>
                        <select id="editStatus" class="form-select">
                            <option value="Confirmed">Confirmed</option>
                            <option value="Completed">Completed</option>
                            <option value="Cancelled">Cancelled</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="updateBooking()">Save Changes</button>
                </div>
            </div>
        </div>
    </div>
            
    <div class="modal fade" id="addBillModal" tabindex="-1" aria-labelledby="addBillLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addBillLabel">Calculate Bill</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <input type="hidden" id="bookingId" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Base Fare</label>
                        <input type="text" id="baseFare" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Distance KM</label>
                        <input type="text" id="distance" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">RatePer KM</label>
                        <input type="text" id="ratePerKm" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Duration</label>
                        <input type="text" id="duration" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">RatePer Minute</label>
                        <input type="text" id="ratePerMinute" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Additional Charges</label>
                        <input type="text" id="additionalCharges" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Tax Rate</label>
                        <input type="text" id="taxRate" class="form-control" required>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="createBill()">Create Bill</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/manageBookings.js"></script>
</body>
</html>