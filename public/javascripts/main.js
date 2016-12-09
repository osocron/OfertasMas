$(document).ready(function () {
   $('#btnSubmit').click(function () {
       var idcodigo = $('#busqueda').val();
       var table = $('#tableCupon > tbody:last-child');
       $.ajax({
           url : '/api/cupon/' + idcodigo,
           method : 'get',
           dataType : 'json',
           success : function (data) {
               if (data.codigoCupon !== undefined) {
                   $('#tableCupon tbody').empty();
                   table.append(
                       '<tr>' +
                       '<td>' + data.codigoCupon + '</td>' +
                       '<td>' + data.fechaCreacion + '</td>' +
                       '<td>' + data.idOferta + '</td>' +
                       '<td>' + data.idUsuario + '</td>' +
                       '<td id="estadoCupon">' + data.idEstadoCupon + '</td>' +
                       '<td><input type="submit" value="canjear" id="btnCanjear" class="btn btn-success" onclick="canjear()"></td>' +
                       '</tr>');
                   document.getElementById("btnCanjear").addEventListener("click", canjear, false);
                   if (data.idEstadoCupon != 1) {
                        $('#btnCanjear').removeClass('btn-success').addClass('btn-danger').prop('disabled', true);
                   }
               } else {
                   alert(data.mensaje);
               }
           },
           error: function (xhr, ajaxOptions, thrownError) {
               alert(xhr.status);
               alert(thrownError);
           }
       });
       function canjear () {
           $.ajax({
               url : '/api/cupon/canjear',
               type : "POST",
               data : {codigoCupon : idcodigo},
               contentType: 'application/x-www-form-urlencoded',
               dataType : 'json',
               success : function (data) {
                   if (!data.error) {
                       $('#btnCanjear').removeClass('btn-success').addClass('btn-danger').prop('disabled', true);
                       $('#estadoCupon').html('3');
                   } else {
                       alert(data.mensaje);
                   }
               },
               error: function (xhr, ajaxOptions, thrownError) {
                   alert(xhr.status);
                   alert(thrownError);
               }
           });
       }
   });
});