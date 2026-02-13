getAllCustomers();
function saveCustomer(){
    let name=$('#Input2').val();
    let address=$('#Input3').val();

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/customer/saveCustomer",
        async:true,
        data:JSON.stringify({
            "id":"",
            "name":name,
            "address":address
        }),
        success: function (response) {
            console.log(response.status)
            console.log(response.message)
            console.log(response.data)

          if (response.status === 201) {
            alert(response.message);
            getAllCustomers();
            clearAll();
          }
        },
        error: function (xhr, status, error){
          let errorResponse = xhr.responseJSON;
          if(errorResponse){
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
function updateCustomer(){
    let id=$('#Input1').val();
    let name=$('#Input2').val();
    let address=$('#Input3').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/customer/updateCustomer",
        async:true,
        data:JSON.stringify({
            "id":id,
            "name":name,
            "address":address
        }),
        success: function (response) {
            console.log(response.status)
            console.log(response.message)
            console.log(response.data)

            if (response.status === 201) {
                alert(response.message);
                getAllCustomers();
                clearAll();
            }
        },
        error: function (xhr, status, error){
            let errorResponse = xhr.responseJSON;
            if(errorResponse){
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
function deleteCustomer(){
    let cID=$('#Input1').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/customer/deleteCustomer/"+cID,
        async:true,
        success: function (response) {
            console.log(response.status)
            console.log(response.message)
            console.log(response.data)

            if (response.status === 201) {
                alert(response.message);
                getAllCustomers();
                clearAll();
            }
        },
        error: function (xhr, status, error){
            let errorResponse = xhr.responseJSON;
            if(errorResponse){
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
function getAllCustomers() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/api/v1/customer/getAllCustomers",
    async: true,
    success: function (response) {
      $('#cusTable').empty();

      console.log("Received Response:", response);

      let customers = response.data;

      for (let cus of customers) {
        let id = cus.id;
        let name = cus.name;
        let address = cus.address;

        var row = `<tr><td>${id}</td><td>${name}</td><td>${address}</td></tr>`;
        $('#cusTable').append(row);
      }
    },
    error: function (xhr, exception) {
      alert("Error Loading Customers");
    }
  });
}
$(document).ready(function () {
    $(document).on('click', '#cusTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();

        $('#Input1').val(col0);
        $('#Input2').val(col1);
        $('#Input3').val(col2);

    })
})

  function clearAll(){
    $("#Input2").val("");
    $("#Input3").val("");
}
