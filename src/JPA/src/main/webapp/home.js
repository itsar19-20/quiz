$(()=> {
    var utente = JSON.parse(localStorage.getItem('userCTFLY'));
    $('#nUser').text(`${utente.username}`);
});

