$(()=> {
    var utente = JSON.parse(localStorage.getItem('user'));
    $('#nUser').text(`${utente.username}`);
});

