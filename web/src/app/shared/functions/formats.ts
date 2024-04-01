
export class Formats {

  // formatFieldNumber(form: FormGroup, fieldName: string): void {
  //   const value = form.get(fieldName)?.value;
  //   form.get(fieldName)?.setValue(this.formatNumber(value));
  // }
  
  formatNumber(value: number | null | undefined): string {
    return (value || 0).toLocaleString('es-ES', { minimumFractionDigits: 2 });
  }
    
  formatDate(dateString: string): string {
    const fechaLimite = new Date(dateString);
    const options: Intl.DateTimeFormatOptions = { year: 'numeric', month: 'numeric', day: 'numeric' };
    const formattedFechaLimite = fechaLimite.toLocaleDateString('es-ES', options);
    return formattedFechaLimite;
  }

  formatDateTime(dateTimeString: string): { fecha: string, hora: string } {
    const [fecha, hora] = dateTimeString.split(' '); // Dividir la cadena en fecha y hora
  
    const fechaObj = new Date(fecha);
    const horaObj = new Date('1970-01-01T' + hora + 'Z'); // Agregar la hora a la fecha de referencia
  
    // Opciones para formatear la fecha
    const fechaOptions: Intl.DateTimeFormatOptions = { year: 'numeric', month: 'numeric', day: 'numeric' };
    // Opciones para formatear la hora sin mostrar los segundos
    const horaOptions: Intl.DateTimeFormatOptions = { hour: 'numeric', minute: 'numeric' };
  
    // Formatear la fecha y la hora por separado
    const formattedFecha = fechaObj.toLocaleDateString('es-ES', fechaOptions);
    const formattedHora = horaObj.toLocaleTimeString('es-ES', horaOptions);
  
    // Devolver la fecha y la hora formateadas por separado
    return { fecha: formattedFecha, hora: formattedHora };
  }

}