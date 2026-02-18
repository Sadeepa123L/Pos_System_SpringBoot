let cart =[];
let allItems = [];

$(document).ready(function (){
  loadAllCustomers();
  loadAllItems();
  getAllOrders();

  let today = new Date().toISOString().split('T')[0];
  $('#txtOrderDate').val(today);
});

function loadAllCustomers(){
  $.ajax({
    url:"http://localhost:8080/api/v1/customer/getAllCustomers",
    method:"GET",
    success:function (response){
      $("#cmbCustomerID").empty();
      $("#cmbCustomerID").append(`<option value="">-- Select Customer --</option>`);

      let Customers = response.data;
      for(let cus of Customers){
        let option = `<option value="${cus.id}">${cus.id} - ${cus.name}</option>`;
        $('#cmbCustomerID').append(option);
      }
    },
    error:function (){
      console.log("Error loading customers");
    }
  });
}

function loadAllItems(){
  $.ajax({
    url: "http://localhost:8080/api/v1/item/getAllItems",
    method: "GET",
    success:function (response){
      $("#cmbItemID").empty();
      $("#cmbItemID").append(`<option value="">-- Select Item --</option>`);

      let Items = response.data;

      allItems = Items;

      for(let item of Items){
        let option = `<option value="${item.id}">${item.id} - ${item.name}</option>`;
        $('#cmbItemID').append(option);
      }
    },
    error:function (){
      console.log("Error loading items");
    }
  });
}

$('#cmbItemID').on('change', function () {
  let selectedItemID = $(this).val();

  let selectedItem = allItems.find(item => item.id == selectedItemID);

  if (selectedItem) {
    $('#txtUnitPrice').val(selectedItem.price);
  } else {
    $('#txtUnitPrice').val('');
  }
});


function addToCart() {
  let itemID = $('#cmbItemID').val();
  let unitPrice = parseFloat($('#txtUnitPrice').val());
  let qty = parseInt($('#txtQty').val());

  if (!itemID || isNaN(qty) || qty <= 0 || isNaN(unitPrice)) {
    alert("Please select an item and enter a valid quantity!");
    return;
  }


  let existingItem = cart.find(cartItem => cartItem.itemId == itemID);

  if (existingItem) {
    existingItem.qty += qty;
  } else {
    let orderDetail = {
      itemId: itemID,
      qty: qty,
      unitPrice: unitPrice
    };
    cart.push(orderDetail);
  }

  updateCartTable();
}

function updateCartTable() {
  $('#cartTableBody').empty();
  let grandTotal = 0;

  for (let i = 0; i < cart.length; i++) {
    let item = cart[i];
    let itemTotal = item.qty * item.unitPrice;
    grandTotal += itemTotal;

    let row = `<tr>
                        <td>${item.itemId}</td>
                        <td>${item.unitPrice.toFixed(2)}</td>
                        <td>${item.qty}</td>
                        <td>${itemTotal.toFixed(2)}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="removeFromCart(${i})">Remove</button>
                        </td>
                   </tr>`;
    $('#cartTableBody').append(row);
  }

  $('#lblTotal').text(grandTotal.toFixed(2));
}

function removeFromCart(index) {
  cart.splice(index, 1);
  updateCartTable();
}



function placeOrder() {
  let customerId = $('#cmbCustomerID').val();
  let orderDate = $('#txtOrderDate').val();
  if (!customerId || !orderDate) {
    alert("Please select a Customer and Order Date!");
    return;
  }
  if (cart.length === 0) {
    alert("Your cart is empty! Please add items first.");
    return;
  }
  let orderDTO = {
    orderDate: orderDate,
    customerId: customerId,
    orderDetails: cart
  };

  $.ajax({
    method: "POST",
    contentType: "application/json",
    url: "http://localhost:8080/api/v1/orders/placeOrder",
    async: true,
    data: JSON.stringify(orderDTO),
    success: function (response) {
      if (response.status === 201) {
        alert(response.message);
        cart = [];
        updateCartTable();
        getAllOrders();
        $('#cmbCustomerID').val('');
        $('#cmbItemID').val('');
        $('#txtQty').val('');
        $('#txtUnitPrice').val('');
      }
    },
    error: function (xhr) {
      let errorResponse = xhr.responseJSON;
      if (errorResponse) {
        if (errorResponse.status === 400) {
          let errors = errorResponse.data;
          let errorMessage = "Validation Failed:\n";
          for (let key in errors) {
            errorMessage += "- " + key + "\n";
          }
          alert(errorMessage);
        } else {
          alert("Error: " + errorResponse.message);
        }
      } else {
        alert("Connection Error!");
      }
    }
  });
}




function getAllOrders(){
  $.ajax({
    method:"GET",
    url:"http://localhost:8080/api/v1/orders/getAllOrders",
    async: true,
    success:function (response){
      $('#ordersTableBody').empty();

      let orders = response.data;

      for(let order of orders){
        for(let details of order.orderDetails){
          let row = `<tr>
                         <td>${order.orderId}</td>
                         <td>${order.orderDate}</td>
                         <td>${order.customerId}</td>
                         <td>${details.itemId}</td>
                         <td>${details.qty}</td>
                     </tr>`;

          $('#ordersTableBody').append(row);
        }
      }
    },
    error: function(xhr) {
      console.log("Error loading all orders", xhr);
    }
  });
}

