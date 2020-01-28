$(()=> {
    if(localStorage.getItem('user')) {
        var utente = JSON.parse(localStorage.getItem('user'));
        $('#nUser').text(`${utente.username}`);
    } else {
        location.href = 'login.html';
    }
});

