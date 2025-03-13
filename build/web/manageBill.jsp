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
                <h2 class="mt-4">Manage Bill</h2>
                

                <table class="table table-striped mt-3">
                    <thead class="table-dark">
                        <tr>
                            <th>Bill ID</th>
                            <th>Booking Id</th>
                            <th>BaseFare</th>
                            <th>distance KM</th>
                            <th>RatePer KM</th>
                            <th>Duration</th>
                            <th>RatePer Minute</th>
                            <th>Additional Charges </th>
                            <th>TaxRate</th>
                            <th>total Fare</th> 
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="billTable">
                    </tbody>
                </table>
            </main>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="scripts/manageBill.js"></script>
</body>
</html>
