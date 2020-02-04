$(() => {
    $('#btnLogin').click((e) => {
        $('#spinny').show();//spinner per segnalare caricamento
        e.preventDefault();//evita al tag <form> di inviare dato senza passare da ajax
        $.ajax({
            url: '/login',
            method: 'post',
            data: {
                username: $('#username').val(),
                password: $('#password').val()
            }
        })
        .done((utente) => {
            if(utente) {
                localStorage.setItem('userCTFLY', JSON.stringify(utente));
                location.href = '/home.html';
            } else {
                $('#error').modal('show');//credenziali errate
                $('#spinny').hide();
            }
        })
    })

})