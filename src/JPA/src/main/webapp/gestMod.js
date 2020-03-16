$(() => {
    var utente = JSON.parse(localStorage.getItem('userCTFLY'));
    console.log(utente);
    if(utente.admin==0) {
        location.href = 'home.html';
    }
    $('#filter').text("Tutti");//setta subito il testo del bottone su "tutti"
    var modList; //variabile per salvare la lista di moderatori
    var utente = JSON.parse(localStorage.getItem('userCTFLY'));
    var lengthmod; //variabile per salvare la lunghezza della lista
    $('#name').text(`${utente.username}`);//nome di chi ha effettuato l'accesso
    $.ajax({
        url: '/webus', //webusersearch
        method: 'post'
    })
        .done((mod) => {
            modList = JSON.parse(mod);
            lengthmod = modList.length;
            var i = 0;
            modList.forEach(u => { //crea la taballa secondo i dati ricevuti dal server
                $('#tabb').append(`<tr class='line' id='${i}'>` + 
                    `<td data-id='${i}' id='user${i}'>${u.username}</td>` + 
                    `<td data-id='${i}' id='pass${i}'>${u.password}</td>` + 
                    `<td data-id='${i}'><span class = 'admin' id='admin${i}'>${u.admin}</span><span class='dot' id='dot${i}'></span></td>` + //lo span è per poter controllare se l'utente di una determinata linea è moderatore
                "</tr>");
                if(u.admin) { //cambia il colore del pallino in base ai permessi da amministratore
                    $('#dot' + i).css("background-color","green");
                } else if(!u.admin) {
                    $('#dot' + i).css("background-color","red");
                }
                if(u.username==utente.username) { //rimuove dalla lista l'utente collegato
                    $('#' + i).remove();
                }
                i++;
            });
        })
    
    $('#insertbtn').click(() => { //pulsante per inserire moderatori
        $('#insertuser').modal('show');
    })

    $('#usernameIns').keyup(() => { //inserimento dell'username del nuovo utente
        var temp = $('#usernameIns').val();
        var i = 0;
        modList.forEach(u => {
            if(u.username==temp || utente.username==temp) { //se l'username esiste gia mostra un errore e toglie il pulsante per salvare
                $('#errorUser').show();
                $('#save').css("visibility", "hidden");
                i = 1;
            } else if (i==0) { //elimina il messaggio di errore se presente e fa riapparire il pulsante per salvare
                $('#errorUser').fadeOut(speed = 300); 
                $('#save').css("visibility", "visible");
            }
        })
    })

    $('#save').click(() => {
        var admin = "false";
        if ($('#adminIns').prop("checked")) { //se la checkbox è attivata setta la variabile admin a true
            admin = "true";
        }
        $.ajax({
            url: '/wua', //webuseradd
            method: 'post',
            data: {
                username: $('#usernameIns').val(),
                password: $('#passwordIns').val(),
                admin: admin
            }
        })
        .done(() => {
            location.reload(true); //dopo aver inserito un utente ricarica la pagina per mostrare le modifiche effettuate
        })
    })

    $('#userinput').focus(() => { //quando la barra di ricerca prende il focus sistema il pulsante di filtro e mostra di nuovo tutte le linee
        $('#filter').text("Tutti");
        $('#btnadmin').show();
        $('#btnmod').show();
        $('#btnall').hide();
        for(i = 0; i<lengthmod; i++) {
            $('#' + i).show();
        }
    })

    $('#userinput').keyup(() => { //barra di ricerca degli utenti
        var inp = $('#userinput').val();
        for(i = 0; i<lengthmod; i++) {
            var user = $('#user' + i).text();
            if(!user.startsWith(inp)) {
                $('#' + i).hide();
            } else {
                $('#' + i).show();
            }
        }
    })

    $('#btnadmin').click(() => { //filtro
        $('#userinput').val('');
        $('#filter').text("Admin");
        $('#btnall').show();
        $('#btnmod').show();
        $('#btnadmin').hide();
        for(i = 0; i<lengthmod; i++) {
            var admin = $('#admin' + i).text();
            if(admin=="true") {
                $('#' + i).show();
            } else {
                $('#' + i).hide();
            }
        }
    })

    $('#btnall').click(() => { //filtro
        $('#userinput').val('');
        $('#filter').text("Tutti");
        $('#btnadmin').show();
        $('#btnmod').show();
        $('#btnall').hide();
        for(i = 0; i<lengthmod; i++) {
            $('#' + i).show();
        }
    })

    $('#btnmod').click(() => { //filtro
        $('#userinput').val('');
        $('#filter').text("Moderatori");
        $('#btnall').show();
        $('#btnadmin').show();
        $('#btnmod').hide();
        for(i = 0; i<lengthmod; i++) {
            var admin = $('#admin' + i).text();
            if(admin=="false") {
                $('#' + i).show();
            } else {
                $('#' + i).hide();
            }
        }
    })

    var idline; //variabile per riferirsi alla linea in cui si sta operando

    $( "#tabb tbody" ).on( "click", ".line td", function() { //funzione per rendere clickabili le righe, editing degli utenti web
        idline = $(this).data('id');
        $('#userEdit').modal('show');
        var username = $(`#user${idline}`).text();
        var password = $(`#pass${idline}`).text();
        $('#usernameEdit').attr('value', username);
        $('#passwordEdit').attr('value', password);
        var admin = $(`#admin${idline}`).text();
        if (admin == "true") {
            $('#adminEdit').prop("checked", true);
        } else {
            $('#adminEdit').prop("checked", false);
        }
      }); 
    
    $('#saveEdit').click(() => { //pulsante di salvataggio delle modifiche
        if ($('#adminEdit').prop('checked')) {
            admin = "true";
        } else {
            admin = "false";
        }
        $.ajax({
            url:"/wuec", //webusereditcontroller
            method: "post",
            data: {
                username: $(`#user${idline}`).text(),
                password: $('#passwordEdit').val(),
                admin: admin
            }
        })
        .done(() => {
            location.reload(true);
        })
    })

    $('#remove').click(() => {
        $.ajax({
            url: "/wurc", //webuserremovecontroller
            method: "post",
            data: {
                username: $(`#user${idline}`).text()
            }
        })
        .done(() => {
            location.reload(true);
        })
    })
})