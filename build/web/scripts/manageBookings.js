
$(document).ready(function () {
    loadBookings();
});

function loadBookings() {
    $.get("http://localhost:8080/MegaCityCab/api/booking/getAllBookings", function (data) {
        let tableRows = "";
        data.forEach(booking => {
            let statusIcon = "";
            if (booking.status === "Confirmed") {
                statusIcon = '<i class="fas fa-check-circle text-success ms-2"></i>';
            } else if (booking.status === "Completed") {
                statusIcon = '<i class="fas fa-check-double text-primary ms-2"></i>';
            } else if (booking.status === "Cancelled") {
                statusIcon = '<i class="fas fa-times-circle text-danger ms-2"></i>';
            }

            tableRows += `
                <tr>
                    <td>${booking.bookingId}</td>
                    <td>${booking.customerId}</td>
                    <td>${booking.vehicleId}</td>
                    <td>${booking.pickupLocation}</td>
                    <td>${booking.dropoffLocation}</td>
                    <td>${booking.status} ${statusIcon}</td>
                    <td>
                        <button class="btn btn-sm btn-warning" onclick="editBooking(${booking.bookingId}, '${booking.status}')">
                            <i class="fas fa-edit"></i> 
                        </button>
                        <button class="btn btn-sm btn-secondary" onclick="cancelBooking(${booking.bookingId})">
                            <i class="fas fa-times-circle"></i> 
                        </button>
                        <button class="btn btn-sm btn-primary" onclick="calculateBill(${booking.bookingId})">
                        <i class="fas fa-file-invoice"></i> 
                        </button>
                        <button class="btn btn-sm btn-danger" onclick="deleteBooking(${booking.bookingId})">
                            <i class="fas fa-trash"></i> 
                        </button>

                    </td>
                </tr>`;
        });
        $("#bookingsTable").html(tableRows);
    }).fail(() => {
        $("#bookingsTable").html("<tr><td colspan='7' class='text-center text-danger'>Failed to load bookings</td></tr>");
    });
}

function editBooking(id, status) {
    $("#editBookingId").val(id);
    $("#editStatus").val(status);
    $("#editBookingModal").modal("show");
}

function updateBooking() {
    let bookingId = $("#editBookingId").val();
    let status = $("#editStatus").val();

    $.ajax({
        url: "http://localhost:8080/MegaCityCab/api/booking/updateBooking/" + bookingId,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({ status: status }),
        success: function () {
            alert("Booking Status updated successfully");
            $("#editBookingModal").modal("hide");
            loadBookings();
        },
        error: function () {
            alert("Failed to update booking Status");
        }
    });
}
function calculateBill(bookingId){
        $("#bookingId").val(bookingId);
        $("#addBillModal").modal("show");
}

function cancelBooking(bookingId) {
    if (confirm("Are you sure you want to Cancel this booking?")) {
        $.ajax({
            url: "http://localhost:8080/MegaCityCab/api/booking/cancelBooking/" + bookingId,
            type: "PUT",
            success: function () {
                alert("Booking Canceled successfully");
                loadBookings();
            },
            error: function () {
                alert("Failed to Cancel booking");
            }
        });
    }
}

function createBill() {
     let bookingId = $("#bookingId").val();
    let billData = {
        baseFare: $("#baseFare").val(),
        distance: $("#distance").val(),
        ratePerKm: $("#ratePerKm").val(),
        duration: $("#duration").val(),
        ratePerMinute: $("#ratePerMinute").val(),
        additionalCharges: $("#additionalCharges").val(),
        taxRate: $("#taxRate").val()
    };

    $.ajax({
        url: `http://localhost:8080/MegaCityCab/api/bills/createBill/${bookingId}`,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(billData),
        success: function (response) {
            alert("Bill created successfully!");
            $("#addBillModal").modal("hide");
            loadVehicles();
        },
        error: function (error) {
            console.log("Error adding vehicle", error);
        }
    });
}

function deleteBooking(bookingId) {
    if (confirm("Are you sure you want to delete this booking?")) {
        $.ajax({
            url: "http://localhost:8080/MegaCityCab/api/booking/deleteBooking/" + bookingId,
            type: "DELETE",
            success: function () {
                alert("Booking deleted successfully");
                loadBookings();
            },
            error: function () {
                alert("Failed to delete booking");
            }
        });
    }
}

