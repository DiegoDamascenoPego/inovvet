import { Injectable } from '@angular/core';

declare var $: any;

export enum TipoMensagem {
	info = ('info'),
	success = ('success'),
	warning = ('warning'),
	danger = ('danger')
}

export enum IconeMensagem {
	success = ('insert_emoticon'),
	alert = ('warning'),
	error = ('error_outline')
}

@Injectable({
	providedIn: 'root'
})
export class MensagemService {

	constructor() { }

	showNotification(titulo, mensagem, tipoMensagem: TipoMensagem, iconst: IconeMensagem) {

		$.notify({
			icon: iconst,
			message: mensagem,
			title: titulo,
			icon_type: 'class'

		}, {
			type: tipoMensagem,
			timer: 4000,
			placement: {
				from: 'bottom',
				align: 'right'
			},
			template:
				'<div   data-notify="container" ' +
				'class="col-xl-4 col-lg-4 col-11 col-sm-4 col-md-4 alert alert-{0} alert-with-icon" role="alert">' +
				'<button mat-button  type="button" aria-hidden="true" class="close mat-button" data-notify="dismiss">  ' +
				'<i class="material-icons">close</i></button>' +
				'<i class="material-icons"  data-notify="icon">' + iconst + '</i> ' +
				'<span data-notify="title">{1}</span> ' +
				'<span data-notify="message">{2}</span>' +
				'<div class="progress" data-notify="progressbar">' +
				'<div class="progress-bar progress-bar-{0}" ' +
				'role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
				'</div>' +
				'<a href="{3}" target="{4}" data-notify="url"></a>' +
				'</div>'
		});
	}

	public onSucess(titulo, mensagem) {
		this.showNotification(titulo, mensagem, TipoMensagem.success, IconeMensagem.success)
	}

	public onError(titulo, mensagem) {
		this.showNotification(titulo, mensagem, TipoMensagem.danger, IconeMensagem.error)
	}

	public onAlert(titulo, mensagem) {
		this.showNotification(titulo, mensagem, TipoMensagem.warning, IconeMensagem.alert)
	}

	public onMessage(tipo, titulo, mensagem) {
		switch (tipo) {
			case 'Alerta':
				this.onAlert(titulo, mensagem);
				break;
			case 'Erro':
				this.onError(titulo, mensagem);
				break;
			default:
				this.onSucess(titulo, mensagem);
				break;
		}
	}

}
