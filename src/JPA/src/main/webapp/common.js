$(() => { //metodi implementati da tutte le pagine post login, da implementare in tutte le pagine dopo il login
    var utente = JSON.parse(localStorage.getItem('userCTFLY'));
    if(localStorage.getItem('userCTFLY')) {
        
    } else {
        location.href = 'login.html';
    }
    setInterval(() => { //controlla che nel localstorage ci sia un utente salvato, in caso contrario riporta alla pagina di login
        if(localStorage.getItem('userCTFLY')) {
        
        } else {
            alert('Sessione scaduta!')
            location.href = 'login.html';
        }
    }, 10000);
    if(utente.admin == 0) { //nasconde la sezione Gestione Moderatori se non si è amministratori
        $('#modgest').hide();
    }
    $('#logout').click(() => {
        localStorage.removeItem('userCTFLY');
    })
    //apre e chiude la sidebar
    $('#toggle').click(function() {
        $('#sidebar').css('width', '250')
    });
    $('#close').click(function() {
        $('#sidebar').css('width', '0')
    });
})