<table class="table table-bordered table-hover table-sm">
      <thead class="thead-light">
      <tr>
        <th>#</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Total Cost</th>
        <th>Status</th>
        <th>Cancel</th>
      </tr>
      </thead>
      <tbody>
      <!-- Loop through the list of order item and display them in the table -->
      <tr th:each="orderItem : ${order.getOrderItems()}">
        <td><img th:src="@{|${orderItem.getProduct().getImage()}|}" width="100"/></td>
        <td th:text="${orderItem.getProduct().getName()}"></td>
        <td th:text="${orderItem.getPrice()}"></td>
        <td th:text="${orderItem.getQuantity()}"></td>
        <td th:text="${orderItem.getPrice().multiply(orderItem.getQuantity())}"></td>
        <td th:text="${orderItem.getStatus()}"></td>

        <td th:if="${orderItem.getStatus() != 'CANCELLED'}">
          <form role="form" th:action="@{/profile/orders/orderdetail/{order_id}/{item_id}(order_id=${order.orderId}, item_id=${orderItem.getId()})}" method="post">
            <input type="hidden" name="_method" value="PUT"/>
            <input type="hidden" name="status" value="CANCELLED" />
            <button type="submit" class="btn btn-success" onclick="return confirm('Cancelling! Are you sure ?')">Cancel Product</button>
          </form>
        </td>


      </tr>
      </tbody>
    </table>