$(() => {
    var user1=null;
    var user2=null;
    $('#registra').click(() => {
        console.log($('#username').val());
        console.log($('#password').val());
        $.ajax({
            url: "/Test",
            method: "post",
            data: {
                username: $('#username').val(),
                password: $('#password').val(),
            }
        })
        .done((utente) => {
            console.log(utente);
            if(user1==null){
                user1 = utente;
            } else {
                user2 = utente;
            }
            if(user1!=null&&user2!=null) {
                usern1 = user1.username;
                usern2 = user2.username;
                $.ajax({
                    url: "/Test2",
                    method: "post",
                    data: {
                        username1: usern1,
                        username2: usern2
                    }
                })
                .done((amichi) => {
                    console.log(amichi[0].amico);
                })
            }
        })
    })
    

})