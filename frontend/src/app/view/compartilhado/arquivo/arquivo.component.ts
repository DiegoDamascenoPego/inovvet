import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Arquivo, ArquivoView } from 'app/model/arquivo/arquivo';
import { Console } from 'console';
import { Ng2ImgMaxService } from 'ng2-img-max';
import { InputFile } from 'ngx-input-file';
import { DefinicaoArquivo } from './../../../model/arquivo/arquivo';
import { ArquivoService } from './../../../service/cadastro/arquivo.service';

@Component({
   selector: 'app-arquivo',
   templateUrl: './arquivo.component.html',
   styleUrls: ['./arquivo.component.css']
})
export class ArquivoComponent implements OnInit {
   @Input() definicao: DefinicaoArquivo;
   @Input() arquivos: Arquivo[];
   @Output() changeAtualizar = new EventEmitter;

   inputFiles: InputFile[] = [];

   constructor(
      private service: ArquivoService,
      private ng2ImgMax: Ng2ImgMaxService
   ) { }

   ngOnInit(): void {

      if (this.arquivos) {
         this.arquivos.forEach(arquivo => {
            this.inputFiles.push({ id: arquivo.id, link: arquivo.url, preview: arquivo.url });
         })
      }

   }

   public salvarImagem(file: File): Promise<any> {
      const arquivo: ArquivoView = new ArquivoView();
      arquivo.referencia = this.definicao.referencia;
      arquivo.referenciaId = this.definicao.referenciaId;
      arquivo.modulo = this.definicao.modulo;
      arquivo.tipo = this.definicao.tipo;
      arquivo.nome = file.name;
      arquivo.file = file;

      return this.service.salvar(arquivo)
         .then(()=> console.log('Enviado com sucesso'))
         .catch(error => console.log(error));
         
   }

   onNovaImagem(input: InputFile) {
      this.onReziseImage(input);
   }

   onCompressImage(file: any) {
      this.ng2ImgMax.compressImage(file, 0.075).subscribe(
         imagem => {
            this.salvarImagem(imagem).then((response) => {
               this.arquivos.push(response);
            });
         },
         error => {
            console.log('😢 Oh no!', error);
         }
      );
   }

   onReziseImage(input: InputFile) {
      this.ng2ImgMax.resizeImage(input.file, 600, 480).subscribe(
         imagem => {
            this.onCompressImage(imagem);
         },
         error => {
            console.log('😢 Oh no!', error);
         }
      );
   }

   onRemoverImagem(input: InputFile) {
      this.arquivos.forEach(imagem => {
         
         if (imagem.id === input.id) {
            this.service.removerImagem(imagem.id).then((response) => {
               const index = this.arquivos.indexOf(imagem, 0);
               if (index > -1) {
                  this.arquivos.splice(index, 1);
                  this.changeAtualizar.emit(this.arquivos);
               }
            });
         }
      });
   }

}
