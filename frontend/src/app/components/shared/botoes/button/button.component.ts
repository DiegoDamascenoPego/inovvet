import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

export enum TypeButton {
   PRIMARY,
   SECONDARY,
   SUCCESS,
   DANGER,
   WARNING,
   INFO,
   LIGHT,
   DARK
}

export enum TypeSizeButton {
   SMALL,
   LARGER,
   REGULAR
}

export enum TypeStyleButton {
   DEFAULT,
   OUTLINE,
   ROUND,
}

export enum TypeAlignButton {
   DEFAULT,
   RIGHT,
}

@Component({
   selector: 'app-button',
   templateUrl: './button.component.html',
   styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

   @Input() name: string;
   @Input() label: string;
   @Input() type = 'primary';
   @Input() size = 'regular';
   @Input() disabled = false;
   @Input() styles = 'default';
   @Input() align = 'right';

   _styles = '';
   _type = TypeButton.PRIMARY;
   _size = TypeSizeButton.REGULAR;
   _style = TypeStyleButton.DEFAULT;
   _align = TypeAlignButton.RIGHT;

   @Output() cClick = new EventEmitter();

   constructor() { }


   onClick(event) {
      this.cClick.emit(event);
   }

   ngOnInit(): void {
      this._type = TypeButton[this.type.toUpperCase()];
      this._style = TypeStyleButton[this.styles.toUpperCase()];
      this._size = TypeSizeButton[this.size.toUpperCase()];
      this._align = TypeAlignButton[this.align.toUpperCase()];
      this._styles = this.getButton() + this.getStyle() + this.getSize() + this.getAlign();
   }

   getButton(): string {
      let style = '';

      switch (this._style) {
         case TypeStyleButton.DEFAULT: style = 'btn '; break;
         case TypeStyleButton.OUTLINE: style = 'btn '; break;
         case TypeStyleButton.ROUND: style = 'btn btn-primary '; break;
         default: style = ' btn-primary '; break;
      }

      return style;
   }

   getStyle(): string {

      if (this._style === TypeStyleButton.OUTLINE) {
         let style = '';

         switch (this._type) {
            case TypeButton.PRIMARY: style = 'btn-outline-primary '; break;
            case TypeButton.SECONDARY: style = 'btn-outline-secondary '; break;
            case TypeButton.SUCCESS: style = 'btn-outline-success '; break;
            case TypeButton.DANGER: style = 'btn-outline-danger '; break;
            case TypeButton.WARNING: style = 'btn-outline-warning '; break;
            case TypeButton.INFO: style = 'btn-outline-info '; break;
            case TypeButton.LIGHT: style = 'btn-outline-light '; break;
            case TypeButton.DARK: style = 'btn-outline-dark '; break;
            default: style = 'btn-outline-primary '; break;
         }

         return style;
      } else {
         let style = '';

         switch (this._type) {
            case TypeButton.PRIMARY: style = 'btn-primary '; break;
            case TypeButton.SECONDARY: style = 'btn-secondary '; break;
            case TypeButton.SUCCESS: style = 'btn-success '; break;
            case TypeButton.DANGER: style = 'btn-danger '; break;
            case TypeButton.WARNING: style = 'btn-warning '; break;
            case TypeButton.INFO: style = 'btn-info '; break;
            case TypeButton.LIGHT: style = 'btn-light '; break;
            case TypeButton.DARK: style = 'btn-dark '; break;
            default: style = ' btn-primary '; break;
         }

         return style;
      }

   }

   getAlign(): string {
      let style = '';

      switch (this._align) {
         case TypeAlignButton.RIGHT: style = ' pull-right '; break;
         case TypeAlignButton.DEFAULT: style = ' '; break;

         default: style = ''; break;
      }

      return style;
   }

   getSize(): string {
      let style = '';

      switch (this._size) {
         case TypeSizeButton.SMALL: style = ' btn-sm '; break;
         case TypeSizeButton.LARGER: style = ' btn-lg '; break;

         default: style = ''; break;
      }

      return style;
   }

}
