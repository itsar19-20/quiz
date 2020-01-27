$(() => {
    $('#btnLogin').click((e) => {
        e.preventDefault();
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
                localStorage.setItem('user', JSON.stringify(utente));
                location.href = '/hometest.html';
            } else {
                $('#error').modal('show');
            }
        })
    })

})