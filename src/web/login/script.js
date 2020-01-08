var $btn = document.getElementById("submit");
var $form = document.getElementById("form")

function signIn() {
  if ($form.checkValidity()) {
    $btn.classList.add('pending');
    window.setTimeout(function(){ $btn.classList.add('granted'); }, 500);
    window.open ('../panel/index.html','_self',false);
  }
}

$btn.addEventListener("click", signIn);
