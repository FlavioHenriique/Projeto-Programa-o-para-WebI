localStorage.removeItem("id");


$('.datepicker').pickadate({
    monthsFull: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
    monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    weekdaysFull: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sabádo'],
    weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
    selectMonths: true,
    selectYears: 160,
    today: 'Hoje',
    clear: 'Limpar',
    close: 'Ok',
    closeOnSelect: false,
    format: 'dd/mm/yyyy'
});

$(document).ready(function () {
    $('select').material_select();
});

function armazenarId(id){
    localStorage.setItem("id",id);
}
 
 function deletarLugar(){
     id = localStorage.getItem("id");
     
     if(id != null){
        window.location.replace("front?command=DeletaLugar&IdLugar="+id);
     }
     else swal("Opa...","Selecione um lugar para ser deletado!","warning");
 }