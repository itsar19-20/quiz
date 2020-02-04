$.ajax({
    url: '/sc',
    method: 'post'
})
    .done((mod) => {
        modList = JSON.parse(mod);
        console.log(modList)
        lengthmod = modList.length;
        var i = 0;
        modList.forEach(u => {
            $('#tabb').append(`<tr class='line' id='${i}'>` + 
                `<td data-id='${i}' id='autore${i}'>${u.autore}</td>` + 
                `<td data-id='${i}' id='pass${i}'>${u.password}</td>` + 
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