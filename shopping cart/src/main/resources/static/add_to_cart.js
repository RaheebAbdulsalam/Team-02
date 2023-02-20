$(document).ready(function(){
    $("#buttonAdd2Cart").on("click",function(e) {
        alert('Add to cart');
            addToCart();

    });

    });

function addToCart(){
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

