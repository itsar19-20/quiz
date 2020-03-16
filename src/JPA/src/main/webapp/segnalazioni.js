    

 var utente = JSON.parse(localStorage.getItem('user'));
 $('#name').text(`${utente.username}`);
 
 $('#tipoSegn').text('Segnalazioni non risolte');
 var flag = false;
 var listSegn;


$.ajax({
    url: '/sc',
    method: 'post'
})
    .done((segn) => {
     listSegn=segn;
     

        //console.log(listSegn);


//Generazione liste
       var nonRisList=[];
       var RisList=[];
       var o =0; 
       var w =0;
       
       listSegn.forEach(s=> {
           if(s.risolta){
              RisList[o]=s;
              o++ 
           }else{
               nonRisList[w]=s
                w ++}; 
       })
       //console.log(RisList) 
       //console.log(nonRisList)     
       
//Generazione tabelle

        var i = 0;
        nonRisList.forEach(u => {   
            $('#tabbIrrisolte').append(
                `<tr class='line' id='${i}'>` +    
                `<td data-id='${i}' id='autore${i}'>${u.autore.username}</td>` + 
                `<td data-id='${i}' id='data${i}'>${u.data}</td>` +
                `<td data-id='${i}' id='tipo${i}'>${u.tipo}</td>` +
               "</tr>");
                     
            i++;
        });
 //lista positiva

var e = 0;
RisList.forEach(u => {   
    $('#tabbRisolte').append(
        `<tr class='line' id='${i}'>` +    
        `<td data-id='${i}' id='autore${i}'>${u.autore.username}</td>` + 
        `<td data-id='${i}' id='data${i}'>${u.data}</td>` +
        `<td data-id='${i}' id='motivazione${i}'>${u.motivazione}</td>` +
      `<td data-id='${i}' id='risolutore${i}'> ${u.risolutore.username } </td>` +
        "</tr>");
             
    e++;
});
   
//bottone


$('#bott').click(() => {
    if (flag){
        $('#tipoSegn').text('Segnalazioni non risolte');
        $('#bott').css('background-color', 'red');
        $('#bott').css('border','red');
        $('#tabbIrrisolte').show();
        $('#tabbRisolte').hide();
        flag=false; 
    }else {$('#tipoSegn').text('Segnalazioni  risolte');
           $('#bott').css('background-color', 'green');
           $('#bott').css('border','green');
           $('#tabbIrrisolte').hide();  
           $('#tabbRisolte').show();
           flag =true; };
        
  
                     
  
} )


})


