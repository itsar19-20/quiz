$(() => {
    var utente = JSON.parse(localStorage.getItem('user'));
    setInterval(() => {
        if(localStorage.getItem('user')) {
        
        } else {
            alert('Sessione scaduta!')
            location.href = 'login.html';
        }
    }, 10000);
    if(utente.admin == 0) {
        $('#modgest').hide();
    }
    $('#logout').click(() => {
        localStorage.removeItem('user');
    })
    $('#toggle').click(function() {
        $('#sidebar').css('width', '250')
    });
    $('#close').click(function() {
        $('#sidebar').css('width', '0')
    });
})