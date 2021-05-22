import { Component, Injector } from '@angular/core';
import { AbstractControl, FormGroup, Validators } from '@angular/forms';
import { EnumModulo } from 'app/model/arquivo/enum-modulo.enum';
import { EnumTipoArquivo } from 'app/model/arquivo/enum-tipo-arquivo.enum';
import { Preco, PrecoCalcular } from 'app/model/produto/preco';
import { Produto } from 'app/model/produto/produto';
import { Loja } from 'app/model/Sistema/loja';
import { LojaService } from 'app/service/cadastro/loja.service';
import { TipoProdService } from 'app/service/cadastro/produto/tipo-prod.service';
import { FormBase } from 'app/view/compartilhado/base/form-base';
import { FacebookService, UIParams, UIResponse } from 'ngx-facebook';
import { ProdutoService } from '../../../../service/cadastro/produto/produto.service';
import { Arquivo, DefinicaoArquivo } from './../../../../model/arquivo/arquivo';
import { EnumReferenciaArquivo } from './../../../../model/arquivo/enum-referencia-arquivo.enum';
import { ClassificacaoOpcoes } from './../../../../model/classificao/classificao';
import { EntityBase, SimpleEntity } from './../../../../model/entity-base';
import { UnidadeService } from './../../../../service/cadastro/produto/unidade.service';

export enum TypeCadastro {
   PRODUTO,
   SERVICO
}

@Component({
   selector: 'app-cadastro-produto-form',
   templateUrl: './cadastro-produto-form.component.html',
})
export class CadastroProdutoFormComponent extends FormBase<Produto> {

   form: FormGroup;
   precoCalcular: PrecoCalcular;

   tipos: SimpleEntity[] = [];
   unidades: SimpleEntity[] = [];
   showOverlayMarca = false;

   classificacao: ClassificacaoOpcoes = new ClassificacaoOpcoes();
   marca: EntityBase = new EntityBase();

   // tslint:disable-next-line:max-line-length
   definicaoArquivo: DefinicaoArquivo = { tipo: EnumTipoArquivo.IMAGEM, modulo: EnumModulo.CADASTRO, referencia: EnumReferenciaArquivo.PRODUTO, referenciaId: 0 }
   arquivos: Arquivo[] = [];
   precos: Preco[] = [];

   CADASTRO = TypeCadastro

   TIPOCADASTRO = TypeCadastro.PRODUTO;

   loja: Loja;
   


   constructor(
      protected service: ProdutoService,
      protected injector: Injector,
      private fbc: FacebookService, 
      lojaService: LojaService

   ) {
      super(service, injector);
      
      fbc.init({
         appId: '581648679139235',
         xfbml: true,
         version: 'v2.9'
       });

      this.tabCount = 2;

      this.unidades = this.route.snapshot.data.unidades;
      this.tipos = this.route.snapshot.data.tipos;

      lojaService.getLoja().subscribe(loja => this.loja = loja);

      
   }

   private disableControl(control: AbstractControl){
      control.disable();
   }

   private enableControl(control: AbstractControl){
      control.enable();
   }

   setForm(entity: Produto) {
      this.form = this.fb.group({
         id: [{ value: entity.id, disabled: true }],
         nome: [entity.nome, [Validators.required, Validators.minLength(2), Validators.maxLength(70)]],
         descricao: [entity.descricao, [Validators.minLength(0), Validators.maxLength(120)]],
         unidade: [entity.unidade, Validators.required],
         tipo: [entity.tipo, Validators.required],
         ean: [entity.ean, [Validators.minLength(7), Validators.maxLength(14),]],
         observacao: [''],
         ativo: [entity.ativo]
      });
   }

   newEntity(): Produto {
      return new Produto();
   }

   setFormToEntity() {
      this.entity = this.form.getRawValue() as Produto;
      this.entity.classificacao = this.classificacao;
      this.entity.marca = this.marca;
      this.entity.arquivos = this.arquivos;
      this.entity.precos = this.precos;
   }

   setEntity(response: any) {
      this.entity = response;
      this.classificacao = response.classificacao;
      this.marca = response.marca;
      this.arquivos = response.arquivos;
      this.definicaoArquivo.referenciaId = this.entity.id;
      this.precos = response.precos;
      this.setForm(this.entity);
   }

   onChangeClassificacao(event) {
      if (event) {
         this.classificacao = event;
      }
   }

   onChangeMarca(event) {
      if (event) {
         this.marca = event;
      }
   }

   onChangeArquivos(event) {
      if (event) {
         this.arquivos = event;
         console.log(event);
         
      }
   }

   onChangeTipo(event) {

      this.TIPOCADASTRO = event.nome === 'Serviço' ? TypeCadastro.SERVICO : TypeCadastro.PRODUTO;

      if (this.TIPOCADASTRO === TypeCadastro.SERVICO) {
         this.disableControl(this.form.controls['ean']);
         this.disableControl(this.form.controls['observacao']);
      } else {
         this.enableControl(this.form.controls['ean']);
         this.disableControl(this.form.controls['observacao']);
      }

   }

   onChangePreco(event) {
      this.precos = event;
   }

   compare(o1: any, o2: any) {
      if (o1.id === o2.id) {
         return true;
      } else { return false }
   }


   share() {

      let params: UIParams = {
        href: 'http://www.inovvet.com.br/site/vitrine/'+this.loja.token+'/'+this.entity.id,
        method: 'share',
        quote:'Confira nossas ofertas',
      };
    
      this.fbc.ui(params)
        .then((res: UIResponse) => console.log(res))
        .catch((e: any) => console.error(e));
    
    }

}
