import { FormGroup, Validators } from "@angular/forms";

export const passwordValidar: Validators = (formGroup: FormGroup) => {
   const senha = formGroup.get('senha').value;
   const senhaConfirmar = formGroup.get('senhaConfirmar').value;

   if (senha.trim() && senhaConfirmar.trim()) {
      return senha == senhaConfirmar ? null : { passwordValidar: true }
   }
   else {
      return null;
   }



}