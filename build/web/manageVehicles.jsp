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
            
            <%@ include file="includes/sidebar.jsp" %> 

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <h2 class="mt-4">Manage Vehicles</h2>
                
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addVehicleModal">Add Vehicle</button>

                <table class="table table-striped mt-3">
                    <thead class="table-dark">
                        <tr>
                            <th>Vehicle ID</th>
                            <th>Model</th>
                            <th>Type</th>
                            <th>Plate Number</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="vehiclesTable">
                        <!-- Data will be loaded dynamically -->
                    </tbody>
                </table>
            </main>
        </div>
    </div>

    <!-- Add Vehicle Modal -->
    <div class="modal fade" id="addVehicleModal" tabindex="-1" aria-labelledby="addVehicleLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addVehicleLabel">Add Vehicle</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Model</label>
                        <input type="text" id="vehicleModel" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Type</label>
                        <input type="text" id="vehicleType" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Plate Number</label>
                        <input type="text" id="vehicleplate" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Status</label>
                        <select id="vehicleStatus" class="form-select">
                            <option value="Available">Available</option>
                            <option value="Booked">Booked</option>
                            <option value="In Service">Maintance</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addVehicle()">Save Vehicle</button>
                </div>
            </div>
        </div>
    </div>
    
     <div class="modal fade" id="editVehicleModal" tabindex="-1" aria-labelledby="editVehicleLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editVehicleLabel">Edit Vehicle Status</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="editVehicleId">
                    <div class="mb-3">
                        <label class="form-label">Status</label>
                        <select id="editStatus" class="form-select">
                            <option value="Available">Available</option>
                            <option value="Maintance">Maintance</option>
                            <option value="Booked">Booked</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="updateVehicle()">Save Changes</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/manageVehicles.js"></script>
</body>
</html>