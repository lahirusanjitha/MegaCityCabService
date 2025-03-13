$(document).ready(function () {
    loadBills();
});

function loadBills() {
    $.get("http://localhost:8080/MegaCityCab/api/bills/getAllBills", function (data) {
        let tableRows = "";
        data.forEach(bill => {
            tableRows += `
                <tr>
                        <td>${bill.billId}</td>
                        <td>${bill.bookingId}</td>
                        <td>${bill.baseFare}</td>
                        <td>${bill.distance}</td>
                        <td>${bill.ratePerKm}</td>
                        <td>${bill.duration}</td>
                        <td>${bill.ratePerMinute}</td>
                        <td>${bill.additionalCharges}</td>
                        <td>${bill.taxRate}</td>
                        <td>${bill.totalFare}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="printBill(${bill.bookingId})">
                                <i class="fas fa-file-pdf"></i>
                            </button>
                            <button class="btn btn-sm btn-danger" onclick="deleteBill(${bill.billId})">
                            <i class="fas fa-trash"></i> 
                             </button>
                        </td>
                 </tr>`;
        });
        $("#billTable").html(tableRows);
    }).fail(() => {
        $("#billTable").html("<tr><td colspan='7' class='text-center text-danger'>Failed to load vehicles</td></tr>");
    });
}


function printBill(bookingId) {
    $.ajax({
        url: `http://localhost:8080/MegaCityCab/api/bills/printpdf/${bookingId}`,
        type: "GET",
        success: function () {
            alert("Bill Prited Successfully See Download to get Pdf");
            loadBills();
        },
        error: function (error) {
            console.log("Error Printing Bill PDF", error);
        }
    });
}
function deleteBill(billId) {
    if (confirm("Are you sure you want to delete this Bill?")) {
        $.ajax({
            url: "http://localhost:8080/MegaCityCab/api/bills/deleteBill/" + billId,
            type: "DELETE",
            success: function () {
                alert("Bill deleted successfully");
                loadBills();
            },
            error: function () {
                alert("Bill to delete booking");
            }
        });
    }
}




