$(()=> {
    if(localStorage.getItem('user')) {
        var utente = JSON.parse(localStorage.getItem('user'));
        $('#nUser').text(`${utente.username}`);
    } else {
        location.href = 'login.html';
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
});

