getAllItems();

function saveItem() {

    let name = $('#Input2').val();
    let qty = $('#Input3').val();
    let price = $('#Input4').val();

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/item/saveItem",
        async:true,
        data:JSON.stringify({
            "id":"",
            "name":name,
            "qtyOnHand":qty,
            "price":price
        }),
        success: function (response) {
            console.log(response.status)
            console.log(response.message)
            console.log(response.data)

          if(response.status===200){
            alert(response.message)
            getAllItems();
          }
        },
      error: function (xhr, status, error) {
        let errorResponse = xhr.responseJSON;
        if(errorResponse) {
          console.log(errorResponse.status)
          console.log(errorResponse.message)
          console.log(errorResponse.data)
          alert("Error: " + errorResponse.message);
        }else {
          alert("Something went wrong")
        }
        }
    })
}

function updateItem() {

    let id = $('#Input1').val();
    let name = $('#Input2').val();
    let qty = $('#Input3').val();
    let price = $('#Input4').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/item/updateItem",
        async:true,
        data:JSON.stringify({
            "id":id,
            "name":name,
            "qtyOnHand":qty,
            "price":price
        }),
        success: function (response) {
            console.log(response.status)
            console.log(response.message)
            console.log(response.data)

          if(response.status === 200){
            alert(response.message)
            getAllItems();
            clearAllItems();
          }
        },
      error: function (xhr, status, error) {
        let errorResponse = xhr.responseJSON;
        if(errorResponse) {
          console.log(errorResponse.status)
          console.log(errorResponse.message)
          console.log(errorResponse.data)
          alert("Error: " + errorResponse.message);
        }else {
          alert("Something went wrong")
        }
      }
    })
}

function deleteItem() {
    let id = $('#Input1').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/item/deleteItem/"+id,
        async:true,
        success: function (data) {
            alert("Deleted")
            getAllItems();
            clearAllItems();
        },
    })
}

function getAllItems(){

    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/v1/item/getAllItems",
        async:true,
        success: function (response) {
            $('#itemTable').empty();

          console.log("Received Response:", response);

          let itemList = response.data;

            for (let item of itemList){
                let id=item.id;
                let name=item.name;
                let qty=item.qtyOnHand;
                let price=item.price;


                var row=`<tr><td>${id}</td><td>${name}</td><td>${qty}</td><td>${price}</td></tr>`;
                $('#itemTable').append(row);
            }
        },
        error: function (xhr, exception) {
            alert("Error loading items")
        }
    })
}
$(document).ready(function () {
    $(document).on('click', '#itemTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();

        $('#Input1').val(col0);
        $('#Input2').val(col1);
        $('#Input4').val(col3);
        $('#Input3').val(col2);

    })
})

function clearAllItems(){
  $("#Input2").val("")
  $("#Input3").val("")
  $("#Input4").val("")
}
