function displayProducts() {
    $(document).ready(function() {
        $.getJSON("/admin/product/list", function(data) {
            var tableBody = $("#product-table tbody");
            $.each(data, function(index, product) {
                var row = $("<tr>")
                    .append($("<td>").text(product.id))
                    .append($("<td>").text(product.categories.map(function(category) {
                        return category.name;
                    }).join(", ")))
                    .append($("<td>").text(product.name))
                    .append($("<td>").text(product.price))
                    .append($("<td>").text(product.stockLevel))
                    .append($("<td>").html("<img src='" + product.image + "'>"));

                var editButton = $("<button>")
                    .addClass("btn btn-info")
                    .text("Edit")
                    .on("click", function() {
                        // Handle edit button click
                        console.log("Edit button clicked for product " + product.id);
                    });

                var deleteButton = $("<button>")
                    .addClass("btn btn-danger")
                    .text("Delete")
                    .on("click", function() {
                        // Handle delete button click
                        console.log("Delete button clicked for product " + product.id);
                    });

                var showButton = $("<button>")
                    .addClass("btn btn-success")
                    .text("Show")
                    .on("click", function() {
                        // Handle show button click
                        console.log("Show button clicked for product " + product.id);
                    });

                row.append($("<td>").append(editButton))
                    .append($("<td>").append(deleteButton))
                    .append($("<td>").append(showButton));

                tableBody.append(row);
            });
        });
    });
}


function addProduct() {
    // Add an event listener to the form submission
    document.querySelector('#addProductForm').addEventListener('submit', function(event) {
        event.preventDefault(); // prevent default form submission behavior

        // Extract the form data into a JSON object
        let formData = {
            name: document.querySelector('#name').value,
            price: document.querySelector('#price').value,
            description: document.querySelector('#description').value,
            stockLevel: document.querySelector('#stockLevel').value,
            image: document.querySelector('#image').value,
            categories: Array.from(document.querySelector('#categories').selectedOptions, option => option.value)
        };

        // Send a POST request to the server with the form data
        fetch('/admin/product', {
            method: 'POST',
            body: JSON.stringify(formData),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    // Product was added successfully, reload the page
                    location.reload();
                } else {
                    alert('Failed to add product');
                }
            })
            .catch(error => alert('Failed to add product: ' + error));
    });
}