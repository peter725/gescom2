Documento temporal para recoger las ideas de como implementar esta funcionalidad de una forma desacoplada de las páginas
y de los componentes.

Crear un servicio que almacene todos los elementos que pueden ser fullscreen organizados por el ID del mismo.
El fullscreen utiliza la API de overlay de Angular material.

```typescript
class FullScreenRef {
  public inView = false;
    
  constructor(
      public readonly id: string,
      public readonly ref: TemplateRef,
  ) { }
  
  // en el futuro, si fuera necesario pordríamos añadir funciones del ciclo de vida
  // afterShow()
  // afterHide()
  // etc.
}

class FullScrrenService {
  elements: Map<string, FullScreenRef | TemplateRef>;

  // la referencia tiene que ser algo que angular puede instanciar o mostrar
  // de momento será template ref
  register(ref: TemplateRef, id?: string): FullScreenRef {
    // creates an ID and is returned with FullScreenRef
  }

  remove(ref: FulLScrrenRef | string) {
  }

  toggle(ref: FullScreenRef | string) {}

  // de momento privado, no sé si dejarlo público
  // se podrían quizás mover a FullScrrenRef
  private show(ref: FullScreenRef) {}

  // de momento privado, no sé si dejarlo público
  // se podrían quizás mover a FullScrrenRef
  private hide(ref: FullScreenRef) {}
}
```

Utilizar una directiva que realice la selección del elemento que puede ser full screen.
Esta selección debe permitir indicar manualmente un ID en formato string.
El selector de la directiva **no debe controlar** el proceso de instanciar la vista, esta debe ser autogenerada por
angular y se haya actualizado en todo momento.

En caso de no indicar manualmente el ID, se puede autogenerar uno, pero podría acabar obligando al componente que tenga
conocimientos acerca de la funcionalidad fullscreen y se perdería el desacoplamiento.

Crear una directiva y/o componente botón que permita abrir/cerrar el elemento fullscreen interactuando con el servicio.
Recibiría un FullScreenRef o un ID string que luego será enviado al servicio.


En el caso de onDestroy quizás sería una buena idea eliminar el elemento del servicio para evitar tener datos
innecesarios en memoria.
