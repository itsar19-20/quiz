$(() => {
    $('#filter').text("Tutti");
    var modList;
    var utente = JSON.parse(localStorage.getItem('user'));
    var lengthmod;
    $('#name').text(`${utente.username}`);
    $.ajax({
        url: '/webus',
        method: 'post'
    })
        .done((mod) => {
            modList = JSON.parse(mod);
            lengthmod = modList.length;
            console.log(modList);
            var i = 0;
            modList.forEach(u => {
                $('#tabb').append(`<tr class='line' id='${i}'>` + 
                    `<td data-id='${i}' id='user${i}'>${u.username}</td>` + 
                    `<td data-id='${i}'>${u.password}</td>` + 
                    `<td data-id='${i}'><span class = 'admin' id='admin${i}'>${u.admin}</span><span class='dot' id='dot${i}'></span></td>` + 
                "</tr>");
                if(u.admin) {
                    $('#dot' + i).css("background-color","green");
                } else if(!u.admin) {
                    $('#dot' + i).css("background-color","red");
                }
                if(u.username==utente.username) {
                    $('#' + i).remove();
                }
                i++;
            });
        })
    
    $('#insertbtn').click(() => {
        $('#insertuser').modal('show');
    })

    $('#usernameIns').keyup(() => {
        var temp = $('#usernameIns').val();
        var i = 0;
        modList.forEach(u => {
            if(u.username==temp || utente.username==temp) {
                $('#errorUser').show();
                $('#save').css("visibility", "hidden");
                i = 1;
            } else if (i==0) {
                $('#errorUser').fadeOut(speed = 300);
                $('#save').css("visibility", "visible");
            }
        })
    })

    $('#save').click(() => {
        var admin = "false";
        if ($('#adminIns').prop("checked")) {
            admin = "true";
        }
        $.ajax({
            url: '/wua',
            method: 'post',
            data: {
                username: $('#usernameIns').val(),
                password: $('#passwordIns').val(),
                admin: admin
            }
        })
        .done(() => {
            location.reload(true);
        })
    })

    $('#userinput').focus(() => {
        $('#filter').text("Tutti");
        $('#btnadmin').show();
        $('#btnmod').show();
        $('#btnall').hide();
        for(i = 0; i<lengthmod; i++) {
            $('#' + i).show();
        }
    })

    $('#userinput').keyup(() => {
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

    $('#btnadmin').click(() => {
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

    $('#btnall').click(() => {
        $('#userinput').val('');
        $('#filter').text("Tutti");
        $('#btnadmin').show();
        $('#btnmod').show();
        $('#btnall').hide();
        for(i = 0; i<lengthmod; i++) {
            $('#' + i).show();
        }
    })

    $('#btnmod').click(() => {
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

    $( "#tabb tbody" ).on( "click", ".line td", function() {
        var idline = $(this).data('id');
        console.log(idline);
        $('#userEdit').modal('show');
        var username = $(`#user${idline}`).text();
        console.log(username);
        $('#usernameEdit').attr('value', username);
      });    
})