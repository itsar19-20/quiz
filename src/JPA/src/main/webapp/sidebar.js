$(() => {
    $('#logout').click(() => {
        localStorage.removeItem('user');
    })
    $('#toggle').click(function() {
        $('#sidebar').css('width', '250')
    });
    $('#close').click(function() {
        $('#sidebar').css('width', '0')
    });
})