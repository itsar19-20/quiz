$(() => {
    $.ajax({
        url: '/uc',
        method: 'post',
        data: {
            action: 'search'
        }
    })
        .done((userList) => {
            
            lengthmod = userList.length;
            var i = 0;
            userList.forEach(u => {
                $('#tabb').append(`<tr class='line' id='${i}'>` +
                    `<td data-id='${i}' id='user${i}'>${u.username}</td>` +
                    `<td data-id='${i}' id='email${i}'>${u.email}</td>` +
                    `<td data-id='${i}'>${u.nazionalita}</td>` +
                    `<td data-id='${i}'>${u.punteggio}</td>` +
                    `<td data-id='${i}'>${u.dataiscrizione}</td>` +
                    "</tr>");
            })
        })
        .fail(() => {
            console.log('Madonnaputtana');
        })

    $('#removebtn').click(() => {
        $.post({
            url: '/uc',
            data: {
                username: $('#username').text(),
                action: 'delete'
            }
        })
            .done(() => {

            })
    })
});