import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LojaContato } from 'app/model/loja/loja-contato';
import { Vitrine } from 'app/model/vitrine/vitrine';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class VitrineService {

	protected urlApi: string = environment.api;

	constructor(private http: HttpClient) { }

	public carregar(token: string, sku: string): Observable<Vitrine> {
		return this.http.get<Vitrine>(`${environment.api}site/vitrine`, {
			params: new HttpParams()
				.set('seller-active', token)
				.set('sku', sku)
		});
	}

	public carregarLoja(token: string): Observable<LojaContato> {
      
		return this.http.get<LojaContato>(`${environment.api}site/loja`, {
			params: new HttpParams()
				.set('seller-active', token)
		});
	}
}
