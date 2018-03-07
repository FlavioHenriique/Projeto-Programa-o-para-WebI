 $('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year,
    today: 'Hoje',
    clear: 'Limpar',
    format:'dd/mm/yyyy',
    close: 'Ok',
    closeOnSelect: false // Close upon selecting a date,
  });