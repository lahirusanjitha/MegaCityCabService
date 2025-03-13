/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
$(document).ready(function () {
    loadVehicles();
});


function loadVehicles() {
    $.get("http://localhost:8080/MegaCityCab/api/vehicles/getAllVehicles", function (data) {
        let tableRows = "";
        data.forEach(vehicle => {
            tableRows += `
                <tr>
                        <td>${vehicle.vehicleId}</td>
                        <td>${vehicle.model}</td>
                        <td>${vehicle.type}</td>
                        <td>${vehicle.plateNumber}</td>
                        <td>${vehicle.status}</td>
                        <td>
                         <button class="btn btn-sm btn-warning" onclick="editVehicle(${vehicle.vehicleId}, '${vehicle.status}')">
                            <i class="fas fa-edit"></i> 
                        </button>
                        <button class="btn btn-sm btn-danger" onclick="deleteVehicle(${vehicle.vehicleId})">
                            <i class="fas fa-trash"></i> 
                        </button>
                        </td>
                 </tr>`;
        });
        $("#vehiclesTable").html(tableRows);
    }).fail(() => {
        $("#vehiclesTable").html("<tr><td colspan='7' class='text-center text-danger'>Failed to load vehicles</td></tr>");
    });
}


function addVehicle() {
    let vehicleData = {
        model: $("#vehicleModel").val(),
        type: $("#vehicleType").val(),
        plateNumber: $("#vehicleplate").val(),
        status: $("#vehicleStatus").val()
    };

    $.ajax({
        url: "http://localhost:8080/MegaCityCab/api/vehicles/addVehicle",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(vehicleData),
        success: function(response) {
            alert("Vehicle added successfully!");
            $("#addVehicleModal").modal("hide");
            loadVehicles();
        },
        error: function(xhr, status, error) {
            console.log("Error adding vehicle:", xhr.responseText);
            alert("Error adding vehicle: " + xhr.responseText);
        }
    });

}

function editVehicle(id, status) {
    $("#editVehicleId").val(id);
    $("#editStatus").val(status);
    $("#editVehicleModal").modal("show");
}

function updateVehicle() {
    let vehicleId = $("#editVehicleId").val();
    let status = $("#editStatus").val();

    $.ajax({
        url: "http://localhost:8080/MegaCityCab/api/vehicles/updateVehicle/" + vehicleId,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({ status: status }),
        success: function () {
            alert("Vehicle Status updated successfully");
            $("#editVehicleId").modal("hide");
            loadVehicles();
        },
        error: function () {
            alert("Failed to update Vehicle Status");
        }
    });
}

function deleteVehicle(vehicleId) {
    if (confirm("Are you sure you want to delete this Vehicle?")) {
        $.ajax({
            url: "http://localhost:8080/MegaCityCab/api/vehicles/deleteVehicle/" + vehicleId,
            type: "DELETE",
            success: function () {
                alert("Vehicle deleted successfully");
                loadVehicles();
            },
            error: function () {
                alert("Failed to delete Vehicle");
            }
        });
    }
}

