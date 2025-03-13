/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    loadDrivers();
});

function loadDrivers() {
    $.get("http://localhost:8080/MegaCityCab/api/drivers/getAllDrivers", function (data) {
        let tableRows = "";
        data.forEach(driver => {
            tableRows += `
                <tr>
                        <td>${driver.driverId}</td>
                        <td>${driver.name}</td>
                        <td>${driver.licenseNumber}</td>
                        <td>${driver.phone}</td>
                        <td>${driver.status}</td>
                        <td>
                         <button class="btn btn-sm btn-warning" onclick="editDriver(${driver.driverId})">
                            <i class="fas fa-edit"></i> 
                        </button>
                        <button class="btn btn-sm btn-danger" onclick="deleteDriver(${driver.driverId})">
                            <i class="fas fa-trash"></i> 
                        </button>
                        </td>
                 </tr>`;
        });
        $("#driverTable").html(tableRows);
    }).fail(() => {
        $("#driverTable").html("<tr><td colspan='7' class='text-center text-danger'>Failed to load drivers</td></tr>");
    });
}


function addDriver() {
    let driverData = {
        name: $("#driverName").val(),
        licenseNumber: $("#licenseNumber").val(),
        phone: $("#phone").val(),
        status: $("#driverStatus").val()
    };

    $.ajax({
        url: "http://localhost:8080/MegaCityCab/api/drivers/addDriver",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(driverData),
        success: function(response) {
            alert("Driver added successfully!");
            $("#addDriverModal").modal("hide");
            loadDrivers();
        },
        error: function(xhr, status, error) {
            console.log("Error adding Driver:", xhr.responseText);
            alert("Error adding Driver: " + xhr.responseText);
        }
    });
}

function editDriver(id, status) {
    $("#editDriverId").val(id);
    $("#editStatus").val(status);
    $("#editDriverModal").modal("show");
}

function updateDriver() {
    let driverId = $("#editDriverId").val();
    let status = $("#editStatus").val();

    $.ajax({
        url: "http://localhost:8080/MegaCityCab/api/drivers/updateDriver/" + driverId,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({ status: status }),
        success: function () {
            alert("Status Status updated successfully");
            $("#editDriverModal").modal("hide");
            loadDrivers();
        },
        error: function () {
            alert("Failed to update Driver Status");
        }
    });
}

function deleteDriver(driverId) {
    if (confirm("Are you sure you want to delete this Driver?")) {
        $.ajax({
            url: "http://localhost:8080/MegaCityCab/api/drivers/deleteDriver/" + driverId,
            type: "DELETE",
            success: function () {
                alert("Driver deleted successfully");
                loadDrivers();
            },
            error: function () {
                alert("Failed to delete driver");
            }
        });
    }
}



