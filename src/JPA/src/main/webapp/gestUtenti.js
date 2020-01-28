$(() => {
    $('#btnUsername').click(() => {
        $.ajax({
            url: '/uc',
            method: 'post',
            data: {
                username: $('#inpUser').val(),
                action: 'search'
            }
        })
            .done((utente) => {
                if (utente) {
                    $('#temp').remove();
                    $("#tabb tbody").prepend("<tr id = 'temp'><td id = 'username'>" + `${utente.username}` +
                        "</td><td>" + `${utente.email}` + "</td><td>" + `${utente.nazionalita}` +
                        "</td><td>" + `${utente.punteggio}` + "</td><td>" + `${utente.dataiscrizione}` + "</td></tr>");
                    $('#removebtn').show();
                } else {
                    $('#errorNF').show();
                    setTimeout(() => {
                        $('#errorNF').hide();
                    }, 2000);
                }
            })
    })
});
$('#removebtn').click(() => {
    $.post({
        url: '/uc',
        data: {
            username: $('#username').text(),
            action: 'delete'
        }
    })
        .done(() => {
            $('#temp').remove();
            $('#removebtn').hide();
        })
        .fail(() => {
            $('#removebtn').hide();
        })
})