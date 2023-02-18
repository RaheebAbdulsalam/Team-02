$(document).ready(function() {
    $.getJSON("/products/list", function(data) {
        var tableBody = $("#product-table tbody");
        $.each(data, function(index, product) {
            var row = $("<tr>")
                .append($("<td>").text(product.id))
                .append($("<td>").text(product.name))
                .append($("<td>").text(product.price))
                .append($("<td>").text(product.description))
                .append($("<td>").text(product.stockLevel));
            tableBody.append(row);
        });
    });
});