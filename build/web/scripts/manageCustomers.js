
$(document).ready(function () {
    loadCustomers();
});

function loadCustomers() {
    $.get("http://localhost:8080/MegaCityCab/api/customers/getAllCustomers", function (data) {
        let tableRows = "";
        data.forEach(customer => {
            tableRows += `
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.address}</td>
                    <td>${customer.nic}</td>
                    <td>${customer.tel}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="deleteCustomer(${customer.id})">
                            <i class="fas fa-trash"></i> 
                        </button>
                    </td>
                </tr>`;
        });
        $("#customerTable").html(tableRows);
    }).fail(() => {
        $("#customerTable").html("<tr><td colspan='7' class='text-center text-danger'>Failed to load customers</td></tr>");
    });
}


function deleteCustomer(customerId) {
    if (confirm("Are you sure you want to delete this customer?")) {
        $.ajax({
            url: "http://localhost:8080/MegaCityCab/api/customers/deleteCustomer/" + customerId,
            type: "DELETE",
            success: function () {
                alert("Customer deleted successfully");
                loadCustomers();
            },
            error: function () {
                alert("Failed to delete Customer");
            }
        });
    }
}

