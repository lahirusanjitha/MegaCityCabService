/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    const apiUrl = "http://localhost:8080/MegaCityCab/api";
    const customerId = sessionStorage.getItem("customerId");
    loadVehicles();
    loadDrivers();

    if (!customerId) {
        alert("Please log in first!");
        window.location.href = "login.jsp"; 
    }

function loadVehicles() {
    $.get(`${apiUrl}/vehicles/getAllVehicles`, function (data) {
        $("#vehiclesTable").empty(); 
        $("#vehicleSelect").empty();
        
        data.forEach(vehicle => {
            $("#vehiclesTable").append(`
                <tr>
                    <td>${vehicle.vehicleId}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.type}</td>
                    <td>${vehicle.plateNumber}</td>
                    <td>${vehicle.status}</td>
                </tr>
            `);
            $("#vehicleSelect").append(`<option value="${vehicle.vehicleId}">${vehicle.model} (${vehicle.plateNumber})</option>`);
        });
    }).fail(function () {
        alert("Failed to load vehicles.");
    });
}

//
//   function loadDrivers() {
//    $.get(`${apiUrl}/drivers/getAllDrivers`, function (data) {
//        if (data.length > 0) {
//            console.log(data);
//            $("#driverSelect").val(data[0].name);
//        } else {
//            $("#driverSelect").val("No drivers available"); // Fallback text
//        }
//    });
//}

    function loadMyBookings() {
    $.get(`${apiUrl}/booking/getBookingByCustId/${customerId}`, function (data) {
        console.log("API Response:", data);  // Debugging

        if (!data || typeof data !== "object") {
            console.error("Invalid API response, expected an object but got:", data);
            $("#bookingTableBody").html("<tr><td colspan='5' class='text-center text-danger'>Invalid response format</td></tr>");
            return;
        }

        // Generate a single row for the booking
        let tableRow = `
            <tr>
                <td>${data.bookingId}</td>
                <td>${data.pickupLocation}</td>
                <td>${data.dropoffLocation}</td>
                <td>${data.bookingDate}</td>
                <td>${data.status}</td>
            </tr>`;

        $("#bookingTableBody").html(tableRow); // Insert into table
    }).fail((jqXHR, textStatus, errorThrown) => {
        console.error("Failed to load booking:", textStatus, errorThrown);
        $("#bookingTableBody").html("<tr><td colspan='5' class='text-center text-danger'>Failed to load booking</td></tr>");
    });
}

//    function loadMyBookings() {
// 
//        $.get(`${apiUrl}/booking/getBookingByCustId/${customerId}`, function (data) {
//            $("#bookingTableBody").empty(); 
//            
//            if (data.length === 0) {
//                $("#bookingTableBody").append(`<tr><td colspan="5" class="text-center">No bookings found.</td></tr>`);
//                return;
//            }
//
//            data.forEach(booking => {
//                $("#bookingTableBody").append(`
//                    <tr>
//                        <td>${booking.bookingId}</td>
//                        <td>${booking.pickupLocation}</td>
//                        <td>${booking.dropoffLocation}</td>
//                        <td>${booking.bookingDate}</td>
//                        <td>${booking.status}</td>
//                    </tr>
//                `);
//            });
//        }).fail(function () {
//            alert("Failed to load bookings.");
//        });
//    }

 $("#bookingForm").submit(function (e) {
    e.preventDefault(); 

    function getFormattedDateTime() {
        let dateTimeInput = $("#bookingDate").val(); 
        return dateTimeInput ? dateTimeInput + ":00" : null; 
    }
    const customerId = sessionStorage.getItem("customerId");
    const bookingData = {
        customerId: customerId,  
        pickupLocation: $("#pickupLocation").val(),
        dropoffLocation: $("#dropLocation").val(), 
        bookingDate: getFormattedDateTime(), 
        vehicleId: $("#vehicleSelect").val(),
        driverId: $("#driverSelect").val()
    };


    $.ajax({
        url: `${apiUrl}/booking/addBooking`,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(bookingData),
        success: function () {
            alert("Booking successful!");
            location.reload();
        },
        error: function (xhr) {
            alert("Booking failed! " + xhr.responseText);
        }
    });
});

    $("#logoutBtn").click(function () {
        sessionStorage.clear();
        window.location.href = "login.jsp";
    });
    $("#viewBookingBtn").click(function () {
        loadMyBookings();
    });
    
    
});
