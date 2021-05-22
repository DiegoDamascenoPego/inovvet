import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
export interface DialogData {
   id: string;
   descricao: string;
   titulo: string;
}

@Component({
   selector: 'app-dialog',
   templateUrl: './dialog.component.html',
   styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit {

   constructor(public dialogRef: MatDialogRef<DialogComponent>,
      @Inject(MAT_DIALOG_DATA) public data: DialogData) {
      if (data.titulo == '') {
         data.titulo = 'Confirma a exclusão do registro?';
      }
   }

   ngOnInit() {
   }

   onNoClick(): void {
      this.dialogRef.close();
   }

}
