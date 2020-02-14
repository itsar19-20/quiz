$(() => {
    var dataPoints = [];
    var option = {
        axisY: {
            title: "Users"
        }

    }
    $.ajax({
            url: '/UtentiOnline',
            method: 'post'
        })
        .done((list) => {
            if (list) {
               var c=list.length; 
                var chart = new CanvasJS.Chart("chartContainer", {
                    theme: "light2",
                    animationEnabled: true,
                    title: {
                        text: "Utenti"
                    },
                    data: [{
                        type: "column",
                        dataPoints: [{
                                label: "Totale",
                                y: c
                            }

                        ]

                    }]
                });
                chart.render();

            }

        })
})