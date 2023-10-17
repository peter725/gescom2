import { AfterViewChecked, Component, ElementRef, Input } from '@angular/core';
import { IconType } from './models';


/**
 * Componente icono que permite utilizar MatIconModule
 * o feather icons
 */
@Component({
  selector: 'mat2-icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.scss'],
})
export class IconComponent implements AfterViewChecked {

  /**
   * Shown icon type: material or css (feather, font-awesome). Default is material.
   */
  private _type: IconType = 'mat';
  /**
   * Icon description
   */
  @Input() alt = '';
  /**
   * Icon size expressed in px or rem.
   * Default is 24px.
   */
  @Input() size = '24px';

  private forcedType: IconType | undefined;
  private activeIcon: string | undefined;

  constructor(private elementRef: ElementRef<HTMLElement>) {
  }

  get icon() {
    return this.activeIcon || '';
  }

  @Input()
  set type(value: IconType) {
    this.forcedType = value;
    this._type = value;
  }

  get type() {
    return this._type;
  }

  ngAfterViewChecked(): void {
    this.updateActiveIcon();
  }

  private updateActiveIcon() {
    const firstChild = this.elementRef.nativeElement.firstChild as HTMLElement;
    const icon = firstChild?.innerText || '';
    const type = this.determineIconType(icon);

    // Avoids expression changed after check error
    setTimeout(() => {
      if (icon !== this.activeIcon) this.activeIcon = icon;
      if (type !== this.type) this._type = type;
    });
  }

  private determineIconType(icon: string): IconType {
    if (this.forcedType) return this.forcedType;

    if (icon.startsWith('icon')) {
      return 'css';
    }

    return 'mat';
  }
}
