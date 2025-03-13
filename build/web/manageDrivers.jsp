<%-- 
    Document   : manageDrivers
    Created on : Mar 7, 2025, 11:35:22 AM
    Author     : Lahiru Sanjitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Vehicles</title>
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
            
            <%@ include file="includes/sidebar.jsp" %> <%-- Sidebar included --%>

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <h2 class="mt-4">Manage Drivers</h2>
                
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addDriverModal">Add Driver</button>

                <table class="table table-striped mt-3">
                    <thead class="table-dark">
                        <tr>
                            <th>Driver ID</th>
                            <th>Name</th>
                            <th>Liecence Number</th>
                            <th>Phone</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="driverTable">
                    </tbody>
                </table>
            </main>
        </div>
    </div>

    <div class="modal fade" id="addDriverModal" tabindex="-1" aria-labelledby="addDriverLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addDriverLabel">Add Vehicle</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Name</label>
                        <input type="text" id="driverName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">License Number</label>
                        <input type="text" id="licenseNumber" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">phone</label>
                        <input type="text" id="phone" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Status</label>
                        <select id="driverStatus" class="form-select">
                            <option value="Available">Available</option>
                            <option value="Unavailable">On Duty</option>
                            <option value="Maintenance">Inactive</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addDriver()">Save Driver</button>
                </div>
            </div>
        </div>
    </div>
            
   <div class="modal fade" id="editDriverModal" tabindex="-1" aria-labelledby="editDriverLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editDriverLabel">Edit Driver Status</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="editDriverId">
                    <div class="mb-3">
                        <label class="form-label">Status</label>
                        <select id="editStatus" class="form-select">
                            <option value="Available">Available</option>
                            <option value="On Duty">On Duty</option>
                            <option value="Inactive">Inactive</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="updateDriver()">Save Changes</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/manageDrivers.js"></script>
</body>
</html>
