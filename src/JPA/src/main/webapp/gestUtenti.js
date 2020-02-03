$(() => {
    var userList;
    var utente = JSON.parse(localStorage.getItem('user'));
    var lengthmod;
    $('#name').text(`${utente.username}`);
    $.ajax({
        url: '/uc',
        method: 'post',
        data: {
            action: 'search'
        }
    })
        .done((utenti) => {
            userList = utenti;
            lengthmod = userList.length;
            var i = 0;
            userList.forEach(u => {
                $('#tabb').append(`<tr class='line' id='${i}'>` +
                    `<td data-id='${i}' id='user${i}'>${u.username}</td>` +
                    `<td data-id='${i}' id='email${i}'>${u.email}</td>` +
                    `<td data-id='${i}'>${u.nazionalita}</td>` +
                    `<td class='punteggio' data-id='${i}'><span id='punt${i}'>${u.punteggio}</span><input class='puntmod' id='pm${i}' value='${u.punteggio}'></td>` +
                    `<td data-id='${i}'>${u.dataiscrizione}</td>` +
                    "</tr>");
                i++;
            })
        })

        

    $('#inpUser').keyup(() => {
        var temp = $('#inpUser').val();
        var i = 0;
        for (i = 0; i < lengthmod; i++) {
            var user = $('#user' + i).text();
            if (!user.startsWith(temp)) {
                $('#' + i).hide();
            } else {
                $('#' + i).show();
            }
        }
    });

    var idline;

    $("#tabb tbody").on("click", ".punteggio", function () {
        idline = $(this).data('id');
        console.log(idline);
       $(`#punt${idline}`).hide();
       $(`#pm${idline}`).show();
    });

    $(`pm${idline}`).focusout(() => {
        
    })

});
