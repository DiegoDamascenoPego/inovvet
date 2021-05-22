import { NgModule } from '@angular/core';
import { Ng2ImgMaxModule } from 'ng2-img-max';
import { FacebookModule } from 'ngx-facebook';
import { InputFileConfig, InputFileModule } from 'ngx-input-file';

const config: InputFileConfig = {
  fileAccept: '*',
  fileLimit: 2
};

@NgModule({
  declarations: [],
  imports: [
    InputFileModule.forRoot(config),
    Ng2ImgMaxModule,
    FacebookModule.forRoot()
  ],
  exports: [    
    InputFileModule,
    Ng2ImgMaxModule,
    FacebookModule
  ],
  
})
export class ExtrasModule { }
