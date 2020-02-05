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
                `<td data-id='${i}' id='autore${i}'>${u.autore_username}</td>` + 
                `<td data-id='${i}' id='data${i}'>${u.data}</td>` +
                `<td data-id='${i}' id='motivazione${i}'>${u.motivazione_username}</td>` +"</tr>");
                        
            i++;
        }
        );
    })