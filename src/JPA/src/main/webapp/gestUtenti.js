$(() => {
    $('#btnUsername').click(() => {
        $('#spinnybtn').show();
        $.ajax({
            url: '/uc',
            method: 'post',
            data: {
                username: $('#inpUser').val(),
                action: 'search'
            }
        })
            .done((utente) => {
                $('#spinnybtn').hide();
                if (utente) {
                    $('#temp').remove();
                    $('#temp2').hide();
                    $("#tabb tbody").prepend("<tr id = 'temp'><td id = 'username'>" + `${utente.username}` +
                        "</td><td>" + `${utente.email}` + "</td><td>" + `${utente.nazionalita}` +
                        "</td><td>" + `${utente.punteggio}` + "</td><td>" + `${utente.dataiscrizione}` + "</td></tr>");
                    $('#removebtn').show();
                } else {
                    $('#temp').remove();
                    $('#removebtn').hide();
                    $('#errorNF').show();
                    $('#temp2').show();
                    setTimeout(() => {
                        $('#errorNF').fadeOut(speed = 300);
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