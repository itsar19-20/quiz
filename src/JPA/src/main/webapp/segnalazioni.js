

 var utente = JSON.parse(localStorage.getItem('user'));
 $('#name').text(`${utente.username}`);
 
 $('#tipoSegn').text('Segnalazioni non risolte');
 var flag = false;
 
$.ajax({
    url: '/sc',
    method: 'post'
})
    .done((mod) => {
        modList = JSON.parse(mod);
  
      console.log(modList) 

 /*lista negativa*/ 
        var nonRisList=[];
      
        var o =0; 
         modList.forEach(el =>{
            if(!el.risolta ){
            nonRisList[o]= el;
          };
         
         o++
        
        }
         )     
     /*   console.log(nonRisList);*/


         lengthmod = modList.length;
        var i = 0;
        nonRisList.forEach(u => {   
            $('#tabbIrrisolte').append(
                `<tr class='line' id='${i}'>` +    
                `<td data-id='${i}' id='autore${i}'>${u.autore.username}</td>` + 
                `<td data-id='${i}' id='data${i}'>${u.data}</td>` +
                `<td data-id='${i}' id='motivazione${i}'>${u.motivazione}</td>` +
               "</tr>");
                     
            i++;
        });
 /*lista positiva*/

   var RisList=[];

var w =0; 
 modList.forEach(el =>{
    if(el.risolta ){

       RisList[w]= el;
  }   
  ;
  w++
}
 )     

 console.log(RisList)


 lengthmod = modList.length;
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
 /*lista positiva*/   


/*bottone*/


$('#bott').click(() => {
    if (flag){
        $('#tipoSegn').text('Segnalazioni non risolte');
        $('#bott').css('background-color', 'red');
        $('#bott').css('border','red');
        $('#tabbIrrisolte').show();
        $('#tabbRisolte').hide();
        flag=false; 
    }else {$('#tipoSegn').text('Segnalazioni  risolte');
           $('#bott').ss('background-color', 'green');
           $('#bott').css('border','green');
           $('#tabbIrrisolte').hide();
           $('#tabbRisolte').show();
           flag =true; };
        
  
                     
  
} )


})