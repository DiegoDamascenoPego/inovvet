import { Injector, OnInit, ViewChild, Directive } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractModel } from 'app/model/abstract-model';
import { FiltroUtil } from 'app/model/util/filtro-util';
import { SistemaService } from 'app/service/seguranca/sistema.service';
import { MensagemService } from 'app/util/mensagem.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { Abstract } from './../../../service/abstract.service';
import { DialogComponent } from './../modal/dialog/dialog.component';

@Directive()
export abstract class PesquisaBase<T extends AbstractModel> implements OnInit {

   @ViewChild(MatPaginator) paginator: MatPaginator;

   filtro: FiltroUtil = new FiltroUtil();
   form: FormGroup;
   public dialog: MatDialog;
   protected route: ActivatedRoute;
   protected router: Router;
   protected fb: FormBuilder;
   protected msg: MensagemService;
   private sistemaService: SistemaService;


   dataSource = new MatTableDataSource<T>();

   protected spinner: NgxSpinnerService;

   public entity: T;
   protected entitySelecionado: T;


   showPesquisa = true;
   enableFilter = false;
   pagelength = 0;
   pageIndex = 0;
   pageSize = 0;
   pages = 0;

   constructor(
      protected service: Abstract<T>,
      protected injector: Injector,
   ) {
      this.route = this.injector.get(ActivatedRoute);
      this.router = this.injector.get(Router);
      this.fb = this.injector.get(FormBuilder);
      this.msg = this.injector.get(MensagemService);
      this.spinner = this.injector.get(NgxSpinnerService);
      this.dialog = this.injector.get(MatDialog);
      this.sistemaService = this.injector.get(SistemaService);
      this.setForm();
   }

   ngOnInit(): void {

   }

   abstract createFiltro();
   abstract create(): T;
   abstract setForm();

   onShow(view: boolean = false) {
      this.showPesquisa = view;
   }

   onNovo() {
      this.entity = this.create();
      this.entitySelecionado = null;
      this.onShow();
   }

   onVoltar() {
      this.onShow(true);
      if (this.enableFilter) {
         this.onPesquisar();
      }
   }

   onEdit(entity: T) {
      this.router.navigate(['../', entity.id], { relativeTo: this.route })
   }

   onDelete(id: number, descricao: String) {
      if (this.sistemaService.hasAutorityRemove(this.route.snapshot)) {
         const dialogRef = this.dialog.open(DialogComponent, {
            data: { id: id, descricao: descricao }
         });

         dialogRef.afterClosed().subscribe(result => {
            if (result && result > 0) {
               this.spinner.show();
               this.service.remover(result).then(
                  (response) => {
                     this.onPesquisar(this.pageIndex);
                     this.msg.onSucess('Sucesso', 'Registro removido');
                  }
               ).catch(
                  (response) => {
                     this.msg.onMessage(response.error.tipo, response.error.titulo, response.error.descricao);
                  }
               );
            }
         });
      } else {
         this.msg.onAlert('Recurso não liberado para usuário', '');
      }
   }

   onPesquisar(pageIndex: number = 0) {
      this.spinner.show();
      this.enableFilter = true;
      this.createFiltro();
      this.filtro.page = pageIndex;

      this.service.filtrar(this.filtro).subscribe((response) => {
         this.dataSource = new MatTableDataSource<T>(response.content);
         this.pageIndex = response.number;
         this.pageSize = response.numberOfElements;
         this.pagelength = response.totalElements;
         this.pages = response.totalPages;
      },
         (error) => {
            this.dataSource = new MatTableDataSource<T>(null);
         })
   }

   onPaginar(event: PageEvent) {
      this.onPesquisar(event.pageIndex);
   }
}


