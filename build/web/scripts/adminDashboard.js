const apiUrl = "http://localhost:8080/MegaCityCab/api"; 
$(document).ready(function () {
    loadDashboardData();
});

function loadDashboardData() {
    getTotalBookings();
    getAvailableVehicles();
    getRegisteredDrivers();
    getRegisteredCustomers();
}

function getTotalBookings() {
    $.get(`${apiUrl}/booking/total`, function (data) {
        $("#totalBookings").text(data.totalBookings);
    }).fail(function () {
        $("#totalBookings").text("Error loading data");
    });
}

function getAvailableVehicles() {
    $.get(`${apiUrl}/vehicles/availableCount`, function (data) {
        $("#availableVehicles").text(data.availableVehicles);
    }).fail(function () {
        $("#availableVehicles").text("Error loading data");
    });
}

function getRegisteredDrivers() {
    $.get(`${apiUrl}/drivers/totalDrivers`, function (data) {
        $("#registeredDrivers").text(data.registeredDrivers);
    }).fail(function () {
        $("#registeredDrivers").text("Error loading data");
    });
}

function getRegisteredCustomers() {
    $.get(`${apiUrl}/customers/totalCustomers`, function (data) {
        $("#registeredCustomers").text(data.registeredCustomers);
    }).fail(function () {
        $("#registeredCustomers").text("Error loading data");
    });
}
