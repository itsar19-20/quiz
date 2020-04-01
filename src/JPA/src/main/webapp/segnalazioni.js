$(() => {   

 var utente = JSON.parse(localStorage.getItem('userCTFLY'));
 
 
 
 $('#name').text(`${utente.username}`);
 
 $('#tipoSegn').text('Segnalazioni non risolte');
 
 var listSegn;
 

$.ajax({
    url: '/sc',
    method:'get'
})
    .done((segn) => {
     listSegn=segn;
     

        //console.log(listSegn);


//Generazione liste
       var nonRisList=[];
       var RisList=[];
       var ConsegnaList=[];
       var o =0; 
       var w =0;
       var f =0;
       
       listSegn.forEach(s=> {
           if((s.risolta) ){
              RisList[o]=s;
              o++ 
           }else{
              if ((s.tipo=="spoiler") &&
                 (!(s.inLavorazione))){
                    nonRisList[w]=s
                    w ++}
              
              if ((s.tipo=="generica") &&
                  (!(s.inLavorazione)) 
                  ){
                      if(s.consegna== null){
                        nonRisList[w]=s
                        w ++}else{ 
                        
                      if(s.consegna.username==utente.username){
                        ConsegnaList[f]=s
                        f++ }
                      }  
                  
                  
                    }
                } 

           })
              
            
            
            
            
           
           
           
       console.log(utente.username ) 
       //console.log(nonRisList)     
       
//Generazione tabelle


//lista negativa
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
     
//tabella consegna
var i = 0;
nonRisList.forEach(u => {   
    $('#ConsegnaList').append(
        `<tr class='line' id='${i}'>` +    
        `<td data-id='${i}' id='autore${i}'>${u.autore.username}</td>` + 
        `<td data-id='${i}' id='data${i}'>${u.data}</td>` +
        `<td data-id='${i}' id='tipo${i}'>${u.tipo}</td>` +
       "</tr>");
             
    i++;
});
     



 /////////////////////////////////////////////////////////////////////////////////
 ///    MODAL IRRISOLTE
 ////////////////////////////////////////////////////////////////////////////////
 
 //Genera i modal
 var idline
          var segnId 
             
          $( "#tabbIrrisolte tbody" ).on( "click", ".line td", function() { 
            idline = $(this).data('id');           
            tipoline=$(this).data('tipo');
            segnId=  (nonRisList[idline].id);
            console.log(segnId);
            var tipoline = $(`#tipo${idline}`).text();                                    
            if (tipoline == "generica"){
            $('#risolviSegnG').modal('show');      
                $('#testoDesc').text(nonRisList[idline].descrizione);
            }
            if (tipoline=="spoiler"){
                $('#risolviSegnS').modal('show');
                $('#testoCom').text(nonRisList[idline].comm.testo)
            };
            
             }); 


                 
             
                          
             //Imposta la lavorazione 
               
             $('#risolviSegnG').on('show.bs.modal', function () {
                $.ajax({
                    url: "/sc", 
                    method: "post",
                    data: {
                        azione:"nascondi",
                        stato: true ,
                        segn:segnId,
                    }
                })
             }) 
            
             
             $('#risolviSegnS').on('show.bs.modal', function () {
                $.ajax({
                    url: "/sc", 
                    method: "post",
                    data: {
                        azione:"nascondi",
                        stato: true ,
                        segn:segnId,
                    }
                })
             })
           
           
           
             
            //Togli lavorazione 

           
           
            
            $('#risolviSegnG').on('hide.bs.modal', function () {
            $.ajax({
                url: "/sc", 
                method:"post",
                data: {
                    azione:"nascondi",
                    stato: false ,
                    segn:segnId,
                }
            })
            })
           
            $('#risolviSegnS').on('hide.bs.modal', function () {
                $.ajax({
                    url: "/sc", 
                    method:"post",
                    data: {
                        azione:"nascondi",
                        stato: false ,
                        segn:segnId,
                    }
                })
                })
              

// risolvi spoiler
                var spoiler ;
                $('#spoiler').click(() => {
                    var spoiler = true
                    $.ajax({
                        url:"/sc", 
                        method: "post",
                        data: {
                            azione:"rspoiler",
                            segn: segnId,
                            user: utente.username,
                            spoiler: spoiler,
                        }
                    })
                    .done(() => {
                       location.reload(true);
                    })
                })
            

                $('#nonSpoiler').click(() => {
                    var spoiler = false
                    $.ajax({
                        url:"/sc", 
                        method: "post",
                        data: {
                            azione:"rspoiler",
                            segn: segnId,
                            user: utente.username,
                            spoiler: spoiler,
                        }
                        
                    })
                    .done(() => {
                       location.reload(true);
                    })
                })

// cosnegna generica
$('#consegna').click(() =>{
    $.ajax({
        url:"/sc", 
        method: "post",
        data: {
            azione:"gconsegna",
            segn:segnId,
            user: utente.username,
        }
        }) .done(() => {
            location.reload(true);
         })
     })
    
             
///////////////////////////////////////////////////////////////////////////////     
 //lista positiva

var e = 0;
RisList.forEach(u => {   
    $('#tabbRisolte').append(
        `<tr class='line' id='${i}'>` +    
        `<td data-id='${i}' id='autore${i}'>${u.autore.username}</td>` + 
        `<td data-id='${i}' id='data${i}'>${u.data}</td>` +
        `<td data-id='${i}' id='tipo${i}'>${u.tipo}</td>` +
      `<td data-id='${i}' id='risolutore${i}'> ${u.risolutore.username } </td>` +
        "</tr>");
             
    e++;
});
   

//bottone cambia lista

var flag = false;

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
})
        
 //bottone consegne
 
 tabbConsegna
 bottConsegne
 var f= true;  

 $('#bottConsegne').click(()=>{
    if(f){         
      $('#tipoSegn').text('Le mie consegne');
      $('#bottConsegne').css('background-color', 'white'); 
      $('#bottConsegne').css('color', 'black'); 
      $('#tabbRisolte').hide();
      $('#tabbIrrisolte').hide();
      $('#bott').hide();
      $('#tabbConsegna').show();
      f= false;
    }else{
        if(flag){
        $('#tipoSegn').text('Segnalazioni  risolte');
           $('#bott').css('background-color', 'green');
           $('#bott').css('border','green');
           $('#tabbIrrisolte').hide();  
           $('#tabbRisolte').show();
           $('#bott').show();
        } else {
        $('#tipoSegn').text('Segnalazioni non risolte');
        $('#bott').css('background-color', 'red');
        $('#bott').css('border','red');
        $('#tabbIrrisolte').show();
        $('#tabbRisolte').hide();
        $('#bott').show();    
    };
        $('#tabbConsegna').hide(); 
        $('#bottConsegne').css('background-color', 'black'); 
        $('#bottConsegne').css('color', 'white');
        f= true;
    }
        })  
                     
  
} )


})
    
