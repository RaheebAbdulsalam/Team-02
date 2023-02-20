$(document).ready(function(){
    $(".minusButton").on("click",function(evt){
        evt.preventDefault();
        decreaseQuantity($(this));

    });

    $(".plusButton").on("click",function(evt){
        evt.preventDefault();
        increaseQuantity($(this));


    });

    $(".link-remove").on("click",function (evt){
        evt.preventDefault();
        removeFromCart($(this));
    });

    updateTotal();
});

function removeFromCart(link){
    url =link.attr("href");
    $.ajax({
        type: "POST",
        url:url,
        beforeSend : function (xhr){
            xhr.setRequestHeader(crsfHeaderName,csrfValue);
        }

    }).done(function (response){
        $("#modelTitle").text("Shopping cart");
        if (response.includes("removed")){
            $("#myModal").on("hide.bs.modal", function (e){
                removeProduct();
            });
        }

    }).fail(function (){
        $("#modelTitle").text("Shopping cart");
        $("#modelBody").text("error while adding product");
        $("#modelBody").modal();
    })
}

function increaseQuantity(link){
    productId = $link.attr("pid");
    qtyInput $("#quantity" + productId);
    newQty = parseInt(qtyInput.val()) +1;
    if (newQty >0) qtyInput.val(newQty);
    updateQuantity(productId,newQty);
}
function updateTotal() {
    total = 0.0;

    $(".productSubtotal").each(function (index, element) {
        total = total + parseFloat(element.innerHTML);
    });

    $("#totalAmount").text("£" + total);

    function decreaseQuantity(link) {
        productId = $link.attr("pid");
        qtyInput
        $("#quantity" + productId);
        newQty = parseInt(qtyInput.val()) - 1;
        if (newQty > 0) qtyInput.val(newQty);
        updateQuantity(productId,newQty);

    }

    function updateQuantity(productID, quantity){

        quantity = $("quantity + productID").val();

        url = contextPath + "cart/add/" + productId + "/" + quantity;

        $.ajax({
            type: "POST",
            url:url,
            beforeSend : function (xhr){
                xhr.setRequestHeader(crsfHeaderName,csrfValue);
            }

        }).done(function (newSubtotal){
            updateSubtotal(newSubtotal, productID);
            updateTotal();

        }).fail(function (){
            $("#modelTitle").text("Shopping cart");
            $("#modelBody").text("error while adding product");
            $("#modelBody").modal();
        })


    }

    function updateQuantity(productID, quantity){

        quantity = $("quantity + productID").val();

        url = contextPath + "cart/add/" + productId + "/" + quantity;

        $.ajax({
            type: "POST",
            url:url,
            beforeSend : function (xhr){
                xhr.setRequestHeader(crsfHeaderName,csrfValue);
            }

        }).done(function (response){
            $("#modelTitle").text("Shopping cart");
            $("#modelBody").text(response);
            $("#modelBody").modal();
        }).fail(function (){
            $("#modelTitle").text("Shopping cart");
            $("#modelBody").text("error while adding product");
            $("#modelBody").modal();
        })


    }
function updateSubtotal(newSubtotal, productID){
        $("subtotal"+productID).text(newSubtotal);
}

}




