$(() => {
    var utente = JSON.parse(localStorage.getItem('user'));
    $('#name').text(`${utente.username}`);
    $.ajax({
        url: '/webus',
        method: 'post'
    })
        .done((mod) => {
            var modList = JSON.parse(mod);
            var i = 0;
            modList.forEach(u => {
                $('#tabb').append("<tr class='line' id = '" + `${i}` + "'><td id = 'user" + `${i}` + "'>" + `${u.username}` + "</td><td id = 'pass" + `${i}` + "'>" + `${u.password}` + "</td><th><span class='dot' id='dot" + `${i}` + "'></span></th></tr>");
                if(u.admin) {
                    $('#dot' + `${i}`).css("background-color","green");
                } else if(!u.admin) {
                    $('#dot' + `${i}`).css("background-color","red");
                }
                if(u.username==utente.username) {
                    $('#' + `${i}`).remove();
                }
                i++;
            });
        })
    
    

    
})