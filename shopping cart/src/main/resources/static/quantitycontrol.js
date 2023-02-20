$(document).ready(function(){
    $(".minusButton").on("click",function(evt){
        evt.preventDefault();
        productId = $(this).attr("pid");
        qtyInput $("#quantity" + productId);
        newQty = parseInt(qtyInput.val()) -1;
        if (newQty >0) qtyInput.val(newQty);

    });

    $(".plusButton").on("click",function(evt){
        evt.preventDefault();
        productId = $(this).attr("pid");
        qtyInput $("#quantity" + productId);


        newQty = parseInt(qtyInput.val()) +1;
        if (newQty <10) qtyInput.val(newQty);
        updateQuantity(productId,newQty);

    });

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


})

