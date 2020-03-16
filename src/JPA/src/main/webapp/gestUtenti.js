$(() => {
    var userList; //variabile per salvare la lista di utenti
    var utente = JSON.parse(localStorage.getItem('userCTFLY'));
    var lengthmod;
    $('#name').text(`${utente.username}`);
    $.ajax({
        url: '/uc', //usercontroller
        method: 'post',
        data: {
            action: 'search' //variabile per segnalare alla servlet l'azione da compiere
        }
    })
        .done((utenti) => {
            userList = utenti;
            console.log(userList);
            lengthmod = userList.length;
            var i = 0;
            userList.forEach(u => { //creazione della tabella
                $('#tabb').append(`<tr id='${i}'>` +
                    `<td id='user${i}'>${u.username}</td>` +
                    `<td>${u.email}</td>` +
                    `<td>${u.nazionalita}</td>` +
                    `<td><span class='togg${i}' id='punt${i}'>${u.punteggio}</span><input class='puntmod togg${i}' id='pm${i}' value='${u.punteggio}'></td>` +
                    `<td>${u.dataiscrizione}</td>` +
                    `<td><button data-id='${i}' type="button" class="btn btn-primary btnmod togg${i}">Modifica</button>
                     <button data-id='${i}' type="button" class="btn btn-primary puntmod btnsave togg${i}" id='pms${i}'>Salva</button></td>` + //bottoni per modificare il punteggio
                    `<td><button data-id='${i}' type="button" class="btn btn-danger btnrmv">X</button></td>` +
                    "</tr>");
                i++;
            })
        })

    $('#inpUser').keyup(() => { //barra di ricerca
        var temp = $('#inpUser').val();
        var i = 0;
        for (i = 0; i < lengthmod; i++) {
            var user = $('#user' + i).text();
            if (!user.startsWith(temp)) {
                $('#' + i).hide();
            } else {
                $('#' + i).show();
            }
        }
    });

    var idline; //variabile

    $("#tabb tbody").on("click", `.btnmod`, function () {
        idline = $(this).data('id');
        for(i=0; i<lengthmod; i++) {
            if($(`#pms${i}`).is(":visible") && i!=idline) {
                $(`.togg${i}`).toggle();
            }
        }
        $(`.togg${idline}`).toggle();
    });

    $("#tabb tbody").on("click", `.btnsave`, function () {
        idline = $(this).data('id');
        mod = $(`#pm${idline}`).val();
        if (!$.isNumeric(mod) || mod>2000000000) {
            alert("Modifica non valida!");
            location.reload(true);
        }
        console.log(mod);
        $.ajax({
            url: '/pac',
            method: 'post',
            data: {
                username: $(`#user${idline}`).text(),
                punteggio: mod
            }
        })
            .done(() => {
                location.reload(true);
            })
    });

    $("#tabb tbody").on("click", `.btnrmv`, function () {
        idline = $(this).data('id');
        $.ajax({
            url: '/uc',
            method: 'post',
            data: {
                username: $(`#user${idline}`).text(),
                action: 'delete'
            }
        })
            .done(() => {
                location.reload(true);
            })
    });
    
});
